package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.DescriptionActivity;
import com.example.lluismasdeu.pprog2_p_final.activities.ResultsActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.RestaurantListViewAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Restaurants;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ListViewFragment extends Fragment {
    private ListView listView;
    private RestaurantListViewAdapter adapter;
    JsonArrayRequest jsArrayRequest;
    List<Restaurants> list_tmp;
    private RestaurantListViewAdapter adapter_tmp;
    private Spinner filtro;
    List<Restaurants> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_results, container, false);

        ResultsActivity activity = (ResultsActivity) getActivity();
        String searchParameter = activity.getMyData();

        filtro=(Spinner) getActivity().findViewById(R.id.menuSort) ;

        list=null;

        // Recuperamos el componente gráfico para poder asignarle un adapter.
        listView =(ListView) view.findViewById(R.id.listview);

        //Iniciamos  coneccion con web service mediante volley

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&s=";

        jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+searchParameter,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            JSONArray search = response;
                            list = new ArrayList<>(search.length());
                            for (int i = 0; i < search.length(); i++) {

                                Restaurants restaurants = new Restaurants();
                                restaurants.setName(search.getJSONObject(i).getString("name"));
                                restaurants.setAdress(search.getJSONObject(i).getString("address"));
                                restaurants.setRate(search.getJSONObject(i).getString("review"));
                                restaurants.setType(search.getJSONObject(i).getString("type"));
                                list.add(restaurants);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new RestaurantListViewAdapter(list, getActivity());
                        listView.setAdapter(adapter);}

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(jsArrayRequest);


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

                Log.d("bla",String.valueOf(filtro.getSelectedItem()));
                if("Filter"!=filtro.getSelectedItem()) {
                    list_tmp=new ArrayList<>(list.size());
                    for(int w=0;w<list.size();w++){
                        if(filtro.getSelectedItem().equals(list.get(w).getType()))
                        {
                            Restaurants tmp_rest= new Restaurants();
                            tmp_rest.setName(list.get(w).getName());
                            tmp_rest.setAdress(list.get(w).getAdress());
                            tmp_rest.setRate(list.get(w).getRate());
                            list_tmp.add(tmp_rest);

                        }

                    }
                    adapter_tmp = new RestaurantListViewAdapter(list_tmp, getActivity());
                    listView.setAdapter(adapter_tmp);
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
