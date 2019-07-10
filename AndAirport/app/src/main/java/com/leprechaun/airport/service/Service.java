package com.leprechaun.airport.service;

import android.util.Log;

import com.leprechaun.airport.Contact;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.RegisterViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    public static ResponseServer r;
    //Account
    public static ResponseServer PostLogin(LoginViewModel model){

        getService().postLogin(model).enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                if(response.body() != null) {
                   r = new ResponseServer();
                    r.setSuccess(response.body().getSuccess());
                    r.setMessage(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("LoginFAILED", t.getMessage());
            }
        });

        return r;
    }

    public static boolean PostRegister(RegisterViewModel model){

        final boolean[] isRegister = {false};
        getService().getRegister(model).enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                if(response.body() != null){
                    isRegister[0] = true;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("RegisterFAILED", t.getMessage());
            }
        });

        return isRegister[0];
    }

    public static IService getService(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(4, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contact.RestServerUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class);
    }
}
