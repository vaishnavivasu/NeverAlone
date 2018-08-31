package com.example.user.neveralone2;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EnterFeed extends AppCompatActivity {


    EditText feed_title;
    EditText feed_text;
    LocationManager locationManager;
    LocationListener listener;
    String lon;
    String ltt;
    String loc;
    DatabaseFeed databaseFeed;

    Feed feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_feed);


        feed_title = findViewById(R.id.feed_title);
        feed_text = findViewById(R.id.feed_text);
        feed = new Feed(feed_title.getText().toString(),feed_text.getText().toString());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lon = location.getLongitude() + "";
                ltt = location.getLatitude() + "";
                loc = getAddress(ltt,lon);
            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }


        };

        feed.setFeedLatitude(ltt);
        feed.setFeedLocation(loc);
        feed.setFeedLongitude(lon);
    }

    public String getAddress(String lat, String lng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lng), 1);
            Address obj = addresses.get(0);

            return obj.getPostalCode();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_feed,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_new_feed:
                databaseFeed = new DatabaseFeed(getApplicationContext());
                databaseFeed.addFeed(feed);
                break;
        }
        return true;
    }
}