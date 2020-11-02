package com.nalewajka.przybornik;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Notatnik extends AppCompatActivity {

    private Realm realm;
    private Integer val;
    private Button edit, delete, copy;
    private ConstraintLayout optionB, option;
    private ArrayList<Notatka> data = new ArrayList<>();
    private String text = "";
    Button deleteAll;
    Adapter adapter;
    Notatka optionNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notatnik);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        copy = findViewById(R.id.copy);
        optionB = findViewById(R.id.optionBackground);
        option = findViewById(R.id.options);
        deleteAll = findViewById(R.id.deleteAll);
        RecyclerView recyclerView = findViewById(R.id.notes_list);
        ImageButton addNote = findViewById(R.id.button_add_note);
        ImageButton settings = findViewById(R.id.settings);
        adapter = new Adapter();
        adapter.setOnItemClickListener(onItemClickListener);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        optionB.setOnClickListener(v -> changeVisibilityOption(val));

        edit.setOnClickListener(v -> edit(optionNote));

        delete.setOnClickListener(v -> {
            delete(optionNote);
            Toast.makeText(this, "Usunięto!", Toast.LENGTH_SHORT).show();
        });

        copy.setOnClickListener(v -> copy(optionNote));

        addNote.setOnClickListener(v -> newNote());

        settings.setOnClickListener(v -> {
            val = 1;
            changeVisibilityOption(val);
        });

        deleteAll.setOnClickListener(v -> deleteAll());
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setData(getData());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void delete(Notatka note) {
        realm.beginTransaction();
        RealmResults<Notatka> rows = realm.where(Notatka.class).equalTo("id", note.getId()).findAll();
        rows.deleteAllFromRealm();
        realm.commitTransaction();
        adapter.setData(getData());
        adapter.notifyDataSetChanged();
        changeVisibilityOption(val);
    }

    private void edit(Notatka note) {
        delete(note);
        text = note.getText();
        newNote();
    }

    private void copy(Notatka note){
        ClipboardManager copy = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData copy2 = ClipData.newPlainText("Kopia", note.getText());
        if (copy != null) {
            copy.setPrimaryClip(copy2);
            Toast.makeText(this, "Skopiowano!", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Notatka> getData(){
        RealmResults<Notatka> realmResults = realm.where(Notatka.class).findAll();
        ArrayList<Notatka> newNotes = new ArrayList<>(realm.copyFromRealm(realmResults));
        return sort(newNotes);
    }

    private ArrayList<Notatka> sort(List<Notatka> notes){
        ArrayList<Notatka> newNotes = new ArrayList<>();
        while (notes.size() > 0){
            Notatka noteMin = notes.get(0);
            for(Notatka item : notes){
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

    private final View.OnClickListener onItemClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        val = 0;
        changeVisibilityOption(val);
        optionNote = data.get(position);
    };

    private void changeVisibilityOption(Integer val){
        switch(val) {
            case 0:
                if (edit.getVisibility() == View.VISIBLE) {
                    optionB.setVisibility(View.GONE);
                    option.setVisibility(View.GONE);
                    edit.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
                    copy.setVisibility(View.GONE);
                } else {
                    optionB.setVisibility(View.VISIBLE);
                    option.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                    copy.setVisibility(View.VISIBLE);
                }
                break;
            case 1:
                if(deleteAll.getVisibility() == View.VISIBLE){
                    optionB.setVisibility(View.GONE);
                    option.setVisibility(View.GONE);
                    deleteAll.setVisibility(View.GONE);
                }
                else{
                    optionB.setVisibility(View.VISIBLE);
                    option.setVisibility(View.VISIBLE);
                    deleteAll.setVisibility(View.VISIBLE);
                }break;

        }
    }

    private void newNote(){
        Intent intent = new Intent(this, NowaNotatka.class);
        intent.putExtra("id", text);
        startActivity(intent);
    }

    private void deleteAll(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        adapter.setData(new ArrayList<>());
        changeVisibilityOption(val);
    }
}