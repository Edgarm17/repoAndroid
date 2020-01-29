package com.edgar.pmm_practica8edgargarcia;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PerfilFragment extends Fragment implements View.OnClickListener{

    private OnPerfilFragmentListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static PerfilFragment newInstance() {
        Bundle informacion = new Bundle();

        PerfilFragment perfilFragment = new PerfilFragment();
        perfilFragment.setArguments(informacion);
        return perfilFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View vistaLayout = inflater.inflate(R.layout.fragment_perfil, container, false);

        getActivity().setTitle("Perfil");

        return vistaLayout;
    }

    @Override
    public void onClick(View v) {
        mListener.onPerfilFragmentClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnPerfilFragmentListener){
            mListener = (OnPerfilFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + "Debe implementar OnPerfilFragmentListener");
        }
    }

    public interface OnPerfilFragmentListener{
        public void onPerfilFragmentClick();
    }
}
