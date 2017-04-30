package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.User;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Actividad principal de la aplicación.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class MainActivity extends AppCompatActivity {
    private DatabaseManagementInterface dbManagement;

    // Constantes
    public static final String CONNECTED_USER_EXTRA = "connected_user";
    public static final String NAME_TAG = "name";
    public static final String SURNAME_TAG = "surname";
    public static final String EMAIL_TAG = "email";
    public static final String PASSWORD_TAG = "password";
    public static final String GENDER_TAG = "gender";
    public static final String DESCRIPTION_TAG = "description";
    public static final String USERNAME_EXTRA = "username";
    public static final String PASSWORD_EXTRA = "password";

    // Componentes
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView errorTextView;

    /**
     * Método encargado de llevar a cabo las tareas iniciales.
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
        usernameEditText = (EditText) findViewById(R.id.username_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        errorTextView = (TextView) findViewById(R.id.errorLogin_textView);
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

            // Accedemos a la actividad de búsqueda.
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(CONNECTED_USER_EXTRA, serializeUser(connectedUser));
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
     * Método encargado de serializar el usuario conectado.
     * @param connectedUser Usuario conectado.
     * @return Objeto serializado
     */
    private String serializeUser(User connectedUser) {
        // Serializamos el usuario para poder accederlo desde el resto de la aplicación.
        JSONObject object = new JSONObject();

        try {
            object.put(NAME_TAG, connectedUser.getName());
            object.put(SURNAME_TAG, connectedUser.getSurname());
            object.put(EMAIL_TAG, connectedUser.getEmail());
            object.put(PASSWORD_TAG, connectedUser.getPassword());
            object.put(GENDER_TAG, connectedUser.getGender());
            object.put(DESCRIPTION_TAG, connectedUser.getDescription());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object.toString();
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
