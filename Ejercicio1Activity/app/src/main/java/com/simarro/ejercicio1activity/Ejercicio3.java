package com.simarro.ejercicio1activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ejercicio3 extends AppCompatActivity implements View.OnClickListener{

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear2);

        btn = (Button)findViewById(R.id.btnAct3);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this,Ejercio1.class);
        startActivity(i);
    }
}
