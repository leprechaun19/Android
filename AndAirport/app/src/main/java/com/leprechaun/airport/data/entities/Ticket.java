package com.leprechaun.airport.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket implements Serializable {

    @SerializedName("ticketID")
    private String TicketID;

    @SerializedName("departureDate")
    private String DepartureDate;

    @SerializedName("airportFrom")
    private String AirportFrom;

    @SerializedName("airportTo")
    private String AirportTo;

    @SerializedName("user")
    private User User;

    @SerializedName("seat")
    private int Seat;

    @SerializedName("price")
    private Double Price;

    @SerializedName("orderDate")
    private String OrderDate;

    public Ticket(){}

    public Ticket(String ticketID, String departureDate, String airportFrom, String airportTo, com.leprechaun.airport.data.entities.User user, int seat, Double price, String orderDate) {
        TicketID = ticketID;
        DepartureDate = departureDate;
        AirportFrom = airportFrom;
        AirportTo = airportTo;
        User = user;
        Seat = seat;
        Price = price;
        OrderDate = orderDate;
    }

    public void setTicketID(String ticketID) {
        TicketID = ticketID;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public void setAirportFrom(String airportFrom) {
        AirportFrom = airportFrom;
    }

    public void setAirportTo(String airportTo) {
        AirportTo = airportTo;
    }

    public void setUser(com.leprechaun.airport.data.entities.User user) {
        User = user;
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

    public String getTicketID() {
        return TicketID;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public String getAirportFrom() {
        return AirportFrom;
    }

    public String getAirportTo() {
        return AirportTo;
    }

    public com.leprechaun.airport.data.entities.User getUser() {
        return User;
    }

    public int getSeat() {
        return Seat;
    }

    public Double getPrice() {
        return Price;
    }

    public String getOrderDate() {
        return OrderDate;
    }
}
