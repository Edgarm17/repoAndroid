package com.simarro.practicaclasse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityNuevo extends AppCompatActivity implements  View.OnClickListener{

    Button btnCrear,btnEditar,btnCancelar;
    EditText etNombre,etMat,etAnyo;
    Spinner spTipo;
    String nombre,material,anyo,tipo;
    Robot r,robotAEditar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etMat = (EditText)findViewById(R.id.etMaterial);
        etAnyo = (EditText)findViewById(R.id.etAnyo);
        spTipo = (Spinner)findViewById(R.id.spTipo);

        btnCrear = (Button)findViewById(R.id.button);
        btnEditar = (Button)findViewById(R.id.btnEditar);


        if (this.getIntent().getSerializableExtra("editar") != null){
            robotAEditar = (Robot)this.getIntent().getSerializableExtra("editar");
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
            btnEditar.setOnClickListener(this);

        }else{
            nombre = etNombre.getText().toString();
            material = etMat.getText().toString();
            anyo = etAnyo.getText().toString();
            tipo = spTipo.getSelectedItem().toString();
            r = new Robot(nombre,material,anyo,tipo);
            btnEditar.setVisibility(View.GONE);

            btnCrear.setOnClickListener(this);
        }





    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(this, MainActivity.class);

        switch (view.getId()){
            case R.id.btnNuevo:

                i.putExtra("robotNew",r);

                break;
            case R.id.btnEditar:

                i.putExtra("robotEditado",robotAEditar);
                i.putExtra("pos",this.getIntent().getExtras().getInt("pos"));
                break;
        }

        startActivity(i);
        //finish();
    }
}
