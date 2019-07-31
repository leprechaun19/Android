package com.leprechaun.airport.data.entities;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Airport implements Serializable {

    @SerializedName("airportID")
    private String AirportID;

    @SerializedName("airportName")
    private String AirportName;

    @SerializedName("countryName")
    private String  CountryName;

    @SerializedName("cityName")
    private String  CityName;

    public Airport() {
    }

    public Airport(String airportID, String airportName, String countryName, String cityName) {
        this.AirportID = airportID;
        this.AirportName = airportName;
        this.CountryName = countryName;
        this.CityName = cityName;
    }

    @NonNull
    @Override
    public String toString() {
        return AirportName + "(" + CountryName + ", " + CityName + ")";
    }

    public void setAirportID(String airportID) {
        AirportID = airportID;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getAirportID() {
        return AirportID;
    }

    public String getAirportName() {
        return AirportName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public String getCityName() {
        return CityName;
    }
}
