package com.leprechaun.airport.tasks.Airline;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.AirlineRecyclerAdapter;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAirlines extends AsyncTask<String, Void, Void> {

    private View view;
    private AirlineRecyclerAdapter adapter;

    public GetAirlines(View view, AirlineRecyclerAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    public GetAirlines(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Service.getService().getAirlines().enqueue(new Callback<ArrayJSON<Airline>>() {
                @Override
                public void onResponse(Call<ArrayJSON<Airline>> call, Response<ArrayJSON<Airline>> response) {
                    if (response.body() != null) {
                        ArrayList<Airline> airlines = new ArrayList<>();

                        try {
                            airlines.addAll(response.body().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AdminnActivity.db.deleteAllAirlines();
                        AdminnActivity.db.addAirlines(airlines);
                        if(adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayJSON<Airline>> call, Throwable t) {
                    Log.e("GetAirlinesFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("GetAirlinesError", e.getMessage());
        }
        return null;
    }
}