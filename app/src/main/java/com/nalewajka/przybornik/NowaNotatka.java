package com.nalewajka.przybornik;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import io.realm.Realm;

public class NowaNotatka extends AppCompatActivity {

    private EditText editText;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.new_note_activity);
        String text = getIntent().getStringExtra("id");
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        editText = findViewById(R.id.editText);
        ImageButton saveButton = findViewById(R.id.button_save);

        editText.setText(text);

        saveButton.setOnClickListener(v -> {
            String str = editText.getText().toString().trim();
            if (!str.equals("")){save(str, new Date());}
        });
    }

    private void save(String text, Date date) {
        Notatka note = new Notatka(text, date);
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
        editText.setText("");
        finish();
    }
}