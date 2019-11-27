package com.edgar.pmm_1eval_practica5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etCorreo, etPass;
    TextView ePassOlvidada;
    Button btn;
    TextInputLayout inpCorreo, inpPass;
    Switch sw ;


    boolean correo = false;
    boolean pass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        etCorreo = (EditText) findViewById(R.id.idCorreo);
        etPass = (EditText) findViewById(R.id.idPass);
        btn = (Button) findViewById(R.id.button);
        inpCorreo = (TextInputLayout) findViewById(R.id.inpCorreo);
        inpPass = (TextInputLayout) findViewById(R.id.inpPass);
        ePassOlvidada = (TextView)findViewById(R.id.idOlvidarPass);
        sw =  (Switch)findViewById(R.id.switch1);

        btn.setOnClickListener(this);
        ePassOlvidada.setOnClickListener(this);
        sw.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:

                if (!Patterns.EMAIL_ADDRESS.matcher(etCorreo.getText().toString()).matches()){
                    inpCorreo.setError("Correo no válido");
                    correo = false;
                }else{
                    correo = true;
                    inpCorreo.setError(null);
                }

                break;
            case R.id.idOlvidarPass:
                inpPass.setHelperText("Indicio: 1234");

                break;
            case R.id.switch1:
                if (sw.isChecked()){
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;

        }







    }
}
