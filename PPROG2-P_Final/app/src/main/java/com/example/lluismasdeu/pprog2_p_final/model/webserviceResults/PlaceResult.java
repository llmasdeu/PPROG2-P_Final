package com.example.lluismasdeu.pprog2_p_final.model.webserviceResults;

/**
 * Clase encargada de guardar el resultado de la respuesta del Webservice.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class PlaceResult {
    private String name;
    private String type;
    private Location location;
    private String address;
    private String opening;
    private String closing;
    private double review;
    private String description;

    /**
     * Getter del nombre de la localización.
     * @return Nombre de la localización.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter del tipo de comida de la localización.
     * @return Tipo de comida de la localización.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter de las coordenadas de la localización.
     * @return Coordenadas de la localización.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter de la dirección de la localización.
     * @return Dirección de la localización.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter de la hora de apertura de la localización.
     * @return Hora de apertura de la localización.
     */
    public String getOpening() {
        return opening;
    }

    /**
     * Getter de la hora de clausura de la localización.
     * @return Hora de clausura de la localización.
     */
    public String getClosing() {
        return closing;
    }

    /**
     * Getter de la valoración media de la localización.
     * @return Valoración media de la localización.
     */
    public double getReview() {
        return review;
    }

    /**
     * Getter de la descripción de la localización.
     * @return Descripción de la localización.
     */
    public String getDescription() {
        return description;
    }
}
