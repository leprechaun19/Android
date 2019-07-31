package com.leprechaun.airport.tasks.Airline;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChangeAirline extends AsyncTask<Airline, Void, Void> {

    private View view;

    public AddChangeAirline(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(Airline... airlines) {

        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.postAirline(airlines[0]);
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        new GetAirlines(view).execute("0");
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Изменения сохранены.", 200);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("AddAirlinesFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось сохранить изменения.", 200);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("AddAirlinesError", e.getMessage());
        }
        return null;
    }
}
