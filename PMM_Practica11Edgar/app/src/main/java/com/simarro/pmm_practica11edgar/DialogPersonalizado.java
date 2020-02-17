package com.simarro.pmm_practica11edgar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogPersonalizado extends DialogFragment {

    private Button btnSignIn,btnCancel;
    private EditText etUser,etPass;
    private TextView tvError,tvVacio;


    public interface OnLoginListener{
        public void onLogin(DialogPersonalizado dialog, String user, String pass);
        public void onCancel(DialogPersonalizado dialog);

    }

    OnLoginListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OnLoginListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " debes implementar DialogPersonalizadoListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(v);

        tvError = (TextView)v.findViewById(R.id.tvError);
        tvVacio = (TextView)v.findViewById(R.id.tvVacio);
        btnSignIn = (Button)v.findViewById(R.id.btnSignIn);
        btnCancel = (Button)v.findViewById(R.id.btnCancel);
        etUser = (EditText)v.findViewById(R.id.username);
        etPass = (EditText)v.findViewById(R.id.password);

        setCancelable(false);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onLogin(DialogPersonalizado.this,etUser.getText().toString(),etPass.getText().toString());
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onCancel(DialogPersonalizado.this);
                getActivity().setTitle("Football Manager");
            }

        });





        return builder.create();
    }

    public void mostrarError(){

        tvVacio.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);

    }

    public void camposVacios(){

        tvError.setVisibility(View.GONE);
        tvVacio.setVisibility(View.VISIBLE);

    }


}
