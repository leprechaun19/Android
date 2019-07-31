package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddFlightModel implements Serializable {

    @SerializedName("fligthID")
    private String FlightID;

    @SerializedName("departureDate")
    private String DepartureDate;

    @SerializedName("destinationDate")
    private String DestinationDate;

    @SerializedName("priceFlight")
    private double PriceFlight;

    @SerializedName("airplaneID")
    private String AirplaneID;

    @SerializedName("airportFromAirportID")
    private String AirportFromAirportID;

    @SerializedName("airportToAirportID")
    private String AirportToAirportID;

    @SerializedName("timeTableID")
    private String TimeTableID;

    public AddFlightModel(){}

    public void setFlightID(String flightID) {
        FlightID = flightID;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public void setDestinationDate(String destinationDate) {
        DestinationDate = destinationDate;
    }

    public void setPriceFlight(double priceFlight) {
        PriceFlight = priceFlight;
    }

    public void setAirplaneID(String airplaneID) {
        AirplaneID = airplaneID;
    }

    public void setAirportFromAirportID(String airportFromAirportID) {
        AirportFromAirportID = airportFromAirportID;
    }

    public void setAirportToAirportID(String airportToAirportID) {
        AirportToAirportID = airportToAirportID;
    }

    public void setTimeTableID(String timeTableID) {
        TimeTableID = timeTableID;
    }
}
