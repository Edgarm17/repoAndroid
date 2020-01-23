package com.simarro.practicaclasse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityNuevo extends AppCompatActivity implements  View.OnClickListener{

    Button btnCrear,btnEditar;
    EditText etNombre,etMat,etAnyo;
    Spinner spTipo;
    String nombre,material,anyo,tipo;
    Robot r,robotAEditar;
    TextView titulo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        getSupportActionBar().setTitle("Insertar robot");
        etNombre = (EditText)findViewById(R.id.etNombre);
        etMat = (EditText)findViewById(R.id.etMaterial);
        etAnyo = (EditText)findViewById(R.id.etAnyo);
        spTipo = (Spinner)findViewById(R.id.spTipo);
        titulo = (TextView)findViewById(R.id.textView);

        btnCrear = (Button)findViewById(R.id.button);
        btnEditar = (Button)findViewById(R.id.btnEditar);

        btnCrear.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        if (this.getIntent().getExtras() != null){

            if (this.getIntent().getExtras().getParcelable("editar") != null){
                getSupportActionBar().setTitle("Editar robot");
                titulo.setText("Edición robot");
                robotAEditar = this.getIntent().getExtras().getParcelable("editar");
                etNombre.setText(robotAEditar.getNombre());
                etMat.setText(robotAEditar.getMaterial());
                etAnyo.setText(robotAEditar.getAnyo());
                if (robotAEditar.getTipo().equals("R2D2")){
                    spTipo.setSelection(0);
                }else if(robotAEditar.getTipo().equals("BENDER")){
                    spTipo.setSelection(1);
                }else{
                    spTipo.setSelection(2);
                }

                btnCrear.setVisibility(View.GONE);


            }else{


                btnEditar.setVisibility(View.GONE);

            }
        }






    }

    @Override
    public void onClick(View view) {


        Intent i = new Intent(this, MainActivity.class);
        Bundle contenedor = new Bundle();

        switch (view.getId()){
            case R.id.button:

                nombre = etNombre.getText().toString();
                material = etMat.getText().toString();
                anyo = etAnyo.getText().toString();
                tipo = spTipo.getSelectedItem().toString();
                r = new Robot(nombre,material,anyo,tipo);
                contenedor.putParcelable("robotNew",r);
                i.putExtras(contenedor);

                break;
            case R.id.btnEditar:

                nombre = etNombre.getText().toString();
                material = etMat.getText().toString();
                anyo = etAnyo.getText().toString();
                tipo = spTipo.getSelectedItem().toString();

                robotAEditar.setNombre(nombre);
                robotAEditar.setMaterial(material);
                robotAEditar.setAnyo(anyo);
                robotAEditar.setTipo(tipo);
                contenedor.putParcelable("robotEditado",robotAEditar);
                contenedor.putInt("pos",this.getIntent().getExtras().getInt("pos"));
                i.putExtras(contenedor);
                break;
        }

        startActivity(i);
        finish();
    }
}
