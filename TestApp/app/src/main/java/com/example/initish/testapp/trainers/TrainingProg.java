package com.example.initish.testapp.trainers;

public class TrainingProg {

    String course,duration,eligibility,fileId;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public TrainingProg(String course, String duration, String eligibility, String fileId) {

        this.course = course;
        this.duration = duration;
        this.eligibility = eligibility;
        this.fileId = fileId;
    }

    public TrainingProg() {

    }

}
