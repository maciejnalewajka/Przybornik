package com.nalewajka.przybornik;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton kalkulator;
    ImageButton konwerter;
    ImageButton notatnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        kalkulator = findViewById(R.id.calculator);
        notatnik = findViewById(R.id.note);
        konwerter = findViewById(R.id.converter);

        kalkulator.setOnClickListener(v -> {
            Intent intent1 = new Intent(v.getContext(), Kalkulator.class);
            startActivity(intent1);
        });
        notatnik.setOnClickListener(v -> {
            Intent intent2 = new Intent(v.getContext(), Notatnik.class);
            startActivity(intent2);
        });
        konwerter.setOnClickListener(v -> {
            Intent intent4 = new Intent(v.getContext(), Konwerter.class);
            startActivity(intent4);
        });
    }
}