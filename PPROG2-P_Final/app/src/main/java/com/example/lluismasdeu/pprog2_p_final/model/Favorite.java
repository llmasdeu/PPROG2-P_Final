package com.example.lluismasdeu.pprog2_p_final.model;

/**
 * Created by eloy on 26-05-2017.
 */

public class Favorite {

    private String name;
    private String address;
    private String rate;
    private int id;

    public Favorite()
    {

    }

    public Favorite(String name, String address, String rate, int id) {
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
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
}
