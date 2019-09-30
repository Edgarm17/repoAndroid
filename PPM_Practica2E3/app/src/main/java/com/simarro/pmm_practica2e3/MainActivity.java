package com.simarro.pmm_practica2e3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    EditText etNombre;
    TextView tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btNombre);
        etNombre = (EditText)findViewById(R.id.etNombre);
        tvNombre = (TextView)findViewById(R.id.tvNombre);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,Actividad2.class);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED){
            Log.i("TAG","Resultado cancelado");

        }else{
            String resultado = data.getExtras().getString("RESULTADO");
            etNombre.setText(resultado);
            tvNombre.setText(resultado);
        }
    }
}
