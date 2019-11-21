package com.simarro.pmm_practica6edgargarcia;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RobotsViewHolder extends RecyclerView.ViewHolder {


    private ImageView imgRobot;
    private TextView tvNombre;
    private TextView tvAnyo;
    private TextView tvTipo;
    private Context contexto;

    public RobotsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        imgRobot = itemView.findViewById(R.id.imgRobot);
        tvNombre=itemView.findViewById(R.id.tvNombre);
        tvAnyo = itemView.findViewById(R.id.tvAnyo);
        tvTipo = itemView.findViewById(R.id.tvTipo);
        contexto=context;


    }

    public void bindRobot(Robot r){
        if (r.getTipo()=="R2D2"){
            imgRobot.setImageDrawable(contexto.getDrawable(R.drawable.r2d2));

        }else if (r.getTipo() == "BENDER"){
            imgRobot.setImageDrawable(contexto.getDrawable(R.drawable.bender));
        }else if (r.getTipo() == "WALLE"){
            imgRobot.setImageDrawable(contexto.getDrawable(R.drawable.walle));
        }

        tvNombre.setText(r.getNombre());
        tvAnyo.setText(r.getAnyo());
        tvTipo.setText(r.getTipo());
    }
}
