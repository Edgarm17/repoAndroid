package com.example.pmm_proyecto_alexbataller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class AcercaDe extends AppCompatActivity implements View.OnClickListener {

    private ImageView twitter;
    private ImageView insta;
    private ImageView youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        twitter = findViewById(R.id.twitter);
        twitter.setOnClickListener(this);
        insta = findViewById(R.id.insta);
        insta.setOnClickListener(this);
        youtube = findViewById(R.id.youtube);
        youtube.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WebActivity.class);
        Bundle b = new Bundle();
        String url = getString(R.string.url_google);
        if (v.getId() == R.id.twitter){
            url = getString(R.string.url_twitter);
        }else if(v.getId() == R.id.insta){
            url = getString(R.string.url_insta);
        }else if(v.getId() == R.id.youtube){
            url = getString(R.string.url_youtube);
        }
        b.putString("url", url);
        intent.putExtras(b);
        startActivity(intent);
    }
}
