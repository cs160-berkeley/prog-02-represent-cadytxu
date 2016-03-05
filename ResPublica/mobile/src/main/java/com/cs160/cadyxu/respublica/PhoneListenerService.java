package com.cs160.cadyxu.respublica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by cadyxu on 3/2/16.
 */
public class PhoneListenerService extends WearableListenerService {
    //   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
    private static final String NAME = "/send_name";
   // private static final String IMAGE = "/send_image";
    private String repName, repImage;

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase(NAME) ) {
            // Value contains the String we sent over in WatchToPhoneService, "good job"
            repName = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            // Make a toast with the String
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, repName, duration);
            toast.show();

            Intent detail = new Intent(getApplicationContext(), DetailActivity.class);
            Bundle extras = new Bundle();
            extras.putString("repName", repName);
            extras.putString("repImage", Integer.toString(R.drawable.dianne_feinstein));
            detail.putExtras(extras);

            detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(detail);

            //you need to add this flag since you're starting a new activity from a service

            Log.d("T", "about to start watch MainActivity with REP_NAME: Dianne Feinstein");
            startActivity(detail);

            // so you may notice this crashes the phone because it's
            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
            // replace sending a toast with, like, starting a new activity or something.
            // who said skeleton code is untouchable? #breakCSconceptions

        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
