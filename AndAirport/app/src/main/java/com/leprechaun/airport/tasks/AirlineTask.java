package com.leprechaun.airport.tasks;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
