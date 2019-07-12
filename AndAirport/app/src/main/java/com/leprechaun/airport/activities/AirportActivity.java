package com.leprechaun.airport.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leprechaun.airport.R;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.tasks.Airport.AddChangeAirport;
import com.leprechaun.airport.tasks.Airport.DeleteAirport;

public class AirportActivity extends AppCompatActivity {

    Airport airport;
    TextView addOrChangeText;
    CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Bundle b = getIntent().getExtras();
        if (b != null){
            airport = (Airport) b.getSerializable(Airport.class.getSimpleName());
            if (airport != null){
                TextView t = findViewById(R.id.airport_name_textview);
                TextView loc = findViewById(R.id.airport_location);

                t.setText(airport.getAirportName());
                loc.setText(String.format("%s, %s", airport.getCountryName(), airport.getCityName()));
            }
        }
        else {
            TextView airlineText = findViewById(R.id.airportText);
            CardView cardView_Name = findViewById(R.id.cardView_name_airport);

            airlineText.setVisibility(View.GONE);
            cardView_Name.setVisibility(View.GONE);
            SetVisibleChange();
            addOrChangeText.setText("Add New Airport");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_change:
                SetVisibleChange();
                SetDataToEditText(airport);
                addOrChangeText.setText("New Airport");
                break;
            case R.id.nav_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure want to delete?");
                builder.setMessage("Confirm your decision.");

                builder.setPositiveButton("Yes, delete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteAirport(airport, AirportActivity.this.getWindow().getDecorView().getRootView()).execute("0");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AirportActivity.this, "Deletion canceled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SetVisibleChange(){

        cardView = findViewById(R.id.layout_change_airport);
        addOrChangeText = findViewById(R.id.new_or_change_name_airport);

        addOrChangeText.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    private void SetDataToEditText(Airport airport){

        EditText name = findViewById(R.id.new_airport_name);
        EditText country = findViewById(R.id.new_airport_country);
        EditText city = findViewById(R.id.new_airport_city);

        name.setText(airport.getAirportName());
        country.setText(airport.getCountryName());
        city.setText(airport.getCityName());
    }

    public void AddChangeAirportClick(View view) {

        Airport a = new Airport();
        EditText name = findViewById(R.id.new_airport_name);
        EditText country = findViewById(R.id.new_airport_country);
        EditText city = findViewById(R.id.new_airport_city);

        if (airport != null){
            a.setAirportID(airport.getAirportID());
        }

        a.setAirportName(name.getText().toString());
        a.setCountryName(country.getText().toString());
        a.setCityName(city.getText().toString());
        new AddChangeAirport(this.getWindow().getDecorView().getRootView()).execute(a);
    }
}
