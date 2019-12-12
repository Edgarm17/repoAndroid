package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class activityNuevo extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    TextView tvNewJugador;
    EditText etNombre,etNumero,etTelefono;
    Spinner spPos;
    String nombre,posicion,telefono,numero;

    Jugador j;

    public Typeface fuenteTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        fuenteTitulo = Typeface.createFromAsset(getAssets(),"fuentes/SFSportsNight.ttf");

        tvNewJugador = (TextView)findViewById(R.id.tvNewJugador);
        tvNewJugador.setTypeface(fuenteTitulo);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etNumero = (EditText)findViewById(R.id.etNumero);
        etTelefono = (EditText)findViewById(R.id.etTelefono);
        spPos = (Spinner)findViewById(R.id.spPosicion);





        btn = (Button)findViewById(R.id.btnAddNuevo);
        btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        nombre = etNombre.getText().toString();
        posicion = spPos.getSelectedItem().toString();
        numero = etNumero.getText().toString();
        telefono = etTelefono.getText().toString();

        j = new Jugador(nombre,posicion,telefono,numero);

        Intent i = new Intent(this,activityJugadores.class);
        i.putExtra("newPlayer",j);
        System.out.println("INFO: "+j.getNombre()+" "+j.getPosicion());
        startActivity(i);
        finish();
    }
}
