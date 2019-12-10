package com.carlesramos.numerosprimosassync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvNumerosEncontrados;
    private TextView tvUltimoPrimo;
    private Button btIniciar;
    private Button btParar;
    private ActionAssync sincronia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNumerosEncontrados = findViewById(R.id.tvPimosEncontrados);
        tvUltimoPrimo = findViewById(R.id.tvUltimoPrimo);
        btParar = findViewById(R.id.btParar);
        btIniciar = findViewById(R.id.btIniciar);
        btParar.setOnClickListener(this);
        btIniciar.setOnClickListener(this);
        sincronia = new ActionAssync();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btIniciar:
                sincronia.execute();
                break;
            case R.id.btParar:
                sincronia.cancel(true);
        }
    }


    private class ActionAssync extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            int posiblesPrimos = 0;
            int numPrimos = 0;
            int ultimoPrimo = 0;
            int contador = 0;
            while (!isCancelled()){
                posiblesPrimos++;
                for(int i=1; i<=posiblesPrimos; i++){

                    if (posiblesPrimos % i == 0){
                        contador++;
                    }
                }
                if (contador == 2){
                    numPrimos++;
                    publishProgress(posiblesPrimos, numPrimos);
                }
                contador = 0;
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            tvUltimoPrimo.setText(String.valueOf(values[0]));
            tvNumerosEncontrados.setText(String.valueOf(values[1]));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Ultimo primo: " + tvUltimoPrimo.getText(),Toast.LENGTH_LONG).show();
        }
    }
}
