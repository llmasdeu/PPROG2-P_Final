package com.example.lluismasdeu.pprog2_p_final.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;

/**
 * Created by lluismasdeu on 20/4/17.
 */

public class SearchActivity extends AppCompatActivity {
    private EditText search;
    private ImageButton clear;
    private SeekBar radius;
    private TextView radiusKm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        search = (EditText) findViewById(R.id.search_editText);
        clear = (ImageButton) findViewById(R.id.clear_imageButton);
        radius = (SeekBar) findViewById(R.id.radius_seekBar);
        radiusKm = (TextView) findViewById(R.id.radius_textView);
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
}
