package com.cs160.cadyxu.respublica;

import java.io.Serializable;

/**
 * Created by cadyxu on 2/28/16.
 */
public class RepSum implements Serializable {
    private int id;
    private int image;
    private String name;
    private String email;
    private String tweet;
    private String party;
    private String title;
    private String website;

    public int getImage() { return image;}

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTweet() {
        return tweet;
    }

    public String getParty() {
        return party;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() { return website; }

    public RepSum(int id, int image, String name, String email, String tweet, String party, String title, String website) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.email = email;
        this.tweet = tweet;
        this.party = party;
        this.title = title;
        this.website = website;
    }



}
