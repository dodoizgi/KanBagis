package com.dodo.kanbagis.API.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("mail")
    @Expose
    public String mail;

    @SerializedName("password")
    @Expose
    public String password;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("lastname")
    @Expose
    public String lastname;

    @SerializedName("phone")
    @Expose
    public String phone;

    public User(String mail, String password, String name, String lastname, String phone) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}