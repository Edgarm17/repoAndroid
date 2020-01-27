package com.edgar.fragmentsrobots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetallesRobotFragment extends Fragment implements View.OnClickListener {

    private OnMiSegundoFragmentListener mListener;
    Bundle info;
    TextView nombre,material,anyo;
    ImageView imagen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    public static DetallesRobotFragment newInstance(){
        Bundle informacion = new Bundle();


        DetallesRobotFragment mFragment = new DetallesRobotFragment();
        mFragment.setArguments(informacion);
        return mFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View vistaLayout = inflater.inflate(R.layout.activity_detalles_robot_fragment,container,false);

        info = getArguments();

        if (info != null){

            Robot robotADescribir = info.getParcelable("robotDetalles");


            nombre = (TextView)vistaLayout.findViewById(R.id.tvDetalleNombre);
            material = (TextView)vistaLayout.findViewById(R.id.tvDetalleMaterial);
            anyo = (TextView)vistaLayout.findViewById(R.id.tvDetalleAnyo);
            imagen = (ImageView)vistaLayout.findViewById(R.id.imageView2);

            nombre.setText(robotADescribir.getNombre());
            material.setText(robotADescribir.getMaterial());
            anyo.setText(robotADescribir.getAnyo());

            switch (robotADescribir.getTipo()){
                case "BENDER":
                    imagen.setImageDrawable(getActivity().getDrawable(R.drawable.bender));
                    break;
                case "R2D2":
                    imagen.setImageDrawable(getActivity().getDrawable(R.drawable.r2d2));
                    break;
                case "WALLE":
                    imagen.setImageDrawable(getActivity().getDrawable(R.drawable.walle));
                    break;
            }
        }

        return vistaLayout;
    }

    public void actualizarDatos(Robot r){
        switch (r.getTipo()){
            case "BENDER":
                imagen.setImageDrawable(getActivity().getDrawable(R.drawable.bender));
                break;
            case "R2D2":
                imagen.setImageDrawable(getActivity().getDrawable(R.drawable.r2d2));
                break;
            case "WALLE":
                imagen.setImageDrawable(getActivity().getDrawable(R.drawable.walle));
                break;
        }

        nombre.setText(r.getNombre());
        material.setText(r.getMaterial());
        anyo.setText(r.getAnyo());
    }

    @Override
    public void onClick(View view) {

        mListener.onMiSegundoFragmentClick();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnMiSegundoFragmentListener){
            mListener = (OnMiSegundoFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()+"debe implementar OnMiPrimerFragmentListener");
        }
    }

    public interface OnMiSegundoFragmentListener{
        public void onMiSegundoFragmentClick();
    }
}
