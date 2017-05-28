package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.StaticValues;
import com.example.lluismasdeu.pprog2_p_final.model.User;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;
import com.example.lluismasdeu.pprog2_p_final.utils.GeneralUtilities;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int TAKE_PICTURE = 1;
    private ImageView profileImageView;
    private EditText nameEditText;
    private EditText surnameEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText descriptionEditText;
    private Button takePictureButton;
    private Button saveButton;
    User user;
    List<User> list;
    DatabaseManagementInterface databaseManagementInterface;

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
        saveButton=(Button) findViewById(R.id.button_save);
        setInformation();
        enableFields(false);
    }

    // boton para tomar foto
    public void onTakePictureButtonClick(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    // OnClick para guardar cambios
    public void onSaveClick(View view) {
        //TODO guardar imagen
       databaseManagementInterface=new DatabaseManagement(this);

        if(femaleRadioButton.isChecked()) {
            // user = new User(nameEditText.getText().toString(), surnameEditText.getText().toString(),
             //       femaleRadioButton.getText().toString() ,descriptionEditText.getText().toString());
        } else if(maleRadioButton.isChecked()) {
        //    user = new User(nameEditText.getText().toString(), surnameEditText.getText().toString(),
        //            maleRadioButton.getText().toString() ,descriptionEditText.getText().toString());
        }

        // Añadimos la imagen.
        GeneralUtilities.saveProfilePicture(((BitmapDrawable) profileImageView.getDrawable())
                .getBitmap(), StaticValues.getInstance().getConnectedUser().getImageFile());

       databaseManagementInterface.updateUser(user);
        Toast.makeText(this,getResources().getString(R.string.save),Toast.LENGTH_SHORT).show();
        setInformation();


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
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setClickable(true);
                break;

            default:
                break;
        }

        return true;
    }

    private void setInformation() {
        databaseManagementInterface=new DatabaseManagement(this);
        list=databaseManagementInterface.getAllUsers();
        for(int i =0;i<list.size();i++)
        {
            if(list.get(i).getUsername().equals(StaticValues.getInstance().getConnectedUser().getUsername()))
            {
                nameEditText.setText(list.get(i).getName());
                surnameEditText.setText(list.get(i).getSurname());
                descriptionEditText.setText(list.get(i).getDescription());
                if (list.get(i).getGender().equals(getResources().getString(R.string.male))) {
                    maleRadioButton.setChecked(true);
                    femaleRadioButton.setChecked(false);
                } else if (list.get(i).getGender().equals(getResources().getString(R.string.female))) {
                    maleRadioButton.setChecked(false);
                    femaleRadioButton.setChecked(true);
                } else {
                    maleRadioButton.setChecked(false);
                    femaleRadioButton.setChecked(false);
                }
            }
        }


        Bitmap image = GeneralUtilities.getInstance(this).getDefaultProfilePhoto();

        if (StaticValues.getInstance().getConnectedUser().getImageFile() != null) {
            image = GeneralUtilities.getInstance(this)
                    .getProfilePhoto(StaticValues.getInstance().getConnectedUser().getImageFile());
        }

        if (image != null)
            profileImageView.setImageBitmap(image);


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
