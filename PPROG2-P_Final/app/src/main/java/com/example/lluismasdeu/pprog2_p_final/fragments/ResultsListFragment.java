package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.DescriptionActivity;
import com.example.lluismasdeu.pprog2_p_final.activities.ResultsActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.RestaurantsAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Restaurant;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ResultsListFragment extends Fragment {
    private ListView listView;
    private RestaurantsAdapter adapter;
    JsonArrayRequest jsArrayRequest;
    List<Restaurant> list_tmp;
    private Spinner filtro;
    List<Restaurant> restaurantsList;
    private List<PlaceResult> placeResults;

    public ResultsListFragment(List<PlaceResult> placeResults) {
        this.placeResults = placeResults;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_results, container, false);

        ResultsActivity activity = (ResultsActivity) getActivity();
        String searchParameter = activity.getMyData();

        filtro = (Spinner) getActivity().findViewById(R.id.menuSort) ;

        // Recuperamos el componente gráfico para poder asignarle un adapter.
        listView =(ListView) view.findViewById(R.id.listview);

        restaurantsList = new ArrayList<Restaurant>();
        Restaurant restaurant;

        for (int i = 0; i < placeResults.size(); i++) {
            restaurant = new Restaurant();
            restaurant.setName(placeResults.get(i).getName());
            restaurant.setType(placeResults.get(i).getType());
            restaurant.setAddress(placeResults.get(i).getAddress());
            restaurant.setRate(placeResults.get(i).getReview());
            restaurantsList.add(restaurant);
        }

        adapter = new RestaurantsAdapter(restaurantsList, getActivity());
        listView.setAdapter(adapter);

        /*
        //Iniciamos  coneccion con web service mediante volley

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&";

        jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+searchParameter,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            JSONArray search = response;
                            restaurantsList = new ArrayList<>(search.length());
                            for (int i = 0; i < search.length(); i++) {

                                Restaurant restaurants = new Restaurant();
                                restaurants.setName(search.getJSONObject(i).getString("name"));
                                restaurants.setAddress(search.getJSONObject(i).getString("address"));
                                restaurants.setRate(search.getJSONObject(i).getString("review"));
                                restaurants.setType(search.getJSONObject(i).getString("type"));
                                restaurantsList.add(restaurants);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new RestaurantsAdapter(restaurantsList, getActivity());
                        listView.setAdapter(adapter);}

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(jsArrayRequest);
        */


        //Listener de listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView temp = (TextView) view.findViewById(R.id.titulo_textView);

                String str = temp.getText().toString();

                updateDetail(str);
            }
        });

        // filtro para la lista mediante spinner
        filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if(getString(R.string.filter)!=filtro.getSelectedItem()) {
                    list_tmp=new ArrayList<>(restaurantsList.size());
                    for(int w = 0; w< restaurantsList.size(); w++){
                        if(filtro.getSelectedItem().equals(restaurantsList.get(w).getType()))
                        {
                            Restaurant tmp_rest= new Restaurant();
                            tmp_rest.setName(restaurantsList.get(w).getName());
                            tmp_rest.setAddress(restaurantsList.get(w).getAddress());
                            tmp_rest.setRate(restaurantsList.get(w).getRate());
                            list_tmp.add(tmp_rest);

                        }

                    }

                    //usar mismo adapter
                    //vaciarlo
                    //notifyadapter
                    adapter = new RestaurantsAdapter(list_tmp, getActivity());
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }

    //intent para DescriptionActivity
    public void updateDetail(String str) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra("name",str);
        startActivity(intent);
    }
}
