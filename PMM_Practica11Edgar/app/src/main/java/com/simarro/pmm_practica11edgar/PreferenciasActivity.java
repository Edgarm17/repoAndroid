package com.simarro.pmm_practica11edgar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);



        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorPreferencias,new PreferenciasFragment()).commit();
    }


}
