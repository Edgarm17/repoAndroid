package com.simarro.pmm_practica11edgar;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class CustomIntentService extends IntentService {
    private final String VALOR = "not";


    public CustomIntentService() {
        super("Servicio app");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String valorRecibido = intent.getStringExtra("datos");

        if (valorRecibido.equals(VALOR)){

        }else{

        }
    }
}
