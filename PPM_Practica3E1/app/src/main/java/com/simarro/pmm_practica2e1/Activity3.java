package com.simarro.pmm_practica2e1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {


    private TextView tv3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Bundle cont3 = this.getIntent().getExtras();
        String nombre3 = cont3.getString("nombre");
        tv3 = (TextView)findViewById(R.id.nombre3);
        tv3.setText("Adios "+nombre3);

    }
}
