package com.cs160.cadyxu.respublica;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cadyxu on 3/11/16.
 */
public class RepDataParser {
    protected static ArrayList<RepSum> mRepList;

    public static ArrayList getRepList(String repJsonString) {
        int repIndex = 0;
        mRepList = new ArrayList<RepSum>();

        try {
            JSONObject jsonResult = new JSONObject(repJsonString);
            //Log.v("RepDataParser", "What is the JSON Object?  " + jsonResult.toString());
            JSONArray results = jsonResult.getJSONArray("results");
            //Log.v("RepDataParser", "What is in the result field?  " + results.toString());
            while (repIndex < results.length()){
                JSONObject rep = results.getJSONObject(repIndex);
               // Log.v("RepDataParser", "Who is this rep? " + rep.toString());
                String repID = rep.getString("bioguide_id");
               // Log.v("RepDataParser", "Who is the rep's id? " + repID.toString());
                String repFirstName = rep.getString("first_name");
                String repLastName = rep.getString("last_name");
                String repWebsite = rep.getString("website");
                String repEmail =  rep.getString("oc_email");
                String repTitle  = rep.getString("title");
                String repTweeterID = rep.getString("twitter_id");
                String repParty = "Party: " + rep.getString("party");
                String repEndTerm = "End Date of Term: " + rep.getString("term_end");
                String repImageURL = "https://theunitedstates.io/images/congress/225x275/" + repID + ".jpg";

                mRepList.add(new RepSum(repID,
                        repImageURL,
                        repTitle + ". " + repFirstName + " "+ repLastName,
                        repEmail,
                        repTweeterID,
                        repParty,
                        repWebsite,
                        repEndTerm));
                repIndex += 1;
            }
            if (mRepList == null){
                Log.d("RepDataParser" , "mRepList is NULL!!!");
            }

            Log.v("RepDataParser", "what is mRepList? :"  + mRepList.toString());
            return mRepList;
        } catch (JSONException e){
            Log.d("RepDataParser", "JSON Exception is thrown!!!");
            return null;
        }

    }
    public static String getCommittees(String cmtJsonString){
        int cmtIndex = 0;
        String cmts = "Committees: \n";

        try {
            JSONObject jsonResult = new JSONObject(cmtJsonString);
            //Log.v("RepDataParser", "What is the JSON Object?  " + jsonResult.toString());
            JSONArray results = jsonResult.getJSONArray("results");
            //Log.v("RepDataParser", "What is in the result field?  " + results.toString());
            while (cmtIndex < results.length()){
                JSONObject cmt = results.getJSONObject(cmtIndex);
                // Log.v("RepDataParser", "Who is this rep? " + rep.toString());
                String cmtName = cmt.getString("name");
                // Log.v("RepDataParser", "Who is the rep's id? " + repID.toString());
                if (cmtName != null){
                    cmts += cmtName + "; \n";
                }
                cmtIndex += 1;
            }
            if (cmts == null){
                Log.d("RepDataParser" , "cmts is NULL!!!");
            }

            return cmts;
        } catch (JSONException e){
            Log.d("RepDataParser", "JSON Exception is thrown!!!");
            return null;
        }
    }
    public static String getBills(String cmtJsonString){
        int billIndex = 0;
        String bills = "Recent bills: \n";

        try {
            JSONObject jsonResult = new JSONObject(cmtJsonString);
            //Log.v("RepDataParser", "What is the JSON Object?  " + jsonResult.toString());
            JSONArray results = jsonResult.getJSONArray("results");
            //Log.v("RepDataParser", "What is in the result field?  " + results.toString());
            while (billIndex < results.length()){
                JSONObject bill = results.getJSONObject(billIndex);
                // Log.v("RepDataParser", "Who is this rep? " + rep.toString());
                String billName = bill.getString("short_title");
                // Log.v("RepDataParser", "Who is the rep's id? " + repID.toString());
                if (billName != null && billName != "null"){
                    bills += billName + "; \n";
                }
                billIndex += 1;
            }

            if (bills == null){
                Log.d("RepDataParser" , "bills is NULL!!!");
            }

            return bills;
        } catch (JSONException e){
            Log.d("RepDataParser", "JSON Exception is thrown!!!");
            return null;
        }
    }
}
