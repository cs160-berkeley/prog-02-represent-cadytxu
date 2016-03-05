package com.cs160.cadyxu.respublica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button mViewRepBtn;
    private Button mViewTurnoutBtn;
    private String zipString;
    //private String mVotes = "78.9%";
    //private String mOppoVotes = "18.2%";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewRepBtn = (Button) findViewById(R.id.viewMyRepsButton);
        mViewTurnoutBtn = (Button) findViewById(R.id.viewTurnout);
        Intent intent = getIntent();
        zipString = intent.getStringExtra("zipString");
        //mViewTurnoutBtn.setText(zipString);

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
    }
}
