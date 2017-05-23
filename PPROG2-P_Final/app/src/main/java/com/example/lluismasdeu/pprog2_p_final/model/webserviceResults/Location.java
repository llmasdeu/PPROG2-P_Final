package com.example.lluismasdeu.pprog2_p_final.model.webserviceResults;

import com.google.gson.annotations.SerializedName;

/**
 * Clase encargada de guardar las coordenadas de un resultado de la respuesta del Webservice.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class Location {
    @SerializedName("lat")
    private double latitude;
    @SerializedName("lng")
    private double longitude;

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
