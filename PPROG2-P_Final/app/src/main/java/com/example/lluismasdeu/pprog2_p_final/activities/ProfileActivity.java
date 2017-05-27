package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.StaticValues;
import com.example.lluismasdeu.pprog2_p_final.utils.GeneralUtilities;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";

    private ImageView profileImageView;
    private EditText nameEditText;
    private EditText surnameEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText descriptionEditText;
    private Button takePictureButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("");

        profileImageView = (ImageView) findViewById(R.id.profile_imageView);
        nameEditText = (EditText) findViewById(R.id.name_editText);
        surnameEditText = (EditText) findViewById(R.id.surname_editText);
        maleRadioButton = (RadioButton) findViewById(R.id.male_radioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.female_radioButton);
        descriptionEditText = (EditText) findViewById(R.id.description_editText);
        takePictureButton = (Button) findViewById(R.id.Button_picture);

        setInformation();
        enableFields(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostrar actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // Intent para ingresar al perfil
                Intent intentPerfil = new Intent(this, FavoritesActivity.class);
                startActivity(intentPerfil);
                break;

            case R.id.action_edit:
                //Vuelve editable los campos
                nameEditText.setEnabled(true);
                surnameEditText.setEnabled(true);
                maleRadioButton.setEnabled(true);
                femaleRadioButton.setEnabled(true);
                descriptionEditText.setEnabled(true);
                takePictureButton.setVisibility(View.VISIBLE);
                takePictureButton.setClickable(true);

                break;

            default:
                break;
        }

        return true;
    }

    private void setInformation() {
        nameEditText.setText(StaticValues.getInstance().getConnectedUser().getName());
        surnameEditText.setText(StaticValues.getInstance().getConnectedUser().getSurname());
        descriptionEditText.setText(StaticValues.getInstance().getConnectedUser().getDescription());

        Bitmap image = GeneralUtilities.getInstance(this).getDefaultProfilePhoto();

        if (StaticValues.getInstance().getConnectedUser().getImageFile() != null) {
            image = GeneralUtilities.getInstance(this)
                    .getProfilePhoto(StaticValues.getInstance().getConnectedUser().getImageFile());
        }

        if (image != null)
            profileImageView.setImageBitmap(image);

        if (StaticValues.getInstance().getConnectedUser().getGender().equals("male")) {
            maleRadioButton.setChecked(true);
            femaleRadioButton.setChecked(false);
        } else if (StaticValues.getInstance().getConnectedUser().getGender().equals("female")) {
            maleRadioButton.setChecked(false);
            femaleRadioButton.setChecked(true);
        } else {
            maleRadioButton.setChecked(false);
            femaleRadioButton.setChecked(false);
        }
    }

    private void enableFields(boolean flag) {
        if (flag) {
            takePictureButton.setVisibility(View.VISIBLE);
        } else {
            takePictureButton.setVisibility(View.GONE);
        }

        nameEditText.setEnabled(flag);
        surnameEditText.setEnabled(flag);
        maleRadioButton.setEnabled(flag);
        femaleRadioButton.setEnabled(flag);
        descriptionEditText.setEnabled(flag);
    }
}
