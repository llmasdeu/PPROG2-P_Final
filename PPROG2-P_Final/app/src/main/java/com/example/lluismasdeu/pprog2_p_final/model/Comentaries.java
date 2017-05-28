package com.example.lluismasdeu.pprog2_p_final.model;

/**
 * Created by eloy on 28-05-2017.
 */

public class Comentaries {
    private String name;
    private String username;
    private String comentary;

    public  Comentaries()
    {

    }
    public Comentaries(String name, String username, String comentary) {
        this.name = name;
        this.username = username;
        this.comentary = comentary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComentary() {
        return comentary;
    }

    public void setComentary(String comentary) {
        this.comentary = comentary;
    }
}
