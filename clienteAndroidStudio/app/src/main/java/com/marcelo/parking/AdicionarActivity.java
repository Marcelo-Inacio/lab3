package com.marcelo.parking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdicionarActivity extends AppCompatActivity {

    Estacionamento park = new Estacionamento();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
    }
    private EditText proprietario;
    private EditText placa;
    private EditText modelo;
    private EditText cor;

    public void btnEnviar(View v){
        Button btn = (Button) v;

        proprietario = (EditText) findViewById(R.id.txtBuscarPlaca);
        placa = (EditText) findViewById(R.id.txtPlaca);
        modelo = (EditText) findViewById(R.id.txtModelo);
        cor = (EditText) findViewById(R.id.txtCor);

        Carro ca = new Carro(proprietario.getText().toString(), placa.getText().toString(), new Espec(modelo.getText().toString(), cor.getText().toString()));
        Status st = park.sendAddCarro(ca);

        TextView resultado = (TextView) findViewById(R.id.textView);
        resultado.setText(st.getStatus()+" "+st.getObs());



    }
}
