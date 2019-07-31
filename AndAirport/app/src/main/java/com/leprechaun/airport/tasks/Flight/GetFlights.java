package com.leprechaun.airport.tasks.Flight;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.FlightRecyclerAdapter;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.Flight;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFlights extends AsyncTask<String, Void, Void> {

    private View view;
    private FlightRecyclerAdapter adapter;

    public GetFlights(View view, FlightRecyclerAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    public GetFlights(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Service.getService().getFlights().enqueue(new Callback<ArrayJSON<Flight>>() {
                @Override
                public void onResponse(Call<ArrayJSON<Flight>> call, Response<ArrayJSON<Flight>> response) {
                    if (response.body() != null) {
                        ArrayList<Flight> flights = new ArrayList<>();

                        try {
                            flights.addAll(response.body().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AdminnActivity.db.deleteAllFlights();
                        AdminnActivity.db.addFlights(flights);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ArrayJSON<Flight>> call, Throwable t) {
                    Log.e("GetFlightsFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("GetFlightsError", e.getMessage());
        }
        return null;
    }
}
