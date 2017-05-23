package com.example.lluismasdeu.pprog2_p_final.repositories;

import com.example.lluismasdeu.pprog2_p_final.model.User;

import java.util.List;

/**
 * Interfície de la clase gestora de la base de datos.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public interface DatabaseManagementInterface {
    void registerUser(User u);
    void unregisterUser(User u);
    void updateUser(User u);
    boolean existsUser(User u, int mode);
    User getConnectedUser(User u);
    List<User> getAllUsers();
    void registerRecentSearch(String recentSearch);
    List<String> getAllRecentSearches();
}
