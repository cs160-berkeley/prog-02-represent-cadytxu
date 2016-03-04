package com.cs160.cadyxu.respublica;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cadyxu on 3/3/16.
 */
public class RepAdapter extends FragmentGridPagerAdapter {
    private final Context mContext;
    private List mRows;
    private final RepSumWatch[][] mPages;


    public RepAdapter(Context ctx, FragmentManager fm, RepSumWatch[][] pages) {
        super(fm);
        mContext = ctx;
        mPages = pages;
    }


    // Obtain the UI fragment at the specified position
    @Override
    public Fragment getFragment(int row, int col) {
        RepSumWatch rep = mPages[row][col];
        RepSumFragment fragment = RepSumFragment.newInstance(rep.getName(), rep.getParty(), rep.getImage());

        fragment.setExpansionEnabled(true);

        return fragment;
    }

    // Obtain the background image for the specific page
    @Override
    public Drawable getBackgroundForPage(int row, int col) {
        RepSumWatch rep = mPages[row][col];
        return mContext.getResources().getDrawable(rep.getImage(), null);
    }

    @Override
    public int getRowCount() {
        return mPages.length;
    }

    @Override
    public int getColumnCount(int row) {
        return mPages[0].length;
    }
}
