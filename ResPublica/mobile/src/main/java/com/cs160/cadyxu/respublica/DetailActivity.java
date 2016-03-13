package com.cs160.cadyxu.respublica;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private String API_KEY = "c662476b364441d1bc94e80bda802325";
    private String SUN_COMMITTEES_API_URL = "http://congress.api.sunlightfoundation.com/committees?";
    private String SUN_BILLS_API_URL = "http://congress.api.sunlightfoundation.com/bills?";
    TextView detailCommittees, detailBills;
    private String cmtURLString, billURLString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent repDetail = getIntent();
        Bundle extras = repDetail.getExtras();
        String repName = extras.getString("repName");
        String repImage = extras.getString("repImage");
        String repParty = extras.getString("repParty");
        String repEndTerm = extras.getString("repEndTerm");
        String repId = extras.getString("repId");


        ImageView repImageView= (ImageView) findViewById(R.id.photo);
        Picasso.with(getApplicationContext()).load(repImage).into(repImageView);

        TextView detailName = (TextView)findViewById(R.id.detailName);
        TextView detailParty = (TextView)findViewById(R.id.detailParty);
        TextView detailEndTerm = (TextView)findViewById(R.id.detailEndTerm);


        detailName.setText(repName);
        detailParty.setText(repParty);
        detailEndTerm.setText(repEndTerm);

        cmtURLString = SUN_COMMITTEES_API_URL + "member_ids=" + repId + "&apikey=" + API_KEY;

        if (cmtURLString != null) {
            FetchCommitteesTask cmtTask = new FetchCommitteesTask();
            try{
                cmtTask.execute(cmtURLString);

            } catch (Exception e){
                Log.e("DetailActivity", "Exception thrown : " + e.toString());
            }
        }

        billURLString = SUN_BILLS_API_URL + "sponsor_id=" + repId + "&apikey=" + API_KEY;
        Log.d("bills url:", billURLString);
        if (billURLString != null) {
            FetchBillsTask billTask = new FetchBillsTask();
            try{
                billTask.execute(billURLString);

            } catch (Exception e){
                Log.e("DetailActivity", "Exception thrown : " + e.toString());
            }
        }
    }

    private class FetchCommitteesTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String committees) {

            detailCommittees = (TextView)findViewById(R.id.detailCommittees);
            detailCommittees.setText(committees);
        }

        @Override
        protected String doInBackground(String... params) {
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
                    Log.v("FetchCmtTask", "Json Line: " + repJsonString);
                    String cmtList = new RepDataParser().getCommittees(repJsonString);
                    if (cmtList == null){
                        Log.d("FetchCmtTask", "REPLIST IS NULL!!!!!");
                    }
                    Log.d("FetchCmtTask", "Rep List: " + cmtList.toString());
                    return cmtList;
                }

            } catch (IOException e) {
                Log.e("FetchCmtTask", "Error", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("FetchCmtTask", "Error closing stream", e);
                    }
                }
                //return null;
            }
        }
    }

    private class FetchBillsTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String bills) {

            detailBills = (TextView)findViewById(R.id.detailRecentBills);
            detailBills.setText(bills);
        }

        @Override
        protected String doInBackground(String... params) {
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
                    Log.v("FetchbillTask", "Json Line: " + repJsonString);
                    String billList = new RepDataParser().getBills(repJsonString);
                    if (billList == null){
                        Log.d("FetchbillTask", "REPLIST IS NULL!!!!!");
                    }
                    Log.d("FetchBillTask", "Rep List: " + billList.toString());
                    return billList;
                }

            } catch (IOException e) {
                Log.e("FetchBillTask", "Error", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("FetchBillTask", "Error closing stream", e);
                    }
                }
                //return null;
            }
        }
    }
}
