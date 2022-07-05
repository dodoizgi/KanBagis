package com.dodo.kanbagis.module;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Blood {

    public String bloodGroup;
    public String rh;
    public String phone;
    public String adress;
    public String messages;

    public Blood() {
    }

    public Blood(String adress, String bloodGroup, String messages, String phone, String rh) {
        this.adress = adress;
        this.bloodGroup = bloodGroup;
        this.messages = messages;
        this.phone = phone;
        this.rh = rh;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
