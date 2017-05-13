package com.example.lluismasdeu.pprog2_p_final.repositories;

import com.example.lluismasdeu.pprog2_p_final.model.User;

import java.util.List;

/**
 * Created by lluismasdeu on 30/4/17.
 */
public interface DatabaseManagementInterface {
    void addUser(User u);
    void removeUser(User u);
    void updateUser(User u);
    boolean existsUser(User u, int mode);
    User getUser(User u);
    List<User> getAllUsers();
    void addRecentSearch(String recentSearch);
    List<String> getAllRecentSearches();
}
