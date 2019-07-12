package com.leprechaun.airport.tasks.Airport;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;
import com.leprechaun.airport.tasks.Airline.GetAirlines;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChangeAirport extends AsyncTask<Airport, Void, Boolean> {

    private View view;

    public AddChangeAirport(View view) {
        this.view = view;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (view != null) {
            if (result) {
                UIHelper.showSnackbar(view, "Не удалось сохранить изменения.", 200);
            }
            else {
                UIHelper.showSnackbar(view, "Изменения сохранены.", 120);
            }
        }
    }

    @Override
    protected Boolean doInBackground(Airport... airports) {

        final boolean[] fail = {true};
        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.postAirport(airports[0]);
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        fail[0] = false;
                        new GetAirports(view).execute("0");
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("AddAirportsFAILED", t.getMessage());
                }
            });
            return fail[0];
        } catch (Exception e) {
            Log.e("AddAirportsError", e.getMessage());
        }
        return fail[0];
    }
}
