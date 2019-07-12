package com.leprechaun.airport.tasks.Airport;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAirports extends AsyncTask<String, Void, Boolean> {

        private View view;

        public GetAirports(View view) {
            this.view = view;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                if (view != null) {
                    UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                }
            }
        }

        @Override
        protected Boolean doInBackground(String... params) {

            final boolean[] fail = {true};
            try {
                Service.getService().getAirports().enqueue(new Callback<ArrayJSON<Airport>>() {
                    @Override
                    public void onResponse(Call<ArrayJSON<Airport>> call, Response<ArrayJSON<Airport>> response) {
                        if (response.body() != null) {
                            fail[0] = false;
                            ArrayList<Airport> airports = new ArrayList<>();

                            try {
                                airports.addAll(response.body().getData());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            AdminnActivity.db.deleteAllAirports();
                            AdminnActivity.db.addAirports(airports);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayJSON<Airport>> call, Throwable t) {
                        Log.e("GetAirportsFAILED", t.getMessage());
                    }
                });
                return fail[0];
            } catch (Exception e) {
                Log.e("GetAirportsError", e.getMessage());
            }
            return fail[0];
        }

    }
