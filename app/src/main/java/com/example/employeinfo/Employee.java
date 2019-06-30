package com.example.employeinfo;

import android.graphics.Bitmap;

public class Employee {
    String EmployeeName;
    String ProfileDetail;
    float Rating;
    Bitmap img;

    public Employee(String employeeName, String profileDetail,Bitmap img,float rating) {
        this.EmployeeName = employeeName;
        this.ProfileDetail = profileDetail;
        this.Rating = rating;
        this.img = img;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getProfileDetail() {
        return ProfileDetail;
    }

    public void setProfileDetail(String profileDetail) {
        ProfileDetail = profileDetail;
    }

    public Float getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

}
