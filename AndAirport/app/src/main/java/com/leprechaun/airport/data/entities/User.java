package com.leprechaun.airport.data.entities;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("userId")
    private String UserId;

    @SerializedName("userName")
    private String UserName;

    @SerializedName("address")
    private String Address;

    @SerializedName("phone")
    private String Phone;

    @SerializedName("email")
    private String Email;

    @SerializedName("createAt")
    private String CreateAt;

    public User() {
    }

    public User(String userId, String userName) {
        UserId = userId;
        UserName = userName;
    }

    public User(String userId, String userName, String address, String phone, String email, String createAt) {
        UserId = userId;
        UserName = userName;
        Address = address;
        Phone = phone;
        Email = email;
        CreateAt = createAt;
    }

    @NonNull
    @Override
    public String toString() {
        return UserName;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCreateAt(String createAt) {
        CreateAt = createAt;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getCreateAt() {
        return CreateAt;
    }
}
