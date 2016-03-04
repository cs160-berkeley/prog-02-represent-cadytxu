package com.cs160.cadyxu.respublica;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TurnoutActivity extends Activity {

    private TextView mObamaText;
    private TextView mRomneyText;
    private String mObamaDisplay = "78.9%";
    private String mRomneyDisplay = "18.2%";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnout);

        mObamaText = (TextView) findViewById(R.id.obamaVotesTextView);
        mRomneyText = (TextView) findViewById(R.id.romneyVotesTextView);
        mObamaText.setText(mObamaDisplay);
        mRomneyText.setText(mRomneyDisplay);

    }

}
