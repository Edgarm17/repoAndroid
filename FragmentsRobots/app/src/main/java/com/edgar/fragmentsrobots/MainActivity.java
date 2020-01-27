package com.edgar.fragmentsrobots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListadoRobotsFragment.OnMiPrimerFragmentListener, DetallesRobotFragment.OnMiSegundoFragmentListener
{

    private RecyclerView recyclerView;
    public AdaptadorRobots adaptador;
    private RecyclerView.LayoutManager layoutManager;
    static ArrayList<Robot> robots = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean pantallaMovil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pantallaMovil = getSupportFragmentManager().findFragmentById(R.id.frag_lista) == null;

        if (pantallaMovil) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            ListadoRobotsFragment listadoRobotsFragment = ListadoRobotsFragment.newInstance();
            transaction.add(R.id.layout_contenedor, listadoRobotsFragment);
            transaction.commit();
        }





    }

    @Override
    public void onClick(View v) {

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
    public void onMiPrimerFragmentClick(Robot r) {

        Bundle datos = new Bundle();
        datos.putParcelable("robotDetalles", r);

        if (pantallaMovil){
            DetallesRobotFragment segundoFragment = DetallesRobotFragment.newInstance();
            segundoFragment.setArguments(datos);

            FragmentManager supporFragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = supporFragmentManager.beginTransaction();
            transaction.replace(R.id.layout_contenedor,segundoFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }else{
            DetallesRobotFragment fragmentDetalle = (DetallesRobotFragment) getSupportFragmentManager().findFragmentById(R.id.frag_detalle);
            fragmentDetalle.actualizarDatos(r);
        }

    }



    @Override
    public void onMiSegundoFragmentClick() {

    }
}
