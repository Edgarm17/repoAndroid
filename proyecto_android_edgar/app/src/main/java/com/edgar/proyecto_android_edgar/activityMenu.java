package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;



public class activityMenu extends AppCompatActivity implements View.OnClickListener{

    TextView tvUsuario,tvJugadores,tvAlineacion;
    TableRow opcionAlineacion,opcionJugadores;
    public Typeface fuenteTitulo;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ico_apk);

        fuenteTitulo = Typeface.createFromAsset(getAssets(),"fuentes/SFSportsNight.ttf");
        tvUsuario = (TextView)findViewById(R.id.tvUsuario);
        tvJugadores = (TextView)findViewById(R.id.tvJugadores);
        tvAlineacion = (TextView)findViewById(R.id.tvAlineacion);
        tvUsuario.setTypeface(fuenteTitulo);
        tvJugadores.setTypeface(fuenteTitulo);
        tvAlineacion.setTypeface(fuenteTitulo);
        Bundle contenedor = this.getIntent().getExtras();
        nombre = contenedor.getString("name");
        tvUsuario.append(nombre);
        opcionAlineacion = (TableRow)findViewById(R.id.opAlineacion);
        opcionJugadores = (TableRow)findViewById(R.id.opJugadores);
        opcionAlineacion.setOnClickListener(this);
        opcionJugadores.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.opAlineacion:

                break;
            case R.id.opJugadores:

                break;
        }

    }
}
