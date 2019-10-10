package com.simarro.ejercicioslayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Puzle extends AppCompatActivity implements View.OnClickListener {


    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnBarajar;
    Button[] piezas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzle);


        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btnBarajar = (Button) findViewById(R.id.barajar);

        piezas = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnBarajar.setOnClickListener(this);





    }


    @Override
    public void onClick(View view) {

        if (view.getId() == btnBarajar.getId()){


            Random r = new Random();

            for (int i = 0; i < piezas.length; i++) {
                int posAleatoria = r.nextInt(piezas.length-1);
                String temp = piezas[i].getText().toString();
                piezas[i].setText(piezas[posAleatoria].getText().toString());
                piezas[posAleatoria].setText(temp);
            }



        }else{




            int posicion=0;

            for (int i = 0; i < piezas.length; i++) {
                if (view.getId()==piezas[i].getId()){
                    posicion = i+1;
                }
            }



            Button botonPulsado = (Button)findViewById(view.getId());



            switch (posicion){
                case 1:
                    for (int i = 0; i < piezas.length; i++){

                    }

                    if (piezas[1].getText().toString().equals("")){
                        piezas[1].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[3].getText().toString().equals("")){
                        piezas[3].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 2:

                    if (piezas[0].getText().toString().equals("")){
                        piezas[0].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[2].getText().toString().equals("")){
                        piezas[2].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[4].getText().toString().equals("")){
                        piezas[4].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 3:

                    if (piezas[1].getText().toString().equals("")){
                        piezas[1].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[5].getText().toString().equals("")){
                        piezas[5].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 4:

                    if (piezas[0].getText().toString().equals("")){
                        piezas[0].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[4].getText().toString().equals("")){
                        piezas[4].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[6].getText().toString().equals("")){
                        piezas[6].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 5:

                    if (piezas[1].getText().toString().equals("")){
                        piezas[1].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[3].getText().toString().equals("")){
                        piezas[3].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[5].getText().toString().equals("")){
                        piezas[5].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[7].getText().toString().equals("")){
                        piezas[7].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 6:

                    if(piezas[2].getText().toString().equals("")){
                        piezas[2].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[8].getText().toString().equals("")){
                        piezas[8].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[4].getText().toString().equals("")){
                        piezas[4].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 7:

                    if(piezas[3].getText().toString().equals("")){
                        piezas[3].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[7].getText().toString().equals("")){
                        piezas[7].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 8:

                    if(piezas[4].getText().toString().equals("")){
                        piezas[4].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[6].getText().toString().equals("")){
                        piezas[6].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }else if(piezas[8].getText().toString().equals("")){
                        piezas[8].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;
                case 9:

                    if(piezas[5].getText().toString().equals("")){
                        piezas[5].setText(botonPulsado.getText());
                        botonPulsado.setText("");

                    }else if(piezas[7].getText().toString().equals("")){

                        piezas[7].setText(botonPulsado.getText());
                        botonPulsado.setText("");
                    }

                    break;


            }




        }




    }
}
