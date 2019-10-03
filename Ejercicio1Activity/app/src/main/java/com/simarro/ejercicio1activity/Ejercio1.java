package com.simarro.ejercicio1activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ejercio1 extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        btn = (Button)findViewById(R.id.idPulsar);
        tv = (TextView) findViewById(R.id.idTv1);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        tv.setVisibility(View.VISIBLE);
        btn.setVisibility(View.INVISIBLE);
    }
}
