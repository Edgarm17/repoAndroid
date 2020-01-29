package com.edgar.pmm_practica8edgargarcia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements  MenuFragment.OnMenuFragmentListener, PerfilFragment.OnPerfilFragmentListener, PreferenciasFragment.OnPreferenciasFragmentListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MenuFragment menuFragment = MenuFragment.newInstance();
        transaction.add(R.id.layout_contenedor,menuFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }




    @Override
    public void viewProfile() {
        PerfilFragment perfilFragment = PerfilFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_contenedor,perfilFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void preferences() {

        /*PreferenciasFragment preferenciasFragment = PreferenciasFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_contenedor, preferenciasFragment );
        */
        getFragmentManager().beginTransaction().replace(R.id.layout_contenedor,new PreferenciasFragment()).commit();
        getFragmentManager().beginTransaction().addToBackStack(null);
    }

    @Override
    public void onPerfilFragmentClick() {

    }

    @Override
    public void onPreferenciasFragmentClick() {

    }
}
