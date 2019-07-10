package com.leprechaun.airport.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DatabaseProvider extends ContentProvider {

    private DatabaseHandler db;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final int AIRLINE = 100;
    public static final int AIRLINE_IDAIRLINE = 101;
    public static final int AIRPLANE = 102;
    public static final int AIRPLANE_IDAIRPLANE = 103;
    public static final int USERS = 104;
    public static final int USERS_IDUSER = 105;

    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.TABLE_AIRLINES, AIRLINE);
        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.TABLE_AIRLINES + "/#", AIRLINE_IDAIRLINE);
        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.TABLE_AIRLINES, AIRPLANE);
        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.TABLE_AIRLINES + "/#", AIRPLANE_IDAIRPLANE);
        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.TABLE_USERS, USERS);
        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.TABLE_USERS + "/#", USERS_IDUSER);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        db = new DatabaseHandler(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String uriA = "";

        switch (match) {
            case AIRLINE:
                queryBuilder.setTables(Contract.TABLE_AIRLINES);
                uriA = Contract.COLUMN_AIRLINE;
                break;
            case AIRLINE_IDAIRLINE:
                queryBuilder.setTables(Contract.TABLE_AIRLINES);
                long idGroup = Contract.getId(uri);
                queryBuilder.appendWhere(Contract.COLUMN_AIRLINE + " = " + idGroup);
                uriA = Contract.COLUMN_AIRLINE;
                break;
            case AIRPLANE:
                queryBuilder.setTables(Contract.TABLE_AIRPLANES);
                uriA = Contract.COLUMN_AIRPLANE;
                break;
            case AIRPLANE_IDAIRPLANE:
                queryBuilder.setTables(Contract.TABLE_AIRPLANES);
                long taskId = Contract.getId(uri);
                queryBuilder.appendWhere(Contract.COLUMN_AIRPLANE+ " = " + taskId);
                uriA = Contract.COLUMN_AIRPLANE;
                break;
            case USERS:
                queryBuilder.setTables(Contract.TABLE_USERS);
                uriA = Contract.COLUMN_USERS;
                break;
            case USERS_IDUSER:
                queryBuilder.setTables(Contract.TABLE_USERS);
                long userId = Contract.getId(uri);
                queryBuilder.appendWhere(Contract.COLUMN_USERS+ " = " + userId);
                uriA = Contract.COLUMN_USERS;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        SQLiteDatabase dbS = db.getReadableDatabase();

        Cursor c = queryBuilder.query(dbS, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), Uri.parse(uriA));
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case AIRLINE:
                return Contract.CONTENT_TYPE_AIRLINA;
            case AIRLINE_IDAIRLINE:
                return Contract.CONTENT_ITEM_TYPE_AIRLINE;
            case AIRPLANE:
                return Contract.CONTENT_TYPE_AIRPLANE;
            case AIRPLANE_IDAIRPLANE:
                return Contract.CONTENT_ITEM_TYPE_AIRPLANE;
            case USERS:
                return Contract.CONTENT_TYPE_USERS;
            case USERS_IDUSER:
                return Contract.CONTENT_ITEM_TYPE_USERS;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        SQLiteDatabase dbS = db.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db;
        Uri returnUri;
        long recordId;

        switch (match) {
            case AIRLINE:
                recordId = dbS.insert(Contract.TABLE_AIRLINES, null, values);
                if (recordId > 0) {
                    returnUri = Contract.createAirlineUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert: " + uri.toString());
                }
                getContext().getContentResolver().notifyChange(Uri.parse(Contract.COLUMN_AIRLINE), null);
                break;
            case AIRPLANE:
                recordId = dbS.insert(Contract.TABLE_AIRPLANES, null, values);
                if (recordId > 0) {
                    returnUri = Contract.createAirplaneUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert: " + uri.toString());
                }
                getContext().getContentResolver().notifyChange(Uri.parse(Contract.COLUMN_AIRPLANE), null);
                break;
            case USERS:
                recordId = dbS.insert(Contract.TABLE_USERS, null, values);
                if (recordId > 0) {
                    returnUri = Contract.createUserUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert: " + uri.toString());
                }
                getContext().getContentResolver().notifyChange(Uri.parse(Contract.COLUMN_USERS ), null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase dbS = db.getWritableDatabase();

        if (match != AIRLINE && match != AIRLINE_IDAIRLINE && match != AIRPLANE && match != AIRPLANE_IDAIRPLANE
                && match != USERS && match != USERS_IDUSER)
            throw new IllegalArgumentException("Unknown URI: " + uri);

        if (match == AIRPLANE) {
            return dbS.delete(Contract.TABLE_AIRPLANES, selection, selectionArgs);
        }

        if (match == AIRLINE) {
            return dbS.delete(Contract.TABLE_AIRLINES, selection, selectionArgs);
        }

        if (match == USERS) {
            return dbS.delete(Contract.TABLE_USERS, selection, selectionArgs);
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase dbS = db.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        String selectionCriteria = selection;

        if (match != AIRLINE && match != AIRLINE_IDAIRLINE && match != AIRPLANE && match != AIRPLANE_IDAIRPLANE
                && match != USERS && match != USERS_IDUSER)
            throw new IllegalArgumentException("Unknown URI: " + uri);

        if (match == AIRLINE_IDAIRLINE) {
            long taskId = Contract.getId(uri);
            selectionCriteria = Contract.COLUMN_AIRLINE + " = " + taskId + " ";
            if ((selection != null) && (selection.length() > 0)) {
                selectionCriteria += " AND (" + selection + ")";
            }
            return dbS.update(Contract.TABLE_AIRLINES, values, selectionCriteria, selectionArgs);
        }

        if (match == AIRPLANE_IDAIRPLANE) {
            long taskId = Contract.getId(uri);
            selectionCriteria = Contract.COLUMN_AIRPLANE + " = " + taskId + " ";
            if ((selection != null) && (selection.length() > 0)) {
                selectionCriteria += " AND (" + selection + ")";
            }
            return dbS.update(Contract.TABLE_AIRPLANES, values, selectionCriteria, selectionArgs);
        }

        if (match == USERS_IDUSER) {
            long taskId = Contract.getId(uri);
            selectionCriteria = Contract.COLUMN_USERS + " = " + taskId + " ";
            if ((selection != null) && (selection.length() > 0)) {
                selectionCriteria += " AND (" + selection + ")";
            }
            return dbS.update(Contract.TABLE_USERS, values, selectionCriteria, selectionArgs);
        }

        return 0;
    }
}
