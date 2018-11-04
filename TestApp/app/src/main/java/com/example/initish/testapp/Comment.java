package com.example.initish.testapp;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Comment {

  private String comment,username,photo_url;
    private @ServerTimestamp Date time;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Comment() {
    }

    public Comment(String comment, String username, String photo_url, Date time) {

        this.comment = comment;
        this.username = username;
        this.photo_url = photo_url;
        this.time = time;
    }
}
