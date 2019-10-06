package com.simarro.ejercicio1activity;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import java.util.ArrayList;

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

        btnSuma.setOnClickListener(this);
        btnResta.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnRes.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);





    }


    @Override
    public void onClick(View v) {
        String contenido="";
        if (tv.getText().toString().trim().length() > 8){
            tv.setTextSize(30);
        }else{
            tv.setTextSize(60);
        }
        switch (v.getId()){
            case R.id.id_0:
                contenido = "0";
                tv.append(contenido);
                break;
            case R.id.id_1:
                contenido = "1";
                tv.append(contenido);
                break;
            case R.id.id_2:
                contenido = "2";
                tv.append(contenido);
                break;
            case R.id.id_3:
                contenido = "3";
                tv.append(contenido);
                break;
            case R.id.id_4:
                contenido = "4";
                tv.append(contenido);
                break;
            case R.id.id_5:
                contenido = "5";
                tv.append(contenido);
                break;
            case R.id.id_6:
                contenido = "6";
                tv.append(contenido);
                break;
            case R.id.id_7:
                contenido = "7";
                tv.append(contenido);
                break;
            case R.id.id_8:
                contenido = "8";
                tv.append(contenido);
                break;
            case R.id.id_9:
                contenido = "9";
                tv.append(contenido);
                break;
            case R.id.id_suma:
                contenido = " + ";
                tv.append(contenido);
                break;
            case R.id.id_menos:
                contenido = " - ";
                tv.append(contenido);
                break;
            case R.id.id_mult:
                contenido = " * ";
                tv.append(contenido);
                break;
            case R.id.id_borrar:
                tv.setText("");
                break;
            case R.id.id_div:
                contenido = " / ";
                tv.append(contenido);
                break;
            case R.id.id_igual:
                resolverOperacion(tv.getText().toString(),tv);
                break;
            default:break;
        }


    }

    public static void comprobarOperaciones(String[] partesOp, ArrayList<Integer> opSuma,ArrayList<Integer> opResta,ArrayList<Integer> opMulti,ArrayList<Integer> opDiv){

        opSuma.clear();
        opResta.clear();
        opDiv.clear();
        opMulti.clear();
        for (int i = 0; i<partesOp.length; i++){
            Log.d("hola","Parte "+i+": "+partesOp[i]);
            switch (partesOp[i]){
                case "+":
                    opSuma.add(i);

                    break;
                case "-":
                    opResta.add(i);

                    break;
                case "*":
                    opMulti.add(i);

                    break;
                case "/":
                    opDiv.add(i);

                    break;
                default:
                    break;

            }
        }

    }

    public static void resolverOperacion(String operacion, TextView tv){
        Log.d("hola",operacion.replace(" ",""));
        //CREAMOS ARRAYLIST DE TODOS LOS TIPOS DE OPERANDOS GUARDANDO SUS POSICIONES EN LA OPERACION.
        //DE ESTA MANERA SABREMOS SI LA OPERACION TIENE LOS DIFERENTES OPERANDOS Y SU POSICIÓN.
        //ESTO SERVIRÁ PARA DESPUÉS APLICAR EL ORDEN DE PRIORIDAD DE LOS OPERANDOS
        ArrayList<Integer> opSuma = new ArrayList<>();
        ArrayList<Integer> opResta = new ArrayList<>();
        ArrayList<Integer> opMulti = new ArrayList<>();
        ArrayList<Integer> opDiv = new ArrayList<>();

        


        String[] partesOp=operacion.split(" ");

        comprobarOperaciones(partesOp,opSuma,opResta,opMulti,opDiv);

        //EN ESTE WHILE SE VAN HACIENDO LAS OPERACIONES SEGUN
        while (partesOp.length!=1){
            if (opMulti.size()>0){
                partesOp=operar(partesOp,opMulti,operacion,1);
                opMulti.clear();
                comprobarOperaciones(partesOp,opSuma,opResta,opMulti,opDiv);
            }else if (opDiv.size()>0){
                partesOp=operar(partesOp,opDiv,operacion,2);
                opDiv.clear();
                comprobarOperaciones(partesOp,opSuma,opResta,opMulti,opDiv);
            }else if (opSuma.size() > 0 ){
                partesOp=operar(partesOp,opSuma,operacion,3);
                opSuma.clear();
                comprobarOperaciones(partesOp,opSuma,opResta,opMulti,opDiv);
            }else if (opResta.size() > 0){
                partesOp=operar(partesOp,opResta,operacion,4);
                opResta.clear();
                comprobarOperaciones(partesOp,opSuma,opResta,opMulti,opDiv);
            }
        }

        operacion="";
        for (int i = 0; i < partesOp.length; i++){
            operacion = partesOp[i];
        }

        tv.setText(operacion);



    }


    //EN ESTE MÉTODO EL VALOR DE TipoOperacion SE DECIDE ARRIBA EN EL CONDICIONAL SEGUN EL TIPO:
    // MULTIPLICACIONES = 1, DIVISIONES = 2, SUMAS = 3, RESTAS = 4
    public static String[] operar(String[] partesOp, ArrayList<Integer> arrayOp, String operacion, int tipoOperacion){
        float num1,num2;
        float resultado=0;
        int posicionOp;
        operacion = "";


            posicionOp = arrayOp.get(0);
            num1 = Float.valueOf( partesOp[posicionOp-1].trim()).floatValue();
            num2 = Float.valueOf( partesOp[posicionOp+1].trim()).floatValue();
            switch (tipoOperacion){
                case 1:
                    resultado = num1 * num2;
                    break;
                case 2:
                    resultado = num1 / num2;
                    break;
                case 3:
                    resultado = num1 + num2;
                    break;
                case 4:
                    resultado = num1 - num2;
                    break;
            }

            Log.d("hola", "Resultado multiplicacion "+num1+"*"+num2+"="+resultado);
            partesOp[posicionOp-1]="";
            partesOp[posicionOp+1]="";
            partesOp[posicionOp]=Float.toString(resultado);

            for (int x = 0; x < partesOp.length; x++){
                if (partesOp[x]!=""){
                    operacion+=partesOp[x]+" ";
                }
            }


        partesOp = operacion.split(" ");

        Log.d("hola",operacion);
        return partesOp;
    }




}
