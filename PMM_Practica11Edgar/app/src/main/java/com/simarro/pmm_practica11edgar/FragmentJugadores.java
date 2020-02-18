package com.simarro.pmm_practica11edgar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

public class FragmentJugadores extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    public AdaptadorJugadores adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String telefonoALlamar;

    private MySQLiteClass helper;
    private SQLiteDatabase db;

    public static FragmentJugadores newInstance(){
        FragmentJugadores fragment = new FragmentJugadores();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_jugadores,container,false);


        setHasOptionsMenu(true);

        telefonoALlamar = "";
        helper = new MySQLiteClass(getActivity());
        cargarDatos(MainActivity.jugadores);



        swipeRefreshLayout = (SwipeRefreshLayout) vista.findViewById(R.id.srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "Lista actualizada", Toast.LENGTH_LONG).show();
                //adaptador.notifyDataSetChanged();
            }
        });

        recyclerView = (RecyclerView) vista.findViewById(R.id.rv_jugadores);
        adaptador = new AdaptadorJugadores(MainActivity.jugadores,getActivity());
        adaptador.setOnClickListener(this);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

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
                        db = helper.getWritableDatabase();
                        Jugador j = MainActivity.jugadores.get(viewHolder.getAdapterPosition());
                        db.execSQL("DELETE FROM Jugador WHERE nombre='"+j.getNombre()+"'");
                        db.close();
                        cargarDatos(MainActivity.jugadores);
                        adaptador.notifyItemRemoved(viewHolder.getAdapterPosition());
                        break;
                    case ItemTouchHelper.LEFT:
                        //Editar
                        MainActivity.modificarJugador = 2;
                        MainActivity.jugadorAModificar = viewHolder.getAdapterPosition();
                        Fragment fragment = FragmentModificarJugador.newInstance();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

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

                    icono = getActivity().getDrawable(R.drawable.ic_delete);
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

                    icono = getActivity().getDrawable(R.drawable.ic_edit);
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

        return vista;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_jugadores, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opAdd:
                MainActivity.modificarJugador = 1;
                Fragment fragment = FragmentModificarJugador.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

                adaptador.notifyDataSetChanged();
                return true;
            case R.id.opReset:

                db = helper.getWritableDatabase();

                db.execSQL("DELETE FROM Jugador");

                db.close();
                cargarDatos(MainActivity.jugadores);

                adaptador.notifyDataSetChanged();
        }

        return  true;

    }

    public void cargarDatos(ArrayList<Jugador> jugadores){

        jugadores.clear();
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Jugador", null);
        if (c.moveToFirst()){
            do {
                String nombre = c.getString(0);
                String numero = c.getString(1);
                String telefono = c.getString(2);
                String tipo = c.getString(3);
                Jugador j = new Jugador(nombre,numero,telefono,tipo);
                jugadores.add(j);
            } while(c.moveToNext());
        }
        db.close();

    }

    @Override
    public void onClick(View v) {

    }
}
