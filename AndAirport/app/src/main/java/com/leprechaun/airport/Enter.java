package com.leprechaun.airport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;

import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.activities.LoginActivity;
import com.leprechaun.airport.activities.UserActivity;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Enter {

    private static String APP_LOGIN = "login";
    private static String APP_PASSWORD = "password";
    private static String APP_MODE = "mode";
    private static String APP_PHOTO = "Url";
    private static final String APP_PREFERENCE = "myPreference";

    public static Intent LogInIntent(Context context) {

        SharedPreferences sp = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        if (sp.contains(APP_LOGIN) && sp.contains(APP_PASSWORD) && sp.contains(APP_MODE)) {
            switch (Objects.requireNonNull(sp.getString(APP_MODE, ""))) {
                case "Super Admin":
                    return SuperAdmin(context, new LoginViewModel(sp.getString(APP_LOGIN, ""), sp.getString(APP_PASSWORD, "")));
                case "User":
                    return User(context, new LoginViewModel(sp.getString(APP_LOGIN, ""), sp.getString(APP_PASSWORD, "")));
                default:
                    return null;
            }
        }
        return null;
    }

    public static void WriteUserInSP(Context context, ResponseServer r, LoginViewModel model) {

        SharedPreferences sp = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        try {
            if (r.getMessage().equals("Super Admin")) {
                e.putString(APP_LOGIN, model.getEmail());
                e.putString(APP_PASSWORD, model.getPassword());
                e.putString(APP_MODE, "Super Admin");
                e.apply();
            } else if (r.getMessage().equals("User")) {
                e.putString(APP_LOGIN, model.getEmail());
                e.putString(APP_PASSWORD, model.getPassword());
                e.putString(APP_MODE, "User");
                e.apply();
            }
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void HasImage(CircleImageView circleImageView, Context context) {

        SharedPreferences sp = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        if (sp.contains(APP_PHOTO)) {
            circleImageView.setImageBitmap(BitmapFactory.decodeFile(sp.getString(APP_PHOTO, "")));
            circleImageView.setVisibility(View.VISIBLE);
        }
    }

    private static Intent SuperAdmin(Context context, LoginViewModel model) {
        Intent intent = new Intent(context, AdminnActivity.class);
        intent.putExtra("login", model.getEmail());
        return intent;
    }

    private static Intent User(Context context, LoginViewModel model) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(LoginActivity.class.getSimpleName(), model);
        return intent;
    }
}
