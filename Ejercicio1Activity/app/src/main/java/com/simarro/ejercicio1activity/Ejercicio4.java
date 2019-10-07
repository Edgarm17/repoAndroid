package com.simarro.ejercicio1activity;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

public class Ejercicio4 extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn0, btn4, btn5, btn6, btn7, btn8, btn9, btnSuma, btnResta, btnMulti, btnRes, btnBorrar, btnDiv,btnDecimal;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_calc);

        tv = (TextView) findViewById(R.id.idResultado);

        btn0 = (Button) findViewById(R.id.id_0);
        btn1 = (Button) findViewById(R.id.id_1);
        btn2 = (Button) findViewById(R.id.id_2);
        btn3 = (Button) findViewById(R.id.id_3);
        btn4 = (Button) findViewById(R.id.id_4);
        btn5 = (Button) findViewById(R.id.id_5);
        btn6 = (Button) findViewById(R.id.id_6);
        btn7 = (Button) findViewById(R.id.id_7);
        btn8 = (Button) findViewById(R.id.id_8);
        btn9 = (Button) findViewById(R.id.id_9);
        btnSuma = (Button) findViewById(R.id.id_suma);
        btnResta = (Button) findViewById(R.id.id_menos);
        btnMulti = (Button) findViewById(R.id.id_mult);
        btnDiv = (Button) findViewById(R.id.id_div);
        btnRes = (Button) findViewById(R.id.id_borrar);
        btnBorrar = (Button) findViewById(R.id.id_igual);
        btnDecimal = (Button) findViewById(R.id.id_decimal);


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
        btnDecimal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String contenido = "";
        if (tv.getText().toString().trim().length() > 8) {
            tv.setTextSize(30);
        } else {
            tv.setTextSize(60);
        }
        switch (v.getId()) {
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
            case R.id.id_decimal:
                contenido = ".";
                tv.append(contenido);
                break;
            case R.id.id_suma:
                contenido = " + ";
                tv.append(contenido);
                break;
            case R.id.id_menos:
                if (tv.getText().toString().equals("")){
                    tv.append("-");
                }else {
                    contenido = " - ";
                    tv.append(contenido);
                }
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
                resolverOperacion(tv.getText().toString(), tv);
                break;
            default:
                break;
        }


    }

    public static void comprobarOperaciones(String[] partesOp, ArrayList<Integer> opSumaResta, ArrayList<Integer> opMultiDiv) {

        opSumaResta.clear();
        opMultiDiv.clear();
        for (int i = 0; i < partesOp.length; i++) {
            //Log.d("hola","Parte "+i+": "+partesOp[i]);
            switch (partesOp[i]) {
                case "+":
                    opSumaResta.add(i);

                    break;
                case "-":
                    opSumaResta.add(i);

                    break;
                case "*":
                    opMultiDiv.add(i);

                    break;
                case "/":
                    opMultiDiv.add(i);

                    break;
                default:
                    break;

            }
        }

    }

    public static void resolverOperacion(String operacion, TextView tv) {
        // Log.d("hola",operacion.replace(" ",""));
        //CREAMOS ARRAYLIST DE TODOS LOS TIPOS DE OPERANDOS GUARDANDO SUS POSICIONES EN LA OPERACION.
        //DE ESTA MANERA SABREMOS SI LA OPERACION TIENE LOS DIFERENTES OPERANDOS Y SU POSICIÓN.
        //ESTO SERVIRÁ PARA DESPUÉS APLICAR EL ORDEN DE PRIORIDAD DE LOS OPERANDOS
        ArrayList<Integer> opSumaResta = new ArrayList<>();
        ArrayList<Integer> opMultiDiv = new ArrayList<>();
        String[] partesOp = operacion.split(" ");
        boolean[] error = new boolean[1];
        //CREAMOS UN VECTOR DE BOOLEANOS DE UN SOLO ESPACIO PARA PODER PASAR POR REFERENCIA EL BOOLEANO Y AL MODIFICARLO
        //DENTRO DE LA FUNCION, TAMBIEN SE MODIFICA FUERA
        error[0]=false;
        comprobarOperaciones(partesOp, opSumaResta, opMultiDiv);

        if (partesOp[partesOp.length-1].equals("+") || partesOp[partesOp.length-1].equals("-") || partesOp[partesOp.length-1].equals("*") || partesOp[partesOp.length-1].equals("/")){
            error[0] = true;
        }


        //EN ESTE WHILE SE VAN HACIENDO LAS OPERACIONES SEGUN
        while (partesOp.length != 1 && !error[0]) {
            if (opMultiDiv.size() > 0) {
                if (error[0]){
                    break;
                }
                partesOp = operar(partesOp, opMultiDiv, operacion, 1,error);
                opMultiDiv.clear();
                comprobarOperaciones(partesOp, opSumaResta,opMultiDiv);
            }else if (opSumaResta.size() > 0) {
                if (error[0]){
                    break;
                }
                partesOp = operar(partesOp, opSumaResta, operacion, 2,error);
                opSumaResta.clear();
                comprobarOperaciones(partesOp, opSumaResta,opMultiDiv);
            }
        }

            if (error[0]){
                operacion = "ERROR";
            }else{
                operacion = "";
                for (int i = 0; i < partesOp.length; i++) {
                    operacion = partesOp[i];
                }
            }




        tv.setText(operacion);

    }

    //EN ESTE MÉTODO EL VALOR DE TipoOperacion SE DECIDE ARRIBA EN EL CONDICIONAL SEGUN EL TIPO:
    // MULTIPLICACIONES o DIVISIONES = 1, SUMAS o RESTAS = 2
    public static String[] operar(String[] partesOp, ArrayList<Integer> arrayOp, String operacion, int tipoOperacion, boolean[] error) {
        float num1=0;
        float num2=0;
        float resultado = 0;
        int posicionOp;
        operacion = "";

        posicionOp = arrayOp.get(0);

        //CON ESTE TRY/CATCH LO QUE SE HACE ES COMPORBAR SI SE HAN METIDO DOS SIMBOLOS DE OPERACION SEGUIDOS. EJ: 4 + - 5

        try {
            num1 = Float.valueOf(partesOp[posicionOp - 1]);
            num2 = Float.valueOf(partesOp[posicionOp + 1]);
        }catch (NumberFormatException nf){
            Log.d("hola","Error");
            error[0] = true;

        }


        switch (tipoOperacion) {
            case 1:
                if (partesOp[posicionOp].equals("*")){
                    resultado = num1 * num2;
                }else if (partesOp[posicionOp].equals("/")){
                    resultado = num1 / num2;
                }

                break;
            case 2:
                if (partesOp[posicionOp].equals("+")){
                    resultado = num1 + num2;
                }else if (partesOp[posicionOp].equals("-")){
                    resultado = num1 - num2;
                }

                break;
        }

    //    Log.d("hola", "Resultado operacion " + num1 + "*" + num2 + "=" + resultado);
        partesOp[posicionOp - 1] = "";
        partesOp[posicionOp + 1] = "";
        partesOp[posicionOp] = Float.toString(resultado);

        for (int x = 0; x < partesOp.length; x++) {
            if (partesOp[x] != "") {
                operacion += partesOp[x] + " ";
            }
        }

        partesOp = operacion.split(" ");

        //   Log.d("hola",operacion);
        return partesOp;
    }

}
