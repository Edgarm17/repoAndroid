package com.simarro.practicaclasse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorAlumnos extends RecyclerView.Adapter<AlumnosViewHolder> implements View.OnClickListener {


    private ArrayList<Alumno> alumnos;
    private Context context;
    private View.OnClickListener mListener;

    public AdaptadorAlumnos(ArrayList<Alumno> alumnos, Context context) {
        this.alumnos = alumnos;
        this.context = context;
    }

    @NonNull
    @Override
    public AlumnosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.elemento_lista, viewGroup, false);
        AlumnosViewHolder viewHolder = new AlumnosViewHolder(itemView, context);
        itemView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosViewHolder viewHolder, int position) {

        Alumno alumno = alumnos.get(position);
        viewHolder.bindAlumno(alumno);
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
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
