package com.cs160.cadyxu.respublica;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText zipText;
    private Button zipSubmitButton;
    private Button curLocSubmitButton;
    private String zipString;
    private TextView errorText;
    private int zipInt;
    private boolean zipValid = false;
    protected static ArrayList<RepSum> mRepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zipSubmitButton = (Button) findViewById(R.id.zipButton);
        curLocSubmitButton = (Button) findViewById(R.id.curLocButton);


        zipSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipText = (EditText)findViewById(R.id.zipEditText);
                zipString = zipText.getText().toString();
                errorText = (TextView)findViewById(R.id.errorTextView);

                try {
                    zipInt = Integer.parseInt(zipText.getText().toString());
                    if (zipInt >= 10000){
                        zipValid = true;
                    }else{
                        errorText.setText("Your zip code should be 5-digit Long.");
                    }
                }catch (NumberFormatException nfe){
                    errorText.setText("Your zip code is not a number.");
                }


                if (zipValid){
                    if (zipString.equals("94704")) {
                        errorText.setText("True, your zipcode is 94704.");
                    } else {
                        errorText.setText("Your zipcode is correct.");
                    }


                    mRepList = new ArrayList<RepSum>();
                    mRepList.add(new RepSum(1,
                            R.drawable.dianne_feinstein,
                            "Sen. Dianne Feinstein",
                            "senator@feinstein.senate.gov",
                            "Vote for Dianne Feinstein!!!",
                            "Democrat","Senator",
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

                    Intent toPhoneIntent = new Intent(getBaseContext(), SummaryActivity.class);
                    toPhoneIntent.putExtra("zipCode", zipString);
                    startActivity(toPhoneIntent);

                    Intent toWatchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    toWatchIntent.putExtra("zipCode", zipString);
                    startService(toWatchIntent);
                }

            }
        });

        curLocSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipString = "94704";

                Intent toPhoneIntent = new Intent(getBaseContext(), SummaryActivity.class);
                toPhoneIntent.putExtra("zipCode", zipString);
                startActivity(toPhoneIntent);

                Intent toWatchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                toWatchIntent.putExtra("zipCode", zipString);
                startService(toWatchIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void zipOnClick(View v){
        Button button = (Button) v;
        button.setText("Clicked");

    }

    public void curLocOnClick(View v){
        Button button = (Button) v;
        button.setText("Clicked");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
