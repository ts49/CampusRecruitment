package com.example.phrs91.campusrecruitment.Fragments;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

public class Company implements Serializable{
    private String name;
    private String CTC;
    private HashSet<String> branches;
    private HashSet<String> courses;
    private Date date;
    private String Desc;
    private Double cpi;

    public Double getCpi() {
        return cpi;
    }

    public void setCpi(Double cpi) {
        this.cpi = cpi;
    }

    public Company(String name, String CTC, HashSet<String> branches, HashSet<String> courses, Date date, String desc,Double cpi) {
        this.name = name;
        this.CTC = CTC;
        this.branches = branches;
        this.courses = courses;
        this.date = date;
        Desc = desc;
        this.cpi=cpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCTC() {
        return CTC;
    }

    public void setCTC(String CTC) {
        this.CTC = CTC;
    }

    public HashSet<String> getBranches() {
        return branches;
    }

    public void setBranches(HashSet<String> branches) {
        this.branches = branches;
    }

    public HashSet<String> getCourses() {
        return courses;
    }

    public void setCourses(HashSet<String> courses) {
        this.courses = courses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
