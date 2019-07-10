package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Airline implements Serializable {

    @SerializedName("airlineID")
    private String airlineID;

    @SerializedName("airlineFullName")
    private String airlineFullName;

    public Airline() {
    }

    public Airline(String airlineID, String airlineFullName) {
        this.airlineID = airlineID;
        this.airlineFullName = airlineFullName;
    }

    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    public void setAirlineFullName(String airlineFullName) {
        this.airlineFullName = airlineFullName;
    }

    public String getAirlineID() {
        return airlineID;
    }

    public String getAirlineFullName() {
        return airlineFullName;
    }
}
