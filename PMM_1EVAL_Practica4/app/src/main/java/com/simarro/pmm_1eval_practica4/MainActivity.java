package com.simarro.pmm_1eval_practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.e1);
        Button btn2 = (Button) findViewById(R.id.e2);
        Button btn3 = (Button) findViewById(R.id.e3);
        Button btn4 = (Button) findViewById(R.id.e4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.e1:
                intent = new Intent(this, Ejercicio1.class);
                break;

            case R.id.e2:
                intent = new Intent(this, Ejercicio2.class);
                break;

            case R.id.e3:
                intent = new Intent(this, Ejercicio3.class);
                break;

            case R.id.e4:
                intent = new Intent(this, Ejercicio4.class);
                break;
        }

        startActivity(intent);
    }
}
