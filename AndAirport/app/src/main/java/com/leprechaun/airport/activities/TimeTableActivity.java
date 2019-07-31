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
import com.leprechaun.airport.data.entities.TimeTable;
import com.leprechaun.airport.tasks.TimeTable.AddChangeTimeTable;
import com.leprechaun.airport.tasks.TimeTable.DeleteTimeTable;

public class TimeTableActivity extends AppCompatActivity {

    TimeTable timeTable;
    TextView addOrChangeText;
    CardView cardView;

    EditText flightTimeEditText;
    EditText mondayEditText;
    EditText tuesdayEditText;
    EditText wednesdayEditText;
    EditText thursdayEditText;
    EditText fridayEditText;
    EditText saturdayEditText;
    EditText sundayEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Bundle b = getIntent().getExtras();
        if (b != null){
            timeTable = (TimeTable) b.getSerializable(TimeTable.class.getSimpleName());
            if (timeTable != null){
                TextView flightTime = findViewById(R.id.timetable_flight_textview);
                TextView time = findViewById(R.id.timetable_raspis);

                flightTime.setText(String.valueOf(timeTable.getFlightTime()));
                time.setText(timeTable.getTimeTableString());
            }
        }
        else {
            TextView timeTableText = findViewById(R.id.timetableText);
            CardView cardView_timetable = findViewById(R.id.cardView_timetable);

            timeTableText.setVisibility(View.GONE);
            cardView_timetable.setVisibility(View.GONE);
            SetVisibleChange();
            addOrChangeText.setText("Add new timetable");
        }

        flightTimeEditText = findViewById(R.id.new_timetable_flight);
        mondayEditText = findViewById(R.id.new_timetable_monday);
        tuesdayEditText = findViewById(R.id.new_timetable_tuesday);
        wednesdayEditText = findViewById(R.id.new_timetable_wednesday);
        thursdayEditText = findViewById(R.id.new_timetable_thursday);
        fridayEditText = findViewById(R.id.new_timetable_friday);
        saturdayEditText = findViewById(R.id.new_timetable_saturday);
        sundayEditText = findViewById(R.id.new_timetable_sunday);
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
                SetDataToEditText(timeTable);
                addOrChangeText.setText("New timetable");
                break;
            case R.id.nav_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure want to delete?");
                builder.setMessage("Confirm your decision.");

                builder.setPositiveButton("Yes, delete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteTimeTable(timeTable, TimeTableActivity.this.getWindow().getDecorView().getRootView()).execute("0");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TimeTableActivity.this, "Deletion canceled.", Toast.LENGTH_SHORT).show();
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

        cardView = findViewById(R.id.layout_change_timatable);
        addOrChangeText = findViewById(R.id.new_or_change_timetable);

        addOrChangeText.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    private void SetDataToEditText(TimeTable timeTable){

        if(timeTable != null) {
            flightTimeEditText.setText(String.valueOf(timeTable.getFlightTime()));
            mondayEditText.setText(timeTable.getMondayTimeTable());
            tuesdayEditText.setText(timeTable.getTuesdayTimeTable());
            wednesdayEditText.setText(timeTable.getWednesdayTimeTable());
            thursdayEditText.setText(timeTable.getThursdayTimeTable());
            fridayEditText.setText(timeTable.getFridayTimeTable());
            saturdayEditText.setText(timeTable.getSaturdayTimeTable());
            sundayEditText.setText(timeTable.getSundayTimeTable());
        }
    }

    public void AddChangeTimeTableClick(View view) {

        TimeTable t = new TimeTable();

        if (timeTable != null){
            t.setTimeTableID(timeTable.getTimeTableID());
        }

        t.setFlightTime(flightTimeEditText.getText().toString());
        t.setMondayTimeTable(mondayEditText.getText().toString());
        t.setTuesdayTimeTable(tuesdayEditText.getText().toString());
        t.setWednesdayTimeTable(wednesdayEditText.getText().toString());
        t.setThursdayTimeTable(thursdayEditText.getText().toString());
        t.setFridayTimeTable(fridayEditText.getText().toString());
        t.setSaturdayTimeTable(saturdayEditText.getText().toString());
        t.setSundayTimeTable(sundayEditText.getText().toString());
        new AddChangeTimeTable(this.getWindow().getDecorView().getRootView()).execute(t);
    }
}
