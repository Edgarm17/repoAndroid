package com.simarro.ejercicio1activity;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ejercicio4 extends AppCompatActivity  implements View.OnClickListener{



    Button btn1,btn2,btn3,btn0,btn4,btn5,btn6,btn7,btn8,btn9,btnSuma,btnResta,btnMulti,btnRes,btnBorrar,btnDiv;
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_calc);

        tv = (TextView)findViewById(R.id.idResultado);

        btn0 = (Button)findViewById(R.id.id_0);
        btn1 = (Button)findViewById(R.id.id_1);
        btn2 = (Button)findViewById(R.id.id_2);
        btn3 = (Button)findViewById(R.id.id_3);
        btn4 = (Button)findViewById(R.id.id_4);
        btn5 = (Button)findViewById(R.id.id_5);
        btn6 = (Button)findViewById(R.id.id_6);
        btn7 = (Button)findViewById(R.id.id_7);
        btn8 = (Button)findViewById(R.id.id_8);
        btn9 = (Button)findViewById(R.id.id_9);
        btnSuma = (Button)findViewById(R.id.id_suma);
        btnResta = (Button)findViewById(R.id.id_menos);
        btnMulti = (Button)findViewById(R.id.id_mult);
        btnDiv = (Button)findViewById(R.id.id_div);
        btnRes = (Button)findViewById(R.id.id_igual);
        btnBorrar = (Button)findViewById(R.id.id_borrar);
/*
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
*/


    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,Ejercicio4.class);
        startActivity(i);
    }


}
