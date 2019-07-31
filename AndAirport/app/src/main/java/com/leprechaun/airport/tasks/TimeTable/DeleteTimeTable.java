package com.leprechaun.airport.tasks.TimeTable;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.data.entities.TimeTable;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteTimeTable  extends AsyncTask<String, Void, Void> {

    private View view;
    private TimeTable timeTable;

    public DeleteTimeTable(TimeTable timeTable, View view) {
        this.view = view;
        this.timeTable = timeTable;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.deleteTimeTable(timeTable.getTimeTableID());
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Запись удалена.", 200);
                            new GetTimeTables(view).execute("0");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("DeleteTimeTableFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось удалить запись", 200);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("DeleteTimeTableERROR", e.getMessage());
        }
        return null;
    }
}
