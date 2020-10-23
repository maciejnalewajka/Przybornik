package com.nalewajka.przybornik;

import java.util.Objects;

public class Wartosci {

    private double liczba;
    private String wybierz1, wybierz2;

    public Wartosci(String wybierz1, String wybierz2, double liczba){
        this.wybierz1 = wybierz1;
        this.wybierz2 = wybierz2;
        this.liczba = liczba;
    }

    public double temperatura(){
        return _temperatura();
    }

    public double predkosc(){
        return _predkosc();
    }

    public double dlugosc(){
        return _dlugosc();
    }

    public double powierzchnia(){
        return _powierzchnia();
    }

    public double waluta(){
        return _waluta();
    }

    public double objetosc(){
        return _objetosc();
    }

    public double czas(){
        return _czas();
    }

    public double masa(){
        return _masa();
    }

    private double _temperatura() {
        switch (wybierz1) {
            case "Celsjusz":
                if (Objects.equals(wybierz2, "Fahrenheit")) {return liczba *= 1.8 + 32;}
                else if (Objects.equals(wybierz2, "Kelvin")) {return liczba += 273;}
                break;
            case "Fahrenheit":
                if (wybierz2.equals("Celsjusz")) {return (0.55555*(liczba - 32));}
                else if (Objects.equals(wybierz2, "Kelvin")) {return (liczba - 32) * 0.55555 + 273;}
                break;
            case "Kelvin":
                if (Objects.equals(wybierz2, "Celsjusz")) {
                    return liczba -= 273;
                } else if (Objects.equals(wybierz2, "Fahrenheit")) {
                    return 1.8 * (liczba - 273) + 32;
                }
                break;}
        return 0;
    }

    private double _predkosc() {
        switch(wybierz1){
            case "m/s": liczba /= 0.44704;break;
            case "km/s": liczba /= 0.00044704;break;
            case "km/h": liczba /= 1.609344;break;
            case "kn": liczba /= 0.868976242;break;
            case "ips": liczba /= 17.6;break;
            case "Ma": liczba /= 0.00131366441;break;
            case "c": liczba /= 0.00000000149116493;break;
            case "fps": liczba /= 1.46666667;break;
        }
        switch(wybierz2){
            case "m/s": return liczba * 0.44704;
            case "km/s": return liczba * 0.00044704;
            case "km/h": return liczba * 1.609344;
            case "kn": return liczba * 0.868976242;
            case "ips": return liczba * 17.6;
            case "Ma": return liczba * 0.00131366441;
            case "c": return liczba * 0.00000000149116493;
            case "fps": return liczba * 1.46666667;
        }
        return 0;
    }

    private double _dlugosc() {
        switch (wybierz1) {
            case "Pikometr": liczba /= Math.pow(10,12);break;
            case "Nanometr": liczba /= Math.pow(10,9);break;
            case "Mikrometr": liczba /= Math.pow(10,6);break;
            case "Milimetr": liczba /= Math.pow(10,3);break;
            case "Centymetr": liczba /= Math.pow(10,2);break;
            case "Decymetr": liczba /= Math.pow(10,1);break;
            case "Kilometr": liczba /= 0.001;break;
            case "Mila morska": liczba /= 0.000539956803;break;
            case "Mila": liczba /= 0.000621371192;break;
            case "Jard": liczba /= 1.0936133;break;
            case "Stopa": liczba /= 3.2808399;break;
            case "Cal": liczba /= 39.3700787;break;
        }
        switch(wybierz2){
            case "Pikometr": return liczba * Math.pow(10,12);
            case "Nanometr": return liczba * Math.pow(10,9);
            case "Mikrometr": return liczba * Math.pow(10,6);
            case "Milimetr": return liczba * Math.pow(10,3);
            case "Centymetr": return liczba * Math.pow(10,2);
            case "Decymetr": return liczba * Math.pow(10,1);
            case "Kilometr": return liczba * 0.001;
            case "Mila morska": return liczba * 0.000539956803;
            case "Mila": return liczba * 0.000621371192;
            case "Jard": return liczba * 1.0936133;
            case "Stopa": return liczba * 3.2808399;
            case "Cal": return liczba * 39.3700787;
        }
        return 0;
    }

    private double _powierzchnia() {
        switch(wybierz1){
            case "Mikron": liczba /= Math.pow(10,12);break;
            case "Milimetr": liczba /= Math.pow(10,6);break;
            case "Centymetr": liczba /= Math.pow(10,4);break;
            case "Decymetr": liczba /= Math.pow(10,2);break;
            case "Ar": liczba /= 0.01;break;
            case "Hektar": liczba /= 0.0001;break;
            case "Kilometr": liczba /= 0.000001;break;
            case "Akr": liczba /= 0.000247105407;break;
            case "Mila": liczba /= 0.000000386102159;break;
            case "Jard": liczba /= 1.19599005;break;
            case "Stopa": liczba /= 10.7639104;break;
            case "Cal": liczba /= 1550.0031;break;
        }
        switch(wybierz2){
            case "Mikron": return liczba * Math.pow(10,12);
            case "Milimetr": return liczba * Math.pow(10,6);
            case "Centymetr": return liczba * Math.pow(10,4);
            case "Decymetr": return liczba * Math.pow(10,2);
            case "Ar": return liczba * 0.01;
            case "Hektar": return liczba * 0.0001;
            case "Kilometr": return liczba * 0.000001;
            case "Akr": return liczba * 0.000247105407;
            case "Mila": return liczba * 0.000000386102159;
            case "Jard": return liczba * 1.19599005;
            case "Stopa": return liczba * 10.7639104;
            case "Cal": return liczba * 1550.0031;
        }
        return 0;
    }

    private double _waluta() {
        switch(wybierz1){
            case "PLN": liczba *= Math.pow(10,12);break;
            case "EUR": liczba *= Math.pow(10,12);break;
            case "USD": liczba *= Math.pow(10,12);break;
            case "CHF": liczba *= Math.pow(10,12);break;
            case "GBP": liczba *= Math.pow(10,12);break;
            case "NOK": liczba *= Math.pow(10,12);break;
            case "CZK": liczba *= Math.pow(10,12);break;
        }
        switch(wybierz2){
            case "PLN": return liczba * Math.pow(10,12);
            case "EUR": return liczba * Math.pow(10,12);
            case "USD": return liczba * Math.pow(10,12);
            case "CHF": return liczba * Math.pow(10,12);
            case "GBP": return liczba * Math.pow(10,12);
            case "NOK": return liczba * Math.pow(10,12);
            case "CZK": return liczba * Math.pow(10,12);
        }
        return 0;
    }

    private double _objetosc() {
        switch(wybierz2){
            case "Mililitr": liczba /= Math.pow(10,5);break;
            case "Centylitr": liczba /= Math.pow(10,4);break;
            case "Decylitr": liczba /= Math.pow(10,3);break;
            case "Litr": liczba /= Math.pow(10,2);break;
            case "Stopa": liczba /= 3.53146667;break;
            case "Cal": liczba /= 6102.37441;break;
            case "Jard": liczba /= 0.130795062;break;
        }
        switch(wybierz1){
            case "Mililitr": return liczba * Math.pow(10,5);
            case "Centylitr": return liczba * Math.pow(10,4);
            case "Decylitr": return liczba * Math.pow(10,3);
            case "Litr": return liczba * Math.pow(10,2);
            case "Stopa": return liczba * 3.53146667;
            case "Cal": return liczba * 6102.37441;
            case "Jard": return liczba * 0.130795062;
        }
        return 0;
    }

    private double _czas() {
        switch(wybierz1){
            case "Pikosekunda": liczba /= (6*Math.pow(10,13));break;
            case "Mikrosekunda": liczba /= (6*Math.pow(10,7));break;
            case "Milisekunda": liczba /= (6*Math.pow(10,4));break;
            case "Sekunda": liczba /= 60;break;
            case "Godzina": liczba /= 0.0166666667;break;
            case "Dzień": liczba /= 0.000694444444;break;
            case "Tydzień": liczba /= 0.0000992063492;break;
            case "Rok": liczba /= 0.00000190258752;break;
        }
        switch(wybierz2){
            case "Pikosekunda": return liczba *= (6*Math.pow(10,13));
            case "Mikrosekunda": return liczba *= (6*Math.pow(10,7));
            case "Milisekunda": return liczba *= (6*Math.pow(10,4));
            case "Sekunda": return liczba *= 60;
            case "Godzina": return liczba *= 0.0166666667;
            case "Dzień": return liczba *= 0.000694444444;
            case "Tydzień": return liczba *= 0.0000992063492;
            case "Rok": return liczba *= 0.00000190258752;
        }
        return 0;
    }

    private double _masa() {
        switch(wybierz1){
            case "Mikrogram": liczba /= Math.pow(10,12);break;
            case "Miligram": liczba /= Math.pow(10,9);break;
            case "Gram": liczba /= Math.pow(10,6);break;
            case "Kilogram": liczba /= Math.pow(10,3);break;
            case "Ziarno": liczba /= 15432358.4;break;
            case "Karat": liczba /= (5*Math.pow(10,6));break;
            case "Uncja": liczba /= 35273.9619;break;
            case "Funt": liczba /= 2204.62262;break;
            case "Kwintal": liczba /= 10;break;
        }
        switch(wybierz2){
            case "Mikrogram": return liczba *= Math.pow(10,12);
            case "Miligram": return liczba *= Math.pow(10,9);
            case "Gram": return liczba *= Math.pow(10,6);
            case "Kilogram": return liczba *= Math.pow(10,3);
            case "Ziarno": return liczba *= 15432358.4;
            case "Karat": return liczba *= (5*Math.pow(10,6));
            case "Uncja": return liczba *= 35273.9619;
            case "Funt": return liczba *= 2204.62262;
            case "Kwintal": return liczba *= 10;
        }
        return 0;
    }
}
