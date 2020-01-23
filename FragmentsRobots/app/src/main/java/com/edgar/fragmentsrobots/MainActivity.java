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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListadoRobotsFragment.OnMiPrimerFragmentListener
{

    private RecyclerView recyclerView;
    public AdaptadorRobots adaptador;
    private RecyclerView.LayoutManager layoutManager;
    static ArrayList<Robot> robots = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ListadoRobotsFragment listadoRobotsFragment = ListadoRobotsFragment.newInstance();
        transaction.add(R.id.layout_contenedor, listadoRobotsFragment);
        transaction.commit();


    }

    @Override
    public void onClick(View v) {

        int posicion = recyclerView.getChildAdapterPosition(v);
        Robot aSeleccionado = robots.get(posicion);
        //Toast.makeText(this,"Robot" + aSeleccionado.getNombre(), Toast.LENGTH_LONG).show();
        //Snackbar.make(recyclerView,"Robot" + aSeleccionado.getNombre(), Snackbar.LENGTH_SHORT).show();
        Snackbar.make(recyclerView, "Robot" + aSeleccionado.getNombre(), Snackbar.LENGTH_SHORT)
                .setAction("Mostrar Mensaje", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Snackbar Pulsada", Toast.LENGTH_LONG).show();
                    }
                })
                .setActionTextColor(getColor(R.color.verde))
                .show();

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
    public void onMiPrimerFragmentClick() {
        FragmentManager supporFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supporFragmentManager.beginTransaction();

        //SegundoFragment segundoFragment = SegundoFragment.newInstance();

        //transaction.replace(R.id.layout_contenedor,segundoFragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}
