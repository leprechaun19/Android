package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class ResponseServer {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public ResponseServer() {
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
