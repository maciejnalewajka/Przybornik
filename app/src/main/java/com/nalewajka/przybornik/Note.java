package com.nalewajka.przybornik;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Note extends RealmObject {

    @PrimaryKey
    @Required
    private Long id;
    @Required
    private String text;
    @Required
    private Date date;

    public Note(String text, Date date) {
        this.id = date.getTime();
        this.text = text;
        this.date = date;
    }

    public Note() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}