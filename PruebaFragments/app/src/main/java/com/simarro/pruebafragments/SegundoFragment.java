package com.simarro.pruebafragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;




public class SegundoFragment extends Fragment{


    public static SegundoFragment newInstance(){
        Bundle informacionFragment = new Bundle();



        SegundoFragment mFragment = new SegundoFragment();
        mFragment.setArguments(informacionFragment);

        return mFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View vistaLayout = inflater.inflate(R.layout.segundo_fragment,container,false);


        return vistaLayout;
    }


}
