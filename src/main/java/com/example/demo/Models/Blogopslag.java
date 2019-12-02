package com.example.demo.Models;

public class Blogopslag {
    int id;
    String titel;
    String content;

    public Blogopslag() {
    }

    public Blogopslag(int id, String titel, String content) {
        this.id = id;
        this.titel = titel;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
