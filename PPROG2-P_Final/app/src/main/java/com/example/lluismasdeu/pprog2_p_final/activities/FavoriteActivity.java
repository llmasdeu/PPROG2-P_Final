package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.lluismasdeu.pprog2_p_final.R;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setTitle("");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostramos actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_favorite, menu);

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


            default:
                break;
        }

        return true;
    }
}
