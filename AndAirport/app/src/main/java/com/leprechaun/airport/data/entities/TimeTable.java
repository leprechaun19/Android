package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class TimeTable implements Serializable {

    @SerializedName("timeTableID")
    public String TimeTableID;

    @SerializedName("flightTime")
    public Date FlightTime;

    @SerializedName("mondayTimeTable")
    public String MondayTimeTable;

    @SerializedName("tuesdayTimeTable")
    public String TuesdayTimeTable;

    @SerializedName("wednesdayTimeTable")
    public String WednesdayTimeTable;

    @SerializedName("thursdayTimeTable")
    public String ThursdayTimeTable;

    @SerializedName("fridayTimeTable")
    public String FridayTimeTable;

    @SerializedName("saturdayTimeTable")
    public String SaturdayTimeTable;

    @SerializedName("sundayTimeTable")
    public String SundayTimeTable;
}
