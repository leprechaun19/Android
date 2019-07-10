package com.leprechaun.airport.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.fragments.AirlineFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static Integer DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "aspnetAirport.db";

    public final static String TABLE_AIRLINES = "arlines";
    public final static String TABLE_AIRPLANES = "airplanes";
    private final static String TABLE_USERS = "users";
    private final static String TABLE_AIRPORTS = "airports";
    private final static String TABLE_FLIGHTS = "flights";
    private final static String TABLE_TICKETS = "tickets";
    private final static String TABLE_TIMETABLES = "timetables";

    private final static String COLUMN_LINE_IDAIRLINE = "AirlineID";
    private final static String COLUMN_LINE_FULLNAME = "AirlineFullName";

    public final static String COLUMN_PLANE_IDAIRPLANE = "AirplaneID";
    public final static String COLUMN_PLANE_TYPE = "AirplaneType";
    public final static String COLUMN_PLANE_NUMPLACES = "NumPlaces";
    public final static String COLUMN_PLANE_IDAIRLINE = "AirlineID";

    public final static String COLUMN_PORT_IDAIRPORT = "AirportID";
    public final static String COLUMN_PORT_NAME = "AirportName";
    public final static String COLUMN_PORT_COUNTRY = "CountryName";
    public final static String COLUMN_PORT_CITY = "CityName";

    private final static String COLUMN_FLIGHT_IDFLIGHT = "FligthID";
    private final static String COLUMN_FLIGHT_DEPARTURE_DATE = "DepartureDate";
    private final static String COLUMN_FLIGHT_DESTINATION_DATE = "DestinationDate";
    private final static String COLUMN_FLIGHT_PLANEID = "AirplaneID";
    private final static String COLUMN_FLIGHT_PORT_FROM_ID = "AirportFromID";
    private final static String COLUMN_FLIGHT_PORT_TO = "AirportTo";
    private final static String COLUMN_FLIGHT_TIMETABLE = "TimeTableID";

    private final static String COLUMN_TICKET_IDTICKET = "TicketID";
    private final static String COLUMN_TICKET_IDFLIGHT = "FlightID";
    private final static String COLUMN_TICKET_IDUSER = "UserId";
    private final static String COLUMN_TICKET_SEAT = "Seat";
    private final static String COLUMN_TICKET_PRICE = "Price";
    private final static String COLUMN_TICKET_ORDER_DATE = "OrderDate";

    private final static String COLUMN_USER_IDUSER = "UserId";
    private final static String COLUMN_USER_NAME = "UserName";
    private final static String COLUMN_USER_ADDRESS = "Address";
    private final static String COLUMN_USER_PHONE = "Phone";
    private final static String COLUMN_USER_EMAIL = "Email";
    private final static String COLUMN_USER_CREATE_AT = "CreateAt";
    private final static String COLUMN_USER_APPLICATION_USER_ID = "applicationUserId";

    private final static String COLUMN_TABLE_IDTIMETABLE = "TimeTableID";
    private final static String COLUMN_TABLE_FLIGHT_TIME = "FlightTime";
    private final static String COLUMN_TABLE_MONDAY = "MondayTimeTable";
    private final static String COLUMN_TABLE_THUESDAY = "TuesdayTimeTable";
    private final static String COLUMN_TABLE_WEDNESDAY = "WednesdayTimeTable";
    private final static String COLUMN_TABLE_THURSDAY = "ThursdayTimeTable";
    private final static String COLUMN_TABLE_FRIDAY = "FridayTimeTable";
    private final static String COLUMN_TABLE_SATURDAY = "SaturdayTimeTable";
    private final static String COLUMN_TABLE_SUNDAY = "SundayTimeTable";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_AIRLINES + " (" +
                COLUMN_LINE_IDAIRLINE + " TEXT PRIMARY KEY," +
                COLUMN_LINE_FULLNAME + " TEXT );");

        db.execSQL("CREATE TABLE " + TABLE_AIRPLANES + " (" +
                COLUMN_PLANE_IDAIRPLANE + " TEXT PRIMARY KEY," +
                COLUMN_PLANE_IDAIRLINE + " TEXT," +
                COLUMN_PLANE_NUMPLACES + " TEXT," +
                COLUMN_PLANE_TYPE + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_PLANE_IDAIRLINE + ") REFERENCES " + TABLE_AIRLINES + "(" + COLUMN_LINE_IDAIRLINE + "));");

        db.execSQL("CREATE TABLE " + TABLE_AIRPORTS + " (" +
                COLUMN_PORT_IDAIRPORT + " TEXT PRIMARY KEY," +
                COLUMN_PORT_NAME + " TEXT," +
                COLUMN_PORT_COUNTRY + " TEXT," +
                COLUMN_PORT_CITY + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_FLIGHTS + " (" +
                COLUMN_FLIGHT_IDFLIGHT + " TEXT PRIMARY KEY, " +
                COLUMN_FLIGHT_DEPARTURE_DATE + " TEXT, " +
                COLUMN_FLIGHT_DESTINATION_DATE + " TEXT, " +
                COLUMN_FLIGHT_PLANEID + " TEXT, " +
                COLUMN_FLIGHT_TIMETABLE + " TEXT, " +
                COLUMN_FLIGHT_PORT_TO + " TEXT, " +
                COLUMN_FLIGHT_PORT_FROM_ID + "  TEXT, " +
                "FOREIGN KEY(" + COLUMN_FLIGHT_PLANEID + ") REFERENCES " + TABLE_AIRPLANES + "(" + COLUMN_PLANE_IDAIRPLANE + ")," +
                "FOREIGN KEY(" + COLUMN_FLIGHT_TIMETABLE + ") REFERENCES " + TABLE_TIMETABLES + "(" + COLUMN_TABLE_IDTIMETABLE + ")," +
                "FOREIGN KEY(" + COLUMN_FLIGHT_PORT_FROM_ID + ") REFERENCES " + TABLE_AIRPORTS + "(" + COLUMN_PORT_IDAIRPORT + "));");

        db.execSQL("CREATE TABLE " + TABLE_TICKETS + " (" +
                COLUMN_TICKET_IDTICKET + " TEXT PRIMARY KEY," +
                COLUMN_TICKET_IDFLIGHT + " TEXT," +
                COLUMN_TICKET_IDUSER + " TEXT," +
                COLUMN_TICKET_SEAT + " TEXT," +
                COLUMN_TICKET_PRICE + " TEXT," +
                COLUMN_TICKET_ORDER_DATE + " TEXT," +
                "FOREIGN KEY(" + COLUMN_TICKET_IDUSER + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_IDUSER + "));");

        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_IDUSER + " TEXT PRIMARY KEY," +
                COLUMN_USER_NAME + " TEXT," +
                COLUMN_USER_ADDRESS + " TEXT," +
                COLUMN_USER_PHONE + " TEXT," +
                COLUMN_USER_EMAIL + " TEXT," +
                COLUMN_USER_CREATE_AT + " TEXT," +
                COLUMN_USER_APPLICATION_USER_ID + "TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_TIMETABLES + " (" +
                COLUMN_TABLE_IDTIMETABLE + " TEXT PRIMARY KEY," +
                COLUMN_TABLE_FLIGHT_TIME + " TEXT," +
                COLUMN_TABLE_MONDAY + " TEXT," +
                COLUMN_TABLE_THUESDAY + " TEXT," +
                COLUMN_TABLE_WEDNESDAY + " TEXT," +
                COLUMN_TABLE_THURSDAY + " TEXT," +
                COLUMN_TABLE_FRIDAY + "TEXT, " +
                COLUMN_TABLE_SATURDAY + " TEXT," +
                COLUMN_TABLE_SUNDAY + "TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKETS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIRPORTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIRPLANES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIRLINES);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL(" PRAGMA foreign_keys = ON ");
    }

    public ArrayList<Airline> getAirlines() {

        ArrayList<Airline> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_AIRLINES, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndex(COLUMN_LINE_IDAIRLINE));
                String name = c.getString(c.getColumnIndex(COLUMN_LINE_FULLNAME));

                result.add(new Airline(id, name));
            }
            while (c.moveToNext());
        }

        c.close();

        return result;
    }

    public void deleteAllAirlines() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AIRLINES, null, null);

    }

    private void addAirline(Airline item) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_LINE_IDAIRLINE, item.getAirlineID());
            values.put(COLUMN_LINE_FULLNAME, item.getAirlineFullName());

            db.insert(TABLE_AIRLINES, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAirlines(ArrayList<Airline> items) {
        for (Airline item : items) {
            addAirline(item);
        }
    }


}
