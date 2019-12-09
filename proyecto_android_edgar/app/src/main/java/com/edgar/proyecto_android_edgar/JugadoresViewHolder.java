package com.edgar.proyecto_android_edgar;

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
        imgJugador = itemView.findViewById(R.id.imgRobot);
        tvNombre=itemView.findViewById(R.id.tvNombre);
        tvPosicon = itemView.findViewById(R.id.tvPosicon);
        tvNumero = itemView.findViewById(R.id.tvNumero);
        contexto=context;


    }

    public void bindAlumno(Jugador j){

        switch (j.getPosicion()){
            case "PORTERO":
                imgJugador.setImageDrawable(contexto.getDrawable(R.drawable.img_portero));
                break;
            default:
                imgJugador.setImageDrawable(contexto.getDrawable(R.drawable.img_jugador));
                break;
        }


}
