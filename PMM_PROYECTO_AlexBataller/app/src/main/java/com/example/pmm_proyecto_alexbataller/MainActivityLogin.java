package com.example.pmm_proyecto_alexbataller;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivityLogin extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private TextInputLayout tilEmail;
    private TextInputLayout tilPasswd;
    private EditText email;
    private EditText passwd;
    private Button btnEntrar;
    private Switch mostrarPasswd;
    private TextView mostrarPasswdTxt;
    private TextView olvidarPasswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        tilEmail = findViewById(R.id.til_email);
        tilPasswd = findViewById(R.id.til_passwd);
        email = findViewById(R.id.email);
        passwd = findViewById(R.id.passwd);
        btnEntrar = findViewById(R.id.btnEntrar);

        mostrarPasswd = findViewById(R.id.mostrarPasswd);

        olvidarPasswd = findViewById(R.id.olvidarPasswd);
        mostrarPasswdTxt = findViewById(R.id.olvidarPasswdShowText);

        mostrarPasswd.setOnCheckedChangeListener(this);

        btnEntrar.setOnClickListener(this);
        olvidarPasswd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnEntrar) {
            if (!Pattern.matches("[A-Za-z0-9_]+@[A-Za-z0-9_]+\\.[a-z]+", email.getText())) {
                tilEmail.setError(getString(R.string.email_error));
            } else {
                if (!Pattern.matches("[A-Za-z0-9]{4,}", passwd.getText())) {
                    tilPasswd.setError(getString(R.string.passwd_error));

                } else {
                    tilPasswd.setError(null);

                    Bundle b = new Bundle();
                    b.putString("username", tilEmail.getEditText().getText().toString());
                    Intent intent = new Intent(this, RecyclerViewContactos.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }
                tilEmail.setError(null);
            }

        } else if (view.getId() == R.id.olvidarPasswd) {
            mostrarPasswdTxt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            passwd.setTransformationMethod(new HideReturnsTransformationMethod());
        } else {
            passwd.setTransformationMethod(new PasswordTransformationMethod());
        }
    }
}
