package com.leprechaun.airport.tasks.Airport;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAirport  extends AsyncTask<String, Void, Boolean> {

    private View view;
    private Airport airport;

    public DeleteAirport(Airport airport, View view) {
        this.view = view;
        this.airport = airport;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(view != null) {
            if (result) {
                UIHelper.showSnackbar(view, "Не удалось удалить запись", 200);
            } else {
                UIHelper.showSnackbar(view, "Запись успешно удалена", 200);
            }
        }
    }

    @Override
    protected Boolean doInBackground(String... params) {

        final boolean[] fail = {true};
        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.deleteAirport(airport.getAirportID());
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
                    Log.e("DeleteAirportFAILED", t.getMessage());
                }
            });
            return fail[0];
        } catch (Exception e) {
            Log.e("DeleteAirportERROR", e.getMessage());
        }
        return fail[0];
    }
}
