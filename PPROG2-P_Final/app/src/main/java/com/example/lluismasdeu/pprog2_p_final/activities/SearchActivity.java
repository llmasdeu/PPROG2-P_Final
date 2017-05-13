package com.example.lluismasdeu.pprog2_p_final.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.fragments.NoRecentSearchesFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.RecentSearchesFragment;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import java.util.List;

/**
 * Created by lluismasdeu on 20/4/17.
 */
public class SearchActivity extends AppCompatActivity {
    private DatabaseManagementInterface dbManagement;

    // Componentes y estructuras
    private EditText searchEditText;
    private ImageButton clearImageButton;
    private SeekBar radiusSeekBar;
    private TextView radiusKmTextView;
    private ListView recentSearchesListView;
    private List<String> recentSearchesList;
    private NoRecentSearchesFragment noRecentSearchesFragment;
    private RecentSearchesFragment recentSearchesFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setTitle("");

        // Localizamos los componentes en el layout.
        searchEditText = (EditText) findViewById(R.id.search_editText);
        clearImageButton = (ImageButton) findViewById(R.id.clear_imageButton);
        radiusSeekBar = (SeekBar) findViewById(R.id.radius_seekBar);
        radiusKmTextView = (TextView) findViewById(R.id.radius_textView);
        recentSearchesListView = (ListView) findViewById(R.id.recentSearches_listView);

        // Inicializamos el gestor de la base de datos.
        dbManagement = new DatabaseManagement(this);

        // Obtenemos las búsquedas recientes.
        updateRecentSearchesList();

        // Inicializamos los fragmentos.
        noRecentSearchesFragment = new NoRecentSearchesFragment();
        recentSearchesFragment = new RecentSearchesFragment(this, recentSearchesList);

        // Comprobamos qué fragment hay que mostrar.
        manageRecentSearchesFragments();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Actualizamos la lista de búsquedas recientes.
        updateRecentSearchesList();

        // Comprobamos qué fragment hay que mostrar.
        manageRecentSearchesFragments();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostramos actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onLocationSearchButtonClick(View view) {
        // TODO
    }

    public void onClearButtonClick(View view) {
        // TODO
    }

    public void onSearchButtonClick(View view) {
        // TODO
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                // Intent para ingresar al perfil
                Intent intentPerfil = new Intent(this, ProfileActivity.class);
                startActivity(intentPerfil);
                break;

            case R.id.action_favorite:
                //intent para ingresar a favoritos
                Intent intentFavorite = new Intent(this, FavoriteActivity.class);
                startActivity(intentFavorite);
                break;

            default:
                break;
        }

        return true;
    }

    private void updateRecentSearchesList() {
        recentSearchesList = dbManagement.getAllRecentSearches();
    }

    private void manageRecentSearchesFragments() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (recentSearchesList.isEmpty()) {
            transaction.replace(R.id.recentSearches_frameLayout, noRecentSearchesFragment);
        } else {
            transaction.replace(R.id.recentSearches_frameLayout, recentSearchesFragment);
        }

        transaction.commit();
    }
}
