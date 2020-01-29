package com.edgar.pmm_practica8edgargarcia;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PreferenciasFragment extends PreferenceFragment implements View.OnClickListener {

    private OnPreferenciasFragmentListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferencias);

    }

    public static PreferenciasFragment newInstance() {
        Bundle informacion = new Bundle();

        PreferenciasFragment preferenciasFragment = new PreferenciasFragment();
        preferenciasFragment.setArguments(informacion);
        return preferenciasFragment;
    }


    @Override
    public void onClick(View v) {
        mListener.onPreferenciasFragmentClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnPreferenciasFragmentListener){
            mListener = (OnPreferenciasFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + "Debe implementar OnPreferenciasFragmentListener");
        }
    }

    public interface OnPreferenciasFragmentListener{
        public void onPreferenciasFragmentClick();
    }
}
