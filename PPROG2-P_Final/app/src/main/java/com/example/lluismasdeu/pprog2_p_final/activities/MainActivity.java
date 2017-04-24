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
    private EditText username;
    private EditText password;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Localizamos los componentes en el Layout.
        username = (EditText) findViewById(R.id.username_editText);
        password = (EditText) findViewById(R.id.password_editText);
        error = (TextView) findViewById(R.id.error_textView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onLoginButtonClick(View view) {
        // TODO
    }

    public void onRegisterButtonClick(View view) {
        // Intent RegisterActivity
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra(USERNAME_EXTRA, String.valueOf(username.getText()));
        intent.putExtra(PASSWORD_EXTRA, String.valueOf(password.getText()));
        startActivity(intent);
    }
}
