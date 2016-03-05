package com.cs160.cadyxu.respublica;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    private ListView lvRep;
    private RepSumAdapter adapter;
    protected static ArrayList<RepSum> mRepList;
    private String zipString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        zipString = getIntent().getStringExtra("zipCode");
        mRepList = new ArrayList<RepSum>();
        if (zipString!=null && zipString.equals("94704")){
            mRepList.add(new RepSum(1,
                    R.drawable.dianne_feinstein,
                    "Sen. Dianne Feinstein",
                    "senator@feinstein.senate.gov",
                    "Vote for Dianne Feinstein!!!",
                    "Democrat", "Senator",
                    "http://www.feinstein.senate.gov"));
            mRepList.add(new RepSum(2,
                    R.drawable.barbara_lee,
                    "Rep. Barbara Lee",
                    "barbaralee@gmail.com",
                    "Vote for Barbara Lee!!!",
                    "Democrat",
                    "Representative",
                    "http://www.barbaralee.com"));
            mRepList.add(new RepSum(3,
                    R.drawable.barbara_boxer,
                    "Sen. Barbara Boxer",
                    "barbaraboxer@gmail.com",
                    "Vote For Barbara Boxer!!!",
                    "Democrat",
                    "Senator",
                    "http://www.boxer.senate.gov"));
        } else {
            mRepList.add(new RepSum(1,
                    R.drawable.barbara_boxer,
                    "Sen. Barbara Boxer",
                    "barbaraboxer@gmail.com",
                    "Vote For Barbara Boxer!!!",
                    "Democrat",
                    "Senator",
                    "http://www.boxer.senate.gov"));
            mRepList.add(new RepSum(2,
                    R.drawable.dianne_feinstein,
                    "Sen. Dianne Feinstein",
                    "senator@feinstein.senate.gov",
                    "Vote for Dianne Feinstein!!!",
                    "Democrat", "Senator",
                    "http://www.feinstein.senate.gov"));
            mRepList.add(new RepSum(3,
                    R.drawable.barbara_lee,
                    "Rep. Barbara Lee",
                    "barbaralee@gmail.com",
                    "Vote for Barbara Lee!!!",
                    "Democrat",
                    "Representative",
                    "http://www.barbaralee.com"));
        }


        /*Toast.makeText(this, zipCode, Toast.LENGTH_LONG).show();
        Notification notification = new Notification.Builder(this)
                .setContentTitle(zipCode)
                .build();
        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, notification); */
        lvRep = (ListView) findViewById(R.id.listview_reps);

        adapter = new RepSumAdapter(getApplicationContext(), mRepList);
        lvRep.setAdapter(adapter);

        lvRep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail = new Intent(getApplicationContext(), DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("repName", mRepList.get(position).getName());
                extras.putString("repImage", String.valueOf(mRepList.get(position).getImage()));
                detail.putExtras(extras);
                startActivity(detail);
            }
        });
    }

}
