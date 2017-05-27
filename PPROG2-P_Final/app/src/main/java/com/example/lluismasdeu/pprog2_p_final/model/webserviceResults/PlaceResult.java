package com.example.lluismasdeu.pprog2_p_final.model.webserviceResults;

/**
 * Clase encargada de guardar el resultado de la respuesta del Webservice.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class PlaceResult {
    private String name;
    private String type;
    private PlaceLocation location;
    private String address;
    private String opening;
    private String closing;
    private double review;
    private String description;

    /**
     * Constructor de la clase.
     * @param name Nombre de la localización.
     * @param type Tipo de comida de la localización.
     * @param location Coordenadas de la localización.
     * @param address Dirección de la localización.
     * @param opening Hora de apertura de la localización.
     * @param closing Hora de clausura de la localización.
     * @param review Valoración media de la localización.
     * @param description Descripción de la localización.
     */
    public PlaceResult(String name, String type, PlaceLocation location, String address,
                       String opening, String closing, double review, String description) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.address = address;
        this.opening = opening;
        this.closing = closing;
        this.review = review;
        this.description = description;
    }

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
    public PlaceLocation getLocation() {
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
