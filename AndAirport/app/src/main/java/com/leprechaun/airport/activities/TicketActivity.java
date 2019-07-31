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
import com.leprechaun.airport.data.entities.AddTicketModel;
import com.leprechaun.airport.data.entities.Flight;
import com.leprechaun.airport.data.entities.Ticket;
import com.leprechaun.airport.data.entities.User;
import com.leprechaun.airport.tasks.Ticket.AddChangeTicket;
import com.leprechaun.airport.tasks.Ticket.DeleteTicket;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TicketActivity extends AppCompatActivity {

    Ticket ticket;
    TextView addOrChangeText;
    CardView cardView;

    Spinner flightSpinner;
    Spinner userSpinner;
    EditText seatEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        flightSpinner = findViewById(R.id.new_ticket_flight);
        userSpinner = findViewById(R.id.new_ticket_user);
        seatEditText = findViewById(R.id.new_ticket_seat);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            ticket = (Ticket) b.getSerializable(Ticket.class.getSimpleName());
            if (ticket != null) {
                TextView fromTo = findViewById(R.id.ticket_from_to_textView);
                TextView user = findViewById(R.id.ticket_user_name);
                TextView seat = findViewById(R.id.ticket_seat_textView);
                TextView price = findViewById(R.id.ticket_price_textView);
                TextView orderdate = findViewById(R.id.ticket_order_date_textView);
                TextView depDate = findViewById(R.id.ticket_departure_textview);

                user.setText(ticket.getUser().getUserName());
                depDate.setText(String.valueOf(ticket.getDepartureDate()));
                fromTo.setText(getFromToTitle(ticket.getAirportFrom(), ticket.getAirportTo()));
                orderdate.setText(String.valueOf(ticket.getOrderDate()));
                price.setText(String.valueOf(ticket.getPrice()));
                seat.setText(String.valueOf(ticket.getSeat()));
            }
        } else {
            TextView timeTableText = findViewById(R.id.ticketText);
            CardView cardView_timetable = findViewById(R.id.cardView_ticket);

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
                        new DeleteTicket(ticket, TicketActivity.this.getWindow().getDecorView().getRootView()).execute("0");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TicketActivity.this, "Deletion canceled.", Toast.LENGTH_SHORT).show();
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

    private void SetVisibleChange() {

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayAdapter<Flight> flightArrayAdapter = new ArrayAdapter<Flight>(this, android.R.layout.simple_spinner_item, db.getFlights());
        ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, db.getUsers());

        flightSpinner.setAdapter(flightArrayAdapter);
        userSpinner.setAdapter(userArrayAdapter);

        cardView = findViewById(R.id.layout_change_ticket);
        addOrChangeText = findViewById(R.id.new_or_change_ticket);
        addOrChangeText.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    public void AddChangeTicketClick(View view) {

        AddTicketModel t = new AddTicketModel();

        if (ticket != null) {
            t.setTicketID(ticket.getTicketID());
        }

        Flight f = (Flight) flightSpinner.getSelectedItem();
        t.setFlightFligthID(f.getFlightID());
        t.setUserId(((User) userSpinner.getSelectedItem()).getUserId());
        t.setOrderDate(getCurrentDate());
        t.setPrice(f.getPriceFlight()*2);
        t.setSeat(Integer.valueOf(seatEditText.getText().toString()));
        new AddChangeTicket(this.getWindow().getDecorView().getRootView()).execute(t);
    }

    private String getFromToTitle(String from, String to) {

        int start = from.indexOf(',') + 1;
        int end = from.indexOf(')');
        String cityFrom = from.substring(start, end);

        int start1 = to.indexOf(',') + 1;
        int end1 = to.indexOf(')');
        String cityTo = to.substring(start1, end1);

        return cityFrom + " -" + cityTo;
    }

    private static String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }
}
