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
    private int radiusKm;

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

        // Iniciamos a 0 la barra de progreso de la SeekBar, y configuramos el mensaje del radio a
        // 1 Km.
        radiusKm = 1;
        radiusSeekBar.setProgress(0);
        radiusKmTextView.setText(radiusKm + getString(R.string.radius_km));

        // Añadimos el listener a la SeekBar.
        radiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 0 && progress <= 10) {
                    radiusKm = 1;
                } else if (progress > 10 && progress <= 20) {
                    radiusKm = 2;
                } else if (progress > 20 && progress <= 30) {
                    radiusKm = 3;
                } else if (progress > 30 && progress <= 40) {
                    radiusKm = 4;
                } else if (progress > 40 && progress <= 50) {
                    radiusKm = 5;
                } else if (progress > 50 && progress <= 60) {
                    radiusKm = 6;
                } else if (progress > 60 && progress <= 70) {
                    radiusKm = 7;
                } else if (progress > 70 && progress <= 80) {
                    radiusKm = 8;
                } else if (progress > 80 && progress <= 90) {
                    radiusKm = 9;
                } else {
                    radiusKm = 10;
                }

                radiusKmTextView.setText(radiusKm + getString(R.string.radius_km));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
