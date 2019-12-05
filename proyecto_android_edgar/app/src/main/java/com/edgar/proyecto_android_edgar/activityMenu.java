package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;



public class activityMenu extends AppCompatActivity {

    TextView tvUsuario;
    public Typeface fuenteTitulo;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        fuenteTitulo = Typeface.createFromAsset(getAssets(),"fuentes/SFSportsNight.ttf");
        tvUsuario = (TextView)findViewById(R.id.tvUsuario);
        tvUsuario.setTypeface(fuenteTitulo);
        Bundle contenedor = this.getIntent().getExtras();
        nombre = contenedor.getString("name");
        tvUsuario.append(nombre);





    }
}
