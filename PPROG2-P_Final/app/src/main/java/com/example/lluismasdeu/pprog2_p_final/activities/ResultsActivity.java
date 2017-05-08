package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.lluismasdeu.pprog2_p_final.R;

public class ResultsActivity extends AppCompatActivity {

    Spinner filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setTitle("");
        //Recuperamos valor del spinner action bar
        filter=(Spinner) findViewById(R.id.menuFilter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostramos actionBar

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_results, menu);

        return true;
    }
//TODO opcion para filtro de resultados

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
