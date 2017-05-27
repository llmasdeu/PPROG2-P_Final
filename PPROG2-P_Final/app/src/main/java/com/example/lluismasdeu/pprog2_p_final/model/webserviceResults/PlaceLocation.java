package com.example.lluismasdeu.pprog2_p_final.model.webserviceResults;

import com.google.gson.annotations.SerializedName;

/**
 * Clase encargada de guardar las coordenadas de un resultado de la respuesta del Webservice.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class PlaceLocation {
    @SerializedName("lat")
    private double latitude;
    @SerializedName("lng")
    private double longitude;

    /**
     * Constructor de la clase.
     * @param latitude Latitud de la localización.
     * @param longitude Longitud de la localización.
     */
    public PlaceLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter de la latitud de la localización.
     * @return Latitud de la localización.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter de la longitud de la localización.
     * @return Longitude de la localización.
     */
    public double getLongitude() {
        return longitude;
    }
}
