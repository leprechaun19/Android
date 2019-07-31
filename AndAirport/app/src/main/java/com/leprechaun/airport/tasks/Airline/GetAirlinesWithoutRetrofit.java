package com.leprechaun.airport.tasks.Airline;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.AirlineRecyclerAdapter;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.service.HttpConnectionHandler;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetAirlinesWithoutRetrofit extends AsyncTask<String, Void, Void> {

    private View view;

    public GetAirlinesWithoutRetrofit(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            HttpConnectionHandler handler = new HttpConnectionHandler();
            String jsonString = handler.makeGetAirlinesCall();

            ArrayJSON<Airline> airlines = new ArrayJSON<>();
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray arrayAirlines = jsonObj.getJSONArray("data");
            if (arrayAirlines != null) {
                for (int i = 0; i < arrayAirlines.length(); i++) {
                    JSONObject airline = arrayAirlines.getJSONObject(i);
                    String id = airline.getString("airlineID");
                    String name = airline.getString("airlineFullName");
                    airlines.addData(new Airline(id, name));
                }

                AdminnActivity.db.deleteAllAirlines();
                AdminnActivity.db.addAirlines(airlines.getData());
            }

        } catch (Exception e) {
            Log.e("GetAirlinesFAILED", e.getMessage());
            if (view != null) {
                UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
            }
        }
        return null;
    }
}
