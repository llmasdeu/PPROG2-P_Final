package com.example.lluismasdeu.pprog2_p_final.repositories.implementations;

import com.example.lluismasdeu.pprog2_p_final.model.Restorants;
import com.example.lluismasdeu.pprog2_p_final.repositories.RestaurantsInterface;

import java.util.List;

/**
 * Created by eloy on 10-05-2017.
 */

public class RestaurantsWebService implements RestaurantsInterface {

    private static final String OMDBAPI_URI_BASE = "http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&";

    @Override
    public List<Restorants> search(String searchParameter) {
        List<Restorants> list=null;
        return null;
    }
}
