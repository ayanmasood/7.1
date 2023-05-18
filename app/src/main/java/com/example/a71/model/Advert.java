package com.example.a71.model;

import java.io.Serializable;

public class Advert implements Serializable {
    private int user_id;
    private String name;
    private int phone;
    private String description;
    private String date;
    private String location;

    public Advert(int user_id, String name, int phone, String description, String date, String location) {
        this.user_id = user_id;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
