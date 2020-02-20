package com.simarro.pmm_practica11edgar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class FragmentPerfil extends Fragment {

    TextView tvUsuario,tvEmail;

    public static FragmentPerfil newInstance(){
        FragmentPerfil fragment = new FragmentPerfil();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View vista = inflater.inflate(R.layout.fragment_perfil,container,false);

        tvUsuario = vista.findViewById(R.id.tvUsuario);
        tvEmail = vista.findViewById(R.id.tvCorreo);

        tvUsuario.setText(MainActivity.nombreUsuario);
        tvEmail.setText(MainActivity.correo);

        return vista;
    }
}
