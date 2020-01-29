package com.edgar.pmm_practica8edgargarcia;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private OnMenuFragmentListener mListener;

    private Button btnPerfil,btnPreferencias;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static MenuFragment newInstance() {
        Bundle informacion = new Bundle();

        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setArguments(informacion);
        return menuFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View vistaLayout = inflater.inflate(R.layout.fragment_menu, container, false);

        getActivity().setTitle("Menú Principal");

        btnPerfil = (Button)vistaLayout.findViewById(R.id.btn_perfil);
        btnPreferencias = (Button)vistaLayout.findViewById(R.id.btn_preferencias);

        btnPerfil.setOnClickListener(this);
        btnPreferencias.setOnClickListener(this);

        return vistaLayout;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_perfil:
                mListener.viewProfile();
                break;
            case R.id.btn_preferencias:
                mListener.preferences();

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnMenuFragmentListener){
            mListener = (OnMenuFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + "Debe implementar OnMenuFragmentListener");
        }
    }

    public interface OnMenuFragmentListener{
        public void viewProfile();
        public void preferences();
    }
}
