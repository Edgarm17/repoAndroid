package com.simarro.pmm_practica11edgar;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TareaAsincrona extends AsyncTask<Integer,Integer,Boolean> {

    private Context mContext;
    private ProgressBar progressBar;

    public TareaAsincrona(Context mContext, ProgressBar progressBar) {
        this.mContext = mContext;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setProgress(0);
        progressBar.setMax(100);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (MainActivity.modoAvion)return;
        Toast.makeText(mContext, "Página cargada", Toast.LENGTH_SHORT).show();
        MainActivity.progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.incrementProgressBy(values[0]);

    }



    @Override
    protected Boolean doInBackground(Integer... integers) {
        for (int i = 0; i < integers[0]; i++){
            try {
                Thread.sleep(1000);
                publishProgress(33);

                if (isCancelled()){
                    break;
                }
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    protected void onCancelled() {

        MainActivity.progressBar.setVisibility(View.GONE);
    }
}
