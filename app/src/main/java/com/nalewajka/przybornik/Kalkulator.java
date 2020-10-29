package com.nalewajka.przybornik;
/*
Kalkulator.java, PostFixCalculator.java, PostFixConverter.java
created by Oskar Kufel
edited by Maciej Nalewajka
*/
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Kalkulator extends AppCompatActivity {
    TextView wynik, textViewHistory;
    Button toggle;
    boolean isHistoryActive = false;

    StringBuilder sb = new StringBuilder();
    ArrayList<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kalkulator);

        wynik = (TextView) findViewById(R.id.wynik);
        toggle = (Button) findViewById(R.id.toggle);
        textViewHistory = (TextView) findViewById(R.id.historyDisplay);
        toggle.setTextColor(Color.BLUE);
    }

    private void displayHistory(String text) {
        if (isHistoryActive) {
            history.add(text);
            String show = textViewHistory.getText().toString() + "\n" + text;
            textViewHistory.setText(show);
        }
    }

    private void result() {
        if(sb.length()>0) try {
            PostFixKonwerter pc = new PostFixKonwerter(sb.toString());
            PostFixKalkulator calc = new PostFixKalkulator(pc.getPostfixAsList());
            wynik.setText(String.valueOf(calc.result().floatValue()));
            displayHistory(calc.result().toString());
            sb.delete(0, sb.length());
            sb.append(calc.result());}
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
            case "sqrt":
                sb.append("q");
                wynik.setText(String.format("%ssqrt", wynik.getText()));
                displayHistory(wynik.getText() + "sqrt");
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                sb.append(liczba);
                wynik.setText(String.format("%s%s", wynik.getText(), liczba));
                displayHistory(wynik.getText().toString());
                break;
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
            case R.id.square:
                wpiszLiczbe("^");
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
                history.clear();
                textViewHistory.setText(R.string.historia);
                break;
            case R.id.backSpace:
                if (wynik.getTextSize() > 0) {
                    if(sb.length()>0){
                        sb.delete(sb.length() - 1, sb.length());
                        wynik.setText(sb.toString());}
                }
                else wynik.setText("");
                break;
            case R.id.toggle:
                if(!isHistoryActive){
                    toggle.setTextColor(Color.RED);
                    isHistoryActive = true;
                }
                else{
                    toggle.setTextColor(Color.BLUE);
                    isHistoryActive = false;
                }
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
}