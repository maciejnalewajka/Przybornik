package com.nalewajka.przybornik;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Notatnik extends AppCompatActivity {

    private Realm realm;
    private EditText editText;
    private Button edit, delete, copy;
    private ConstraintLayout optionB, option;
    private ArrayList<Note> data = new ArrayList<>();
    Adapter adapter;
    Note optionNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notatnik);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        editText = findViewById(R.id.editText);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        copy = findViewById(R.id.copy);
        optionB = findViewById(R.id.optionBackground);
        option = findViewById(R.id.options);
        ImageButton saveButton = findViewById(R.id.button_save);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notes_list);


        adapter = new Adapter(getData());
        adapter.setOnItemClickListener(onItemClickListener);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        saveButton.setOnClickListener(v -> save(editText.getText().toString().trim(), new Date()));

        optionB.setOnClickListener(v -> changeVisibility());

        edit.setOnClickListener(v -> edit(optionNote));

        delete.setOnClickListener(v -> delete(optionNote));

        copy.setOnClickListener(v -> copy(optionNote));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void save(String text, Date date) {
        Note note = new Note(text, date);
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
        editText.setText("");
        hideKeyboard();
        adapter.setData(getData());
        adapter.notifyDataSetChanged();
    }

    private void delete(Note note) {
        realm.beginTransaction();
        RealmResults<Note> rows = realm.where(Note.class).equalTo("id", note.getId()).findAll();
        rows.deleteAllFromRealm();
        realm.commitTransaction();
        adapter.setData(getData());
        adapter.notifyDataSetChanged();
        changeVisibility();
    }

    private void edit(Note note) {
        editText.setText(note.getText());
        delete(note);
    }

    private void copy(Note note){
        ClipboardManager copy = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData copy2 = ClipData.newPlainText("Kopia", note.getText());
        if (copy != null) {
            copy.setPrimaryClip(copy2);
            Toast.makeText(this, "Skopiowano!", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Note> getData(){
        RealmResults<Note> realmResults = realm.where(Note.class).findAll();
        ArrayList<Note> newNotes = new ArrayList<>(realm.copyFromRealm(realmResults));
        return sort(newNotes);
    }

    private ArrayList<Note> sort(List<Note> notes){
        ArrayList<Note> newNotes = new ArrayList<>();
        while (notes.size() > 0){
            Note noteMin = notes.get(0);
            for(Note item : notes){
                if (item.getId() > noteMin.getId()){
                    noteMin = item;
                }
            }
            newNotes.add(noteMin);
            notes.remove(noteMin);
        }
        data = newNotes;
        return newNotes;
    }

    private View.OnClickListener onItemClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        changeVisibility();
        hideKeyboard();
        optionNote = data.get(position);
    };

    private void changeVisibility(){
        if(edit.getVisibility() == View.VISIBLE){
            optionB.setVisibility(View.INVISIBLE);
            option.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
            copy.setVisibility(View.INVISIBLE);
        }
        else{
            optionB.setVisibility(View.VISIBLE);
            option.setVisibility(View.VISIBLE);
            edit.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            copy.setVisibility(View.VISIBLE);
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}