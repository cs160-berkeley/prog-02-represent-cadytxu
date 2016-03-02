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
    protected static List<RepSum> mRepList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        String zipCode = intent.getIntExtra("zipText", 0) + "";
        Toast.makeText(this, zipCode, Toast.LENGTH_LONG).show();
        Notification notification = new Notification.Builder(this)
                .setContentTitle(zipCode)
                .build();
        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, notification);
        lvRep = (ListView) findViewById(R.id.listview_reps);
        mRepList = new ArrayList<>();
        mRepList.add(new RepSum(1,
                R.drawable.dianne_feinstein,
                "Dianne Feinstein",
                "senator@feinstein.senate.gov",
                "Vote for Dianne Feinstein!!!",
                "Democrat","Senator",
                "http://www.feinstein.senate.gov"));
        mRepList.add(new RepSum(2,
                R.drawable.barbara_lee,
                "Barbara Lee",
                "barbaralee@gmail.com",
                "Vote for Barbara Lee!!!",
                "Democrat",
                "Representative",
                "http://www.barbaralee.com"));
        mRepList.add(new RepSum(3,
                R.drawable.barbara_boxer,
                "Barbara Boxer",
                "barbaraboxer@gmail.com",
                "Vote For Barbara Boxer!!!",
                "Democrat",
                "Senator",
                "http://www.boxer.senate.gov"));

        adapter = new RepSumAdapter(getApplicationContext(), mRepList);
        lvRep.setAdapter(adapter);

        lvRep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail = new Intent(getApplicationContext(), DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("personId", mRepList.get(position).getName());
                extras.putString("personPhoto", String.valueOf(mRepList.get(position).getImage()));
                detail.putExtras(extras);
                startActivity(detail);
            }
        });
    }
    /*
    public void onDetailed(View v) {
        Intent detail = new Intent(this, DetailedView.class);
        detail.putExtra("personId", v.getTag().toString());
        startActivity(detail);
    }
    */
}
