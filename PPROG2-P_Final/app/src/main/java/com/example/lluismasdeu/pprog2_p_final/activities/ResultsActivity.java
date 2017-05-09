package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.example.lluismasdeu.pprog2_p_final.R;

public class ResultsActivity extends AppCompatActivity {

    Spinner filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().hide();
        //Recuperamos valor del spinner action bar
        filter=(Spinner) findViewById(R.id.menuSort);

    }
    // Intenet para ProfileActivity
    public void onClickProfile(View view){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    //Intent para Favorite Activity
    public void onClickFavorite(View view){
        Intent intent=new Intent(this,FavoriteActivity.class);
        startActivity(intent);
    }

//TODO opcion para filtro de resultados


}
