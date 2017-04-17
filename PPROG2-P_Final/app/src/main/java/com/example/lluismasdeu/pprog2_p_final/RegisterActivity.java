package com.example.lluismasdeu.pprog2_p_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText surname;
    private ImageView image;
    private EditText mail;
    private EditText password;
    private EditText repeatPassword;
    private RadioButton male;
    private RadioButton female;
    private CheckBox termins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void RegisteronClick(View view) {

    }
}
