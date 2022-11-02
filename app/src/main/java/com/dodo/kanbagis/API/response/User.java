package com.dodo.kanbagis.API.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
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

}