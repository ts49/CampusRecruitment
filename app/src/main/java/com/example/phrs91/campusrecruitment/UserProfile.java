package com.example.phrs91.campusrecruitment;

import java.io.Serializable;

/**
 * Created by ashish on 19/3/18.
 */

public class UserProfile implements Serializable {

    private String name;
    private String RegisterNumber;
    private String Email;
    private String cpi;
    private String p10th;
    private String p12th;
    private String address;
    private String course;
    private String branch;
    private String gender;
    private   String state;

    public String getName() {
        return name;
    }
    public UserProfile()
    {

    }

    public UserProfile(String name, String registerNumber, String email, String cpi, String p10th, String p12th, String address, String course, String branch, String gender, String state, String country, String password, String phone, String dobirth, String cate, String resume, String photo) {
        this.name = name;
        this.RegisterNumber = registerNumber;
        this.Email = email;
        this.cpi = cpi;
        this.p10th = p10th;
        this.p12th = p12th;
        this.address = address;
        this.course = course;
        this.branch = branch;
        this.gender = gender;
        this.state = state;
        this.country = country;
        this.password = password;
        Phone = phone;
        Dobirth = dobirth;
        this.cate = cate;
        this.resume = resume;
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getRegisterNumber() {
        return RegisterNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        RegisterNumber = registerNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCpi() {
        return cpi;
    }

    public void setCpi(String cpi) {
        this.cpi = cpi;
    }

    public String getP10th() {
        return p10th;
    }

    public void setP10th(String p10th) {
        this.p10th = p10th;
    }

    public String getP12th() {
        return p12th;
    }

    public void setP12th(String p12th) {
        this.p12th = p12th;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDobirth() {
        return Dobirth;
    }

    public void setDobirth(String dobirth) {
        Dobirth = dobirth;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String country;
    private String password;
    private String Phone;
    private String Dobirth;
    private String cate;
    private String resume;
    private String photo;




}
