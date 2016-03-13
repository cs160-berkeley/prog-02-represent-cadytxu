package com.cs160.cadyxu.respublica;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    private ListView lvRep;
    private RepSumAdapter adapter;
    protected static ArrayList<RepSum> mRepList;
    private String urlString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        urlString = getIntent().getStringExtra("url");

        if (urlString != null) {
            FetchRepTask repTask = new FetchRepTask();
            try{
                repTask.execute(urlString);
                if (mRepList == null){
                    Log.d("SummaryActivity" , "mRepList is NULL!!!");
                } else {
                    Log.d("SummaryActivity", "mRepList: " + mRepList.toString());
                }
            } catch (Exception e){
                Log.e("SummaryActivity", "Exception thrown : " + e.toString());
            }
        }

        lvRep = (ListView) findViewById(R.id.listview_reps);



        lvRep.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail = new Intent(getApplicationContext(), DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("repName", mRepList.get(position).getName());
                extras.putString("repImage", mRepList.get(position).getImage());
                extras.putString("repParty", mRepList.get(position).getParty());
                extras.putString("repId", mRepList.get(position).getId());
                extras.putString("repEndTerm", mRepList.get(position).getEndTerm());
                detail.putExtras(extras);
                startActivity(detail);
            }
        });
    }

    private class FetchRepTask extends AsyncTask<String, Void, ArrayList<RepSum>> {

        @Override
        protected void onPostExecute(ArrayList<RepSum> repSums) {
            mRepList = repSums;
            adapter = new RepSumAdapter(getApplicationContext(), mRepList);
            lvRep.setAdapter(adapter);
        }

        @Override
        protected ArrayList<RepSum> doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String repJsonString = null;

            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    //Nothing to do
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader((inputStream)));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    //Log.d("SummaryActivity", "Json Line: " + line);
                }

                if (buffer.length() == 0) {
                    //stream is empty
                    return null;
                } else {
                    repJsonString = buffer.toString();
                    Log.v("FetchRepTask", "Json Line: " + repJsonString);
                    ArrayList<RepSum> repList = new RepDataParser().getRepList(repJsonString);
                    if (repList == null){
                        Log.d("FetchRepTask", "REPLIST IS NULL!!!!!");
                    }
                    Log.d("FetchRepTask", "Rep List: " + repList.toString());
                    return repList;
                }

            } catch (IOException e) {
                Log.e("FetchRepTask", "Error", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("FetchRepTask", "Error closing stream", e);
                    }
                }
                //return null;
            }
        }
    }
}




