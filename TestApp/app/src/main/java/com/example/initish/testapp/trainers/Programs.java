package com.example.initish.testapp.trainers;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Programs {

    String name,sector,duration,elig,sub,fileId;
    private @ServerTimestamp Date time;


    public Programs() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Programs(String name, String sector, String duration, String elig, String sub, String fileId, Date time) {

        this.name = name;
        this.sector = sector;
        this.duration = duration;
        this.elig = elig;
        this.sub = sub;
        this.fileId = fileId;
        this.time = time;
    }
}
