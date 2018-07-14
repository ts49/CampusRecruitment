package com.example.phrs91.campusrecruitment;

import java.io.Serializable;

/**
 * Created by ashish on 19/3/18.
 */

public class UserProfile implements Serializable {

    public String name;
    public String RegisterNumber;
    public String Email;
    public String cpi;
    public String p10th;
    public String p12th;
    public String address;

    public String getName() {
        return name;
    }

    public UserProfile() {
    }

    public String getRegisterNumber() {
        return RegisterNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getCpi() {
        return cpi;
    }

    public String getP10th() {
        return p10th;
    }

    public String getP12th() {
        return p12th;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPassword() {
        return password;
    }

    public String getCate() {
        return cate;
    }

    public String getCourse() {
        return course;
    }

    public String getBranch() {
        return branch;
    }

    public String getGender() {
        return gender;
    }

    public    String state;
    public String country;
    public String password;
    public String Phone;
    public String Dob;

    public String getPhone() {
        return Phone;
    }

    public String getDob() {
        return Dob;
    }

    public String getResume() {
        return resume;
    }

    public String getPhoto() {
        return photo;
    }

    public String cate;
    public String resume;
    public String photo;

    public UserProfile(String name, String registerNumber, String email, String cpi, String p10th, String p12th, String address, String state, String country, String password, String cate, String resume, String photo, String course, String branch, String gender,String phone,String dob) {
        this.name = name;
        RegisterNumber = registerNumber;
        Email = email;
        this.cpi = cpi;
        this.p10th = p10th;
        this.p12th = p12th;
        this.address = address;
        this.state = state;
        this.country = country;
        this.password = password;
        Phone = phone;
        Dob = dob;
        this.cate = cate;
        this.resume = resume;
        this.photo = photo;
        this.course = course;
        this.branch = branch;
        this.gender = gender;
    }

    public String course;
    public String branch;
    public String gender;
}
