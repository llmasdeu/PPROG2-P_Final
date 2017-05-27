package com.example.lluismasdeu.pprog2_p_final.model;

import android.graphics.Bitmap;

/**
 * Created by eloy on 10-05-2017.
 */

public class Restaurant {
    private String name;
    private String address;
    private double rate;
    private Bitmap image;
    private String type;

    public Restaurant(){

    }

    public Restaurant(String name, String address, double rate, Bitmap image, String type) {
        this.name = name;
        this.address = address;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
