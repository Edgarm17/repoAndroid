package com.simarro.practicaclasse;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RobotsViewHolder extends RecyclerView.ViewHolder {


    private ImageView imgRobot;
    private TextView tvNombre;
    private TextView tvMaterial;
    private TextView tvAnyo;
    private TextView tvTipo;
    private Context contexto;

    public RobotsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        imgRobot = itemView.findViewById(R.id.imgRobot);
        tvNombre=itemView.findViewById(R.id.tvNombre);
        tvMaterial = itemView.findViewById(R.id.tvMaterial);
        tvAnyo = itemView.findViewById(R.id.tvAnyo);
        tvTipo = itemView.findViewById(R.id.tvTipo);
        contexto=context;


    }

    public void bindAlumno(Robot a){

        switch (a.getTipo()){
            case "BENDER":
                imgRobot.setImageDrawable(contexto.getDrawable(R.drawable.bender));
                break;
            case "R2D2":
                imgRobot.setImageDrawable(contexto.getDrawable(R.drawable.r2d2));
                break;
            case "WALLE":
                imgRobot.setImageDrawable(contexto.getDrawable(R.drawable.walle));
                break;
        }


        tvNombre.setText(a.getNombre());
        tvMaterial.setText(a.getMaterial());
        tvAnyo.setText(a.getAnyo());

    }
}
