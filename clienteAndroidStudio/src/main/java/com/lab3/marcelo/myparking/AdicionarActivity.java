package com.lab3.marcelo.myparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdicionarActivity extends AppCompatActivity {

    private Estacionamento park;
    private Carro carro;
    private Status st;
    private EditText proprietario;
    private EditText placa;
    private EditText modelo;
    private EditText cor;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
    }

    public void btnEnviar(View v){
        Button btn = (Button) v;
        park = new Estacionamento();

        try{
            proprietario = (EditText) findViewById(R.id.etAddProprietario);
            placa = (EditText) findViewById(R.id.etAddPlaca);
            modelo = (EditText) findViewById(R.id.etAddModelo);
            cor = (EditText) findViewById(R.id.etAddCor);
            resultado = (TextView) findViewById(R.id.tvAddResultado);

            carro = new Carro(proprietario.getText().toString(), placa.getText().toString(), new Espec(modelo.getText().toString(), cor.getText().toString()));
            st = park.sendAddCarro(carro);

            resultado.setText(st.getStatus()+" "+st.getObs());
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }

    }
}
