package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArrayJSON<T> {

    @SerializedName("data")
    private ArrayList<T> data;

    public ArrayJSON(){}

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public ArrayList<T> getData() {
        return data;
    }
}
