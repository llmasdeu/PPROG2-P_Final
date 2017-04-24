package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;

public class MainActivity extends AppCompatActivity {
    // Constantes
    private static final String USERNAME_EXTRA = "username";
    private static final String PASSWORD_EXTRA = "password";

    // Componentes
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Escondemos la ActionBar en esta actividad.
        getSupportActionBar().hide();

        // Localizamos los componentes en el Layout.
        usernameEditText = (EditText) findViewById(R.id.username_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        errorTextView = (TextView) findViewById(R.id.errorLogin_textView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onLoginButtonClick(View view) {
        String[] messageErrors = getResources().getStringArray(R.array.main_activity_errors);

        if (String.valueOf(usernameEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[0]);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (String.valueOf(passwordEditText.getText()).equals("")) {
            errorTextView.setText(messageErrors[1]);
            errorTextView.setVisibility(View.VISIBLE);
        } else {
            // TODO
            //  - Comprobar credenciales en la base de datos.
            //  - Acceso al resto de la aplicación.
        }
    }

    /**
     * Método encargado de
     * @param view
     */
    public void onRegisterButtonClick(View view) {
        // Intent RegisterActivity
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra(USERNAME_EXTRA, String.valueOf(usernameEditText.getText()));
        intent.putExtra(PASSWORD_EXTRA, String.valueOf(passwordEditText.getText()));
        startActivity(intent);

        // Reseteamos los componentes.
        resetComponents();
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
