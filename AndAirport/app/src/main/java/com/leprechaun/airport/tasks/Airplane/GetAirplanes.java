package com.leprechaun.airport.tasks.Airplane;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.AirplaneRecyclerAdapter;
import com.leprechaun.airport.data.entities.Airplane;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAirplanes extends AsyncTask<String, Void, Void> {

    private View view;
    private AirplaneRecyclerAdapter adapter;

    public GetAirplanes(View view, AirplaneRecyclerAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    public GetAirplanes(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Service.getService().getAirplanes().enqueue(new Callback<ArrayJSON<Airplane>>() {
                @Override
                public void onResponse(Call<ArrayJSON<Airplane>> call, Response<ArrayJSON<Airplane>> response) {
                    if (response.body() != null) {
                        ArrayList<Airplane> airplanes = new ArrayList<>();

                        try {
                            airplanes.addAll(response.body().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AdminnActivity.db.deleteAllAirplanes();
                        AdminnActivity.db.addAirplanes(airplanes);
                        if(adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayJSON<Airplane>> call, Throwable t) {
                    Log.e("GetAirplanesFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("GetAirplanesError", e.getMessage());
        }
        return null;
    }
}
