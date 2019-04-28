package com.example.android_assignment;

public class Note {

    private String text;

    public Note( String text) {
        this.text = text;
    }
    public Note()
    {

    }


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}