package com.simarro.pmm_practica11edgar;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PreferenciasAplicacion {
    public static final String EMAIL = "username";
    public static final String PAGINA = "paginaweb";
    public static final String ACTUALIZACIONES = "actualizaciones";

    public static String correo;
    public static String paginaWeb="";

    public static void obtenerPreferencias(SharedPreferences preferences, Context context){
        String msjError="Ok";

        correo = preferences.getString(EMAIL,"CORREO");
        paginaWeb = preferences.getString(PAGINA,"PAGINA");

        MainActivity.correo = correo;


    }

    public static void mostrarPreferencias(SharedPreferences preferences,Context context){
        String msj = "Email: "+correo+"\n";
        msj += "Pagina web favorita: "+paginaWeb;

        Toast.makeText(context,msj,Toast.LENGTH_LONG).show();
    }

}
