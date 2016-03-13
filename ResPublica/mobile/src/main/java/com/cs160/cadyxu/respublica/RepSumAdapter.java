package com.cs160.cadyxu.respublica;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.List;

/**
 *
 * Created by cadyxu on 2/27/16.
 */
public class RepSumAdapter extends BaseAdapter {
    private Context mContext;
    private List<RepSum> mRepList;

    public RepSumAdapter(Context mContext, List<RepSum> mRepList) {
        this.mContext = mContext;
        this.mRepList = mRepList;
    }

    @Override
    public int getCount() {
        if (mRepList != null){
            return mRepList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mRepList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final int loc = position;

        View repView = inflater.inflate(R.layout.list_item_sum, parent, false);
        TextView nameView = (TextView) repView.findViewById(R.id.nameText);
        TextView partyView = (TextView) repView.findViewById(R.id.partyText);
        TextView webView = (TextView) repView.findViewById(R.id.websiteText);
        TextView emailView = (TextView) repView.findViewById(R.id.emailText);
        TextView tweetView = (TextView) repView.findViewById(R.id.tweetText);
        ImageView imageView = (ImageView) repView.findViewById(R.id.senatorImage);


        nameView.setText(mRepList.get(position).getName());
        partyView.setText(mRepList.get(position).getParty());
        webView.setText(mRepList.get(position).getWebsite());
        emailView.setText(mRepList.get(position).getEmail());
        tweetView.setText(mRepList.get(position).getTweet());
        //imageView.setImageResource(mRepList.get(position).getImage());

        Picasso.with(mContext).load(mRepList.get(position).getImage()).into(imageView);

        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String urlString = mRepList.get(loc).getWebsite();
                Log.d("urlString: ", urlString);
                intent.setData(Uri.parse(urlString));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });

        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                String[] addresses = new String[1];
                addresses[0] = mRepList.get(loc).getEmail();
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }

            }
        });


        return repView;
    }




}
