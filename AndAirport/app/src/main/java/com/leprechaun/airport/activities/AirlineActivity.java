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
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.tasks.Airline.AddChangeAirline;
import com.leprechaun.airport.tasks.Airline.DeleteAirline;

public class AirlineActivity extends AppCompatActivity {

    Airline airline;
    TextView addOrChangeText;
    CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Bundle b = getIntent().getExtras();
        if (b != null){
            airline = (Airline) b.getSerializable(Airline.class.getSimpleName());
            if (airline != null){
                TextView t = findViewById(R.id.name);
                t.setText(airline.getAirlineFullName());
            }
        }
        else {
            TextView airlineText = findViewById(R.id.airlineText);
            CardView cardView_Name = findViewById(R.id.cardView_name);

            airlineText.setVisibility(View.GONE);
            cardView_Name.setVisibility(View.GONE);
            SetVisibleChange();
            addOrChangeText.setText("Add New Airline");
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
                addOrChangeText.setText("New Airline Name");
                break;
            case R.id.nav_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure want to delete?");
                builder.setMessage("Confirm your decision.");

                builder.setPositiveButton("Yes, delete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteAirline(airline, AirlineActivity.this.getWindow().getDecorView().getRootView()).execute("0");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AirlineActivity.this, "Deletion canceled.", Toast.LENGTH_SHORT).show();
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
        cardView = findViewById(R.id.layout_change);
        addOrChangeText = findViewById(R.id.new_or_change_name);

        addOrChangeText.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    public void AddChangeAirlineClick(View view) {

        Airline a = new Airline();
        EditText editTextName = findViewById(R.id.new_airlina_name);

        if (airline != null){
            a.setAirlineID(airline.getAirlineID());
            a.setAirlineFullName(editTextName.getText().toString());

        }
        else {
            a.setAirlineFullName(editTextName.getText().toString());
        }
        new AddChangeAirline(this.getWindow().getDecorView().getRootView()).execute(a);
    }
}
