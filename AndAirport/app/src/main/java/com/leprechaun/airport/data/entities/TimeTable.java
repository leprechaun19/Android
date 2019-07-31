package com.leprechaun.airport.data.entities;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TimeTable implements Serializable {

    @SerializedName("timeTableID")
    public String TimeTableID;

    @SerializedName("flightTime")
    public String FlightTime;

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

    public TimeTable(String timeTableID, String flightTime, String mondayTimeTable, String tuesdayTimeTable, String wednesdayTimeTable, String thursdayTimeTable, String fridayTimeTable, String saturdayTimeTable, String sundayTimeTable) {
        TimeTableID = timeTableID;
        FlightTime = flightTime;
        MondayTimeTable = mondayTimeTable;
        TuesdayTimeTable = tuesdayTimeTable;
        WednesdayTimeTable = wednesdayTimeTable;
        ThursdayTimeTable = thursdayTimeTable;
        FridayTimeTable = fridayTimeTable;
        SaturdayTimeTable = saturdayTimeTable;
        SundayTimeTable = sundayTimeTable;
    }

    public TimeTable(){}

    @NonNull
    @Override
    public String toString() {
        return String.format("Time - %s; M - %s; Tue - %s; W - %s; Thu - %s; F - %s; Sat - %s; Sun - %s; ",
                FlightTime,
                MondayTimeTable, TuesdayTimeTable, WednesdayTimeTable, ThursdayTimeTable,
                FridayTimeTable, SaturdayTimeTable, SundayTimeTable);
    }

    public void setTimeTableID(String timeTableID) {
        TimeTableID = timeTableID;
    }

    public void setFlightTime(String flightTime) {
        FlightTime = flightTime;
    }

    public void setMondayTimeTable(String mondayTimeTable) {
        MondayTimeTable = mondayTimeTable;
    }

    public void setTuesdayTimeTable(String tuesdayTimeTable) {
        TuesdayTimeTable = tuesdayTimeTable;
    }

    public void setWednesdayTimeTable(String wednesdayTimeTable) {
        WednesdayTimeTable = wednesdayTimeTable;
    }

    public void setThursdayTimeTable(String thursdayTimeTable) {
        ThursdayTimeTable = thursdayTimeTable;
    }

    public void setFridayTimeTable(String fridayTimeTable) {
        FridayTimeTable = fridayTimeTable;
    }

    public void setSaturdayTimeTable(String saturdayTimeTable) {
        SaturdayTimeTable = saturdayTimeTable;
    }

    public void setSundayTimeTable(String sundayTimeTable) {
        SundayTimeTable = sundayTimeTable;
    }

    public String getTimeTableID() {
        return TimeTableID;
    }

    public String getFlightTime() {
        return FlightTime;
    }

    public String getMondayTimeTable() {
        return MondayTimeTable;
    }

    public String getTuesdayTimeTable() {
        return TuesdayTimeTable;
    }

    public String getWednesdayTimeTable() {
        return WednesdayTimeTable;
    }

    public String getThursdayTimeTable() {
        return ThursdayTimeTable;
    }

    public String getFridayTimeTable() {
        return FridayTimeTable;
    }

    public String getSaturdayTimeTable() {
        return SaturdayTimeTable;
    }

    public String getSundayTimeTable() {
        return SundayTimeTable;
    }

    public String getTimeTableString(){ return String.format("M - %s; Tue - %s; W - %s; Thu - %s; F - %s; Sat - %s; Sun - %s; ",
            MondayTimeTable, TuesdayTimeTable, WednesdayTimeTable, ThursdayTimeTable,
            FridayTimeTable, SaturdayTimeTable, SundayTimeTable);}
}
