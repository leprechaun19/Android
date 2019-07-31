package com.leprechaun.airport.data.entities;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

public class Airline implements Serializable {

    @Element(name = "AirlineID", required = false)
    @SerializedName("airlineID")
    private String AirlineID;

    @Element(name = "AirlineFullName", required = false)
    @SerializedName("airlineFullName")
    private String AirlineFullName;

    public Airline() {
    }

    public Airline(String airlineID, String airlineFullName) {
        this.AirlineID = airlineID;
        this.AirlineFullName = airlineFullName;
    }

    public void setAirlineID(String airlineID) {
        this.AirlineID = airlineID;
    }

    public void setAirlineFullName(String airlineFullName) {
        this.AirlineFullName = airlineFullName;
    }

    public String getAirlineID() {
        return AirlineID;
    }

    public String getAirlineFullName() {
        return AirlineFullName;
    }

    @NonNull
    @Override
    public String toString() {
        return AirlineFullName;
    }
}
