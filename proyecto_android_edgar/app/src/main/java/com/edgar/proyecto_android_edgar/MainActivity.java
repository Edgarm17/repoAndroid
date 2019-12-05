package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etCorreo, etPass;
    TextInputLayout inpCorreo,inpPass;
    TextView tvPassForget,tvTitulo;
    Button btn;
    Switch swPass;

    boolean correo,pass;

    public Typeface fuenteTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = false;
        pass = false;

        etCorreo = (EditText)findViewById(R.id.etCorreo);
        etPass = (EditText)findViewById(R.id.etPass);
        inpCorreo = (TextInputLayout) findViewById(R.id.inpCorreo);
        inpPass = (TextInputLayout)findViewById(R.id.inpPass);
        tvPassForget = (TextView)findViewById(R.id.tvPassForget);
        tvTitulo = (TextView)findViewById(R.id.tvLogin);
        btn = (Button)findViewById(R.id.btnLogin);
        swPass = (Switch)findViewById(R.id.swShow);

        btn.setOnClickListener(this);
        tvPassForget.setOnClickListener(this);
        swPass.setOnClickListener(this);

        fuenteTitulo = Typeface.createFromAsset(getAssets(),"fuentes/SFSportsNight.ttf");
        tvTitulo.setTypeface(fuenteTitulo);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:

                if (!Patterns.EMAIL_ADDRESS.matcher(etCorreo.getText().toString()).matches()){
                    if (tvPassForget.getText().toString().equals("Has olvidado la contraseña?")){
                        inpCorreo.setError("Correo no válido! Ejemplo: example@mail.com");
                    }else{
                        inpCorreo.setError("Email not valid! Use: example@mail.com");
                    }

                    correo = false;
                    inpPass.setHelperText(null);
                }else{
                    correo = true;
                    inpCorreo.setError(null);
                    inpPass.setHelperText(null);
                    Intent i = new Intent(this,splashCargaMenu.class);
                    Bundle contenedor = new Bundle();
                    String[] partesCorreo = etCorreo.getText().toString().split("@");

                    contenedor.putString("name",partesCorreo[0]);
                    i.putExtras(contenedor);
                    startActivity(i);
                }

                break;
            case R.id.tvPassForget:
                if (tvPassForget.getText().toString().equals("Has olvidado la contraseña?")){
                    inpPass.setHelperText("Indicio: 1234");
                }else{
                    inpPass.setHelperText("Try with: 1234");
                }

                break;
            case R.id.swShow:
                if (swPass.isChecked()){
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }

    }
}
