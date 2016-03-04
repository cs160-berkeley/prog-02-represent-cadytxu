package com.cs160.cadyxu.respublica;

import android.os.Bundle;
import android.app.Activity;
import android.support.wearable.view.GridViewPager;

public class SummaryActivity extends Activity {
    private RepSumWatch[][] repSumList = new RepSumWatch[1][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        repSumList[0][0] = new RepSumWatch(1, R.drawable.dianne_feinstein, "Sen. Dianne Feinstein", "Democrat");
        repSumList[0][1] = new RepSumWatch(2, R.drawable.barbara_boxer, "Sen. Barbara Boxer", "Democrat");
        repSumList[0][2] = new RepSumWatch(3, R.drawable.barbara_lee, "Rep. Barbara Lee", "Democrat");

        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new RepAdapter(this, getFragmentManager(), repSumList));
    }

}
