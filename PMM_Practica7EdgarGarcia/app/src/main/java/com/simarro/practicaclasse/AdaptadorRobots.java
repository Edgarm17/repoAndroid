package com.simarro.practicaclasse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRobots extends RecyclerView.Adapter<RobotsViewHolder> implements View.OnClickListener {


    private ArrayList<Robot> robots;
    private Context context;
    private View.OnClickListener mListener;

    public AdaptadorRobots(ArrayList<Robot> robots, Context context) {
        this.robots = robots;
        this.context = context;
    }

    @NonNull
    @Override
    public RobotsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.elemento_lista, viewGroup, false);
        RobotsViewHolder viewHolder = new RobotsViewHolder(itemView, context);
        itemView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RobotsViewHolder viewHolder, int position) {

        Robot robot = robots.get(position);
        viewHolder.bindAlumno(robot);
    }

    @Override
    public int getItemCount() {
        return robots.size();
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
