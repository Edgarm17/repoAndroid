package com.simarro.pmm_practica11edgar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTiempo extends Fragment {

    public static FragmentTiempo newInstance(){
        FragmentTiempo fragment = new FragmentTiempo();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    TextView tvEstado,tvTemperatura,tvHumedad,tvViento;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_tiempo,container,false);


        tvTemperatura = vista.findViewById(R.id.tvTemperatura);
        tvHumedad = vista.findViewById(R.id.tvHumedad);
        tvViento = vista.findViewById(R.id.tvViento);


        tvTemperatura.setText(MainActivity.temperatura);
        tvHumedad.setText(MainActivity.humedad);
        tvViento.setText(MainActivity.viento);

        return vista;
    }
}
