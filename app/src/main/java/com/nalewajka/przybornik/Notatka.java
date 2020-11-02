package com.nalewajka.przybornik;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Notatka extends RealmObject {

    @PrimaryKey
    @Required
    private Long id;
    @Required
    private String text;

    public Notatka(String text, Date date) {
        this.id = date.getTime();
        this.text = text;
    }

    public Notatka() {
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }
}