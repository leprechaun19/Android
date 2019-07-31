package com.leprechaun.airport.data.entities;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Flight implements Serializable {

    @SerializedName("fligthID")
    private String FlightID;

    @SerializedName("departureDate")
    private String DepartureDate;

    @SerializedName("destinationDate")
    private String DestinationDate;

    @SerializedName("airplaneType")
    private String AirplaneType;

    @SerializedName("airportFrom")
    private String AirportFrom;

    @SerializedName("airportTo")
    private String AirportTo;

    @SerializedName("flightTime")
    private String FlightTime;

    @SerializedName("priceFlight")
    private double PriceFlight;

    public Flight(){}

    public Flight(String flightID, String departureDate, String destinationDate, String airplaneType, String airportFrom, String airportTo, String flightTime, double priceFlight) {
        FlightID = flightID;
        DepartureDate = departureDate;
        DestinationDate = destinationDate;
        AirplaneType = airplaneType;
        AirportFrom = airportFrom;
        AirportTo = airportTo;
        FlightTime = flightTime;
        PriceFlight = priceFlight;
    }

    @NonNull
    @Override
    public String toString() {
        int start = AirportFrom.indexOf(',') + 1;
        int end = AirportFrom.indexOf(')');
        String cityFrom = AirportFrom.substring(start, end);

        int start1 = AirportTo.indexOf(',') + 1;
        int end1 = AirportTo.indexOf(')');
        String cityTo = AirportTo.substring(start1, end1);

        return DepartureDate + "; " + cityFrom + " -" + cityTo;
    }

    public String getFlightID() {
        return FlightID;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public String getDestinationDate() {
        return DestinationDate;
    }

    public String getAirplaneType() {
        return AirplaneType;
    }

    public String getAirportFrom() {
        return AirportFrom;
    }

    public String getAirportTo() {
        return AirportTo;
    }

    public String getFlightTime() {
        return FlightTime;
    }

    public double getPriceFlight() {
        return PriceFlight;
    }
}
