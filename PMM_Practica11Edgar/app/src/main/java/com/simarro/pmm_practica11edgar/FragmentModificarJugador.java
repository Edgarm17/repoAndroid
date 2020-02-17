package com.simarro.pmm_practica11edgar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentModificarJugador extends Fragment implements View.OnClickListener{

    Button btnCrear,btnEditar;
    EditText etNombre,etNumero,etTelefono;
    Spinner spTipo;
    String nombre,numero,telefono,tipo,nombreAnterior;
    Jugador j;
    TextView titulo;

    private MySQLiteClass helper;
    private SQLiteDatabase db;

    public static FragmentModificarJugador newInstance(){
        FragmentModificarJugador fragment = new FragmentModificarJugador();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_modificar_jugador,container,false);

        etNombre = (EditText)vista.findViewById(R.id.etNombre);
        etNumero = (EditText)vista.findViewById(R.id.etNumero);
        etTelefono = (EditText)vista.findViewById(R.id.etTelefono);
        spTipo = (Spinner)vista.findViewById(R.id.spTipo);
        titulo = (TextView)vista.findViewById(R.id.textView);

        btnCrear = (Button)vista.findViewById(R.id.button);
        btnEditar = (Button)vista.findViewById(R.id.btnEditar);

        btnCrear.setOnClickListener(this);
        btnEditar.setOnClickListener(this);


        if (MainActivity.modificarJugador == 2){

            titulo.setText("Editar jugador");
            btnCrear.setVisibility(View.GONE);

            j = MainActivity.jugadores.get(MainActivity.jugadorAModificar);

            nombreAnterior = j.getNombre();
            etNombre.setText(j.getNombre());
            etNumero.setText(j.getNumero());
            etTelefono.setText(j.getTelefono());

            if (j.getPosicion().equals("Portero")){
                spTipo.setSelection(0);
            }else if (j.getPosicion().equals("Defensa")){
                spTipo.setSelection(1);
            }else if (j.getPosicion().equals("Medio")){
                spTipo.setSelection(2);
            }else{
                spTipo.setSelection(3);
            }



        }else if (MainActivity.modificarJugador == 1){
            btnEditar.setVisibility(View.GONE);
        }


        return vista;
    }

    public void cargarDatos(ArrayList<Jugador> jugadores){

        jugadores.clear();
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Jugador", null);
        if (c.moveToFirst()){
            do {
                String nombre = c.getString(0);
                String numero = c.getString(1);
                String telefono = c.getString(2);
                String tipo = c.getString(3);
                Jugador j = new Jugador(nombre,numero,telefono,tipo);
                jugadores.add(j);
            } while(c.moveToNext());
        }
        db.close();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditar:
                nombre = etNombre.getText().toString();
                numero = etNumero.getText().toString();
                telefono = etTelefono.getText().toString();
                tipo = spTipo.getSelectedItem().toString();

                helper = new MySQLiteClass(getActivity());

                db = helper.getWritableDatabase();

                db.execSQL("UPDATE Jugador SET nombre='"+nombre+"', numero='"+numero+"', telefono='"+telefono+"', posicion='"+tipo+"' WHERE nombre='"+nombreAnterior+"'");
                db.close();
                cargarDatos(MainActivity.jugadores);

                Fragment fragment = FragmentJugadores.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                break;
            case R.id.button:

                nombre = etNombre.getText().toString();
                numero = etNumero.getText().toString();
                telefono = etTelefono.getText().toString();
                tipo = spTipo.getSelectedItem().toString();

                helper = new MySQLiteClass(getActivity());

                db = helper.getWritableDatabase();

                db.execSQL("INSERT INTO Jugador (nombre,numero,telefono,posicion) VALUES ('"+nombre+"','"+numero+"','"+telefono+"','"+tipo+"')");
                db.close();
                cargarDatos(MainActivity.jugadores);



                Fragment f = FragmentJugadores.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,f).commit();
                break;
        }
    }
}

