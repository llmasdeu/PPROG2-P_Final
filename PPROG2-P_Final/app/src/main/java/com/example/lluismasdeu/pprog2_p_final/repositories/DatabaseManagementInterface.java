package com.example.lluismasdeu.pprog2_p_final.repositories;

import com.example.lluismasdeu.pprog2_p_final.model.Comentaries;
import com.example.lluismasdeu.pprog2_p_final.model.Favorite;
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
    boolean existsRecentSearch(String recentSearch);
    List<String> getAllRecentSearches();
    void registerFavorite(Favorite favorite);
    boolean existFavorite(String name);
    void deleteFavorite(String name);
    List<Favorite> getAllFavorite();
    void addComentary(Comentaries c);
    List<Comentaries> getAllComentaries();
}
