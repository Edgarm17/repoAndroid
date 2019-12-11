package com.edgar.proyecto_android_edgar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class activityInfo extends AppCompatActivity implements View.OnClickListener{

    CheckBox check;
    TextView appinfo,team,manager;
    public Typeface fuenteTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        fuenteTitulo = Typeface.createFromAsset(getAssets(),"fuentes/SFSportsNight.ttf");
        appinfo = (TextView)findViewById(R.id.textView2);
        team = (TextView)findViewById(R.id.textView3);
        manager = (TextView)findViewById(R.id.textView4);
        appinfo.setTypeface(fuenteTitulo);
        team.setTypeface(fuenteTitulo);
        manager.setTypeface(fuenteTitulo);

        check = (CheckBox)findViewById(R.id.checkBox);
        check.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        if (check.isChecked()){
            Toast.makeText(this, "A partir de ahora recibirá info", Toast.LENGTH_SHORT).show();
        }
    }
}
