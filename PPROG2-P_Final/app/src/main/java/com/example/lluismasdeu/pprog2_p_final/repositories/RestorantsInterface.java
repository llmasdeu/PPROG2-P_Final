package com.example.lluismasdeu.pprog2_p_final.repositories;

import com.example.lluismasdeu.pprog2_p_final.model.Restorants;

import java.util.List;

/**
 * Created by eloy on 10-05-2017.
 */

public interface RestorantsInterface {
    List<Restorants> search(String searchParameter);
}
