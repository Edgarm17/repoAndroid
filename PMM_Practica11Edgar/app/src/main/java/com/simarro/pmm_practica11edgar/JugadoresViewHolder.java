package com.simarro.pmm_practica11edgar;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JugadoresViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgJugador;
    private TextView tvNombre;
    private TextView tvPosicon;
    private  TextView tvNumero;
    private Context contexto;

    public JugadoresViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        imgJugador = itemView.findViewById(R.id.imgJugador);
        tvNombre=itemView.findViewById(R.id.tvNombre);
        tvPosicon = itemView.findViewById(R.id.tvPosicion);
        tvNumero = itemView.findViewById(R.id.tvNumero);
        contexto=context;


    }

    public void bindAlumno(Jugador j) {

        switch (j.getPosicion()) {
            case "Portero":
                imgJugador.setImageDrawable(contexto.getDrawable(R.drawable.img_portero));
                break;
            default:
                imgJugador.setImageDrawable(contexto.getDrawable(R.drawable.img_jugador));
                break;
        }

        tvNombre.setText(j.getNombre());
        tvPosicon.setText(j.getPosicion());
        tvNumero.setText(j.getNumero());

    }
}
