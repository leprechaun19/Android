package com.leprechaun.airport.tasks.Ticket;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.AddTicketModel;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChangeTicket extends AsyncTask<AddTicketModel, Void, Void> {

    private View view;

    public AddChangeTicket( View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(AddTicketModel... tickets) {

        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.postTicket(tickets[0]);
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        new GetTickets(view).execute("0");
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Изменения сохранены.", 200);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("AddTicketFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось сохранить изменения.", 200);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("AddTicketError", e.getMessage());
        }
        return null;
    }
}
