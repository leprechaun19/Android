package com.leprechaun.airport.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.data.entities.Airline;

public class AirlineActivity extends AppCompatActivity {

    Airline airline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline);

        TextView t = findViewById(R.id.name);

        Bundle b = getIntent().getExtras();
        assert b != null;
        airline = (Airline) b.getSerializable(Airline.class.getSimpleName());
        if (airline != null){
            t.setText(airline.getAirlineFullName());
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

        if (id == R.id.nav_change) {
            LinearLayout l = findViewById(R.id.layout_change);

            EditText t = new EditText(AirlineActivity.this);
            t.setPadding(10,10,10,10);
            t.setHint("New Airline Name");
            t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            Button b = new Button(AirlineActivity.this);
            b.setText(AirlineActivity.this.getString(R.string.add_airline));
            b.setGravity(Gravity.CENTER);
            b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            l.addView(t);
            l.addView(b);
        }
        else if(id == R.id.nav_delete){

        }

        return super.onOptionsItemSelected(item);
    }
}
