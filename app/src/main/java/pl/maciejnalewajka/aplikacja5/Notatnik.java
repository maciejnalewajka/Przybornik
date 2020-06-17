package pl.maciejnalewajka.aplikacja5;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Notatnik extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public int colorAsInt;
    private final int MEMORY_ACCESS=5 ;
    private String path = Environment.getExternalStorageDirectory().toString();
    private ListView listView;
    private EditText editText;
    private ArrayList<String> data;
    private ArrayList<String> pliki;
    private ArrayAdapter<String> adapter;
    private Toast toast1;
    public File file;
    private String text;
    private FileReader fr = null;;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case MEMORY_ACCESS:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){}
                else
                {Toast.makeText(getApplicationContext(), "Zgoda wymagana", Toast.LENGTH_LONG ).show();}
        }}  // Uprawnienia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notatnik);
        if(ActivityCompat.shouldShowRequestPermissionRationale(Notatnik.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){}
        else{ActivityCompat.requestPermissions(Notatnik.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);}

        ColorDiagram();

        listView = (ListView) findViewById(R.id.listView1);
        editText = (EditText) findViewById(R.id.edit_n1);
        data = new ArrayList<String>();
        pliki = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        zapelnij();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (data.isEmpty()) {poka2();}
    }

    public void wyczysc(View view) {
        EditText edit_n1 = (EditText) findViewById(R.id.edit_n1);
        edit_n1.setText("");
    }                           // Przycisk wyczyść

    public void zapisz(View view) throws IOException {
        toast1 = Toast.makeText(this, "Zapisano", Toast.LENGTH_SHORT);

        text = String.valueOf(editText.getText());
        data.add(text);
        adapter.notifyDataSetChanged();
        editText.setText("");
        toast1.show();
        zapisz2();
    }           // Przycisk zapisz

    public void ColorDiagram(){
        String[] mColors = {
                "#39add1", // light blue
                "#3079ab", // dark blue
                "#c25975", // mauve
                "#e15258", // red
                "#f9845b", // orange
                "#838cc7", // lavender
                "#7d669e", // purple
                "#53bbb4", // aqua
                "#51b46d", // green
                "#e0ab18", // mustard
                "#637a91", // dark gray
                "#f092b0", // pink
                "#b7c0c7"};  // light gray

        Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(mColors.length);
            String color = mColors[randomNumber];
            colorAsInt = Color.parseColor(color);
        }                               // Losowy kolor

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        final String[] kategorie = {"Kopiuj", "Usuń", "Edytuj"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(kategorie, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item == 0){
                    ClipboardManager kop = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData kop2 = ClipData.newPlainText("Kopia", data.get(position));
                    kop.setPrimaryClip(kop2);
                }
                else if(item == 1){
                    File file = new File(pliki.get(position));
                    file.delete();
                    pliki.remove(position);
                    data.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Usunięto", Toast.LENGTH_SHORT).show();

                }
                else{
                    File file = new File(pliki.get(position));
                    file.delete();
                    pliki.remove(position);
                    editText.setText(data.get(position));
                    data.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.create();
        builder.show();

    }           // Przyciski kopiuj, usuń, edytuj wywoływane na notatkach

    public void zapisz2() throws FileNotFoundException {
        String nazwa = null;
        int i = 0;
        while (true){
            nazwa = path + "//"+String.valueOf(i)+".txt";
            File file = new File(nazwa);
            pliki.add( nazwa);
            if(file.exists()){i++;}
            else{
                PrintWriter zapis = new PrintWriter(nazwa);
                zapis.print(text);
                zapis.close();
                break;
            }
        }i=0;
    }       // Zapisuje notatki do tablicy i plików oraz nazwe pliku do tablicy   -- wykonanie zapisz()

    public void poka2(){
        String name = null;
        String notka = "";
        String s;
        int i = 0;
        while (true) {
            try {
                name = path + "//"+String.valueOf(i)+".txt";
                fr = new FileReader(name);
            } catch (FileNotFoundException e) {if(i>100){break;}
                i++; continue;
            }
            Scanner plik = new Scanner(fr);
            while (plik.hasNext()) {
                s = plik.next();
                notka = notka + s;
            }
            plik.close();
            i++;
            data.add(notka);
            notka = "";
        }i=0;
    }                   // Przesyła notatki z plików do tablicy    -- wykonanie onResume()

    public void zapelnij(){
        String nazwa = null;
        pliki.clear();
        int i = 0;
        while(true) {
            nazwa = path + "//"+String.valueOf(i)+".txt";
            try {fr = new FileReader(nazwa);}
            catch (FileNotFoundException e) {if(i>100){break;} i++; continue;}
            pliki.add(nazwa);
            i++;
        }i=0;
    }               // Zapełnia tablice nazwami plików   -- wykonanie onCreate()
}
