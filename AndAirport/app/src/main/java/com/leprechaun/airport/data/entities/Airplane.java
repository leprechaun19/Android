package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Airplane implements Serializable {

    @SerializedName("airplaneID")
    private String airplaneID;

    @SerializedName("airplaneType")
    private String airplaneType;

    @SerializedName("numPlaces")
    private String numPlaces;

    @SerializedName("airlineID")
    private String airlineID;

    @SerializedName("airline")
    private Airline airline;

    public Airplane() {
    }

    public Airplane(String airplaneID, String airplaneType, String numPlaces, String airlineID, Airline airline) {
        this.airplaneID = airplaneID;
        this.airplaneType = airplaneType;
        this.numPlaces = numPlaces;
        this.airlineID = airlineID;
        this.airline = airline;
    }

    public String getAirplaneID() {
        return airplaneID;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public String getNumPlaces() {
        return numPlaces;
    }

    public String getAirlineID() {
        return airlineID;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirplaneID(String airplaneID) {
        this.airplaneID = airplaneID;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public void setNumPlaces(String numPlaces) {
        this.numPlaces = numPlaces;
    }

    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
