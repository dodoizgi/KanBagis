package com.dodo.kanbagis.API.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
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
}