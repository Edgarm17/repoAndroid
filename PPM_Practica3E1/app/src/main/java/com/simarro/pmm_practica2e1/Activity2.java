package com.simarro.pmm_practica2e1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnLongClickListener {

    Bundle contenedorRecuperado;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        contenedorRecuperado = this.getIntent().getExtras();
        String nombreRecu = contenedorRecuperado.getString("nombre");
        tv = (TextView)findViewById(R.id.nombre2);
        tv.setText("Hola "+ nombreRecu);

        tv.setOnLongClickListener(this);

    }

    @Override
    public boolean onLongClick(View v) {
        Intent i2 = new Intent(this,Activity3.class);
        i2.putExtras(contenedorRecuperado);
        startActivity(i2);
        return true;
    }
}
