package com.example.lluismasdeu.pprog2_p_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Localizamos los componentes en el Layout.
        username = (EditText) findViewById(R.id.username_editText);
        password = (EditText) findViewById(R.id.password_editText);
    }

    public void onLoginButtonClick(View view) {
        // TODO
    }

    public void onRegisterButtonClick(View view) {
        // Intent RegisterActivity
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
