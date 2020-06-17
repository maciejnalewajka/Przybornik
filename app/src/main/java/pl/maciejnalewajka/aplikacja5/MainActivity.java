package pl.maciejnalewajka.aplikacja5;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button1(View view) {
        Intent intent1 = new Intent(this, Kalkulator.class);
        startActivity(intent1);
    }

    public void button2(View view) {
        Intent intent2 = new Intent(this, Notatnik.class);
        startActivity(intent2);
    }

    public void button3(View view) {
        Intent intent3 = new Intent(this, Przypominajka.class);
        startActivity(intent3);
    }

    public void button4(View view) {
        Intent intent4 = new Intent(this, Konwerter.class);
        startActivity(intent4);
    }

    public void button5(View view) {
        Intent intent5 = new Intent(this, Plan.class);
        startActivity(intent5);
    }

    public void iid(View view) {
        final String[] autorzy = {"Maciej Nalewajka", "Oskar Kufel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Autorzy:").getContext().setTheme(R.style.TextAppearance_Compat_Notification_Title);

        builder.setItems(autorzy, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {}
        }).getContext().setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.create();
        builder.show();
    }
}
