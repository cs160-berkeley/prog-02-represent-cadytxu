package com.cs160.cadyxu.respublica;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button mViewRepBtn;
    private Button mViewTurnoutBtn;
    private String zipString;

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewRepBtn = (Button) findViewById(R.id.viewMyRepsButton);
        mViewTurnoutBtn = (Button) findViewById(R.id.viewTurnout);
        Intent intent = getIntent();
        zipString = intent.getStringExtra("zipString");


        mViewRepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), SummaryActivity.class);
                sendIntent.putExtra("zipString", zipString);
                startActivity(sendIntent);
            }
        });

        mViewTurnoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), TurnoutActivity.class);
                sendIntent.putExtra("zipString", zipString);
                startActivity(sendIntent);
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            @Override
            public void onShake() {
                Log.d("T", "DO This after shake!");
                Toast.makeText(getApplicationContext(), "Information Randomized!", Toast.LENGTH_SHORT).show();
                int random = (int )(Math.random() * 100000);
                zipString = Integer.toString(random);

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
