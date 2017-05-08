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
        getSupportActionBar().hide();
        //Recuperamos valor del spinner action bar
        filter=(Spinner) findViewById(R.id.menuFilter);
    }

//TODO opcion para filtro de resultados


}
