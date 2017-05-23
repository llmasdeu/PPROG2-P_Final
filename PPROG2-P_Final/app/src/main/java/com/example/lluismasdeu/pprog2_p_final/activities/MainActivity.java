package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.StaticValues;
import com.example.lluismasdeu.pprog2_p_final.model.User;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;

import java.io.IOException;
import java.io.InputStream;

/**
 * Actividad principal de la aplicación.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class MainActivity extends AppCompatActivity {
    private DatabaseManagementInterface dbManagement;

    // Constantes
    private static final String APP_LOGO = "app_logo.png";
    public static final String USERNAME_EXTRA = "username";
    public static final String PASSWORD_EXTRA = "password";

    // Componentes
    private ImageView logoImageView;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView errorTextView;

    /**
     * Método encargado de crear la actividad.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Escondemos la ActionBar en esta actividad.
        getSupportActionBar().hide();

        // Creamos la comunicación con la base de datos.
        dbManagement = new DatabaseManagement(getApplicationContext());

        // Localizamos los componentes en el Layout.
        logoImageView = (ImageView) findViewById(R.id.logo_imageView);
        usernameEditText = (EditText) findViewById(R.id.username_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        errorTextView = (TextView) findViewById(R.id.errorLogin_textView);

        // Añadimos el logo de la aplicación.
        setAppLogo();
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
     * Método encargado de reestablecer el estado de la actividad.
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Método encargado de llevar a cabo las tareas cuando el usuario pula el botón de inicio de
     * sesión.
     * @param view
     */
    public void onLoginButtonClick(View view) {
        String[] messageErrors = getResources().getStringArray(R.array.main_activity_errors);

        if (String.valueOf(usernameEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[0]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (String.valueOf(passwordEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[1]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (!dbManagement.existsUser(new User(String.valueOf(usernameEditText.getText()),
                String.valueOf(passwordEditText.getText())), 1)) {
            errorTextView.setText(messageErrors[2]);
            errorTextView.setVisibility(View.VISIBLE);
        } else {
            // Obtenemos los parámetros completos del usuario conectado.
            User connectedUser = dbManagement.getUser(new User(String.valueOf(usernameEditText.getText()),
                    String.valueOf(passwordEditText.getText())));

            // Guardamos el usuario actual.
            StaticValues.getInstance().setConnectedUser(connectedUser);

            // Mostramos mensaje por pantalla.
            Toast.makeText(this, getString(R.string.correct_login), Toast.LENGTH_SHORT).show();

            // Accedemos a la actividad de búsqueda.
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);

            // Reseteamos los componentes.
            resetComponents();
        }
    }

    /**
     * Método encargado de llevar a cabo las tareas cuando el usuario pulsa el botón de registro de
     * usuario.
     * @param view
     */
    public void onRegisterButtonClick(View view) {
        // Accedemos a la actividad de registro.
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra(USERNAME_EXTRA, String.valueOf(usernameEditText.getText()));
        intent.putExtra(PASSWORD_EXTRA, String.valueOf(passwordEditText.getText()));
        startActivity(intent);

        // Reseteamos los componentes.
        resetComponents();
    }

    /**
     * Método encargada de posicionar el logo de la aplicación.
     */
    private void setAppLogo() {
        InputStream inputStream = null;

        try {
            // Cargamos el recurso.
            AssetManager assetManager = getAssets();
            inputStream = assetManager.open(APP_LOGO);

            // Decodificamos la imagen, y la colocamos en el componente.
            Bitmap defaultImage = BitmapFactory.decodeStream(inputStream);
            logoImageView.setImageBitmap(defaultImage);
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
        usernameEditText.setText("");
        passwordEditText.setText("");
        errorTextView.setText("");
        errorTextView.setVisibility(View.GONE);
    }
}
