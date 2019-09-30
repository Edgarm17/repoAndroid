package com.simarro.pmm_practica2e1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Actividad1 extends AppCompatActivity implements View.OnClickListener {

    private Button boton;
    private EditText etn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etn = (EditText)findViewById(R.id.idNombre);

        boton = findViewById(R.id.button);
        boton.setOnClickListener(this);


    }

    //Método botón enviar

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,Activity2.class);
        Bundle contenedor = new Bundle();
        contenedor.putString("nombre",etn.getText().toString());
        i.putExtras(contenedor);
        startActivity(i);
    }
}
