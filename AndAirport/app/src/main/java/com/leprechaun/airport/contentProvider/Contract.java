package com.leprechaun.airport.contentProvider;

import android.content.ContentUris;
import android.net.Uri;

public class Contract {
    public final static String TABLE_AIRLINES = "arlines";
    public final static String TABLE_AIRPLANES = "airplanes";
    public final static String TABLE_USERS = "users";
    public final static String TABLE_AIRPORTS = "airports";
    public final static String TABLE_FLIGHTS = "flights";
    public final static String TABLE_TICKETS = "tickets";
    public final static String TABLE_TIMETABLES = "timetables";


    static final String CONTENT_AUTHORITY = "com.leprechaun.airport.provider";
    static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static final String CONTENT_TYPE_AIRLINA = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_AIRLINES;
    static final String CONTENT_ITEM_TYPE_AIRLINE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_AIRLINES;
    static final String CONTENT_TYPE_AIRPLANE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_AIRPLANES;
    static final String CONTENT_ITEM_TYPE_AIRPLANE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_AIRPLANES;
    static final String CONTENT_TYPE_USERS = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_USERS;
    static final String CONTENT_ITEM_TYPE_USERS= "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_USERS;

    final static String COLUMN_AIRLINE = "AirlineID";
    final static String COLUMN_AIRPLANE = "AirplaneID";
    final static String COLUMN_USERS = "UserId";


    private static final Uri CONTENT_URI_AIRLINE = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_AIRLINES);
    private static final Uri CONTENT_URI_AIRPLANE = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_AIRPLANES);
    private static final Uri CONTENT_URI_USERS = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_USERS);

    static Uri createUserUri(long idGroup){
        return ContentUris.withAppendedId(CONTENT_URI_USERS, idGroup);
    }

    static long getId(Uri uri){
        return ContentUris.parseId(uri);
    }

    static Uri createAirplaneUri(long idGroup){
        return ContentUris.withAppendedId(CONTENT_URI_AIRPLANE, idGroup);
    }

    static Uri createAirlineUri(long idGroup){
        return ContentUris.withAppendedId(CONTENT_URI_AIRLINE, idGroup);
    }
}
