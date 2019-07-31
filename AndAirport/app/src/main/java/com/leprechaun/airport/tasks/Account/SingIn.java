package com.leprechaun.airport.tasks.Account;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.Enter;
import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingIn extends AsyncTask<LoginViewModel, Void, Void> {

    private View view;

    public SingIn(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(final LoginViewModel... models) {

        try {
            Service.getService().postLogin(models[0]).enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.body() != null) {
                        ResponseServer r = new ResponseServer();
                        r.setSuccess(response.body().getSuccess());
                        r.setMessage(response.body().getMessage());

                        Enter.WriteUserInSP(view.getContext(), r, models[0]);
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("LoginFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось войти. Проверьте введенные данные.", 100);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("LoginError", e.getMessage());
        }
        return null;
    }
}
