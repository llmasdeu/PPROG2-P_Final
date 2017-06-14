package com.example.lluismasdeu.pprog2_p_final.model;

import android.graphics.Bitmap;

/**
 * Clase encargada de guardar los datos de un restaurante.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class Restaurant {
    private String name;
    private String type;
    private String address;
    private String opening;
    private String closing;
    private double review;
    private String description;
    private double latitude;
    private double longitude;
    private Bitmap image;

    /**
     * Constructor de la clase.
     * @param name Nombre de la localización.
     * @param type Tipo de comida de la localización.
     * @param address Dirección de la localización.
     * @param opening Hora de apertura de la localización.
     * @param closing Hora de clausura de la localización.
     * @param review Valoración media de la localización.
     * @param description Descripción de la localización.
     * @param latitude Latitud del restaurante.
     * @param longitude Longitud del restaurante.
     */
    public Restaurant(String name, String type, String address, String opening, String closing,
                      double review, String description, double latitude, double longitude) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.opening = opening;
        this.closing = closing;
        this.review = review;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter del nombre del restaurante.
     * @return Nombre del restaurante.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter del tipo de restaurante.
     * @return Tipo de restaurante.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter de la dirección del restaurante.
     * @return Dirección del restaurante.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter de la hora de apertura del restaurante.
     * @return Hora de apertura del restaurante.
     */
    public String getOpening() {
        return opening;
    }

    /**
     * Getter de la hora de clausura del restaurante.
     * @return Hora de clausura del restaurante.
     */
    public String getClosing() {
        return closing;
    }

    /**
     * Getter de la puntuación media del restaurante.
     * @return Puntuación media del restaurante.
     */
    public double getReview() {
        return review;
    }

    /**
     * Getter de la descripción del restaurante.
     * @return Descripción del restaurante.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter de la latitud del restaurante.
     * @return Latitud del restaurante.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter de la longitud del restaurante.
     * @return Longitud del restaurante.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Getter de la imagen del restaurante.
     * @return Imagen del restaurante.
     */
    public Bitmap getImage() {
        return image;
    }

    /**
     * Setter de la imagen del restaurante.
     * @param image Imagen del restaurante.
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }
}
