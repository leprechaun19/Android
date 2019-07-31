package com.leprechaun.airport.tasks.Flight;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.Flight;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteFlight  extends AsyncTask<String, Void, Void> {

    private View view;
    private Flight flight;

    public DeleteFlight(Flight flight, View view) {
        this.view = view;
        this.flight = flight;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.deleteFlight(flight.getFlightID());
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        new GetFlights(view).execute("0");
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Запись удалена.", 200);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("DeleteFlightFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось удалить запись", 200);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("DeleteFlightERROR", e.getMessage());
        }
        return null;
    }
}
