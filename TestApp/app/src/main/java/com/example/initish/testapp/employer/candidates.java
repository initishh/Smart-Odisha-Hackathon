package com.example.initish.testapp.employer;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class candidates {

    String candidateId,email,name,phone,edu,district,city,pin,locality,skills,trainings,dob,state,photourl,exp;
    private @ServerTimestamp Date time;

    public candidates() {
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getTrainings() {
        return trainings;
    }

    public void setTrainings(String trainings) {
        this.trainings = trainings;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public candidates(String candidateId, String email, String name, String phone, String edu, String district, String city, String pin, String locality, String skills, String trainings, String dob, String state, String photourl, String exp, Date time) {

        this.candidateId = candidateId;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.edu = edu;
        this.district = district;
        this.city = city;
        this.pin = pin;
        this.locality = locality;
        this.skills = skills;
        this.trainings = trainings;
        this.dob = dob;
        this.state = state;
        this.photourl = photourl;
        this.exp = exp;
        this.time = time;
    }
}
