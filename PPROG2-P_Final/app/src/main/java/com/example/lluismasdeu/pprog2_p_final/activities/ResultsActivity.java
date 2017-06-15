package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabsAdapter;
import com.example.lluismasdeu.pprog2_p_final.fragments.ResultsListFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.OpenResultsListFragment;
import com.example.lluismasdeu.pprog2_p_final.model.Restaurant;
import com.example.lluismasdeu.pprog2_p_final.utils.GeneralUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Actividad de resultados de la aplicación.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";

    List<Restaurant> restaurantsList;
    List<Restaurant> openRestaurantsList;
    List<String> types;
    private Spinner typesSpinner;
    private ResultsListFragment resultsListFragment;
    private OpenResultsListFragment openResultsListFragment;

    /**
     * Método encaragado de llevar a cabo las tareas cuando se crea la actividad.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        getSupportActionBar().hide();

        // Recuperamos valor del spinner de la ActionBar.
        typesSpinner = (Spinner) findViewById(R.id.menuSort);

        // Recuperamos la información.
        manageResults();

        // Creamos los Fragments.
        resultsListFragment = new ResultsListFragment(restaurantsList);
        openResultsListFragment = new OpenResultsListFragment(openRestaurantsList);

        // Configuramos el spinner de los tipos.
        typesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateRestaurantsByType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // Creamos las pestañas.
        createTabs();

        // Creamos el adapter.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typesSpinner.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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
        entries.add(new TabsAdapter.TabEntry(resultsListFragment, getString(R.string.all)));
        entries.add(new TabsAdapter.TabEntry(openResultsListFragment, getString(R.string.only_open)));

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void manageResults() {
        String response = getIntent().getStringExtra(SearchActivity.SEARCH_RESULT_EXTRA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String dateFormatted = dateFormat.format(new Date());

        restaurantsList = new ArrayList<Restaurant>();
        openRestaurantsList = new ArrayList<Restaurant>();
        types = new ArrayList<String>();

        try {
            JSONArray placesArray = new JSONArray(response);
            JSONObject place, location;
            Restaurant restaurant;
            String name, type, address, opening, closing, description;
            double review, latitude, longitude;

            for (int i = 0; i < placesArray.length(); i++) {
                place = placesArray.getJSONObject(i);
                name = place.getString("name");
                type = place.getString("type");
                location = place.getJSONObject("location");
                latitude = location.getDouble("lat");
                longitude = location.getDouble("lng");
                address = place.getString("address");
                opening = place.getString("opening");
                closing = place.getString("closing");
                review = place.getDouble("review");
                description = place.getString("description");

                // Guardamos los datos del restaurante.
                restaurant = new Restaurant(name, type, address, opening, closing, review,
                        description, latitude, longitude);

                // Asignamos una imagen de manera aleatoria.
                restaurant.setImage(GeneralUtilities.getInstance(this).getRestaurantImage());

                // Añadimos el restaurante a la lista.
                restaurantsList.add(restaurant);
            }

            // Guardamos en una lista separada los restaurantes abiertos.
            for (int i = 0; i < restaurantsList.size(); i++) {
                if (dateFormatted.compareTo(restaurantsList.get(i).getOpening()) >= 0
                        && dateFormatted.compareTo(restaurantsList.get(i).getClosing()) <= 0)
                    openRestaurantsList.add(restaurantsList.get(i));
            }

            boolean flag;

            types.add(getString(R.string.all));

            // Creamos el listado de tipos de restaurantes.
            for (int i = 0; i < restaurantsList.size(); i++) {
                flag = false;

                for (int j = 0; j < types.size(); j++) {
                    if (restaurantsList.get(i).getType().equals(types.get(j)))
                        flag = true;
                }

                if (!flag)
                    types.add(restaurantsList.get(i).getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateRestaurantsByType() {
        if (getString(R.string.filter) != typesSpinner.getSelectedItem()) {
            List<Restaurant> restaurantsByType = new ArrayList<Restaurant>();
            List<Restaurant> openRestaurantsByType = new ArrayList<Restaurant>();

            if (typesSpinner.getSelectedItem().equals(getString(R.string.all))) {
                restaurantsByType = restaurantsList;
                openRestaurantsByType = openRestaurantsList;
            } else {
                for (int i = 0; i < restaurantsList.size(); i++) {
                    if (typesSpinner.getSelectedItem().equals(restaurantsList.get(i).getType()))
                        restaurantsByType.add(restaurantsList.get(i));
                }

                for (int i = 0; i < openRestaurantsList.size(); i++) {
                    if (typesSpinner.getSelectedItem().equals(openRestaurantsList.get(i).getType()))
                        openRestaurantsByType.add(openRestaurantsList.get(i));
                }
            }

            resultsListFragment.updateRestaurantsList(restaurantsByType);
            openResultsListFragment.updateRestaurantsList(openRestaurantsByType);
        }

    }
}
