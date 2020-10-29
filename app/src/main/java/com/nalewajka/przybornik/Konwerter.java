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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nalewajka.przybornik.R;
import com.nalewajka.przybornik.Wartosci;

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
        textView = findViewById(R.id.appName);
        buttonKategoria = findViewById(R.id.buttonKategoria);
        buttonWybierz1 = findViewById(R.id.buttonWybierz1);
        buttonWybierz2 = findViewById(R.id.buttonWybierz2);
        Button buttonKonwertuj = findViewById(R.id.buttonKonwertuj);
        ImageButton buttonCopy = findViewById(R.id.buttonCopy);
        ImageButton buttonPaste = findViewById(R.id.buttonPaste);

        buttonKategoria.setOnClickListener(v -> kategoria());

        buttonWybierz1.setOnClickListener(v -> wybierz1());

        buttonWybierz2.setOnClickListener(v -> wybierz2());

        buttonKonwertuj.setOnClickListener(v -> konwertuj());

        buttonCopy.setOnClickListener(v -> copy());

        buttonPaste.setOnClickListener(v -> paste());
    }

    private void copy(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Kopia", textView.getText().toString());
        Objects.requireNonNull(clipboardManager).setPrimaryClip(clipData);
        Toast.makeText(this, "Skopiowano!", Toast.LENGTH_SHORT).show();
    }

    private void paste(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager != null ? clipboardManager.getPrimaryClip() : null;
        ClipData.Item item = clipData != null ? clipData.getItemAt(0) : null;
        String tekst = item != null ? item.getText().toString() : null;
        editText.setText(tekst);
    }

    private void kategoria() {
        final String[] kategorie = {"Temperatura", "Prędkość", "Długość", "Powierzchnia", "Objętość", "Czas", "Masa"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kategorie");
        builder.setItems(kategorie, (dialog, item) ->
                buttonKategoria.setText(kategorie[item])).getContext()
                .setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.create();
        builder.show();
        buttonWybierz1.setText(R.string.z);
        buttonWybierz2.setText(R.string.naco);
        hideKeyboard(this);
    }

    public void wybierz1() {
        switch (buttonKategoria.getText().toString()) {
            case "Temperatura":
                String[] temperatury = {"Celsjusz", "Fahrenheit", "Kelvin"};
                builderWybierz(temperatury, buttonWybierz1);
                break;
            case "Prędkość":
                String[] predkosci = {"m/s", "km/s", "km/h", "kn", "mph", "ips", "Ma", "c", "fps"};
                builderWybierz(predkosci, buttonWybierz1);
                break;
            case "Długość":
                String[] dlugosc = {"Pikometr", "Nanometr", "Mikrometr", "Milimetr", "Centymetr", "Decymetr", "Metr", "Kilometr", "Mila morska", "Mila", "Jard", "Stopa", "Cal"};
                builderWybierz(dlugosc, buttonWybierz1);
                break;
            case "Powierzchnia":
                String[] powierzchnia = {"Mikron", "Milimetr", "Centymetr", "Decymetr", "Metr", "Ar", "Hektar", "Kilometr", "Akr", "Mila", "Jard", "Stopa", "Cal"};
                builderWybierz(powierzchnia, buttonWybierz1);
                break;
            case "Objętość":
                String[] objetosc = {"Mililitr", "Centylitr", "Decylitr", "Litr", "Hektolitr", "Stopa", "Cal", "Jard"};
                builderWybierz(objetosc, buttonWybierz1);
                break;
            case "Czas":
                String[] czas = {"Pikosekunda", "Mikrosekunda", "Milisekunda", "Sekunda", "Minuta", "Godzina", "Dzień", "Tydzień", "Rok"};
                builderWybierz(czas, buttonWybierz1);
                break;
            case "Masa":
                String[] masa = {"Mikrogram", "Miligram", "Gram", "Kilogram", "Tona", "Ziarno", "Karat", "Uncja", "Funt", "Kwintal"};
                builderWybierz(masa, buttonWybierz1);
                break;
        }
    }

    public void wybierz2() {
        switch (buttonKategoria.getText().toString()) {
            case "Temperatura":
                String[] temperatury = {"Celsjusz", "Fahrenheit", "Kelvin"};
                builderWybierz(temperatury, buttonWybierz2);
                break;
            case "Prędkość":
                String[] predkosci = {"m/s", "km/s", "km/h", "kn", "mph", "ips", "Ma", "c", "fps"};
                builderWybierz(predkosci, buttonWybierz2);
                break;
            case "Długość":
                String[] dlugosc = {"Pikometr", "Nanometr", "Mikrometr", "Milimetr", "Centymetr", "Decymetr", "Metr", "Kilometr", "Mila morska", "Mila", "Jard", "Stopa", "Cal"};
                builderWybierz(dlugosc, buttonWybierz2);
                break;
            case "Powierzchnia":
                String[] powierzchnia = {"Mikron", "Milimetr", "Centymetr", "Decymetr", "Metr", "Ar", "Hektar", "Kilometr", "Akr", "Mila", "Jard", "Stopa", "Cal"};
                builderWybierz(powierzchnia, buttonWybierz2);
                break;
            case "Objętość":
                String[] objetosc = {"Mililitr", "Centylitr", "Decylitr", "Litr", "Hektolitr", "Stopa", "Cal", "Jard"};
                builderWybierz(objetosc, buttonWybierz2);
                break;
            case "Czas":
                String[] czas = {"Pikosekunda", "Mikrosekunda", "Milisekunda", "Sekunda", "Minuta", "Godzina", "Dzień", "Tydzień", "Rok"};
                builderWybierz(czas, buttonWybierz2);
                break;
            case "Masa":
                String[] masa = {"Mikrogram", "Miligram", "Gram", "Kilogram", "Tona", "Ziarno", "Karat", "Uncja", "Funt", "Kwintal"};
                builderWybierz(masa, buttonWybierz2);
                break;
        }
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