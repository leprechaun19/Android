package com.leprechaun.airport.service;

import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.RegisterViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IService {

    //Account
    @POST("/api/Account/")
    Call<ResponseServer> postLogin(@Body LoginViewModel model);

    @GET("/api/Account")
    Call<ResponseServer> getRegister(@Body RegisterViewModel model);

    //Airline
    @GET("/api/Airline")
    Call<ArrayJSON<Airline>> getAirlines();

    @POST("/api/Airline")
    Call<ResponseServer> postAirline(@Query("data") Airline airline);

    @DELETE("/api/Airline")
    Call<ResponseServer> deleteAirline(@Query("id") String id);
}
