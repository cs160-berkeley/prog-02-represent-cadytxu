package com.cs160.cadyxu.respublica;

import java.io.Serializable;

/**
 * Created by cadyxu on 3/3/16.
 */
public class RepSumWatch implements Serializable {


    private int id;
    private int image;
    private String name;

    private String party;

    public RepSumWatch(int id, int image, String name, String party) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.party = party;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
