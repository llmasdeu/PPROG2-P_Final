package com.example.lluismasdeu.pprog2_p_final.model;

/**
 * Created by eloy on 26-05-2017.
 */

public class Favorite {

    private String name;
    private String address;
    private String rate;
    private String type;
    private String open;
    private String close;
    private int id;

    public Favorite()
    {

    }

    public Favorite(String name, String address, String rate, int id,String type,String open,String close) {
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.id = id;
        this.type=type;
        this.open=open;
        this.close=close;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }
}
