package com.leprechaun.airport.tasks.Airline;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.AirlineRecyclerAdapter;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.ArrayXmlAirline;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAirlineSoapXml extends AsyncTask<String, Void, Void> {

    private View view;
    private AirlineRecyclerAdapter adapter;

    public GetAirlineSoapXml(View view, AirlineRecyclerAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    public GetAirlineSoapXml(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Service.getXmlService().getSoapXmlAirlines().enqueue(new Callback<ArrayXmlAirline>() {
                @Override
                public void onResponse(Call<ArrayXmlAirline> call, Response<ArrayXmlAirline> response) {
                    if (response.body() != null) {
                        ArrayList<Airline> airlines = new ArrayList<>();

                        try {
                            for (ArrayXmlAirline.Airline a : response.body().getData()) {
                                airlines.add(new Airline(a.getAirlineID(), a.getAirlineFullName()));
                            }
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
                public void onFailure(Call<ArrayXmlAirline> call, Throwable t) {
                    Log.e("GetAirlinesXmlFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("GetAirlinesXmlError", e.getMessage());
        }
        return null;
    }
}
