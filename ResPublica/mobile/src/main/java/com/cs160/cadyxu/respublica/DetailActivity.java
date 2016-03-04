package com.cs160.cadyxu.respublica;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent repDetail = getIntent();
        Bundle extras = repDetail.getExtras();
        String repName = extras.getString("repName");
        int img = Integer.parseInt(extras.getString("repImage"));
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(img);
        TextView detailName = (TextView)findViewById(R.id.detailName);
        detailName.setText(repName);
    }

}
