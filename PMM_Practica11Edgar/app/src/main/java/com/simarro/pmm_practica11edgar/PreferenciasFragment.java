package com.simarro.pmm_practica11edgar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferenciasFragment extends PreferenceFragmentCompat {


    public PreferenciasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String key) {
        setPreferencesFromResource(R.xml.preferencias, key);
    }


}
