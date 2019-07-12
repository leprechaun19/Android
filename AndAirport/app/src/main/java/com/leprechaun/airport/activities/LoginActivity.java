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

import com.leprechaun.airport.Enter;
import com.leprechaun.airport.R;
import com.leprechaun.airport.data.entities.LoginViewModel;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.Service;
import com.leprechaun.airport.tasks.Account.SingIn;

import java.time.Instant;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        Intent intent = Enter.LogInIntent(this);
        if(intent != null){
            startActivity(intent);
            finish();
        }
    }

    public void LoginClick(View view) {

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loadingProgressBar.setVisibility(View.VISIBLE);
        new SingIn(getWindow().getDecorView().getRootView()).execute(new LoginViewModel(usernameEditText.getText().toString(), passwordEditText.getText().toString()));
        loadingProgressBar.setVisibility(View.INVISIBLE);

        Intent intent = Enter.LogInIntent(this);
        if(intent != null){
            startActivity(intent);
            finish();
        }
    }
}
