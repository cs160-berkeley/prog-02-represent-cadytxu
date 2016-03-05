package com.cs160.cadyxu.respublica;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.widget.Toast;

public class SummaryActivity extends Activity {
    private RepSumWatch[][] repSumList = new RepSumWatch[1][3];
    private String zipString;
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        zipString = getIntent().getStringExtra("zipString");

        if (zipString!= null && zipString.equals("94704")){
            repSumList[0][0] = new RepSumWatch(1, R.drawable.dianne_feinstein, "Sen. Dianne Feinstein", "Democrat");
            repSumList[0][1] = new RepSumWatch(2, R.drawable.barbara_boxer, "Sen. Barbara Boxer", "Democrat");
            repSumList[0][2] = new RepSumWatch(3, R.drawable.barbara_lee, "Rep. Barbara Lee", "Democrat");
        } else {
            repSumList[0][1] = new RepSumWatch(1, R.drawable.dianne_feinstein, "Sen. Dianne Feinstein", "Democrat");
            repSumList[0][2] = new RepSumWatch(2, R.drawable.barbara_boxer, "Sen. Barbara Boxer", "Democrat");
            repSumList[0][0] = new RepSumWatch(3, R.drawable.barbara_lee, "Rep. Barbara Lee", "Democrat");
        }


        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new RepAdapter(this, getFragmentManager(), repSumList));
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
