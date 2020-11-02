package com.nalewajka.przybornik;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Konwerter extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button buttonKategoria;
    private Button buttonWybierz1;
    private Button buttonWybierz2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_konwerter);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.wynik);
        buttonKategoria = findViewById(R.id.buttonKategoria);
        buttonWybierz1 = findViewById(R.id.buttonWybierz1);
        buttonWybierz2 = findViewById(R.id.buttonWybierz2);
        ImageView imageView = findViewById(R.id.imageView2);
        ImageView imageView2 = findViewById(R.id.imageView);
        ImageView imageView3 = findViewById(R.id.imageView3);
        Button buttonKonwertuj = findViewById(R.id.buttonKonwertuj);
        ImageButton buttonCopy = findViewById(R.id.buttonCopy);
        ImageButton buttonPaste = findViewById(R.id.buttonPaste);
        ImageView change = findViewById(R.id.change);

        buttonKategoria.setOnClickListener(v -> kategoria());
        imageView.setOnClickListener(v -> kategoria());

        buttonWybierz1.setOnClickListener(v -> wybierz1());
        imageView2.setOnClickListener(v -> wybierz1());

        buttonWybierz2.setOnClickListener(v -> wybierz2());
        imageView3.setOnClickListener(v -> wybierz2());

        buttonKonwertuj.setOnClickListener(v -> konwertuj());

        buttonCopy.setOnClickListener(v -> copy());

        buttonPaste.setOnClickListener(v -> paste());

        change.setOnClickListener(v -> change());
    }

    private void change() {
        String text = buttonWybierz1.getText().toString();
        buttonWybierz1.setText(buttonWybierz2.getText().toString());
        buttonWybierz2.setText(text);
    }

    private void copy(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Kopia", textView.getText().toString());
        Objects.requireNonNull(clipboardManager).setPrimaryClip(clipData);
        Toast.makeText(this, "Skopiowano!", Toast.LENGTH_SHORT).show();
        hideKeyboard(this);
    }

    private void paste(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager != null ? clipboardManager.getPrimaryClip() : null;
        ClipData.Item item = clipData != null ? clipData.getItemAt(0) : null;
        String tekst = item != null ? item.getText().toString() : null;
        editText.setText(tekst);
        hideKeyboard(this);
    }

    private void kategoria() {
        final String[] kategorie = {"Temperatura", "Prędkość", "Długość", "Powierzchnia", "Objętość", "Czas", "Masa"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kategorie");
        builder.setItems(kategorie, (dialog, which) -> {
            buttonKategoria.setText(kategorie[which]);
            buttonWybierz1.setText(nameList(kategorie[which])[0]);
            buttonWybierz2.setText(nameList(kategorie[which])[1]);
        });
        builder.getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.create();
        builder.show();
        hideKeyboard(this);
    }

    public void wybierz1() {
        builderWybierz(nameList(buttonKategoria.getText().toString()), buttonWybierz1);
    }

    public void wybierz2() {
        builderWybierz(nameList(buttonKategoria.getText().toString()), buttonWybierz2);
    }

    private void konwertuj() {
        try{
            String wybierz1 = buttonWybierz1.getText().toString();
            String wybierz2 = buttonWybierz2.getText().toString();
            double liczba = Double.parseDouble(editText.getText().toString());
            String kategoria = buttonKategoria.getText().toString();
            if(Objects.equals(wybierz1, wybierz2)){textView.setText(String.valueOf(liczba));}
            else{
                Wartosci wartosci = new Wartosci(wybierz1, wybierz2, liczba);
                switch (kategoria){
                    case "Temperatura":
                        textView.setText(String.valueOf(wartosci.temperatura()));
                        break;
                    case "Prędkość":
                        textView.setText(String.valueOf(wartosci.predkosc()));
                        break;
                    case "Długość":
                        textView.setText(String.valueOf(wartosci.dlugosc()));
                        break;
                    case "Powierzchnia":
                        textView.setText(String.valueOf(wartosci.powierzchnia()));
                        break;
                    case "Objętość":
                        textView.setText(String.valueOf(wartosci.objetosc()));
                        break;
                    case "Czas":
                        textView.setText(String.valueOf(wartosci.czas()));
                        break;
                    case "Masa":
                        textView.setText(String.valueOf(wartosci.masa()));
                        break;
                }
            }
        }
        catch (IllegalArgumentException e){textView.setText(R.string.zla_wartosc);}
        hideKeyboard(this);
    }

    private String[] nameList(String kategoria){
        switch (kategoria) {
            case "Temperatura":
                return new String[]{"Celsjusz", "Fahrenheit", "Kelvin"};
            case "Prędkość":
                return new String[]{"m/s", "km/s", "km/h", "kn", "mph", "ips", "Ma", "c", "fps"};
            case "Długość":
                return new String[]{"Pikometr", "Nanometr", "Mikrometr", "Milimetr", "Centymetr", "Decymetr", "Metr", "Kilometr", "Mila morska", "Mila", "Jard", "Stopa", "Cal"};
            case "Powierzchnia":
                return new String[]{"Mikron", "Milimetr", "Centymetr", "Decymetr", "Metr", "Ar", "Hektar", "Kilometr", "Akr", "Mila", "Jard", "Stopa", "Cal"};
            case "Objętość":
                return new String[]{"Mililitr", "Centylitr", "Decylitr", "Litr", "Hektolitr", "Stopa", "Cal", "Jard"};
            case "Czas":
                return new String[]{"Pikosekunda", "Mikrosekunda", "Milisekunda", "Sekunda", "Minuta", "Godzina", "Dzień", "Tydzień", "Rok"};
            case "Masa":
                return new String[]{"Mikrogram", "Miligram", "Gram", "Kilogram", "Tona", "Ziarno", "Karat", "Uncja", "Funt", "Kwintal"};
        }
        return new String[]{};
    }

    private void builderWybierz(String[] list, Button but){
        final String[] wybierzLista = list;
        final Button button = but;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz");
        builder.setItems(wybierzLista, (dialog, which) -> button.setText(wybierzLista[which]))
                .getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.create();
        builder.show();
        hideKeyboard(this);
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}