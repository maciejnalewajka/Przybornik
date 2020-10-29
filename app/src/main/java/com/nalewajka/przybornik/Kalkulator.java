package com.nalewajka.przybornik;
/*
Kalkulator.java, PostFixCalculator.java, PostFixConverter.java
created by Oskar Kufel
*/
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nalewajka.przybornik.PostFixKalkulator;
import com.nalewajka.przybornik.PostFixKonwerter;
import com.nalewajka.przybornik.R;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Kalkulator extends AppCompatActivity {
    TextView notatko, hDisplay;
    Button hh;
    boolean flagHistory = false;

    StringBuilder sb = new StringBuilder();
    ArrayList<String> history = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kalkulator);

        notatko = (TextView) findViewById(R.id.resCalc);
        hh = (Button) findViewById(R.id.toggle);
        hDisplay = (TextView) findViewById(R.id.historyDisplay);
        hh.setBackgroundColor(Color.BLUE);
    }

    public void displayHistory(String texto) {
        if (flagHistory) {
            history.add(texto);
            hDisplay.setText(hDisplay.getText().toString()+"\n"+texto);
        }
    }

    public void pi(View view) {
        notatko.setText(notatko.getText()+Double.toString(Math.PI));
        sb.append(Double.toString(Math.PI));
    }

    public void resultOnScreen(View view) {
        if(sb.length()>0) try {
            PostFixKonwerter pc = new PostFixKonwerter(sb.toString());
            PostFixKalkulator calc = new PostFixKalkulator(pc.getPostfixAsList());
            notatko.setText(calc.result().toString());
            displayHistory(calc.result().toString());
            sb.delete(0, sb.length());
            sb.append(calc.result());}
        catch (IllegalStateException e) {
            notatko.setText("zle dane");}
        catch (NumberFormatException l) {
            notatko.setText("zle dane");}
        catch (NoSuchElementException o) {notatko.setText("zle dane");}
        else notatko.setText("");


    }

    public void delate(View view) {
        if (notatko.getTextSize() > 0) {
            if(sb.length()>0){
                sb.delete(sb.length() - 1, sb.length());
                notatko.setText(sb.toString());}
        }
        else notatko.setText("");
    }

    public void log(View view) {
        if(!flagHistory){
            hh.setBackgroundColor(Color.RED);
            flagHistory=true;
        }
        else{
            hh.setBackgroundColor(Color.BLUE);
            flagHistory=false;
        }
    }

    public void ClearScreen(View view) {
        sb.delete(0, sb.length());
        notatko.setText("");
        history.clear();
        hDisplay.setText("History:");
    }

    public void wpiszLiczbe(String liczba) {
        if (liczba.equals("sin")) {
            sb.append("s");
            notatko.setText(notatko.getText() + "sin");
        }
        else if (liczba.equals("cos")) {
            sb.append("c");
            notatko.setText(notatko.getText() + "cos");
        }
        else if (liczba.equals("tan")) {
            sb.append("t");
            notatko.setText(notatko.getText() + "tan");
        }
        else if (liczba.equals("log")) {
            sb.append("l");
            notatko.setText(notatko.getText() + "log");
        }
        else if (liczba.equals("sqrt")) {
            sb.append("q");
            notatko.setText(notatko.getText() + "sqrt");
            displayHistory(notatko.getText() + "sqrt");
        }
        else if (liczba.equals("+") || liczba.equals("-") || liczba.equals("*") || liczba.equals("/")){
            sb.append(liczba);
            notatko.setText(notatko.getText() + liczba);
            displayHistory(notatko.getText().toString() );
        }
        else {
            sb.append(liczba);
            notatko.setText(notatko.getText() +liczba );
        }
    }

    public void clickOn9(View view) {

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
        }
    }

    public void addDot(View view) {
        wpiszLiczbe(".");
        if (sb.length()>1){
            if ( Character.toString(sb.charAt(sb.length()-2)).equals(".") &&  Character.toString(sb.charAt(sb.length()-1)).equals(".") ) {
                notatko.setText(".");
            }
        }
    }
}