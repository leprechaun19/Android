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

public class DeleteAirline extends AsyncTask<String, Void, Void> {

    private View view;
    private Airline airline;

    public DeleteAirline(Airline airline, View view) {
        this.view = view;
        this.airline = airline;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.deleteAirline(airline.getAirlineID());
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        new GetAirlines(view).execute("0");
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Запись успешно удалена", 200);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("DeleteAirlineFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось удалить запись", 200);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("DeleteAirlineERROR", e.getMessage());
        }
        return null;
    }
}
