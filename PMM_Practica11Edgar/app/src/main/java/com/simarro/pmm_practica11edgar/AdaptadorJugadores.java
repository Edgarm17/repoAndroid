package com.simarro.pmm_practica11edgar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorJugadores extends RecyclerView.Adapter<JugadoresViewHolder> implements View.OnClickListener {
    private ArrayList<Jugador> jugadores;
    private Context context;
    private View.OnClickListener mListener;

    public AdaptadorJugadores(ArrayList<Jugador> jugadores, Context context) {
        this.jugadores = jugadores;
        this.context = context;
    }

    @NonNull
    @Override
    public JugadoresViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.elemento_lista, viewGroup, false);
        JugadoresViewHolder viewHolder = new JugadoresViewHolder(itemView, context);
        itemView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JugadoresViewHolder viewHolder, int position) {

        Jugador jugador = jugadores.get(position);
        viewHolder.bindAlumno(jugador);
    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {

        if (mListener != null) {
            mListener.onClick(view);
        }
    }
}
