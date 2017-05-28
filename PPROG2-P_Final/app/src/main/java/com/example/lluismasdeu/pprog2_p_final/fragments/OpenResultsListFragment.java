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

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.DescriptionActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.ResultsAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloy on 18-05-2017.
 */


//Fragment para only open
public class OpenResultsListFragment extends Fragment {
    private ListView listView;
    private ResultsAdapter adapter;
    private List<PlaceResult> openPlaceResults;
    //JsonArrayRequest jsArrayRequest;
    //List<Restaurant> list;
    private Spinner typesFilter;
    List<PlaceResult> resultsByType;

    public OpenResultsListFragment(List<PlaceResult> openPlaceResults) {
        this.openPlaceResults = openPlaceResults;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        /*
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm");
        final String datetime = dateformat.format(c.getTime());
        ResultsActivity activity = (ResultsActivity) getActivity();
        String searchParameter = activity.getMyData();
        */

        typesFilter = (Spinner) getActivity().findViewById(R.id.menuSort);

        //list=null;

        // Recuperamos el componente gráfico para poder asignarle un adapter.
        listView = (ListView) view.findViewById(R.id.listview);

        adapter = new ResultsAdapter(openPlaceResults, getActivity());
        listView.setAdapter(adapter);

        /*
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
                            list = new ArrayList<>(search.length());
                            for (int i = 0; i < search.length(); i++) {

                                Restaurant restaurant = new Restaurant();
                                if(datetime.compareTo(search.getJSONObject(i).getString("opening"))>=0 && datetime.compareTo(search.getJSONObject(i).getString("closing"))<=0)
                                {
                                    restaurant.setName(search.getJSONObject(i).getString("name"));
                                    restaurant.setAddress(search.getJSONObject(i).getString("address"));
                                    restaurant.setRate(search.getJSONObject(i).getDouble("review"));
                                    restaurant.setType(search.getJSONObject(i).getString("type"));
                                    list.add(restaurant);
                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new ResultsAdapter(list, getActivity());
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp = (TextView) view.findViewById(R.id.resultTitle_textView);
                String str = temp.getText().toString();
                updateDetail(str);
            }
        });

        // typesFilter para la lista mediante spinner
        typesFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (getString(R.string.filter) != typesFilter.getSelectedItem()) {
                    resultsByType = new ArrayList<PlaceResult>(openPlaceResults.size());

                    if (typesFilter.getSelectedItem().equals(getString(R.string.all))) {
                        resultsByType = openPlaceResults;
                    } else {
                        for (int w = 0; w < openPlaceResults.size(); w++) {
                            if (typesFilter.getSelectedItem()
                                    .equals(openPlaceResults.get(w).getType()))
                                resultsByType.add(openPlaceResults.get(w));
                        }
                    }

                    adapter = new ResultsAdapter(resultsByType, getActivity());
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
    public void updateDetail(String str) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra("name",str);
        startActivity(intent);
    }
}
