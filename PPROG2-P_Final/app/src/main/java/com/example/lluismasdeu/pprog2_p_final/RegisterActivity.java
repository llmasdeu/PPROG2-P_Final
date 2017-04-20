package com.example.lluismasdeu.pprog2_p_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText surname;
    private ImageView profileImage;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private RadioButton male;
    private RadioButton female;
    private CheckBox terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

    public void orRegisterUserButtonClick(View view) {
        // TODO
    }

    /**
     * Método encargada de cargar la imagen de perfil por defecto.
     */
    private void setDefaultProfilePhoto() {
        InputStream iStream = getResources().openRawResource(R.raw.basic_profile_photo);
        Bitmap defaultImage = BitmapFactory.decodeStream(iStream);

        profileImage.setImageBitmap(defaultImage);
    }
}
