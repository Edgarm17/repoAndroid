package com.example.pmm_proyecto_alexbataller;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RobotViewHolder extends RecyclerView.ViewHolder {

    private ImageView img;
    private TextView tvNombre;
    private TextView tvPhone;
    private TextView tvId;
    private Context contexto;

    public RobotViewHolder(@NonNull View itemView, Context contexto) {
        super(itemView);
        this.img = itemView.findViewById(R.id.img);
        this.tvNombre = itemView.findViewById(R.id.tvNombre);
        this.tvPhone = itemView.findViewById(R.id.tvPhone);
        this.tvId = itemView.findViewById(R.id.tvId);
        this.contexto = contexto;
    }

    public void bindRobot(Contacto r) {
        switch (r.getCt()) {
            case MUJER:
                this.img.setImageDrawable(contexto.getDrawable(R.drawable.woman));
                break;
            default:
                this.img.setImageDrawable(contexto.getDrawable(R.drawable.male));
        }

        this.tvNombre.setText(r.getNombre());
        this.tvPhone.setText(r.getTelefono());
        this.tvId.setText(String.valueOf(r.getId()));
    }
}
