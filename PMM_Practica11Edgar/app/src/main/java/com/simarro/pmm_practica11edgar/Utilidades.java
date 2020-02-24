package com.simarro.pmm_practica11edgar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.core.app.ActivityCompat;

public class Utilidades {

    public static void solicitarPermiso(final String permiso, String explicacion, final int codigoDePeticion, final Activity activity){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,permiso)){
            new AlertDialog.Builder(activity)
                    .setTitle("Solicitud de permiso")
                    .setMessage(explicacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity,new String[]{permiso},codigoDePeticion);
                        }
                    }).show();
        }else{
            ActivityCompat.requestPermissions(activity,new String[]{permiso},codigoDePeticion);
        }
    }
}
