package com.leprechaun.airport.data.entities;

import java.util.UUID;

public class Airplane {

    private UUID airplaneID;
    private String airplaneType;
    private String numPlaces;
    private UUID airlineID;
    private Airline airline;

    public Airplane() {
    }

    public UUID getAirplaneID() {
        return airplaneID;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public String getNumPlaces() {
        return numPlaces;
    }

    public UUID getAirlineID() {
        return airlineID;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirplaneID(UUID airplaneID) {
        this.airplaneID = airplaneID;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public void setNumPlaces(String numPlaces) {
        this.numPlaces = numPlaces;
    }

    public void setAirlineID(UUID airlineID) {
        this.airlineID = airlineID;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
