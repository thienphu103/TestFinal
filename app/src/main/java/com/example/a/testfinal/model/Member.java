package com.example.a.testfinal.model;

import java.io.Serializable;

public class Member implements Serializable  {
    String username;
    String password;
    boolean gender;
    String datefbirth;
    String placeofbirth;
    int depID;
    String image;
    int acctype;

    public Member() {
    }

    public Member(String username, String password, boolean gender, String datefbirth, String placeofbirth, int depID, String image, int acctype) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.datefbirth = datefbirth;
        this.placeofbirth = placeofbirth;
        this.depID = depID;
        this.image = image;
        this.acctype = acctype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDatefbirth() {
        return datefbirth;
    }

    public void setDatefbirth(String datefbirth) {
        this.datefbirth = datefbirth;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAcctype() {
        return acctype;
    }

    public void setAcctype(int acctype) {
        this.acctype = acctype;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    public int getDepID() {
        return depID;
    }

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", datefbirth='" + datefbirth + '\'' +
                ", placeofbirth='" + placeofbirth + '\'' +
                ", depID=" + depID +
                ", image='" + image + '\'' +
                ", acctype=" + acctype +
                '}';
    }
}
