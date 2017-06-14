package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabsAdapter;
import com.example.lluismasdeu.pprog2_p_final.fragments.ResultsListFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.OpenResultsListFragment;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceLocation;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";

    List<PlaceResult> placeResults;
    List<PlaceResult> openPlaceResults;
    List<String> types;
    private Spinner typesFilter;
    private ResultsListFragment allPlacesFragment;
    private OpenResultsListFragment openPlacesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        getSupportActionBar().hide();

        // Recuperamos valor del spinner de la ActionBar.
        typesFilter = (Spinner) findViewById(R.id.menuSort);

        // Recuperamos la información.
        managePlaceResults();

        // Creamos los Fragments.
        allPlacesFragment = new ResultsListFragment(placeResults);
        openPlacesFragment = new OpenResultsListFragment(openPlaceResults);

        // Creamos las pestañas.
        createTabs();

        // Creamos el adapter.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typesFilter.setAdapter(adapter);
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

        List<TabsAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabsAdapter.TabEntry(allPlacesFragment, getString(R.string.all)));
        entries.add(new TabsAdapter.TabEntry(openPlacesFragment, getString(R.string.only_open)));

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void managePlaceResults() {
        String response = getIntent().getStringExtra(SearchActivity.SEARCH_RESULT_EXTRA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String dateFormatted = dateFormat.format(new Date());

        placeResults = new ArrayList<PlaceResult>();
        openPlaceResults = new ArrayList<PlaceResult>();
        types = new ArrayList<String>();

        try {
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

            for (int i = 0; i < placeResults.size(); i++) {
                if (dateFormatted.compareTo(placeResults.get(i).getOpening()) >= 0
                        && dateFormatted.compareTo(placeResults.get(i).getClosing()) <= 0)
                    openPlaceResults.add(placeResults.get(i));
            }

            boolean flag;

            types.add(getString(R.string.all));

            for (int i = 0; i < placeResults.size(); i++) {
                flag = false;

                for (int j = 0; j < types.size(); j++) {
                    if (placeResults.get(i).getType().equals(types.get(j)))
                        flag = true;
                }

                if (!flag)
                    types.add(placeResults.get(i).getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
