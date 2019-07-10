package com.leprechaun.airport.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.leprechaun.airport.R;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.Service;

import java.time.Instant;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sp;
    private static final String APP_PREFERENCE = "myPreference";
    private String APP_LOGIN = "login";
    private String APP_PASSWORD = "password";
    private String APP_MODE = "mode";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button signButton = findViewById(R.id.signin);
        final Button registerButton = findViewById(R.id.register);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        sp = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        if (sp.contains(APP_LOGIN) && sp.contains(APP_PASSWORD) && sp.contains(APP_MODE)) {
            switch (Objects.requireNonNull(sp.getString(APP_MODE, ""))) {
                case "Super Admin":
                    SuperAdmin(new LoginViewModel(sp.getString(APP_LOGIN, ""), sp.getString(APP_PASSWORD, "")));
                    break;
                case "User":
                    SuperAdmin(new LoginViewModel(sp.getString(APP_LOGIN, ""), sp.getString(APP_PASSWORD, "")));
                    break;

            }
        }
    }

    public void LoginClick(View view) {

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loadingProgressBar.setVisibility(View.VISIBLE);
        Service.PostLogin(new LoginViewModel(usernameEditText.getText().toString(), passwordEditText.getText().toString()));
        ResponseServer is = Service.r;
                loadingProgressBar.setVisibility(View.INVISIBLE);
        SharedPreferences.Editor e = sp.edit();

        if(is != null) {
            if (is.getSuccess() && is.getMessage().equals("Super Admin")) {
                e.putString(APP_LOGIN, usernameEditText.getText().toString());
                e.putString(APP_PASSWORD, passwordEditText.getText().toString());
                e.putString(APP_MODE, "Super Admin");
                e.apply();

                SuperAdmin(new LoginViewModel(usernameEditText.getText().toString(), passwordEditText.getText().toString()));

            } else if (is.getSuccess() && is.getMessage().equals("User")) {
                e.putString(APP_LOGIN, usernameEditText.getText().toString());
                e.putString(APP_PASSWORD, passwordEditText.getText().toString());
                e.putString(APP_MODE, "Super Admin");
                e.apply();

                User(new LoginViewModel(usernameEditText.getText().toString(), passwordEditText.getText().toString()));
            } else {
                Toast.makeText(LoginActivity.this, is.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SuperAdmin(LoginViewModel model) {
        Intent intent = new Intent(LoginActivity.this, AdminnActivity.class);
        intent.putExtra("login", model.getEmail());
        startActivity(intent);
    }

    private void User(LoginViewModel model) {
        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
        intent.putExtra(LoginActivity.class.getSimpleName(), model);
        startActivity(intent);
    }
}
