package com.simarro.pmm_practica11edgar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentNoticias extends Fragment {

    private WebView webView;
    public static TareaAsincrona tareaAsincrona;

    public static FragmentNoticias newInstance(){
        FragmentNoticias fragment = new FragmentNoticias();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_noticias,container,false);

        webView = vista.findViewById(R.id.webView);
        MainActivity.progressBar = vista.findViewById(R.id.pbar);

        tareaAsincrona = new TareaAsincrona(getActivity(),MainActivity.progressBar);
        tareaAsincrona.execute(3);
        MainActivity.webView = webView;

        if (!PreferenciasAplicacion.paginaWeb.equals("")){
            webView.loadUrl(PreferenciasAplicacion.paginaWeb);
            webView.setWebViewClient(new WebViewClient());

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }


        return vista;
    }


}
