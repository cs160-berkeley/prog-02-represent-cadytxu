package com.cs160.cadyxu.respublica;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cadyxu on 3/3/16.
 */
public class RepSumFragment extends CardFragment {

    View result;
    TextView nameText;
    TextView partyText;
    ImageView image;


    private String nameString, partyString;
    private int imageInt;

    public static RepSumFragment newInstance(String name, String party, int image) {
        RepSumFragment fragment = new RepSumFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("party", party);
        args.putInt("image", image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        result = inflater.inflate(R.layout.rep_fragment, container, false);
        nameText = (TextView)result.findViewById(R.id.repNameText);
        partyText = (TextView)result.findViewById(R.id.repPartyText);
        image = (ImageView)result.findViewById(R.id.repImage);
        nameText.setText(getArguments().getString("name"));
        partyText.setText(getArguments().getString("party"));
        image.setImageResource(getArguments().getInt("image"));

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detail = new Intent(getActivity().getBaseContext(), WatchToPhoneService.class);
                Bundle extras = new Bundle();
                extras.putString("repName", getArguments().getString("name"));
                extras.putString("repImage", image.getDrawable().toString());
                extras.putString("repParty", getArguments().getString("party"));
                detail.putExtras(extras);
                getActivity().startService(detail);
            }
        });


        return result;
    }

}
