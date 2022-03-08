package com.example.instagram;

/* loaded from: classes.dex */
public class Comments {
    public String comment;
    public String date;
    public String time;
    public String username;

    public Comments() {
    }

    public Comments(String comment, String date, String time, String username) {
        this.comment = comment;
        this.date = date;
        this.time = time;
        this.username = username;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
