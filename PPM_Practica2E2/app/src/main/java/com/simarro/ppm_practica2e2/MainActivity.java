package com.simarro.ppm_practica2e2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.button);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            contactos();

        }else{
            Utilidades.solicitarPermiso(Manifest.permission.READ_CONTACTS,"La app necesita permiso para leer contactos.",1,this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults){
        if (requestCode == 1){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Log.d("Acceso","Se ha accedido a los contactos");
            }else{
                Log.d("Acceso","Sin permiso no se puede acceder a contactos");
            }
        }
    }

    public void contactos(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivity(i);
    }
}
