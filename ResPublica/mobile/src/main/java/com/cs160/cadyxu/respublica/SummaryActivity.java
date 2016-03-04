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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Bundle bundleRepList = getIntent().getExtras();
        mRepList = (ArrayList<RepSum>)bundleRepList.getSerializable("repList");
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
    /*
    public void onDetailed(View v) {
        Intent detail = new Intent(this, DetailedView.class);
        detail.putExtra("personId", v.getTag().toString());
        startActivity(detail);
    }
    */
}
