package com.simarro.practicaclasse;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlumnosViewHolder extends RecyclerView.ViewHolder {


    private ImageView imgSexo;
    private TextView tvNombre;
    private TextView tvDni;
    private Context contexto;

    public AlumnosViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        imgSexo = itemView.findViewById(R.id.img_sexo);
        tvNombre=itemView.findViewById(R.id.tv_nombre);
        tvDni = itemView.findViewById(R.id.tv_dni);
        contexto=context;


    }

    public void bindAlumno(Alumno a){
        if (a.getSexo()=='H'){
            imgSexo.setImageDrawable(contexto.getDrawable(R.drawable.male));

        }else{
            imgSexo.setImageDrawable(contexto.getDrawable(R.drawable.female));
        }

        tvNombre.setText(a.getNombre());
        tvDni.setText(a.getDni());
    }
}
