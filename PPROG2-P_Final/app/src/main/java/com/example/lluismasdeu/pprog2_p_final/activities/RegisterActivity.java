package com.example.lluismasdeu.pprog2_p_final.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;

import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    // Constantes
    private static final String USERNAME_EXTRA = "username";
    private static final String PASSWORD_EXTRA = "password";

    // Componentes
    private EditText name;
    private EditText surname;
    private ImageView profileImage;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private RadioButton male;
    private RadioButton female;
    private CheckBox terms;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Escondemos la ActionBar en esta actividad.
        getSupportActionBar().hide();

        // Localizamos los componentes en el Layout.
        name = (EditText) findViewById(R.id.name_editText);
        surname = (EditText) findViewById(R.id.surname_editText);
        profileImage = (ImageView) findViewById(R.id.profile_imageView);
        email = (EditText) findViewById(R.id.email_editText);
        password = (EditText) findViewById(R.id.passwordRegister_editText);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordRegister_editText);
        male = (RadioButton) findViewById(R.id.male_radioButton);
        female = (RadioButton) findViewById(R.id.female_radioButton);
        terms = (CheckBox) findViewById(R.id.terms_checkBox);
        error = (TextView) findViewById(R.id.errorRegister_textView);

        // Recuperamos el nombre de usuario y la contraseña enviadas desde la actividad principal.
        getPreviousValues();
        
        // Definimos la imagen de perfil predefinida.
        setDefaultProfilePhoto();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onTakePictureButtonClick(View view) {
        // TODO
    }

    public void onRegisterUserButtonClick(View view) {
        // TODO
    }

    /**
     * Método encargado de recuperar los valores introducidos en los campos de usuario y contraseña
     * de la actividad anterior.
     */
    private void getPreviousValues() {
        email.setText(getIntent().getExtras().getString(USERNAME_EXTRA));
        password.setText(getIntent().getExtras().getString(PASSWORD_EXTRA));
    }

    /**
     * Método encargado de cargar la imagen de perfil por defecto.
     */
    private void setDefaultProfilePhoto() {
        InputStream iStream = getResources().openRawResource(R.raw.basic_profile_photo);
        Bitmap defaultImage = BitmapFactory.decodeStream(iStream);

        profileImage.setImageBitmap(defaultImage);
    }
}
