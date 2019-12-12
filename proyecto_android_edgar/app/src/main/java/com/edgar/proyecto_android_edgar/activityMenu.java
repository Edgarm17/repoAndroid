package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class activityMenu extends AppCompatActivity implements View.OnClickListener{

    TextView tvUsuario,tvJugadores,tvAlineacion;
    TableRow opcionAlineacion,opcionJugadores;
    public Typeface fuenteTitulo;
    String nombre;
    static ArrayList<Jugador> listaJugadores = new ArrayList<>();
    ImageButton btnInfo;
    RadioButton rdWinner, rdLooser, rdDef;
    View fondo;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cargarDatos(listaJugadores);

        rl = (RelativeLayout)findViewById(R.id.relMenu);
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
        btnInfo = (ImageButton)findViewById(R.id.btnInfo);
        rdWinner = (RadioButton)findViewById(R.id.radioButton2);
        rdLooser = (RadioButton)findViewById(R.id.radioButton);
        rdDef = (RadioButton)findViewById(R.id.rbDefault);
        fondo = (View)findViewById(R.id.fondoMenu);

        rdWinner.setTypeface(fuenteTitulo);
        rdDef.setTypeface(fuenteTitulo);
        rdLooser.setTypeface(fuenteTitulo);

        opcionAlineacion.setOnClickListener(this);
        opcionJugadores.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        rdWinner.setOnClickListener(this);
        rdLooser.setOnClickListener(this);
        rdDef.setOnClickListener(this);



    }

    public void cargarDatos(ArrayList<Jugador> jugadores){
        jugadores.add(new Jugador("Jugador1","Portero","699004034","1"));
        jugadores.add(new Jugador("Jugador2","Defensa","65214256","2"));
        jugadores.add(new Jugador("Jugador3","Defensa","65214256","3"));
        jugadores.add(new Jugador("Jugador4","Defensa","65214256","4"));
        jugadores.add(new Jugador("Jugador5","Medio","65214256","5"));
        jugadores.add(new Jugador("Jugador6","Medio","65214256","6"));
        jugadores.add(new Jugador("Jugador7","Medio","65214256","7"));
        jugadores.add(new Jugador("Jugador8","Medio","65214256","8"));
        jugadores.add(new Jugador("Jugador9","Delantero","65214256","9"));
        jugadores.add(new Jugador("Jugador10","Delantero","65214256","10"));
        jugadores.add(new Jugador("Jugador11","Delantero","65214256","11"));
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.opAlineacion:
                i = new Intent(this,lineupActivity.class);
                startActivity(i);
                break;
            case R.id.opJugadores:
                i = new Intent(this,activityJugadores.class);
                i.putExtra("lista",listaJugadores);
                startActivity(i);
                break;
            case R.id.btnInfo:
                i = new Intent(this,activityInfo.class);
                startActivity(i);
                break;
            case R.id.radioButton:

                rl.setBackground(getDrawable(R.drawable.fondo_looser));
                break;
            case R.id.radioButton2:

                rl.setBackground(getDrawable(R.drawable.fondo_pro));
                break;
            case R.id.rbDefault:

                rl.setBackground(getDrawable(R.drawable.soccer_field));
                break;
        }

    }
}
