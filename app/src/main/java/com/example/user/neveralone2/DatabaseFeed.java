package com.example.user.neveralone2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFeed extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "never_alone.db";

    private static final String TABLE_FEED = "feed";

    private static final String COLUMN_FEED_ID = "feed_id";
    private static final String COLUMN_FEED_NAME = "feed_title";
    private static final String COLUMN_FEED_TEXT = "feed_text";
    private static String COLUMN_FEED_LOCATION = "feed_loc";
    private static final String COLUMN_FEED_LONGITUDE = "feed_lon";
    private static final String COLUMN_FEED_LATITUDE = "feed_lat";

    private String CREATE_FEED_TABLE = "CREATE TABLE " + TABLE_FEED + "("
            + COLUMN_FEED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FEED_NAME + " TEXT,"
            + COLUMN_FEED_TEXT + " TEXT," + COLUMN_FEED_LOCATION + " TEXT," + COLUMN_FEED_LONGITUDE
            + " TEXT," + COLUMN_FEED_LATITUDE + " TEXT" + ")";

    private String DROP_FEED_TABLE = "DROP TABLE IF EXISTS " + TABLE_FEED;

    public DatabaseFeed(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FEED_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_FEED_TABLE);

        // Create tables again
        onCreate(db);

    }


    public void addFeed(Feed feed) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FEED_NAME, feed.getFeedTitle());
        values.put(COLUMN_FEED_TEXT, feed.getFeedText());
        values.put(COLUMN_FEED_LOCATION, feed.getFeedLocation());
        values.put(COLUMN_FEED_LATITUDE, feed.getFeedLatitude());
        values.put(COLUMN_FEED_LONGITUDE, feed.getFeedLongitude());

        // Inserting Row
        db.insert(TABLE_FEED, null, values);
        db.close();
    }


    public List<Feed> getAllFeed(String loc) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_FEED_ID,
                COLUMN_FEED_NAME,
                COLUMN_FEED_TEXT,
                COLUMN_FEED_LOCATION,
                COLUMN_FEED_LATITUDE,
                COLUMN_FEED_LONGITUDE
        };
        // sorting orders
        String sortOrder =
                COLUMN_FEED_NAME + " ASC";
        List<Feed> feedList = new ArrayList<Feed>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FEED, //Table to query
                columns,    //columns to return
                COLUMN_FEED_LOCATION = loc,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Feed feed = new Feed((cursor.getString(cursor.getColumnIndex(COLUMN_FEED_NAME))),(cursor.getString(cursor.getColumnIndex(COLUMN_FEED_TEXT))));
                feed.setFeedLocation(cursor.getString(cursor.getColumnIndex(COLUMN_FEED_LOCATION)));
                // Adding user record to list
                feedList.add(feed);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return feedList;
    }

}
