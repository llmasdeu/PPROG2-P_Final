package com.example.lluismasdeu.pprog2_p_final.model;

import android.graphics.Bitmap;

/**
 * Created by eloy on 10-05-2017.
 */

public class Restorants {
    private String name;
    private String adress;
    private String rate;
    private Bitmap image;

    public Restorants(){

    }

    public Restorants(String name, String adress, String rate, Bitmap image) {
        this.name = name;
        this.adress = adress;
        this.rate = rate;
        this.image = image;
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
}
