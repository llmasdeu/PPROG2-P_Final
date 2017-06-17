package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
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
import com.example.lluismasdeu.pprog2_p_final.fragments.OpenRestaurantsListFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.RestaurantsListFragment;
import com.example.lluismasdeu.pprog2_p_final.model.Restaurant;
import com.example.lluismasdeu.pprog2_p_final.model.StaticValues;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private DatabaseManagementInterface dbManagement;
    private List<Restaurant> favoritesList;
    private List<Restaurant> openFavoritesList;
    private List<String> typesList;
    private Spinner typesSpinner;
    private RestaurantsListFragment favoritesListFragment;
    private OpenRestaurantsListFragment openFavoritesListFragment;

    /**
     * Método encaragado de llevar a cabo las tareas cuando se crea la actividad.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Definimos el Layout de la actividad.
        setContentView(R.layout.activity_favorites);

        // Escondemos la ActionBar en esta actividad.
        getSupportActionBar().hide();

        // Recuperamos valor del spinner de la ActionBar.
        typesSpinner = (Spinner) findViewById(R.id.restaurantTypes_spinner);

        // Recuperamos la información.
        manageResults();

        // Creamos el conector con la base de datos.
        dbManagement = new DatabaseManagement(this);

        // Creamos los Fragments.
        favoritesListFragment = new RestaurantsListFragment(favoritesList);
        openFavoritesListFragment = new OpenRestaurantsListFragment(openFavoritesList);

        // Configuramos el listener del spinner de los tipos de restaurantes.
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
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, typesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typesSpinner.setAdapter(adapter);
    }

    /**
     * Método encargado de guardar el estado actual de la actividad.
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Método encargado de reestablecer el estado actual de la actividad.
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Método encargado de acceder a la actividad de perfil.
     * @param view
     */
    public void onClickProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Método encargado de acceder a la actividad de favoritos.
     * @param view
     */
    public void onClickFavorite(View view){
        Intent intent = new Intent(this,FavoritesActivity.class);
        startActivity(intent);
    }

    /**
     * Método encargado de crear las pestañas.
     */
    private void createTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.results_tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.results_viewPager);

        List<TabsAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabsAdapter.TabEntry(favoritesListFragment, getString(R.string.all)));
        entries.add(new TabsAdapter.TabEntry(openFavoritesListFragment, getString(R.string.only_open)));

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Método encargado de gestionar la información obtenida del Webservice.
     */
    private void manageResults() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String dateFormatted = dateFormat.format(new Date());

        favoritesList = dbManagement.getAllFavorites(StaticValues.getInstance().getConnectedUser());
        openFavoritesList = new ArrayList<Restaurant>();
        typesList = new ArrayList<String>();

        // Guardamos en una lista separada los restaurantes abiertos.
        for (int i = 0; i < favoritesList.size(); i++) {
            if (dateFormatted.compareTo(favoritesList.get(i).getOpening()) >= 0
                    && dateFormatted.compareTo(favoritesList.get(i).getClosing()) <= 0)
                openFavoritesList.add(favoritesList.get(i));
        }

        boolean flag;

        typesList.add(getString(R.string.all));

        // Creamos el listado de tipos de restaurantes.
        for (int i = 0; i < favoritesList.size(); i++) {
            flag = false;

            for (int j = 0; j < typesList.size(); j++) {
                if (favoritesList.get(i).getType().equals(typesList.get(j)))
                    flag = true;
            }

            if (!flag)
                typesList.add(favoritesList.get(i).getType());
        }
    }

    /**
     * Método encargado de actualizar el listado de restaurantes por el tipo seleccionado.
     */
    private void updateRestaurantsByType() {
        if (getString(R.string.filter) != typesSpinner.getSelectedItem()) {
            List<Restaurant> favoritesByType = new ArrayList<Restaurant>();
            List<Restaurant> openFavoritesByType = new ArrayList<Restaurant>();

            if (typesSpinner.getSelectedItem().equals(getString(R.string.all))) {
                favoritesByType = favoritesList;
                openFavoritesByType = openFavoritesList;
            } else {
                for (int i = 0; i < favoritesList.size(); i++) {
                    if (typesSpinner.getSelectedItem().equals(favoritesList.get(i).getType()))
                        favoritesByType.add(favoritesList.get(i));
                }

                for (int i = 0; i < openFavoritesList.size(); i++) {
                    if (typesSpinner.getSelectedItem().equals(openFavoritesList.get(i).getType()))
                        openFavoritesByType.add(openFavoritesList.get(i));
                }
            }

            favoritesListFragment.updateRestaurantsList(favoritesByType);
            openFavoritesListFragment.updateRestaurantsList(openFavoritesByType);
        }
    }
}
