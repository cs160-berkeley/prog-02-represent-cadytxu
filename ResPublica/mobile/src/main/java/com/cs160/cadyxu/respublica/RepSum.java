package com.cs160.cadyxu.respublica;

import java.io.Serializable;

/**
 * Created by cadyxu on 2/28/16.
 */
public class RepSum implements Serializable {
    private String id;
    private String image;
    private String name;
    private String email;
    private String tweet;
    private String party;;
    private String website;

    public String getEndTerm() {
        return endTerm;
    }

    private String endTerm;

    public String getImage() { return image;}

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTweet() {
        return tweet;
    }

    public String getParty() { return party; }

    public String getWebsite() { return website; }

    public RepSum(String id, String image, String name, String email, String tweet, String party, String website, String endTerm) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.email = email;
        this.tweet = tweet;
        this.party = party;
        this.website = website;
        this.endTerm = endTerm;
    }



}
