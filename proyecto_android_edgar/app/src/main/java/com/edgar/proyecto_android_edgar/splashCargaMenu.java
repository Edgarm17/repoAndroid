package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splashCargaMenu extends AppCompatActivity {
    TextView saludo,carga;
    public Typeface fuenteTitulo;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_carga_menu);

        fuenteTitulo = Typeface.createFromAsset(getAssets(),"fuentes/SFSportsNight.ttf");
        saludo = (TextView)findViewById(R.id.idSaludo);
        carga = (TextView)findViewById(R.id.textView);
        Animation animacion = AnimationUtils.loadAnimation(this,R.anim.anim_tv);
        carga.setAnimation(animacion);
        saludo.setTypeface(fuenteTitulo);
        carga.setTypeface(fuenteTitulo);
        Bundle contenedor = this.getIntent().getExtras();
        nombre = contenedor.getString("name");
        saludo.append("\n"+contenedor.getString("name"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashCargaMenu.this,activityMenu.class);
                Bundle contenedor1 = new Bundle();
                contenedor1.putString("name",nombre);
                i.putExtras(contenedor1);
                startActivity(i);
                finish();

            }
        },2000);
    }
}
