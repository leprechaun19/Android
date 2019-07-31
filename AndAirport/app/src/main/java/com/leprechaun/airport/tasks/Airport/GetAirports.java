package com.leprechaun.airport.tasks.Airport;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.AirportRecyclerAdapter;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAirports extends AsyncTask<String, Void, Void> {

        private View view;
        private AirportRecyclerAdapter adapter;

    public GetAirports(View view, AirportRecyclerAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    public GetAirports(View view) {
        this.view = view;
    }

    @Override
        protected Void doInBackground(String... params) {

            try {
                Service.getService().getAirports().enqueue(new Callback<ArrayJSON<Airport>>() {
                    @Override
                    public void onResponse(Call<ArrayJSON<Airport>> call, Response<ArrayJSON<Airport>> response) {
                        if (response.body() != null) {
                            ArrayList<Airport> airports = new ArrayList<>();

                            try {
                                airports.addAll(response.body().getData());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            AdminnActivity.db.deleteAllAirports();
                            AdminnActivity.db.addAirports(airports);
                            if(adapter != null) {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayJSON<Airport>> call, Throwable t) {
                        Log.e("GetAirportsFAILED", t.getMessage());
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                        }
                    }
                });
            } catch (Exception e) {
                Log.e("GetAirportsError", e.getMessage());
            }
            return null;
        }
    }
