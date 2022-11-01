package com.dodo.kanbagis.API.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Advert {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bloodGroup")
    @Expose
    private String bloodGroup;
    @SerializedName("rh")
    @Expose
    private String rh;
    @SerializedName("adress")
    @Expose
    private String adress;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("phone")
    @Expose
    private String phone;

    public Advert(String adress, String bloodGroup, String messages, String phone, String rh) {
        this.adress = adress;
        this.bloodGroup = bloodGroup;
        this.messages = messages;
        this.phone = phone;
        this.rh = rh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}