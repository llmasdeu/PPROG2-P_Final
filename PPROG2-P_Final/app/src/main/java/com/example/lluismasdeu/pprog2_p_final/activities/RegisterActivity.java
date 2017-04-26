package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.IOException;
import java.io.InputStream;

/**
 * Actividad encargada del registro de usuario.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class RegisterActivity extends AppCompatActivity {
    // Constantes
    private static final String DEFAULT_PHOTO = "default_photo.jpg";
    private static final int TAKE_PICTURE = 1;

    // Componentes
    private EditText nameEditText;
    private EditText surnameEditText;
    private ImageView profileImageView;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
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

        // Localizamos los componentes en el Layout.
        nameEditText = (EditText) findViewById(R.id.name_editText);
        surnameEditText = (EditText) findViewById(R.id.surname_editText);
        profileImageView = (ImageView) findViewById(R.id.profile_imageView);
        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.passwordRegister_editText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordRegister_editText);
        maleRadioButton = (RadioButton) findViewById(R.id.male_radioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.female_radioButton);
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
        // TODO
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
}
