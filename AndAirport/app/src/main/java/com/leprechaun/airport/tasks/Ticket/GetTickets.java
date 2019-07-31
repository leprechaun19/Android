package com.leprechaun.airport.tasks.Ticket;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.TicketRecyclerAdapter;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.Ticket;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTickets extends AsyncTask<String, Void, Void> {

    private View view;
    private TicketRecyclerAdapter adapter;

    public GetTickets(View view, TicketRecyclerAdapter adapter) {
        this.adapter = adapter;
        this.view = view;
    }

    public GetTickets(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Service.getService().getTickets().enqueue(new Callback<ArrayJSON<Ticket>>() {
                @Override
                public void onResponse(Call<ArrayJSON<Ticket>> call, Response<ArrayJSON<Ticket>> response) {
                    if (response.body() != null) {
                        ArrayList<Ticket> tickets = new ArrayList<>();

                        try {
                            tickets.addAll(response.body().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AdminnActivity.db.deleteAllTickets();
                        AdminnActivity.db.addTickets(tickets);
                        if(adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayJSON<Ticket>> call, Throwable t) {
                    Log.e("GetTicketsFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("GetTicketsError", e.getMessage());
        }
        return null;
    }
}
