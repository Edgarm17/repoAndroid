package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;



public class activityMenu extends AppCompatActivity {

    TextView saludo;
    public Typeface fuenteTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fuenteTitulo = Typeface.createFromAsset(getAssets(),"fuentes/SFSportsNight.ttf");
        saludo = (TextView)findViewById(R.id.idSaludo);
        saludo.append("\nedgar");
        saludo.setTypeface(fuenteTitulo);
        //Bundle contenedor = this.getIntent().getExtras();
        //saludo.append("\n"+contenedor.getString("name"));





    }
}
