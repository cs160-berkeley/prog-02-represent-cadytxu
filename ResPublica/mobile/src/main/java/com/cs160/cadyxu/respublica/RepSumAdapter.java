package com.cs160.cadyxu.respublica;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.doubleclick.CustomRenderedAd;

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
        return mRepList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


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
        imageView.setImageResource(mRepList.get(position).getImage());

        return repView;
    }

}
