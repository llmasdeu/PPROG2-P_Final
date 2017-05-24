package com.example.lluismasdeu.pprog2_p_final.model;

import android.graphics.Bitmap;

/**
 * Created by eloy on 10-05-2017.
 */

public class Restaurants {
    private String name;
    private String adress;
    private String rate;
    private Bitmap image;
    private String type;

    public Restaurants(){

    }

    public Restaurants(String name, String adress, String rate, Bitmap image,String type) {
        this.name = name;
        this.adress = adress;
        this.rate = rate;
        this.image = image;
        this.type=type;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
