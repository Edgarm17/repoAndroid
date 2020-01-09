package com.simarro.pruebafragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PrimerFragment extends Fragment implements View.OnClickListener{

    private final static String ARG_NOMBRE = "ARG_NOMBRE";
    private OnMiPrimerFragmentListener mListener;

    private String nombre;
    private TextView tvNombre;
    private Button btnPulsar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle informacionRecibida = getArguments();

        if (informacionRecibida != null){
            nombre = informacionRecibida.getString(ARG_NOMBRE);

        }


    }

    public static PrimerFragment newInstance(String nombre){
        Bundle informacion = new Bundle();
        informacion.putString(ARG_NOMBRE,nombre);


        PrimerFragment mFragment = new PrimerFragment();
        mFragment.setArguments(informacion);
        return mFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View vistaLayout = inflater.inflate(R.layout.primer_fragment,container,false);

        tvNombre = vistaLayout.findViewById(R.id.tvNombre);
        btnPulsar = vistaLayout.findViewById(R.id.btnClick);
        btnPulsar.setOnClickListener(this);


        if(nombre != null){
            tvNombre.setText(nombre);
        }

        return vistaLayout;
    }

    @Override
    public void onClick(View view) {

        mListener.onMiPrimerFragmentClick();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnMiPrimerFragmentListener){
            mListener = (OnMiPrimerFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()+"debe implementar OnMiPrimerFragmentListener");
        }
    }

    public interface OnMiPrimerFragmentListener{
        public void onMiPrimerFragmentClick();
    }

}


