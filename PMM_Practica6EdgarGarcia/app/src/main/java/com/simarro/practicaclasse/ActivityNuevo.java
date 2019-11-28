package com.simarro.practicaclasse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityNuevo extends AppCompatActivity implements  View.OnClickListener{

    Button btn;
    EditText etNombre,etMat,etAnyo;
    Spinner spTipo;
    String nombre,material,anyo,tipo;
    Robot r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etMat = (EditText)findViewById(R.id.etMaterial);
        etAnyo = (EditText)findViewById(R.id.etAnyo);
        spTipo = (Spinner)findViewById(R.id.spTipo);

        btn = (Button)findViewById(R.id.button);
        nombre = etNombre.getText().toString();
        material = etMat.getText().toString();
        anyo = etAnyo.getText().toString();
        tipo = spTipo.getSelectedItem().toString();

        r = new Robot(nombre,material,anyo,tipo);

        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("robot",r);
        startActivity(i);
    }
}
