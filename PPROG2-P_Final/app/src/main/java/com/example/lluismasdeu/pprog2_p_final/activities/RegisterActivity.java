package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.StaticValues;
import com.example.lluismasdeu.pprog2_p_final.model.User;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;
import com.example.lluismasdeu.pprog2_p_final.utils.GeneralUtilities;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Actividad encargada del registro de usuario.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class RegisterActivity extends AppCompatActivity {
    private DatabaseManagementInterface dbManagement;

    // Constantes
    private static final String DEFAULT_PHOTO = "default_photo.jpg";
    private static final int TAKE_PICTURE = 1;

    // Componentes
    private ImageView profileImageView;
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText descriptionEditText;
    private CheckBox termsCheckBox;
    private TextView errorTextView;

    /**
     * Método encargado de llevar a cabo las tareas iniciales.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Escondemos la ActionBar en esta actividad.
        getSupportActionBar().hide();

        // Creamos la comunicación con la base de datos.
        dbManagement = new DatabaseManagement(getApplicationContext());

        // Localizamos los componentes en el Layout.
        profileImageView = (ImageView) findViewById(R.id.profile_imageView);
        nameEditText = (EditText) findViewById(R.id.name_editText);
        surnameEditText = (EditText) findViewById(R.id.surname_editText);
        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.passwordRegister_editText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordRegister_editText);
        maleRadioButton = (RadioButton) findViewById(R.id.male_radioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.female_radioButton);
        descriptionEditText = (EditText) findViewById(R.id.description_editText);
        termsCheckBox = (CheckBox) findViewById(R.id.terms_checkBox);
        errorTextView = (TextView) findViewById(R.id.errorRegister_textView);

        // Recuperamos el nombre de usuario y la contraseña enviadas desde la actividad principal.
        getPreviousValues();
        
        // Definimos la imagen de perfil predefinida.
        setDefaultProfilePhoto();
    }

    /**
     * Método encargado de guardar el estado de la actividad.
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Método encargado de llevar a cabo las tareas cuando el usuario pulsa el botón para hacer la
     * foto de perfil.
     * @param view
     */
    public void onTakePictureButtonClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    /**
     * Método encargado de llevar a cabo las tareas cuando el usuario pulsa el botón de registro de
     * usuario.
     * @param view
     */
    public void onRegisterUserButtonClick(View view) {
        String[] messageErrors = getResources().getStringArray(R.array.register_activity_errors);

        if (String.valueOf(nameEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[0]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (String.valueOf(surnameEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[1]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (String.valueOf(emailEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[2]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (String.valueOf(passwordEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[3]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (!String.valueOf(passwordEditText.getText())
                .equals(String.valueOf(confirmPasswordEditText.getText()))) {
            errorTextView.setText(messageErrors[4]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (!termsCheckBox.isChecked()) {
            errorTextView.setText(messageErrors[5]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (dbManagement.existsUser(new User(String.valueOf(emailEditText.getText())), 2)) {
            errorTextView.setText(messageErrors[6]);
            errorTextView.setVisibility(View.VISIBLE);
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("img_").append(GeneralUtilities.getNumberPictures() + 1).append(".jpg");
            String imageFile = builder.toString(), gender = "";
            GeneralUtilities.saveImage(((BitmapDrawable) profileImageView.getDrawable())
                    .getBitmap(), imageFile);

            if (femaleRadioButton.isChecked()) {
                gender = "female";
            } else {
                gender = "male";
            }

            User newUser = new User(String.valueOf(nameEditText.getText()),
                    String.valueOf(surnameEditText.getText()),
                    String.valueOf(emailEditText.getText()),
                    String.valueOf(passwordEditText.getText()),
                    gender, String.valueOf(descriptionEditText.getText()), imageFile);
            dbManagement.addUser(newUser);

            // Obtenemos los parámetros completos del usuario conectado.
            User connectedUser = dbManagement.getUser(new User(String.valueOf(emailEditText.getText()),
                    String.valueOf(passwordEditText.getText())));

            if (connectedUser != null) {
                // Guardamos el usuario actual.
                StaticValues.getInstance(connectedUser);

                // Accedemos a la actividad de búsqueda.
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);

                // Reseteamos los componentes.
                resetComponents();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    // Obtenemos los resultados de la
                    Bundle bundle = data.getExtras();
                    Bitmap image = (Bitmap) bundle.get("data");
                    profileImageView.setImageBitmap(image);
                }
                break;
        }
    }

    /**
     * Método encargado de recuperar los valores introducidos en los campos de usuario y contraseña
     * de la actividad anterior.
     */
    private void getPreviousValues() {
        emailEditText.setText(getIntent().getExtras().getString(MainActivity.USERNAME_EXTRA));
        passwordEditText.setText(getIntent().getExtras().getString(MainActivity.PASSWORD_EXTRA));
    }

    /**
     * Método encargado de cargar la imagen de perfil por defecto.
     */
    private void setDefaultProfilePhoto() {
        InputStream inputStream = null;

        try {
            // Cargamos el recurso.
            AssetManager assetManager = getAssets();
            inputStream = assetManager.open(DEFAULT_PHOTO);

            // Decodificamos la imagen, y la colocamos en el componente.
            Bitmap defaultImage = BitmapFactory.decodeStream(inputStream);
            profileImageView.setImageBitmap(defaultImage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método encargado de resetear la interfaz gráfica de la actividad.
     */
    private void resetComponents() {
        nameEditText.setText("");
        surnameEditText.setText("");
        emailEditText.setText("");
        passwordEditText.setText("");
        confirmPasswordEditText.setText("");
        maleRadioButton.setChecked(false);
        femaleRadioButton.setChecked(false);
        descriptionEditText.setText("");
        termsCheckBox.setChecked(false);
        errorTextView.setText("");
        errorTextView.setVisibility(View.GONE);

        setDefaultProfilePhoto();
    }
}
