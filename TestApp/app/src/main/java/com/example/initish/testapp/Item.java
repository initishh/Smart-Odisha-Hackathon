package com.example.initish.testapp;

import android.net.Uri;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Item {

    String query,username,desc,title_id,uID,photo_url;
    private @ServerTimestamp Date time;

    public Item() {

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
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

    public Item(String query, String username, String desc, String title_id, String uID, String photo_url, Date time) {

        this.query = query;
        this.username = username;
        this.desc = desc;
        this.title_id = title_id;
        this.uID = uID;
        this.photo_url = photo_url;
        this.time = time;
    }
}
