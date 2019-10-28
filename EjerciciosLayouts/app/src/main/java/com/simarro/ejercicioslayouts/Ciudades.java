package com.simarro.ejercicioslayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Ciudades extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Typeface pokemonFuente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudades);



        Spinner sp1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.paises, R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(this);

        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ciudadesEs, R.layout.spinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(this);

        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText(sp2.getSelectedItem().toString());
        String fuente = "fuentes/Pokemon Hollow.ttf";
        this.pokemonFuente = Typeface.createFromAsset(getAssets(),fuente);
        tv.setTypeface(pokemonFuente);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spAux = (Spinner) findViewById(R.id.spinner2);
        Spinner spPa = (Spinner) findViewById(R.id.spinner);

        ImageView img = (ImageView)findViewById(R.id.imgCiudad);

        if (parent.getId() == R.id.spinner) {
            switch (position) {
                case 0:
                    ArrayAdapter<CharSequence> adapterC0 = ArrayAdapter.createFromResource(this, R.array.ciudadesEs,  R.layout.spinner);
                    adapterC0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spAux.setAdapter(adapterC0);
                    break;
                case 1:
                    ArrayAdapter<CharSequence> adapterC1 = ArrayAdapter.createFromResource(this, R.array.ciudadesEn,  R.layout.spinner);
                    adapterC1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spAux.setAdapter(adapterC1);
                    break;

                case 2:
                    ArrayAdapter<CharSequence> adapterC2 = ArrayAdapter.createFromResource(this, R.array.ciudadesFr,  R.layout.spinner);
                    adapterC2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spAux.setAdapter(adapterC2);
                    break;
            }
        } else if (parent.getId() == R.id.spinner2) {
            TextView tvAux = (TextView) findViewById(R.id.textView2);
            tvAux.setText(parent.getSelectedItem().toString());
            switch (tvAux.getText().toString()){
                case "Barcelona":
                    img.setImageResource(R.drawable.barcelona);
                    break;
                case "Valencia":
                    img.setImageResource(R.drawable.valencia);
                    break;
                case "Paris":
                    img.setImageResource(R.drawable.paris);
                    break;
                case "Burdeos":
                    img.setImageResource(R.drawable.burdeos);
                    break;
                case "Londres":
                    img.setImageResource(R.drawable.londres);
                    break;
                case "Liverpool":
                    img.setImageResource(R.drawable.lliverpool);
                    break;

            }
//            img.getLayoutParams().height = 100;
//            img.getLayoutParams().width = 100;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
