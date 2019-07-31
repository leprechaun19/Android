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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leprechaun.airport.R;
import com.leprechaun.airport.contentProvider.DatabaseHandler;
import com.leprechaun.airport.data.entities.AddFlightModel;
import com.leprechaun.airport.data.entities.Airplane;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.data.entities.Flight;
import com.leprechaun.airport.data.entities.TimeTable;
import com.leprechaun.airport.tasks.Flight.AddFlight;
import com.leprechaun.airport.tasks.Flight.DeleteFlight;

import java.util.ArrayList;

public class FlightActivity extends AppCompatActivity {

    Flight flight;
    TextView addOrChangeText;
    CardView cardView;

    Spinner fromSpinner;
    Spinner toSpinner;
    Spinner airplaneSpinner;
    EditText destinationEditText;
    EditText departureEditText;
    Spinner flightTimeSpinner;
    EditText priceEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        flightTimeSpinner = findViewById(R.id.new_flight_timetable);
        priceEditText = findViewById(R.id.new_flight_price);
        departureEditText = findViewById(R.id.new_flight_departure_date);
        destinationEditText = findViewById(R.id.new_flight_destination_date);
        fromSpinner = findViewById(R.id.new_flight_airport_from);
        toSpinner = findViewById(R.id.new_flight_airport_to);
        airplaneSpinner = findViewById(R.id.new_flight_airplane);

        Bundle b = getIntent().getExtras();
        if (b != null){
            flight = (Flight) b.getSerializable(Flight.class.getSimpleName());
            if (flight != null){
                TextView from = findViewById(R.id.flight_airport_from_text);
                TextView to = findViewById(R.id.flight_airport_to_text);
                TextView price = findViewById(R.id.flight_price_text);
                TextView airplane = findViewById(R.id.flight_airplane_type_text);
                TextView depDate = findViewById(R.id.flight_departure_text);
                TextView desDate = findViewById(R.id.flight_destination_text);
                TextView time = findViewById(R.id.flight_flight_time_text);

                depDate.setText(String.valueOf(flight.getDepartureDate()));
                from.setText(flight.getAirportFrom());
                desDate.setText(String.valueOf(flight.getDestinationDate()));
                to.setText(flight.getAirportTo());
                time.setText(String.valueOf(flight.getFlightTime()));
                price.setText(String.valueOf(flight.getPriceFlight()));
                airplane.setText(flight.getAirplaneType());
            }
        }
        else {
            TextView timeTableText = findViewById(R.id.flightText);
            CardView cardView_timetable = findViewById(R.id.cardView_flight);

            timeTableText.setVisibility(View.GONE);
            cardView_timetable.setVisibility(View.GONE);
            SetVisibleChange();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_without_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure want to delete?");
                builder.setMessage("Confirm your decision.");

                builder.setPositiveButton("Yes, delete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteFlight(flight, FlightActivity.this.getWindow().getDecorView().getRootView()).execute("0");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FlightActivity.this, "Deletion canceled.", Toast.LENGTH_SHORT).show();
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

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayAdapter<Airplane> airlineArrayAdapter = new ArrayAdapter<Airplane>(this, android.R.layout.simple_spinner_item, db.getAirplanes());
        ArrayList<Airport> airports = db.getAirports();
        ArrayAdapter<Airport> airportFromArrayAdapter = new ArrayAdapter<Airport>(this, android.R.layout.simple_spinner_item, airports);
        ArrayAdapter<Airport> airportToArrayAdapter = new ArrayAdapter<Airport>(this, android.R.layout.simple_spinner_item, airports);
        ArrayAdapter<TimeTable> timeTableArrayAdapter = new ArrayAdapter<TimeTable>(this, android.R.layout.simple_spinner_item, db.getTimeTables());

        airplaneSpinner.setAdapter(airlineArrayAdapter);
        fromSpinner.setAdapter(airportFromArrayAdapter);
        toSpinner.setAdapter(airportToArrayAdapter);
        flightTimeSpinner.setAdapter(timeTableArrayAdapter);

        cardView = findViewById(R.id.layout_add_flight);
        addOrChangeText = findViewById(R.id.new_flight);
        addOrChangeText.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    public void AddChangeFlightClick(View view) {

        AddFlightModel f = new AddFlightModel();

        if (flight != null){
            f.setAirplaneID(flight.getFlightID());
        }

        f.setAirportFromAirportID(((Airport)fromSpinner.getSelectedItem()).getAirportID());
        f.setAirportToAirportID(((Airport)toSpinner.getSelectedItem()).getAirportID());
        f.setAirplaneID(((Airplane)airplaneSpinner.getSelectedItem()).getAirplaneID());
        f.setTimeTableID(((TimeTable)flightTimeSpinner.getSelectedItem()).getTimeTableID());
        f.setDepartureDate(departureEditText.getText().toString());
        f.setDestinationDate(destinationEditText.getText().toString());
        f.setPriceFlight(Double.valueOf(priceEditText.getText().toString()));
        new AddFlight(this.getWindow().getDecorView().getRootView()).execute(f);
    }
}
