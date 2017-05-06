package com.example.lluismasdeu.pprog2_p_final.activities;

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

/**
 * Created by lluismasdeu on 20/4/17.
 */
public class SearchActivity extends AppCompatActivity {
    private String connectedUser;

    // Componentes
    private EditText search;
    private ImageButton clear;
    private SeekBar radius;
    private TextView radiusKm;
    private ListView recentSearches;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        search = (EditText) findViewById(R.id.search_editText);
        clear = (ImageButton) findViewById(R.id.clear_imageButton);
        radius = (SeekBar) findViewById(R.id.radius_seekBar);
        radiusKm = (TextView) findViewById(R.id.radius_textView);
        recentSearches = (ListView) findViewById(R.id.recentSearches_listView);
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
}
