package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabsAdapter;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragmentOpen;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceLocation;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";
    private static final String SEARCH_RESULT_EXTRA = "search_result";

    private List<PlaceResult> placeResults;
    private Spinner filter;
    private String searchParameter = "Search";
    List<String> types;
    JsonArrayRequest jsArrayRequest;
    private ListViewFragment allPlacesFragment;
    private ListViewFragmentOpen openPlacesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        getSupportActionBar().hide();

        //Recuperamos valor del spinner de la ActionBar.
        filter = (Spinner) findViewById(R.id.menuSort);

        managePlaceResults();

        // Creamos las pestañas.
        createTabs();
        //searchParameter = getIntent().getStringExtra(SEARCH_RESULT_EXTRA);

        types = new ArrayList<String>();
        /*
        //llenamos spinner
        types.add(getString(R.string.filter));
        String url ="http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&";
        RequestQueue queue = Volley.newRequestQueue(this);
        jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+searchParameter,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONArray search = response;
                            for (int i = 0; i < search.length(); i++) {
                                if(!types.contains(search.getJSONObject(i).getString("type")))
                                {

                                    types.add(search.getJSONObject(i).getString("type"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );


        queue.add(jsArrayRequest);
        */
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapter);

    }

    // Intenet para ProfileActivity
    public void onClickProfile(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    //Intent para Favorite Activity
    public void onClickFavorite(View view){
        Intent intent = new Intent(this,FavoritesActivity.class);
        startActivity(intent);
    }

    private void createTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        allPlacesFragment = new ListViewFragment(placeResults);
        List<TabsAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabsAdapter.TabEntry(allPlacesFragment, getString(R.string.all)));
        entries.add(new TabsAdapter.TabEntry(new ListViewFragmentOpen(), getString(R.string.only_open)));

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void managePlaceResults() {
        String response = getIntent().getStringExtra(SearchActivity.SEARCH_RESULT_EXTRA);
        placeResults = new ArrayList<PlaceResult>();

        /*
        public PlaceResult(String name, String type, PlaceLocation placeLocation, String address,
                       String opening, String closing, double review, String description) {
            this.name = name;
            this.type = type;
            this.placeLocation = placeLocation;
            this.address = address;
            this.opening = opening;
            this.closing = closing;
            this.review = review;
            this.description = description;
        }
         */

        try {
            Log.d(TAG, "managePlaceResults: " + response);
            JSONArray placesArray = new JSONArray(response);
            JSONObject location;

            PlaceResult placeResult;
            PlaceLocation placeLocation;

            String name, type, address, opening, closing, description;
            double review, latitude, longitude;

            for (int i = 0; i < placesArray.length(); i++) {
                name = placesArray.getJSONObject(i).getString("name");
                type = placesArray.getJSONObject(i).getString("type");
                location = placesArray.getJSONObject(i).getJSONObject("location");
                latitude = location.getDouble("lat");
                longitude = location.getDouble("lng");
                address = placesArray.getJSONObject(i).getString("address");
                opening = placesArray.getJSONObject(i).getString("opening");
                closing = placesArray.getJSONObject(i).getString("closing");
                review = placesArray.getJSONObject(i).getDouble("review");
                description = placesArray.getJSONObject(i).getString("description");

                placeLocation = new PlaceLocation(latitude, longitude);
                placeResult = new PlaceResult(name, type, placeLocation, address, opening, closing,
                        review, description);
                placeResults.add(placeResult);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMyData() {
        return searchParameter;
    }
}
