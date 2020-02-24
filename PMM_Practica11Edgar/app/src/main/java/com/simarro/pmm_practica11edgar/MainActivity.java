package com.simarro.pmm_practica11edgar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DialogPersonalizado.OnLoginListener,View.OnClickListener{




    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static int modificarJugador;
    public static int jugadorAModificar;
    public static ArrayList<Jugador> jugadores = new ArrayList<>();
    public static WebView webView;
    public static boolean modoAvion;
    public static String estado,temperatura,humedad,viento,nombreUsuario,correo;


    public static ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ModoVueloReceiver receiver = new ModoVueloReceiver();

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(receiver,intentFilter);

        Fragment fragment = FragmentPerfil.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

        SharedPreferences prefs = getSharedPreferences("Usuarios", Context.MODE_PRIVATE);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        correo = preferences.getString("username","CORREO");
        PreferenciasAplicacion.obtenerPreferencias(preferences,this);
        PreferenciasAplicacion.mostrarPreferencias(preferences,this);

        PreferenceManager.setDefaultValues(this,R.xml.preferencias,false);

        if (prefs.getString("username","").equals("")){
            mostrarDialogoPersonalizado();


            getSupportActionBar().setTitle("Perfil");
        }


        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (!prefs.getString("username","").equals("")){
            String user = prefs.getString("username","noUser");
            nombreUsuario = user;
            View vistaHeaderNav = navigationView.getHeaderView(0);
            TextView tvName = (TextView)vistaHeaderNav.findViewById(R.id.tvNombre);
            ImageView img = (ImageView)vistaHeaderNav.findViewById(R.id.imgView);
            img.setOnClickListener(this);

            switch (user){

                case "edgar":

                    img.setImageDrawable(getDrawable(R.drawable.yoda));

                    break;
                case "jose":
                    img.setImageDrawable(getDrawable(R.drawable.darth));
                    break;
                case "pepe":

                    img.setImageDrawable(getDrawable(R.drawable.chew));
                    break;
            }
            tvName.setText(user);
            Log.d("tag","El usuario "+user+" esta logeado");
        }

    }

    public void mostrarDialogoPersonalizado(){

        DialogFragment dialog = new DialogPersonalizado();
        dialog.show(getSupportFragmentManager(),"DialogPersonalizado");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferencias, menu);

        return  true;

    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.menu_op1:
                getSupportActionBar().setTitle("Información");
                fragment = FragmentInfo.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                break;
            case R.id.menu_op2:
                getSupportActionBar().setTitle("Jugadores");
                fragment = FragmentJugadores.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                break;
            case R.id.menu_op3:
                getSupportActionBar().setTitle("Noticias");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                PreferenciasAplicacion.obtenerPreferencias(preferences,this);
                fragment = FragmentNoticias.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

                break;
            case R.id.menu_subopcion_1:
                fragment = FragmentFondo.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                SharedPreferences prefs = getSharedPreferences("Usuarios", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.remove("username").commit();
                getSupportActionBar().setTitle("Login");
                mostrarDialogoPersonalizado();
                break;

            case R.id.menu_op4:
                fragment = FragmentTiempo.newInstance();

                ClassConnection connection = new ClassConnection();

                try {
                    String response = connection.execute("https://api.openweathermap.org/data/2.5/weather?q=Xativa,ES&appid=58a861071ea256255543e94ca1d17e28&lang=es").get();

                    JSONObject jsonRoot = new JSONObject(response);



                    JSONObject objetoMain = jsonRoot.getJSONObject("main");
                    JSONObject objetoViento = jsonRoot.getJSONObject("wind");




                    double temp = Float.parseFloat(objetoMain.getString("temp"))-273.15;
                    long t = Math.round(temp);
                    temperatura = "TEMPERATURA: "+Long.toString(t)+" ºC";
                    humedad ="HUMEDAD: "+ objetoMain.getString("humidity")+"%";
                    viento = "VIENTO: "+objetoViento.getString("speed")+"m/s";

                }catch (InterruptedException e ){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }



        if (navigationView.getCheckedItem() != null){
            navigationView.getCheckedItem().setChecked(false);
        }

        menuItem.setChecked(true);



        drawerLayout.closeDrawers();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.opPref:
                Intent i = new Intent(this,PreferenciasActivity.class);
                startActivity(i);


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLogin(DialogPersonalizado dialog, String user, String pass) {
        if (user.equals("") || pass.equals("")){
            dialog.camposVacios();

        }else{

            try {
                InputStream inputStream = getResources().openRawResource(R.raw.users);

                InputStreamReader sr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(sr);

                Boolean loginCorrecto = false;
                String linea = "";
                String usuario="";
                String password;

                while ((linea = br.readLine()) != null){

                    String[] valors = linea.split(";");

                    usuario = valors[1];
                    password = valors[2];

                    if (usuario.equals(user) && password.equals(pass)){
                        loginCorrecto = true;
                    }


                }

                br.close();
                sr.close();

                if (loginCorrecto){

                    SharedPreferences prefs = getSharedPreferences("Usuarios", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putString("username",user);


                    TextView tvName = (TextView)findViewById(R.id.tvNombre);
                    ImageView img = (ImageView)findViewById(R.id.imgView);
                    switch (user){
                        case "edgar":
                            img.setImageDrawable(getDrawable(R.drawable.darth));
                            break;
                        case "jose":
                            img.setImageDrawable(getDrawable(R.drawable.yoda));
                            break;
                        case "pepe":

                            img.setImageDrawable(getDrawable(R.drawable.chew));
                            break;
                    }
                    tvName.setText(user);
                    prefsEditor.commit();
                    dialog.dismiss();
                }else{
                    dialog.mostrarError();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }






        }
    }

    @Override
    public void onCancel(DialogPersonalizado dialog) {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (FragmentNoticias.tareaAsincrona != null){
            FragmentNoticias.tareaAsincrona.cancel(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgView:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                PreferenciasAplicacion.obtenerPreferencias(preferences,this);
                Fragment f = FragmentPerfil.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,f).commit();

                getSupportActionBar().setTitle("Perfil");
                drawerLayout.closeDrawers();
        }
    }
}
