package com.nalewajka.przybornik;
/*
Kalkulator.java, PostFixCalculator.java, PostFixConverter.java
created by Oskar Kufel
edited by Maciej Nalewajka
*/
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Kalkulator extends AppCompatActivity {
    TextView wynik;
    private final StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kalkulator);

        wynik = findViewById(R.id.wynik);
        ImageView copy = findViewById(R.id.copy);
        copy.setOnClickListener(v -> copy());
    }

    private void result() {
        if(sb.length()>0) try {
            Postfix pc = new Postfix(sb.toString());
            Infix calc = new Infix(pc.getPostfixAsList());
            wynik.setText(String.valueOf(calc.result().floatValue()));
            sb.delete(0, sb.length());
            sb.append(calc.result().floatValue());}
        catch (IllegalStateException | NumberFormatException | NoSuchElementException e) {
            wynik.setText(R.string.zla_wartosc);}
        else wynik.setText("");
    }

    private void wpiszLiczbe(String liczba) {
        switch (liczba) {
            case "sin":
                sb.append("s");
                wynik.setText(String.format("%ssin", wynik.getText()));
                break;
            case "cos":
                sb.append("c");
                wynik.setText(String.format("%scos", wynik.getText()));
                break;
            case "tan":
                sb.append("t");
                wynik.setText(String.format("%stan", wynik.getText()));
                break;
            case "log":
                sb.append("l");
                wynik.setText(String.format("%slog", wynik.getText()));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            default:
                sb.append(liczba);
                wynik.setText(String.format("%s%s", wynik.getText(), liczba));
                break;
        }
    }

    public void click(View view){onClick(view);}

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                wpiszLiczbe("0");
                break;
            case R.id.btn1:
                wpiszLiczbe("1");
                break;
            case R.id.btn2:
                wpiszLiczbe("2");
                break;
            case R.id.btn3:
                wpiszLiczbe("3");
                break;
            case R.id.btn4:
                wpiszLiczbe("4");
                break;
            case R.id.btn5:
                wpiszLiczbe("5");
                break;
            case R.id.btn6:
                wpiszLiczbe("6");
                break;
            case R.id.btn7:
                wpiszLiczbe("7");
                break;
            case R.id.btn8:
                wpiszLiczbe("8");
                break;
            case R.id.btn9:
                wpiszLiczbe("9");
                break;
            case R.id.openBracket:
                wpiszLiczbe("(");
                break;
            case R.id.closeBracket:
                wpiszLiczbe(")");
                break;
            case R.id.minus:
                wpiszLiczbe("-");
                break;
            case R.id.plus:
                wpiszLiczbe("+");
                break;
            case R.id.divide:
                wpiszLiczbe("/");
                break;
            case R.id.multiply:
                wpiszLiczbe("*");
                break;
            case R.id.sqrt:
                wpiszLiczbe("sqrt");
                break;
            case R.id.posneg:
                wpiszLiczbe("%");
                break;
            case R.id.cos:
                wpiszLiczbe("cos");
                break;
            case R.id.tan:
                wpiszLiczbe("tan");
                break;
            case R.id.sin:
                wpiszLiczbe("sin");
                break;
            case R.id.log:
                wpiszLiczbe("log");
                break;
            case R.id.xpowy:
                wpiszLiczbe("^");
                break;
            case R.id.mode:
                wpiszLiczbe("m");
                break;
            case R.id.factorial:
                wpiszLiczbe("f");
                break;
            case R.id.dot:
                wpiszLiczbe(".");
                if (sb.length()>1){
                    if ( Character.toString(sb.charAt(sb.length()-2)).equals(".") && Character.toString(sb.charAt(sb.length()-1)).equals(".") ) {
                        wynik.setText(".");
                    }
                }
                break;
            case R.id.clear:
                sb.delete(0, sb.length());
                wynik.setText("");
                break;
            case R.id.backSpace:
                if (wynik.getTextSize() > 0 & sb.length() > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                    wynik.setText(sb.toString());
                }
                else wynik.setText("");
                break;
            case R.id.pi:
                String show = wynik.getText() + Double.toString(Math.PI);
                wynik.setText(show);
                sb.append(Math.PI);
                break;
            case R.id.equal:
                result();
                break;

        }
    }

    private void copy(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Kopia", wynik.getText().toString());
        Objects.requireNonNull(clipboardManager).setPrimaryClip(clipData);
        Toast.makeText(this, "Skopiowano!", Toast.LENGTH_SHORT).show();
    }
}