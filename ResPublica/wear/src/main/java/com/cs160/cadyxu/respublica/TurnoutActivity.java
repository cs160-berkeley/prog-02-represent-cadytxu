package com.cs160.cadyxu.respublica;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TurnoutActivity extends Activity {

    private TextView mObamaText;
    private TextView mRomneyText;
    private String mObamaDisplay;
    private String mRomneyDisplay;
    private String zipString;

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

    }

}
