package com.leprechaun.airport.tasks.User;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.data.entities.User;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUser  extends AsyncTask<String, Void, Void> {

    private View view;
    private User user;

    public DeleteUser(User user, View view) {
        this.view = view;
        this.user = user;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.deleteUser(user.getUserId());
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        new GetUsers(view).execute("0");
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Запись удалена.", 200);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("DeleteUserFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось удалить запись", 200);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("DeleteUserERROR", e.getMessage());
        }
        return null;
    }
}
