package com.example.lluismasdeu.pprog2_p_final.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.lluismasdeu.pprog2_p_final.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostrar actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_profile, menu);
        return true;
    }
}