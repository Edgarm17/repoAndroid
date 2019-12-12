package com.example.pmm_proyecto_alexbataller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.core.app.ActivityCompat;

public class Utilidades {

    public static void solicitarPermiso(final String permiso, String explicacion, final int codigoDePeticion, final Activity actividad, Context context){
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad, permiso)){
            new AlertDialog.Builder(actividad)
                    .setTitle(context.getString(R.string.sol_permiso))
                    .setMessage(explicacion)
                    .setPositiveButton(actividad.getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(actividad, new String[]{permiso}, codigoDePeticion);
                        }
                    })
                    .show();
        }else
            ActivityCompat.requestPermissions(actividad, new String[]{permiso}, codigoDePeticion);
    }
}
