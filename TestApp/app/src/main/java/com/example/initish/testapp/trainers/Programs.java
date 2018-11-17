package com.example.initish.testapp.trainers;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Programs {

    String name,duration,elig,sub,fileId,desc,seats,date,status,interested;
    private @ServerTimestamp Date time;


    public Programs() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getElig() {
        return elig;
    }

    public void setElig(String elig) {
        this.elig = elig;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Programs(String name, String duration, String elig, String sub, String fileId, String desc, String seats, String date, String status, String interested, Date time) {

        this.name = name;
        this.duration = duration;
        this.elig = elig;
        this.sub = sub;
        this.fileId = fileId;
        this.desc = desc;
        this.seats = seats;
        this.date = date;
        this.status = status;
        this.interested = interested;
        this.time = time;
    }
}
