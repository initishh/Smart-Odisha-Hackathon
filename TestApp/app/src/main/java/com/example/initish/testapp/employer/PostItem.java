package com.example.initish.testapp.employer;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class PostItem {

    String address,desc,exp,salary,vacancy,title,skill,userId,fileId;
    private @ServerTimestamp Date time;

    public PostItem() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public PostItem(String address, String desc, String exp, String salary, String vacancy, String title, String skill, String userId, String fileId, Date time) {

        this.address = address;
        this.desc = desc;
        this.exp = exp;
        this.salary = salary;
        this.vacancy = vacancy;
        this.title = title;
        this.skill = skill;
        this.userId = userId;
        this.fileId = fileId;
        this.time = time;
    }
}
