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
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.Airplane;
import com.leprechaun.airport.tasks.Airplane.AddChangeAirplane;
import com.leprechaun.airport.tasks.Airplane.DeleteAirplane;

public class AirplaneActivity extends AppCompatActivity {

    Airplane airplane;
    TextView addOrChangeText;
    CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Bundle b = getIntent().getExtras();
        if (b != null){
            airplane = (Airplane) b.getSerializable(Airplane.class.getSimpleName());
            if (airplane != null){
                TextView type = findViewById(R.id.airplane_type_textview);
                TextView seats = findViewById(R.id.airplane_num_of_seats);
                TextView airline = findViewById(R.id.airplane_airline);

                type.setText(airplane.getAirplaneType());
                seats.setText(airplane.getNumPlaces());
                airline.setText(airplane.getAirline().getAirlineFullName());
            }
        }
        else {
            TextView airplaneText = findViewById(R.id.airplaneText);
            CardView cardViewAirplane = findViewById(R.id.cardView_airplane);

            airplaneText.setVisibility(View.GONE);
            cardViewAirplane.setVisibility(View.GONE);
            SetVisibleChange();
            addOrChangeText.setText("Add new airplane");
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
                SetDataToEditText(airplane);
                addOrChangeText.setText("Change airplane");
                break;
            case R.id.nav_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure want to delete?");
                builder.setMessage("Confirm your decision.");

                builder.setPositiveButton("Yes, delete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteAirplane(airplane, AirplaneActivity.this.getWindow().getDecorView().getRootView()).execute("0");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AirplaneActivity.this, "Deletion canceled.", Toast.LENGTH_SHORT).show();
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

        cardView = findViewById(R.id.layout_change_airplane);
        addOrChangeText = findViewById(R.id.new_or_change_name_airplane);
        addOrChangeText.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);

        Spinner airline = findViewById(R.id.new_airplane_airline);
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayAdapter<Airline> adapter = new ArrayAdapter<Airline>(this, android.R.layout.simple_spinner_item, db.getAirlines());
        airline.setAdapter(adapter);
    }

    private void SetDataToEditText(Airplane airplane){

        if(airplane != null) {
            EditText type = findViewById(R.id.new_airplane_type);
            EditText seats = findViewById(R.id.new_airplane_seats);

            type.setText(airplane.getAirplaneType());
            seats.setText(airplane.getNumPlaces());
        }
    }

    public void AddChangeAirplaneClick(View view) {
        Airplane a = new Airplane();
        EditText type = findViewById(R.id.new_airplane_type);
        EditText seats = findViewById(R.id.new_airplane_seats);
        Spinner airline = findViewById(R.id.new_airplane_airline);

        if (airplane != null){
            a.setAirplaneID(airplane.getAirplaneID());
        }

        a.setAirplaneType(type.getText().toString());
        a.setNumPlaces(seats.getText().toString());
        a.setAirlineID(((Airline)airline.getSelectedItem()).getAirlineID());
        new AddChangeAirplane(this.getWindow().getDecorView().getRootView()).execute(a);
    }
}
