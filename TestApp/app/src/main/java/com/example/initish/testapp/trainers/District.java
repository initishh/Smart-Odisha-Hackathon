package com.example.initish.testapp.trainers;

public class District {

    String name;
    int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public District() {

    }

    public District(String name, int number) {

        this.name = name;
        this.number = number;
    }
}
