package com.example.lluismasdeu.pprog2_p_final.model;

/**
 * Created by lluismasdeu on 30/4/17.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String gender;
    private String description;
    private String imageFile;

    public User(String email) {
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String surname, String email, String password, String gender,
                String description, String imageFile) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.description = description;
        this.imageFile = imageFile;
    }

    public User(int id, String name, String surname, String email, String password, String gender,
                String description, String imageFile) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.description = description;
        this.imageFile = imageFile;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public String getImageFile() {
        return imageFile;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("{ ").append("ID: ").append(id).append("; Name: "). append(name)
                .append("; Surname: ").append(surname).append("; E-mail: ").append(email)
                .append("; Password: ").append(password).append("; Gender: ").append(gender)
                .append("; Description: ").append(description).append("; Image File: ")
                .append(imageFile).append(" }");

        return builder.toString();
    }
}
