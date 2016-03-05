package com.cs160.cadyxu.respublica;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TurnoutActivity extends Activity {

    private TextView mObamaText;
    private TextView mRomneyText;
    private String mObamaDisplay;
    private String mRomneyDisplay;
    private String zipString;
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnout);
        zipString = getIntent().getStringExtra("zipString");

        if (zipString != null && zipString.equals("94704")){
            mObamaDisplay = "78.9%";
            mRomneyDisplay = "18.2%";
        } else {
            mObamaDisplay = "45.3%";
            mRomneyDisplay = "53.6%";
        }
        mObamaText = (TextView) findViewById(R.id.obamaVotesTextView);
        mRomneyText = (TextView) findViewById(R.id.romneyVotesTextView);
        mObamaText.setText(mObamaDisplay);
        mRomneyText.setText(mRomneyDisplay);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                Log.d("T", "DO This after shake!");
                Toast.makeText(getApplicationContext(), "Information Randomized!", Toast.LENGTH_SHORT).show();
                int random = (int )(Math.random() * 100000);
                zipString = Integer.toString(random);

                Intent sendIntent = new Intent(getBaseContext(), MainActivity.class);
                sendIntent.putExtra("zipString", zipString);
                startActivity(sendIntent);

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

}
