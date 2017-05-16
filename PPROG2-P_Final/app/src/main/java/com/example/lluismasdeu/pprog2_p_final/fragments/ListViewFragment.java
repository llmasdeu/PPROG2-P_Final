package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.RestorantListViewAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Restorants;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ListViewFragment extends Fragment {
    private ListView listView;
    private ArrayList<Restorants> cityArrayList;
    private RestorantListViewAdapter adapter;
    JsonArrayRequest jsArrayRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_results, container, false);

        // Recuperamos el componente gráfico para poder asignarle un adapter.
        listView =(ListView) view.findViewById(R.id.listview);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&s=Barcelona";

        jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        List<Restorants> list = null;
                        try {
                            JSONArray search = response;
                            list = new ArrayList<>(search.length());
                            for (int i = 0; i < search.length(); i++) {
                                Restorants movie = new Restorants();
                                movie.setName(search.getJSONObject(i).getString("name"));
                                movie.setAdress(search.getJSONObject(i).getString("address"));
                                list.add(movie);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new RestorantListViewAdapter(list, getActivity());
                        listView.setAdapter(adapter);}

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(jsArrayRequest);

        listView.setAdapter(adapter);


        return view;
    }
}
