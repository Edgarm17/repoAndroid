package com.simarro.ejercicio1activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1,btn2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        if (view.getId()==R.id.btn1){
            i = new Intent(this, Ejercio1.class);
            startActivity(i);
        }else if (view.getId()==R.id.btn2){
            i = new Intent(this, Ejercicio3.class);
            startActivity(i);
        }else if (view.getId()==R.id.btn3){
            i = new Intent(this, Ejercicio4.class);
            startActivity(i);
        }

    }
}
