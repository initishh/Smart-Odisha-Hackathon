package com.example.initish.testapp.trainers;

public class Trainer {

    String name,address,pin,district,phone,website,email,fileId;



    public Trainer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Trainer(String name, String address, String pin, String district, String phone, String website, String email, String fileId) {

        this.name = name;
        this.address = address;
        this.pin = pin;
        this.district = district;
        this.phone = phone;
        this.website = website;
        this.email = email;
        this.fileId = fileId;
    }
}
