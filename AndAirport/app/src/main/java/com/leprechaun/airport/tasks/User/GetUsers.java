package com.leprechaun.airport.tasks.User;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.leprechaun.airport.UIHelper;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.adapters.UserRecyclerAdapter;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.User;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUsers extends AsyncTask<String, Void, Void> {

    private View view;
    private UserRecyclerAdapter adapter;

    public GetUsers(View view, UserRecyclerAdapter adapter) {
        this.adapter = adapter;
        this.view = view;
    }

    public GetUsers(View view) {
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Service.getService().getUsers().enqueue(new Callback<ArrayJSON<User>>() {
                @Override
                public void onResponse(Call<ArrayJSON<User>> call, Response<ArrayJSON<User>> response) {
                    if (response.body() != null) {
                        ArrayList<User> users = new ArrayList<>();

                        try {
                            users.addAll(response.body().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AdminnActivity.db.deleteAllUsers();
                        AdminnActivity.db.addUsers(users);
                        if(adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayJSON<User>> call, Throwable t) {
                    Log.e("GetUsersFAILED", t.getMessage());
                    if (view != null) {
                        UIHelper.showSnackbar(view, "Не удалось загрузить данные. Возможно, отсутствует связь.", 60);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("GetUsersError", e.getMessage());
        }
        return null;
    }
}
