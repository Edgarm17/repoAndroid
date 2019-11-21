package com.simarro.pmm_practica6edgargarcia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private AdaptadorRobots adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Robot> robots;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Button btnNuevo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robots = new ArrayList<>();

        Robot r1 = new Robot("Pepe","BENDER","Hierro",2000);
        Robot r2 = new Robot("Pepa","WALLE","Chatarra",5000);
        Robot r3 = new Robot("Pepi","R2D2","Metal",1200);

        robots.add(r1);
        robots.add(r2);
        robots.add(r3);

        recyclerView = (RecyclerView) findViewById(R.id.rv_robots);
        adaptador = new AdaptadorRobots(robots,this);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        btnNuevo = (Button)findViewById(R.id.btnNew);
        btnNuevo.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adaptador.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setColorSchemeColors(2);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(1);

        ItemTouchHelper.SimpleCallback myCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction){
                    case ItemTouchHelper.RIGHT:
                        robots.remove(viewHolder.getAdapterPosition());
                        adaptador.notifyItemRemoved(viewHolder.getAdapterPosition());
                        break;
                    case ItemTouchHelper.LEFT:
                        adaptador.notifyDataSetChanged();
                        break;
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNew){
            Intent n = new Intent(this,CrearUser.class);
            startActivity(n);
        }
    }
}
