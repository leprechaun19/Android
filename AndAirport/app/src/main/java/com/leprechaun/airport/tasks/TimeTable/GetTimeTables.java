package com.leprechaun.airport.tasks.TimeTable;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.TimeTableRecyclerAdapter;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.TimeTable;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTimeTables extends AsyncTask<String, Void, Void> {

    private View view;
    private TimeTableRecyclerAdapter adapter;

    public GetTimeTables(View view, TimeTableRecyclerAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    public GetTimeTables(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Service.getService().getTimeTables().enqueue(new Callback<ArrayJSON<TimeTable>>() {
                @Override
                public void onResponse(Call<ArrayJSON<TimeTable>> call, Response<ArrayJSON<TimeTable>> response) {
                    if (response.body() != null) {
                        ArrayList<TimeTable> timeTables = new ArrayList<>();

                        try {
                            timeTables.addAll(response.body().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AdminnActivity.db.deleteAllTimeTables();
                        AdminnActivity.db.addTimeTables(timeTables);
                        if(adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayJSON<TimeTable>> call, Throwable t) {
                    Log.e("GetTimeTablesFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("GetTimeTablesError", e.getMessage());
        }
        return null;
    }
}
