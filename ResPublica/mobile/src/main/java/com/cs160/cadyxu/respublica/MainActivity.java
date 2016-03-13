package com.cs160.cadyxu.respublica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Tianyu_Xu";
    private static final String TWITTER_SECRET = "13573181548";

    private EditText zipText;
    private Button zipSubmitButton;
    private Button curLocSubmitButton;
    private String zipString;
    private TextView errorText;
    private int zipInt;
    private boolean zipValid = false, urlValid = true;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double mLongitude = 0;
    private double mLatitude = 0;
    private String API_KEY = "c662476b364441d1bc94e80bda802325";
    private String urlString;
    private String SUN_LOCATE_API_URL = "http://congress.api.sunlightfoundation.com/legislators/locate?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addApi(Wearable.API)  // used for data layer API
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }


        zipSubmitButton = (Button) findViewById(R.id.zipButton);
        curLocSubmitButton = (Button) findViewById(R.id.curLocButton);


        zipSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipText = (EditText) findViewById(R.id.zipEditText);
                zipString = zipText.getText().toString();
                errorText = (TextView) findViewById(R.id.errorTextView);

                try {
                    zipInt = Integer.parseInt(zipText.getText().toString());
                    if (zipInt >= 10000) {
                        zipValid = true;
                    } else {
                        errorText.setText("Your zip code should be 5-digit Long.");
                    }
                } catch (NumberFormatException nfe) {
                    errorText.setText("Your zip code is not a number.");
                }

                if (zipValid){
                    urlString = SUN_LOCATE_API_URL + "zip=" + zipString + "&apikey=" + API_KEY;

                    Intent toPhoneIntent = new Intent(getBaseContext(), SummaryActivity.class);
                    toPhoneIntent.putExtra("url", urlString);
                    startActivity(toPhoneIntent);

                    Intent toWatchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    toWatchIntent.putExtra("url", urlString);
                    startService(toWatchIntent);
                }
            }
        });

        curLocSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorText = (TextView) findViewById(R.id.errorTextView);

                zipString = "94704";
                mGoogleApiClient.connect();
                mLongitude = mLastLocation.getLongitude();
                mLatitude = mLastLocation.getLatitude();
                mGoogleApiClient.disconnect();

                errorText.setText("Long: " + Double.toString(mLongitude) + "  Lat: " + Double.toString(mLatitude));
                urlString = SUN_LOCATE_API_URL + "latitude=" + mLatitude + "&longitude=" + mLongitude + "&apikey=" + API_KEY;

                Intent toPhoneIntent = new Intent(getBaseContext(), SummaryActivity.class);
                toPhoneIntent.putExtra("url", urlString);
                startActivity(toPhoneIntent);

                Intent toWatchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                toWatchIntent.putExtra("url", urlString);
                startService(toWatchIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void zipOnClick(View v) {
        Button button = (Button) v;
        button.setText("Clicked");

    }

    public void curLocOnClick(View v) {
        Button button = (Button) v;
        button.setText("Clicked");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("MainActivity", "Location services connected.");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("MainActivity", "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
