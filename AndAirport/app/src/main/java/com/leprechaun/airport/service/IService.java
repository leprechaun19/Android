package com.leprechaun.airport.service;

import com.leprechaun.airport.data.entities.AddFlightModel;
import com.leprechaun.airport.data.entities.AddTicketModel;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.Airplane;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.ArrayXmlAirline;
import com.leprechaun.airport.data.entities.Flight;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.RegisterViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.data.entities.Ticket;
import com.leprechaun.airport.data.entities.TimeTable;
import com.leprechaun.airport.data.entities.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IService {

    //Account
    @POST("/api/Account/")
    Call<ResponseServer> postLogin(@Body LoginViewModel model);

    @GET("/api/Account")
    Call<ResponseServer> getRegister(@Body RegisterViewModel model);

    //Airline
    @GET("/api/Airline")
    Call<ArrayJSON<Airline>> getAirlines();

    @GET("/soap/Airline")
    Call<ArrayXmlAirline> getSoapXmlAirlines();

    @POST("/api/Airline")
    Call<ResponseServer> postAirline(@Body Airline airline);

    @DELETE("/api/Airline/{id}")
    Call<ResponseServer> deleteAirline(@Path("id") String id);

    //Airport
    @GET("/api/Airport")
    Call<ArrayJSON<Airport>> getAirports();

    @POST("/api/Airport")
    Call<ResponseServer> postAirport(@Body Airport airport);

    @DELETE("/api/Airport/{id}")
    Call<ResponseServer> deleteAirport(@Path("id") String id);


    //Airplane
    @GET("/api/Airplane")
    Call<ArrayJSON<Airplane>> getAirplanes();

    @POST("/api/Airplane")
    Call<ResponseServer> postAirplane(@Body Airplane airplane);

    @DELETE("/api/Airplane/{id}")
    Call<ResponseServer> deleteAirplane(@Path("id") String id);

    //User
    @GET("/api/User")
    Call<ArrayJSON<User>> getUsers();

    @POST("/api/User")
    Call<ResponseServer> postUser(@Body User user);

    @DELETE("/api/User/{id}")
    Call<ResponseServer> deleteUser(@Path("id") String id);

    //TimeTable
    @GET("/api/TimeTable")
    Call<ArrayJSON<TimeTable>> getTimeTables();

    @POST("/api/TimeTable")
    Call<ResponseServer> postTimeTable(@Body TimeTable timeTable);

    @DELETE("/api/TimeTable/{id}")
    Call<ResponseServer> deleteTimeTable(@Path("id") String id);

    //Flight
    @GET("/api/Flight")
    Call<ArrayJSON<Flight>> getFlights();

    @POST("/api/Flight")
    Call<ResponseServer> postFlight(@Body AddFlightModel flight);

    @DELETE("/api/Flight/{id}")
    Call<ResponseServer> deleteFlight(@Path("id") String id);

    //Ticket
    @GET("/api/Ticket")
    Call<ArrayJSON<Ticket>> getTickets();

    @POST("/api/Ticket")
    Call<ResponseServer> postTicket(@Body AddTicketModel ticket);

    @DELETE("/api/Ticket/{id}")
    Call<ResponseServer> deleteTicket(@Path("id") String id);
}
