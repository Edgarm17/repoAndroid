package com.edgar.proyecto_android_edgar;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static com.edgar.proyecto_android_edgar.activityMenu.listaJugadores;

public class activityJugadores extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    public AdaptadorJugadores adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Jugador> jugadores;
    private FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugadores);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(activityJugadores.this, "Lista actualizada", Toast.LENGTH_LONG).show();
                adaptador.notifyDataSetChanged();
            }
        });

        jugadores = listaJugadores;


        recyclerView = (RecyclerView) findViewById(R.id.rv_jugadores);
        adaptador = new AdaptadorJugadores(jugadores,this);
        adaptador.setOnClickListener(this);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        final ItemTouchHelper.SimpleCallback myCallback  = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                switch (direction){
                    case ItemTouchHelper.RIGHT:
                        //Eliminar
                        jugadores.remove(viewHolder.getAdapterPosition());
                        adaptador.notifyItemRemoved(viewHolder.getAdapterPosition());
                        break;
                    case ItemTouchHelper.LEFT:
                        //Editar
                        adaptador.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                int margen = (int)getResources().getDimension(R.dimen.margen);
                Drawable icono;
                Paint pincel = new Paint();
                pincel.setColor(Color.WHITE);
                int sizetext = getResources().getDimensionPixelSize(R.dimen.textsize);
                pincel.setTextSize(sizetext);


                //Hacia la dcha
                if(dX > 0){
                    c.clipRect(viewHolder.itemView.getLeft(),viewHolder.itemView.getTop(),dX,viewHolder.itemView.getBottom());

                    if(dX < recyclerView.getWidth() / 3)
                        c.drawColor(Color.GRAY);
                    else
                        c.drawColor(Color.RED);

                    icono = getDrawable(R.drawable.ic_delete);
                    icono.setBounds(new Rect(viewHolder.itemView.getLeft()+margen, viewHolder.itemView.getTop()+margen, viewHolder.itemView.getHeight()-margen, viewHolder.itemView.getBottom()-margen));
                    icono.draw(c);
                    pincel.setTextAlign(Paint.Align.LEFT);
                    c.drawText(getResources().getString(R.string.delete),viewHolder.itemView.getHeight(),viewHolder.itemView.getBottom()-viewHolder.itemView.getHeight()/2+sizetext/2,pincel);

                    //Hacia la izqda
                }else if(dX < 0){
                    c.clipRect(viewHolder.itemView.getRight()+dX,viewHolder.itemView.getTop(),viewHolder.itemView.getRight(),viewHolder.itemView.getBottom());
                    if(Math.abs(dX) < recyclerView.getWidth() / 3)
                        c.drawColor(Color.GRAY);
                    else
                        c.drawColor(Color.BLUE);

                    icono = getDrawable(R.drawable.ic_edit);
                    icono.setBounds(new Rect(viewHolder.itemView.getRight()-viewHolder.itemView.getHeight()+margen, viewHolder.itemView.getTop()+margen, viewHolder.itemView.getRight()-margen, viewHolder.itemView.getBottom()-margen));
                    icono.draw(c);
                    pincel.setTextAlign(Paint.Align.RIGHT);
                    c.drawText(getResources().getString(R.string.edit),viewHolder.itemView.getRight()-viewHolder.itemView.getHeight(),viewHolder.itemView.getBottom()-viewHolder.itemView.getHeight()/2+sizetext/2,pincel);

                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab){
            Intent i = new Intent(this,activityNuevo.class);
            startActivity(i);
        }else{
            int posicion = recyclerView.getChildAdapterPosition(v);
            Jugador jSeleccionado = jugadores.get(posicion);
            //Toast.makeText(this,"Robot" + jSeleccionado.getNombre(), Toast.LENGTH_LONG).show();
            //Snackbar.make(recyclerView,"Robot" + jSeleccionado.getNombre(), Snackbar.LENGTH_SHORT).show();
            Snackbar.make(recyclerView, "Telefono: " + jSeleccionado.getTelefono(), Snackbar.LENGTH_SHORT)
                    .setAction("LLAMAR", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(activityJugadores.this, "Snackbar Pulsada", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setActionTextColor(getColor(R.color.splash))
                    .show();
        }

    }


}
