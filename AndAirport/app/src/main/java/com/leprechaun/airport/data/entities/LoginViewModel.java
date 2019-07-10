package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginViewModel implements Serializable {

    public LoginViewModel(){

    }

    public LoginViewModel(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @SerializedName("Email")
    private String email;

    @SerializedName("Password")
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
