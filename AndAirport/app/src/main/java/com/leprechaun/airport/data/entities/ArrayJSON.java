package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArrayJSON<T> {

    @SerializedName("data")
    private ArrayList<T> data;

    public ArrayJSON(){
        data = new ArrayList<>();
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void addData(T item ) {
        data.add(item);
    }
}
