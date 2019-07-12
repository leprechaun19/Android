package com.leprechaun.airport.tasks.Account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.Enter;
import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingIn extends AsyncTask<LoginViewModel, Void, Boolean> {

    private View view;

    public SingIn(View view) {
        this.view = view;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (view != null) {
            if (result) {
                UIHelper.showSnackbar(view, "Не удалось войти. Проверьте введенные данные.", 100);
            }
        }
    }

    @Override
    protected Boolean doInBackground(final LoginViewModel... models) {

        final boolean[] fail = {true};
        try {
            Service.getService().postLogin(models[0]).enqueue(new Callback<ResponseServer>() {
                @Override
                public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                    if (response.body() != null) {
                        fail[0] = false;
                        ResponseServer r = new ResponseServer();
                        r.setSuccess(response.body().getSuccess());
                        r.setMessage(response.body().getMessage());

                        Enter.WriteUserInSP(view.getContext(), r, models[0]);
                    }
                }

                @Override
                public void onFailure(Call<ResponseServer> call, Throwable t) {
                    Log.e("LoginFAILED", t.getMessage());
                }
            });
            return fail[0];
        } catch (Exception e) {
            Log.e("LoginError", e.getMessage());
        }
        return fail[0];
    }
}
