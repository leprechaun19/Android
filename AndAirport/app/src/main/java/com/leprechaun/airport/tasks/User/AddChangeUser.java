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

public class AddChangeUser  extends AsyncTask<User, Void, Void> {

    private View view;

    public AddChangeUser( View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(User... users) {

        try {
            final IService apiService = Service.getService();

            Call<ResponseServer> call = apiService.postUser(users[0]);
            call.enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.isSuccessful()) {
                        new GetUsers(view).execute("0");
                        if (view != null) {
                            UIHelper.showSnackbar(view, "Изменения сохранены.", 200);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("AddUserFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось сохранить изменения.", 200);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("AddUserError", e.getMessage());
        }
        return null;
    }
}
