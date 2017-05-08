package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.lluismasdeu.pprog2_p_final.R;

public class ProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText surnameEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText descriptionEditText;
    private Button pictureButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("");


        nameEditText = (EditText) findViewById(R.id.name_editText);
        surnameEditText=(EditText) findViewById(R.id.surname_editText);
        maleRadioButton=(RadioButton) findViewById(R.id.male_radioButton);
        femaleRadioButton=(RadioButton) findViewById(R.id.female_radioButton);
        descriptionEditText=(EditText) findViewById(R.id.description_editText);
        pictureButton=(Button) findViewById(R.id.Button_picture);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostrar actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_profile, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                // Intent para ingresar al perfil
                Intent intentPerfil = new Intent(this, ProfileActivity.class);
                startActivity(intentPerfil);
                break;

            case R.id.action_edit:
                //Vuelve editable los campos
                nameEditText.setEnabled(true);
                surnameEditText.setEnabled(true);
                maleRadioButton.setEnabled(true);
                femaleRadioButton.setEnabled(true);
                descriptionEditText.setEnabled(true);
                pictureButton.setVisibility(View.VISIBLE);
                pictureButton.setClickable(true);

                break;

            default:
                break;
        }

        return true;
    }

}
