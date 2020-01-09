package com.simarro.pruebafragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PrimerFragment.OnMiPrimerFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager suppFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = suppFragmentManager.beginTransaction();
        PrimerFragment mFragment = PrimerFragment.newInstance("Edgar Garcia");
        transaction.add(R.id.layout_contenedor,mFragment);
        //PrimerFragment sFragment = PrimerFragment.newInstance("Pepe");
        //transaction.add(R.id.layout_contenedor,sFragment);


        transaction.commit();
    }

    @Override
    public void onMiPrimerFragmentClick() {
        FragmentManager supporFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supporFragmentManager.beginTransaction();

        SegundoFragment segundoFragment = SegundoFragment.newInstance();

        transaction.replace(R.id.layout_contenedor,segundoFragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}
