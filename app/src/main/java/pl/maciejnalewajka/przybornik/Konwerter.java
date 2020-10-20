package pl.maciejnalewajka.przybornik;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Konwerter extends AppCompatActivity {
    EditText edit2;
    EditText edit;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    String text;
    String text2;
    String kat;
    double wynik;
    double liczba;
    double wynik0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_konwerter);
        edit2 = (EditText) findViewById(R.id.edit1);
        edit = (EditText) findViewById(R.id.edit2);
        button1 = (Button) findViewById(R.id.idkategoria);
        button2 = (Button) findViewById(R.id.idwybierz1);
        button3 = (Button) findViewById(R.id.idwybierz2);
        button4 = (Button) findViewById(R.id.idkonwertuj);}




    // Przycisk wklej
    private void wklej() {
        ClipboardManager copy = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData copy2 = copy != null ? copy.getPrimaryClip() : null;
        ClipData.Item item = copy2 != null ? copy2.getItemAt(0) : null;
        String tekst = item != null ? item.getText().toString() : null;
        edit2.setText(tekst);
    }

    // Przycisk kopiuj
    private void kopia() {
        ClipboardManager kop = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData kop2 = ClipData.newPlainText("Kopia", edit.getText().toString());
        Objects.requireNonNull(kop).setPrimaryClip(kop2);
    }

    // Przycisk kategoria
    public void kategoria(View view) {
        final String[] kategorie = {"Temperatura", "Prędkość", "Długość", "Powierzchnia", "Waluta", "Objętość", "Czas", "Masa"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kategorie");
        builder.setItems(kategorie, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                button1.setText(kategorie[item]);
            }
        }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.create();
        builder.show();
        button2.setText("Wybierz");
        button3.setText("Wybierz");
    }       // Przycisk kategoria

    public void wybierz1(View view) {           // Przycisk Wybierz 1
        switch (button1.getText().toString()) {
            case "Temperatura":
                final String[] temperatury = {"Celsjusz", "Fahrenheit", "Kelvin"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Wybierz");
                builder.setItems(temperatury, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(temperatury[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.create();
                builder.show();
                break;
            case "Prędkość":
                final String[] predkosci = {"m/s", "km/s", "km/h", "kn", "mph", "ips", "Ma", "c", "fps"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Wybierz");
                builder1.setItems(predkosci, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(predkosci[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder1.create();
                builder1.show();
                break;
            case "Długość":
                final String[] dlugosc = {"Pikometr", "Nanometr", "Mikrometr", "Milimetr", "Centymetr", "Decymetr", "Metr", "Kilometr", "Mila morska", "Mila", "Jard", "Stopa", "Cal"};
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Wybierz");
                builder2.setItems(dlugosc, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(dlugosc[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder2.create();
                builder2.show();
                break;
            case "Powierzchnia":
                final String[] powierzchnia = {"Mikron", "Milimetr", "Centymetr", "Decymetr", "Metr", "Ar", "Hektar", "Kilometr", "Akr", "Mila", "Jard", "Stopa", "Cal"};
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setTitle("Wybierz");
                builder3.setItems(powierzchnia, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(powierzchnia[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder3.create();
                builder3.show();
                break;
            case "Waluta":
                final String[] waluta = {"PLN", "EUR", "USD", "CHF", "GBP", "RUB", "NOK", "CZK"};
                AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
                builder4.setTitle("Wybierz");
                builder4.setItems(waluta, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(waluta[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder4.create();
                builder4.show();
                break;
            case "Objętość":
                final String[] objetosc = {"Mililitr", "Centylitr", "Decylitr", "Litr", "Hektolitr", "Stopa", "Cal", "Jard"};
                AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
                builder5.setTitle("Wybierz");
                builder5.setItems(objetosc, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(objetosc[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder5.create();
                builder5.show();
                break;
            case "Czas":
                final String[] czas = {"Pikosekunda", "Mikrosekunda", "Milisekunda", "Sekunda", "Minuta", "Godzina", "Dzień", "Tydzień", "Rok"};
                AlertDialog.Builder builder6 = new AlertDialog.Builder(this);
                builder6.setTitle("Wybierz");
                builder6.setItems(czas, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(czas[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder6.create();
                builder6.show();
                break;
            case "Masa":
                final String[] masa = {"Mikrogram", "Miligram", "Gram", "Kilogram", "Tona", "Ziarno", "Karat", "Uncja", "Funt", "Kwintal"};
                AlertDialog.Builder builder7 = new AlertDialog.Builder(this);
                builder7.setTitle("Wybierz");
                builder7.setItems(masa, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button2.setText(masa[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder7.create();
                builder7.show();
                break;
        }

    }         // Pole wyboru 1

    public void wybierz2(View view) {           // Przycisk Wybierz 2
        switch (button1.getText().toString()) {
            case "Temperatura":
                final String[] temperatury = {"Celsjusz", "Fahrenheit", "Kelvin"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Wybierz");
                builder.setItems(temperatury, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(temperatury[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.create();
                builder.show();
                break;
            case "Prędkość":
                final String[] predkosci = {"m/s", "km/s", "km/h", "kn", "mph", "ips", "Ma", "c", "fps"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Wybierz");
                builder1.setItems(predkosci, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(predkosci[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder1.create();
                builder1.show();
                break;
            case "Długość":
                final String[] dlugosc = {"Pikometr", "Nanometr", "Mikrometr", "Milimetr", "Centymetr", "Decymetr", "Metr", "Kilometr", "Mila morska", "Mila", "Jard", "Stopa", "Cal"};
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Wybierz");
                builder2.setItems(dlugosc, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(dlugosc[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder2.create();
                builder2.show();
                break;
            case "Powierzchnia":
                final String[] powierzchnia = {"Mikron", "Milimetr", "Centymetr", "Decymetr", "Metr", "Ar", "Hektar", "Kilometr", "Akr", "Mila", "Jard", "Stopa", "Cal"};
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setTitle("Wybierz");
                builder3.setItems(powierzchnia, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(powierzchnia[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder3.create();
                builder3.show();
                break;
            case "Waluta":
                final String[] waluta = {"PLN", "EUR", "USD", "CHF", "GBP", "RUB", "NOK", "CZK"};
                AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
                builder4.setTitle("Wybierz");
                builder4.setItems(waluta, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(waluta[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder4.create();
                builder4.show();
                break;
            case "Objętość":
                final String[] objetosc = {"Mililitr", "Centylitr", "Decylitr", "Litr", "Hektolitr", "Stopa", "Cal", "Jard"};
                AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
                builder5.setTitle("Wybierz");
                builder5.setItems(objetosc, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(objetosc[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder5.create();
                builder5.show();
                break;
            case "Czas":
                final String[] czas = {"Pikosekunda", "Mikrosekunda", "Milisekunda", "Sekunda", "Minuta", "Godzina", "Dzień", "Tydzień", "Rok"};
                AlertDialog.Builder builder6 = new AlertDialog.Builder(this);
                builder6.setTitle("Wybierz");
                builder6.setItems(czas, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(czas[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder6.create();
                builder6.show();
                break;
            case "Masa":
                final String[] masa = {"Mikrogram", "Miligram", "Gram", "Kilogram", "Tona", "Ziarno", "Karat", "Uncja", "Funt", "Kwintal"};
                AlertDialog.Builder builder7 = new AlertDialog.Builder(this);
                builder7.setTitle("Wybierz");
                builder7.setItems(masa, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        button3.setText(masa[item]);
                    }
                }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder7.create();
                builder7.show();
                break;
        }
    }          // Pole wyboru 2

    public void konwertuje(View view) {        // Przycisk konwertuj
        try{
            text2 = button2.getText().toString();
            text = button3.getText().toString();
            liczba = Double.parseDouble(edit2.getText().toString());
            kat = button1.getText().toString();
            if(Objects.equals(text2, text)){wynik = liczba;}
            else{
                switch (kat){
                    case "Temperatura":
                        ktemperatura(view);
                        break;
                    case "Prędkość":
                        kpredkosc(view);
                        break;
                    case "Długość":
                        kdlugosc(view);
                        break;
                    case "Powierzchnia":
                        kpowierzchnia(view);
                        break;
                    case "Waluta":
                        kwaluta(view);
                        break;
                    case "Objętość":
                        kobjetosc(view);
                        break;
                    case "Czas":
                        kczas(view);
                        break;
                    case "Masa":
                        kmasa(view);
                        break;}
            }
            edit.setText(String.valueOf(wynik));}
            catch (IllegalArgumentException e){edit.setText(String.valueOf("Zła wartość!"));}
    }       // Przycisk konwertuj




    public void ktemperatura(View view) {
        switch (text2) {
            case "Celsjusz":
                if (Objects.equals(text, "Fahrenheit")) {wynik = liczba * 9 / 5 + 32;}
                else if (Objects.equals(text, "Kelvin")) {wynik = liczba + 273;}
                break;
            case "Fahrenheit":
                if (text.equals("Celsjusz")) {wynik = (0.55555*(liczba - 32));}
                else if (Objects.equals(text, "Kelvin")) {wynik = (liczba - 32) * 0.55555 + 273;}
                break;
            case "Kelvin":
                if (Objects.equals(text, "Celsjusz")) {
                    wynik = liczba - 273;
                } else if (Objects.equals(text, "Fahrenheit")) {
                    wynik = (9 / 5) * (liczba - 273) + 32;
                }
                break;}
    }       // Temperatura

    public void kpredkosc(View view) {
        switch(text2){
            case "m/s": wynik0=liczba/0.44704;break;
            case "km/s": wynik0=liczba/0.00044704;break;
            case "km/h": wynik0=liczba/1.609344;break;
            case "kn": wynik0=liczba/0.868976242;break;
            case "mph": wynik0=liczba;break;
            case "ips": wynik0=liczba/17.6;break;
            case "Ma": wynik0=liczba/0.00131366441;break;
            case "c": wynik0=liczba/0.00000000149116493;break;
            case "fps": wynik0=liczba/1.46666667;break;
        }
        switch(text){
            case "m/s": wynik=liczba*0.44704;break;
            case "km/s": wynik=liczba*0.00044704;break;
            case "km/h": wynik=liczba*1.609344;break;
            case "kn": wynik=liczba*0.868976242;break;
            case "mph": wynik=liczba;break;
            case "ips": wynik=liczba*17.6;break;
            case "Ma": wynik=liczba*0.00131366441;break;
            case "c": wynik=liczba*0.00000000149116493;break;
            case "fps": wynik=liczba*1.46666667;break;
        }
    }           // Prędkość

    public void kdlugosc(View view) {
        switch (text2) {
            case "Pikometr": wynik0 = liczba/Math.pow(10,12);break;
            case "Nanometr": wynik0 = liczba/Math.pow(10,9);break;
            case "Mikrometr": wynik0 = liczba/Math.pow(10,6);break;
            case "Milimetr": wynik0 = liczba/Math.pow(10,3);break;
            case "Centymetr": wynik0 = liczba/Math.pow(10,2);break;
            case "Decymetr": wynik0 = liczba/Math.pow(10,1);break;
            case "Metr": wynik0 = liczba;break;
            case "Kilometr": wynik0 = liczba/0.001;break;
            case "Mila morska": wynik0 = liczba/0.000539956803;break;
            case "Mila": wynik0 = liczba/0.000621371192;break;
            case "Jard": wynik0 = liczba/1.0936133;break;
            case "Stopa": wynik0 = liczba/3.2808399;break;
            case "Cal": wynik0 = liczba/39.3700787;break;
        }
        switch(text){
            case "Pikometr": wynik = wynik0*Math.pow(10,12);break;
            case "Nanometr": wynik = wynik0*Math.pow(10,9);break;
            case "Mikrometr": wynik = wynik0*Math.pow(10,6);break;
            case "Milimetr": wynik = wynik0*Math.pow(10,3);break;
            case "Centymetr": wynik = wynik0*Math.pow(10,2);break;
            case "Decymetr": wynik = wynik0*Math.pow(10,1);break;
            case "Metr": wynik = wynik0;break;
            case "Kilometr": wynik = wynik0*0.001;break;
            case "Mila morska": wynik = wynik0*0.000539956803;break;
            case "Mila": wynik = wynik0*0.000621371192;break;
            case "Jard": wynik = wynik0*1.0936133;break;
            case "Stopa": wynik = wynik0*3.2808399;break;
            case "Cal": wynik = wynik0*39.3700787;break;
        }
    }           // Długość

    public void kpowierzchnia(View view) {
        switch(text2){
            case "Mikron": wynik0=liczba/Math.pow(10,12);break;
            case "Milimetr": wynik0=liczba/Math.pow(10,6);break;
            case "Centymetr": wynik0=liczba/Math.pow(10,4);break;
            case "Decymetr": wynik0=liczba/Math.pow(10,2);break;
            case "Metr": wynik0=liczba;break;
            case "Ar": wynik0=liczba/0.01;break;
            case "Hektar": wynik0=liczba/0.0001;break;
            case "Kilometr": wynik0=liczba/0.000001;break;
            case "Akr": wynik0=liczba/0.000247105407;break;
            case "Mila": wynik0=liczba/0.000000386102159;break;
            case "Jard": wynik0=liczba/1.19599005;break;
            case "Stopa": wynik0=liczba/10.7639104;break;
            case "Cal": wynik0=liczba/1550.0031;break;
        }
        switch(text){
            case "Mikron": wynik=wynik0*Math.pow(10,12);break;
            case "Milimetr": wynik=wynik0*Math.pow(10,6);break;
            case "Centymetr": wynik=wynik0*Math.pow(10,4);break;
            case "Decymetr": wynik=wynik0*Math.pow(10,2);break;
            case "Metr": wynik=wynik0;break;
            case "Ar": wynik=wynik0*0.01;break;
            case "Hektar": wynik=wynik0*0.0001;break;
            case "Kilometr": wynik=wynik0*0.000001;break;
            case "Akr": wynik=wynik0*0.000247105407;break;
            case "Mila": wynik=wynik0*0.000000386102159;break;
            case "Jard": wynik=wynik0*1.19599005;break;
            case "Stopa": wynik=wynik0*10.7639104;break;
            case "Cal": wynik=wynik0*1550.0031;break;
        }
    }       // Powierzchnia

    public void kwaluta(View view) {
        switch(text2){
            case "PLN": wynik0 = liczba*Math.pow(10,12);break;
            case "EUR": wynik0 = liczba * Math.pow(10,12);break;
            case "USD": wynik0 = liczba * Math.pow(10,12);break;
            case "CHF": wynik0 = liczba * Math.pow(10,12);break;
            case "GBP": wynik0 = liczba * Math.pow(10,12);break;
            case "RUB": wynik0 = liczba;break;
            case "NOK": wynik0 = liczba * Math.pow(10,12);break;
            case "CZK": wynik0 = liczba * Math.pow(10,12);break;
        }
        switch(text){
            case "PLN": wynik = wynik0*Math.pow(10,12);break;
            case "EUR": wynik = wynik0 * Math.pow(10,12);break;
            case "USD": wynik = wynik0 * Math.pow(10,12);break;
            case "CHF": wynik = wynik0 * Math.pow(10,12);break;
            case "GBP": wynik = wynik0 * Math.pow(10,12);break;
            case "RUB": wynik = wynik0;break;
            case "NOK": wynik = wynik0 * Math.pow(10,12);break;
            case "CZK": wynik = wynik0 * Math.pow(10,12);break;
        }
    }           // Waluta

    public void kobjetosc(View view){
        switch(text2){
            case "Mililitr": wynik0=liczba/Math.pow(10,5);break;
            case "Centylitr": wynik0=liczba/Math.pow(10,4);break;
            case "Decylitr": wynik0=liczba/Math.pow(10,3);break;
            case "Litr": wynik0=liczba/Math.pow(10,2);break;
            case "Hektolitr": wynik0=liczba;break;
            case "Stopa": wynik0=liczba/3.53146667;break;
            case "Cal": wynik0=liczba/6102.37441;break;
            case "Jard": wynik0=liczba/0.130795062;break;
        }
        switch(text2){
            case "Mililitr": wynik=wynik0*Math.pow(10,5);break;
            case "Centylitr": wynik=wynik0*Math.pow(10,4);break;
            case "Decylitr": wynik=wynik0*Math.pow(10,3);break;
            case "Litr": wynik=wynik0*Math.pow(10,2);break;
            case "Hektolitr": wynik=wynik0;break;
            case "Stopa": wynik=wynik0*3.53146667;break;
            case "Cal": wynik=wynik0*6102.37441;break;
            case "Jard": wynik=wynik0*0.130795062;break;
        }
    }           // Objętość

    public void kczas(View view){
        switch(text2){
            case "Pikosekunda": wynik0=liczba/(6*Math.pow(10,13));break;
            case "Mikrosekunda": wynik0=liczba/(6*Math.pow(10,7));break;
            case "Milisekunda": wynik0=liczba/(6*Math.pow(10,4));break;
            case "Sekunda": wynik0=liczba/60;break;
            case "Minuta": wynik0=liczba;break;
            case "Godzina": wynik0=liczba/0.0166666667;break;
            case "Dzień": wynik0=liczba/0.000694444444;break;
            case "Tydzień": wynik0=liczba/0.0000992063492;break;
            case "Rok": wynik0=liczba/0.00000190258752;break;
        }
        switch(text){
            case "Pikosekunda": wynik=wynik0*(6*Math.pow(10,13));break;
            case "Mikrosekunda": wynik=wynik0*(6*Math.pow(10,7));break;
            case "Milisekunda": wynik=wynik0*(6*Math.pow(10,4));break;
            case "Sekunda": wynik=wynik0*60;break;
            case "Minuta": wynik=wynik0;break;
            case "Godzina": wynik=wynik0*0.0166666667;break;
            case "Dzień": wynik=wynik0*0.000694444444;break;
            case "Tydzień": wynik=wynik0*0.0000992063492;break;
            case "Rok": wynik=wynik0*0.00000190258752;break;
        }
    }               // Czas

    public void kmasa(View view){
        switch(text2){
            case "Mikrogram": wynik0=liczba/Math.pow(10,12);break;
            case "Miligram": wynik0=liczba/Math.pow(10,9);break;
            case "Gram": wynik0=liczba/Math.pow(10,6);break;
            case "Kilogram": wynik0=liczba/Math.pow(10,3);break;
            case "Tona": wynik0=liczba;break;
            case "Ziarno": wynik0=liczba/15432358.4;break;
            case "Karat": wynik0=liczba/(5*Math.pow(10,6));break;
            case "Uncja": wynik0=liczba/35273.9619;break;
            case "Funt": wynik0=liczba/2204.62262;break;
            case "Kwintal": wynik0=liczba/10;break;
        }
        switch(text){
            case "Mikrogram": wynik=wynik0*Math.pow(10,12);break;
            case "Miligram": wynik=wynik0*Math.pow(10,9);break;
            case "Gram": wynik=wynik0*Math.pow(10,6);break;
            case "Kilogram": wynik=wynik0*Math.pow(10,3);break;
            case "Tona": wynik=wynik0;break;
            case "Ziarno": wynik=wynik0*15432358.4;break;
            case "Karat": wynik=wynik0*(5*Math.pow(10,6));break;
            case "Uncja": wynik=wynik0*35273.9619;break;
            case "Funt": wynik=wynik0*2204.62262;break;
            case "Kwintal": wynik=wynik0*10;break;
        }
    }               // Masa
}


