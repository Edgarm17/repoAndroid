package com.simarro.pmm_practica2e3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Actividad2 extends AppCompatActivity implements View.OnClickListener {

    private Button btAceptar;
    private Button btCancelar;
    private EditText etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        btAceptar = (Button)findViewById(R.id.btAceptar);
        btCancelar = (Button)findViewById(R.id.btCancelar);
        etResult = (EditText) findViewById(R.id.etResult);

        btAceptar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btAceptar){
            String resultado = etResult.getText().toString();
            Intent i = getIntent();
            i.putExtra("RESULTADO",resultado);
            setResult(RESULT_OK,i);
            finish();
        }else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
