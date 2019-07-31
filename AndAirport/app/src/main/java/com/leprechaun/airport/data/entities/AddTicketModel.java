package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddTicketModel implements Serializable {

    @SerializedName("ticketID")
    private String TicketID;

    @SerializedName("flightFligthID")
    private String FlightFligthID;

    @SerializedName("userId")
    private String UserId;

    @SerializedName("seat")
    private int Seat;

    @SerializedName("price")
    private Double Price;

    @SerializedName("orderDate")
    private String OrderDate;

    public AddTicketModel(){}

    public AddTicketModel(String ticketID, String flightFligthID, String userId, int seat, Double price, String orderDate) {
        TicketID = ticketID;
        FlightFligthID = flightFligthID;
        UserId = userId;
        Seat = seat;
        Price = price;
        OrderDate = orderDate;
    }

    public void setTicketID(String ticketID) {
        TicketID = ticketID;
    }

    public void setFlightFligthID(String flightFligthID) {
        FlightFligthID = flightFligthID;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setSeat(int seat) {
        Seat = seat;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }
}
