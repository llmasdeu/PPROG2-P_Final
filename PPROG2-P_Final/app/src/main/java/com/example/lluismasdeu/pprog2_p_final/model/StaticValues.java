package com.example.lluismasdeu.pprog2_p_final.model;

/**
 * Created by lluismasdeu on 6/5/17.
 */
public class StaticValues {
    private static StaticValues instance = null;
    private User connectedUser;

    private StaticValues(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public static StaticValues getInstance(Object... params) {
        if (instance == null)
            instance = new StaticValues(null);

        if (params.length != 0)
            instance = new StaticValues((User) params[0]);

        return instance;
    }

    public User getConnectedUser() {
        return connectedUser;
    }
}
