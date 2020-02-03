package com.edgar.practica10edgargarcia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DialogPersonalizado.OnLoginListener {

    TextView tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNombre = (TextView)findViewById(R.id.tvName);
        mostrarDialogoPersonalizado();


    }

    public void mostrarDialogoPersonalizado(){
        DialogFragment dialog = new DialogPersonalizado();
        dialog.show(getSupportFragmentManager(),"DialogPersonalizado");
    }




    @Override
    public void onLogin(DialogPersonalizado dialog, String user, String pass) {
        if (user.equals("") || pass.equals("")){
            dialog.camposVacios();

        }else if (user.equals(pass)){
            dialog.dismiss();
            tvNombre.setText("Bienvenido "+ user);
        }else{
            dialog.mostrarError();
        }
    }

    @Override
    public void onCancel(DialogPersonalizado dialog) {
        finish();
    }


}
