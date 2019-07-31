package com.leprechaun.airport.activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leprechaun.airport.Enter;
import com.leprechaun.airport.R;
import com.leprechaun.airport.contentProvider.DatabaseHandler;
import com.leprechaun.airport.fragments.AirlineFragment;
import com.leprechaun.airport.fragments.AirplaneFragment;
import com.leprechaun.airport.fragments.AirportFragment;
import com.leprechaun.airport.fragments.FlightFragment;
import com.leprechaun.airport.fragments.TicketFragment;
import com.leprechaun.airport.fragments.TimeTableFragment;
import com.leprechaun.airport.fragments.UserFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminnActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static DatabaseHandler db;
    CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHandler(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        imageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentPhoto, 1);
            }
        });

        Bundle b = getIntent().getExtras();
        if (b != null) {
            TextView textViewName = navigationView.getHeaderView(0).findViewById(R.id.user_name);
            textViewName.setText(b.getString("login"));
            Enter.HasImage(imageView, this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_OK:

                break;
            case RESULT_FIRST_USER:
                Uri selectedImage = data.getData();
                String[] filepsth = {MediaStore.Images.Media.DATA};

                String picturePath = "";
                Cursor c = getContentResolver().query(selectedImage, filepsth, null, null, null);
                if (c.moveToFirst()) {
                    int index = c.getColumnIndex(filepsth[0]);
                    picturePath = c.getString(index);
                }
                c.close();
                if(picturePath != "") {
                    ImageView i = (ImageView) imageView;
                    i.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                    SharedPreferences sp = AdminnActivity.this.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("Url", picturePath);
                    e.apply();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adminn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction manager = getFragmentManager().beginTransaction();
        switch (id) {
            case R.id.nav_airlines:
                AirlineFragment a = new AirlineFragment();
                manager.replace(R.id.frame, a).commit();
                break;
            case R.id.nav_airports:
                AirportFragment port = new AirportFragment();
                manager.replace(R.id.frame, port).commit();
                break;
            case R.id.nav_users:
                UserFragment user = new UserFragment();
                manager.replace(R.id.frame, user).commit();
                break;
            case R.id.nav_airplanes:
                AirplaneFragment plane = new AirplaneFragment();
                manager.replace(R.id.frame, plane).commit();
                break;
            case R.id.nav_flights:
                FlightFragment flight = new FlightFragment();
                manager.replace(R.id.frame, flight).commit();
                break;
            case R.id.nav_timetable:
                TimeTableFragment time = new TimeTableFragment();
                manager.replace(R.id.frame, time).commit();
                break;
            case R.id.nav_tickets:
                TicketFragment ticket = new TicketFragment();
                manager.replace(R.id.frame, ticket).commit();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

