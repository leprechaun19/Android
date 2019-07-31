package com.leprechaun.airport.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.Airplane;
import com.leprechaun.airport.data.entities.Airport;
import com.leprechaun.airport.data.entities.Flight;
import com.leprechaun.airport.data.entities.Ticket;
import com.leprechaun.airport.data.entities.TimeTable;
import com.leprechaun.airport.data.entities.User;

import java.util.ArrayList;

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
    private final static String COLUMN_FLIGHT_PLANE_TYPE = "AirplaneType";
    private final static String COLUMN_FLIGHT_PORT_FROM = "AirportFrom";
    private final static String COLUMN_FLIGHT_PORT_TO = "AirportTo";
    private final static String COLUMN_FLIGHT_PRICE_FLIGHT = "PriceFlight";
    private final static String COLUMN_FLIGHT_TIME = "FlightTime";

    private final static String COLUMN_TICKET_IDTICKET = "TicketID";
    private final static String COLUMN_TICKET_DEPARTURE_DATE = "DepartureDate";
    private final static String COLUMN_TICKET_PORT_FROM = "AirportFrom";
    private final static String COLUMN_TICKET_PORT_TO = "AirportTo";
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
                COLUMN_PLANE_TYPE + " TEXT );");

        db.execSQL("CREATE TABLE " + TABLE_AIRPORTS + " (" +
                COLUMN_PORT_IDAIRPORT + " TEXT PRIMARY KEY," +
                COLUMN_PORT_NAME + " TEXT," +
                COLUMN_PORT_COUNTRY + " TEXT," +
                COLUMN_PORT_CITY + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_FLIGHTS + " (" +
                COLUMN_FLIGHT_IDFLIGHT + " TEXT PRIMARY KEY, " +
                COLUMN_FLIGHT_DEPARTURE_DATE + " TEXT, " +
                COLUMN_FLIGHT_DESTINATION_DATE + " TEXT, " +
                COLUMN_FLIGHT_PLANE_TYPE + " TEXT, " +
                COLUMN_FLIGHT_PORT_FROM + " TEXT, " +
                COLUMN_FLIGHT_PORT_TO + " TEXT, " +
                COLUMN_FLIGHT_PRICE_FLIGHT + " TEXT, " +
                COLUMN_FLIGHT_TIME + "  TEXT );");

        db.execSQL("CREATE TABLE " + TABLE_TICKETS + " (" +
                COLUMN_TICKET_IDTICKET + " TEXT PRIMARY KEY," +
                COLUMN_TICKET_DEPARTURE_DATE + " TEXT," +
                COLUMN_TICKET_PORT_FROM + " TEXT," +
                COLUMN_TICKET_PORT_TO + " TEXT," +
                COLUMN_TICKET_IDUSER + " TEXT," +
                COLUMN_TICKET_SEAT + " TEXT," +
                COLUMN_TICKET_PRICE + " TEXT," +
                COLUMN_TICKET_ORDER_DATE + " TEXT );");

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
                COLUMN_TABLE_FRIDAY + " TEXT, " +
                COLUMN_TABLE_SATURDAY + " TEXT," +
                COLUMN_TABLE_SUNDAY + " TEXT);");

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

    public ArrayList<Airport> getAirports() {

        ArrayList<Airport> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_AIRPORTS, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndex(COLUMN_PORT_IDAIRPORT));
                String name = c.getString(c.getColumnIndex(COLUMN_PORT_NAME));
                String countryN = c.getString(c.getColumnIndex(COLUMN_PORT_COUNTRY));
                String cityN = c.getString(c.getColumnIndex(COLUMN_PORT_CITY));

                result.add(new Airport(id, name, countryN, cityN));
            }
            while (c.moveToNext());
        }

        c.close();

        return result;
    }

    public ArrayList<Airplane> getAirplanes() {

        ArrayList<Airplane> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_AIRPLANES, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndex(COLUMN_PLANE_IDAIRPLANE));
                String type = c.getString(c.getColumnIndex(COLUMN_PLANE_TYPE));
                String seats = c.getString(c.getColumnIndex(COLUMN_PLANE_NUMPLACES));
                String airlineId = c.getString(c.getColumnIndex(COLUMN_PLANE_IDAIRLINE));

                Cursor airlineCursor = db.query(TABLE_AIRLINES, new String[]{COLUMN_LINE_FULLNAME}, COLUMN_LINE_IDAIRLINE + " == ? ",
                        new String[]{airlineId}, null, null, null);
                if (airlineCursor.moveToFirst()) {
                    String name = airlineCursor.getString(airlineCursor.getColumnIndex(COLUMN_LINE_FULLNAME));
                    result.add(new Airplane(id, type, seats, airlineId, new Airline(airlineId, name)));
                }
            }
            while (c.moveToNext());
        }
        c.close();
        return result;
    }

    public ArrayList<User> getUsers() {

        ArrayList<User> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_USERS, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndex(COLUMN_USER_IDUSER));
                String phone = c.getString(c.getColumnIndex(COLUMN_USER_PHONE));
                String email = c.getString(c.getColumnIndex(COLUMN_USER_EMAIL));
                String address = c.getString(c.getColumnIndex(COLUMN_USER_ADDRESS));
                String createTime = c.getString(c.getColumnIndex(COLUMN_USER_CREATE_AT));
                String name = c.getString(c.getColumnIndex(COLUMN_USER_NAME));

                result.add(new User(id, name, address, phone, email, createTime));
            }
            while (c.moveToNext());
        }
        c.close();
        return result;
    }

    public ArrayList<TimeTable> getTimeTables() {

        ArrayList<TimeTable> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_TIMETABLES, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndex(COLUMN_TABLE_IDTIMETABLE));
                String flightTime = c.getString(c.getColumnIndex(COLUMN_TABLE_FLIGHT_TIME));
                String monday = c.getString(c.getColumnIndex(COLUMN_TABLE_MONDAY));
                String tuesday = c.getString(c.getColumnIndex(COLUMN_TABLE_THUESDAY));
                String wednesday = c.getString(c.getColumnIndex(COLUMN_TABLE_WEDNESDAY));
                String thursday = c.getString(c.getColumnIndex(COLUMN_TABLE_THURSDAY));
                String friday = c.getString(c.getColumnIndex(COLUMN_TABLE_FRIDAY));
                String saturday = c.getString(c.getColumnIndex(COLUMN_TABLE_SATURDAY));
                String sunday = c.getString(c.getColumnIndex(COLUMN_TABLE_SUNDAY));

                result.add(new TimeTable(id, flightTime, monday, tuesday, wednesday, thursday, friday, saturday, sunday));
            }
            while (c.moveToNext());
        }
        c.close();
        return result;
    }

    public ArrayList<Flight> getFlights() {

        ArrayList<Flight> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_FLIGHTS, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndex(COLUMN_FLIGHT_IDFLIGHT));
                String airplaneType = c.getString(c.getColumnIndex(COLUMN_FLIGHT_PLANE_TYPE));
                String airportFrom = c.getString(c.getColumnIndex(COLUMN_FLIGHT_PORT_FROM));
                String airportTo = c.getString(c.getColumnIndex(COLUMN_FLIGHT_PORT_TO));
                String departureDate = c.getString(c.getColumnIndex(COLUMN_FLIGHT_DEPARTURE_DATE));
                String destinationDate = c.getString(c.getColumnIndex(COLUMN_FLIGHT_DESTINATION_DATE));
                String flightTime = c.getString(c.getColumnIndex(COLUMN_FLIGHT_TIME));
                double price = Double.valueOf(c.getString(c.getColumnIndex(COLUMN_FLIGHT_PRICE_FLIGHT)));

                result.add(new Flight(id, departureDate, destinationDate, airplaneType, airportFrom, airportTo, flightTime, price));
            }
            while (c.moveToNext());
        }
        c.close();
        return result;
    }

    public ArrayList<Ticket> getTickets() {

        ArrayList<Ticket> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_TICKETS, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndex(COLUMN_TICKET_IDTICKET));
                String departureDate = c.getString(c.getColumnIndex(COLUMN_TICKET_DEPARTURE_DATE));
                String airportFrom = c.getString(c.getColumnIndex(COLUMN_TICKET_PORT_FROM));
                String airportTo = c.getString(c.getColumnIndex(COLUMN_TICKET_PORT_TO));
                String idUser = c.getString(c.getColumnIndex(COLUMN_TICKET_IDUSER));
                int seat = Integer.valueOf(c.getString(c.getColumnIndex(COLUMN_TICKET_SEAT)));
                double price = Double.valueOf(c.getString(c.getColumnIndex(COLUMN_TICKET_PRICE)));
                String orderDate = c.getString(c.getColumnIndex(COLUMN_TICKET_ORDER_DATE));

                Cursor userCursor = db.query(TABLE_USERS, new String[]{COLUMN_USER_NAME }, COLUMN_USER_IDUSER + " == ? ",
                        new String[]{idUser}, null, null, null);
                if (userCursor.moveToFirst()) {
                    String name = userCursor.getString(userCursor.getColumnIndex(COLUMN_USER_NAME));
                    result.add(new Ticket(id, departureDate, airportFrom, airportTo, new User(idUser, name), seat, price, orderDate));
                }
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

    public void deleteAllAirports() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AIRPORTS, null, null);

    }

    public void deleteAllAirplanes() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AIRPLANES, null, null);

    }

    public void deleteAllUsers() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, null, null);

    }

    public void deleteAllTimeTables() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TIMETABLES, null, null);

    }

    public void deleteAllFlights() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FLIGHTS, null, null);

    }

    public void deleteAllTickets() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TICKETS, null, null);

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

    private void addAirport(Airport item) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PORT_IDAIRPORT, item.getAirportID());
            values.put(COLUMN_PORT_NAME, item.getAirportName());
            values.put(COLUMN_PORT_COUNTRY, item.getCountryName());
            values.put(COLUMN_PORT_CITY, item.getCityName());

            db.insert(TABLE_AIRPORTS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAirplane(Airplane item) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PLANE_IDAIRPLANE, item.getAirplaneID());
            values.put(COLUMN_PLANE_TYPE, item.getAirplaneType());
            values.put(COLUMN_PLANE_NUMPLACES, item.getNumPlaces());
            values.put(COLUMN_PLANE_IDAIRLINE, item.getAirlineID());

            db.insert(TABLE_AIRPLANES, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addUser(User item) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_IDUSER, item.getUserId());
            values.put(COLUMN_USER_NAME, item.getUserName());
            values.put(COLUMN_USER_EMAIL, item.getEmail());
            values.put(COLUMN_USER_PHONE, item.getPhone());
            values.put(COLUMN_USER_ADDRESS, item.getAddress());
            values.put(COLUMN_USER_CREATE_AT, item.getCreateAt());

            db.insert(TABLE_USERS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTimeTable(TimeTable item) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {


            ContentValues values = new ContentValues();
            values.put(COLUMN_TABLE_IDTIMETABLE, item.getTimeTableID());
            values.put(COLUMN_TABLE_FLIGHT_TIME, String.valueOf(item.getFlightTime()));
            values.put(COLUMN_TABLE_MONDAY, item.getMondayTimeTable());
            values.put(COLUMN_TABLE_THUESDAY, item.getTuesdayTimeTable());
            values.put(COLUMN_TABLE_WEDNESDAY, item.getWednesdayTimeTable());
            values.put(COLUMN_TABLE_THURSDAY, item.getThursdayTimeTable());
            values.put(COLUMN_TABLE_FRIDAY, item.getFridayTimeTable());
            values.put(COLUMN_TABLE_SATURDAY, item.getSaturdayTimeTable());
            values.put(COLUMN_TABLE_SUNDAY, item.getSundayTimeTable());

            db.insert(TABLE_TIMETABLES, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFlight(Flight flight) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FLIGHT_IDFLIGHT, flight.getFlightID());
            values.put(COLUMN_FLIGHT_DEPARTURE_DATE, flight.getDepartureDate());
            values.put(COLUMN_FLIGHT_DESTINATION_DATE, flight.getDestinationDate());
            values.put(COLUMN_FLIGHT_PLANE_TYPE, flight.getAirplaneType());
            values.put(COLUMN_FLIGHT_PORT_FROM, flight.getAirportFrom());
            values.put(COLUMN_FLIGHT_PORT_TO, flight.getAirportTo());
            values.put(COLUMN_FLIGHT_TIME, flight.getFlightTime());
            values.put(COLUMN_FLIGHT_PRICE_FLIGHT, String.valueOf(flight.getPriceFlight()));

            db.insert(TABLE_FLIGHTS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTicket(Ticket ticket) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TICKET_IDTICKET, ticket.getTicketID());
            values.put(COLUMN_TICKET_DEPARTURE_DATE, ticket.getDepartureDate());
            values.put(COLUMN_TICKET_PORT_FROM, ticket.getAirportFrom());
            values.put(COLUMN_TICKET_PORT_TO, ticket.getAirportTo());
            values.put(COLUMN_TICKET_IDUSER, ticket.getUser().getUserId());
            values.put(COLUMN_TICKET_SEAT, ticket.getSeat());
            values.put(COLUMN_TICKET_PRICE, String.valueOf(ticket.getPrice()));
            values.put(COLUMN_TICKET_ORDER_DATE, ticket.getOrderDate());

            db.insert(TABLE_TICKETS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAirlines(ArrayList<Airline> items) {
        for (Airline item : items) {
            addAirline(item);
        }
    }

    public void addAirports(ArrayList<Airport> items) {
        for (Airport item : items) {
            addAirport(item);
        }
    }

    public void addAirplanes(ArrayList<Airplane> items) {
        for (Airplane item : items) {
            addAirplane(item);
        }
    }

    public void addUsers(ArrayList<User> items) {
        for (User item : items) {
            addUser(item);
        }
    }

    public void addTimeTables(ArrayList<TimeTable> items) {
        for (TimeTable item : items) {
            addTimeTable(item);
        }
    }

    public void addFlights(ArrayList<Flight> items) {
        for (Flight item : items) {
            addFlight(item);
        }
    }

    public void addTickets(ArrayList<Ticket> items) {
        for (Ticket item : items) {
            addTicket(item);
        }
    }
}
