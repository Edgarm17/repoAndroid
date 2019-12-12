package com.example.pmm_proyecto_alexbataller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorContactos extends RecyclerView.Adapter<RobotViewHolder> implements View.OnClickListener {

    private ArrayList<Contacto> contactos;
    private Context context;

    private View.OnClickListener mListener;

    public AdaptadorContactos(ArrayList<Contacto> contactos, Context context) {
        this.contactos = contactos;
        this.context = context;
    }

    @NonNull
    @Override
    public RobotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_rv, parent, false);
        itemView.setOnClickListener(this);
        RobotViewHolder viewHolder = new RobotViewHolder(itemView, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RobotViewHolder holder, int position) {
        Contacto c = contactos.get(position);
        holder.bindRobot(c);
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onClick(view);
        }
    }

    public void addContacto(Contacto c){
        contactos.add(0,c);
    }

    public void removeContacto(int pos) {
        if (pos >= 0)
            contactos.remove(pos);
    }
}
