package com.example.user.neveralone2;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewFeed extends AppCompatActivity {

    String lon;
    String ltt;
    String loc;
    LocationManager locationManager;
    LocationListener listener;
    ListView nFeedList;
    DatabaseFeed databaseFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lon = location.getLongitude() + "";
                ltt = location.getLatitude() + "";
                loc = getAddress(ltt,lon);
            }

            private String getAddress(String ltt, String lon) {
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(ltt), Double.parseDouble(lon), 1);
                    Address obj = addresses.get(0);

                    return obj.getPostalCode();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return "";
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_feed,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_new_feed:
                Intent intent = new Intent(getApplicationContext(),EnterFeed.class);
                startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseFeed = new DatabaseFeed(getApplicationContext());
        nFeedList.setAdapter(null);
        ArrayList<Feed> notes = (ArrayList<Feed>) databaseFeed.getAllFeed(loc);

        if(notes == null || notes.size()==0){
            Toast.makeText(getApplicationContext(),"No feeds to show",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            FeedAdaptor na = new FeedAdaptor(this, R.id.feed_item, notes);
            nFeedList.setAdapter(na);

        }
    }
}
