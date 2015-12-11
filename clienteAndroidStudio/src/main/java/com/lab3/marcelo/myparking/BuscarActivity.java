package com.lab3.marcelo.myparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarActivity extends AppCompatActivity {

    private TextView proprietario;
    private TextView placa;
    private TextView modelo;
    private TextView cor;
    private TextView data;
    private TextView hora;
    private EditText placaEntrada;
    private Carro carro;
    private Estacionamento park;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
    }

    public void btnBuscarPlaca (View v){
        ImageButton botao = (ImageButton) v;

        proprietario = (TextView) findViewById(R.id.textViewBuscarProprietario);
        placa = (TextView) findViewById(R.id.textViewBuscarPlaca);
        modelo = (TextView) findViewById(R.id.textViewBuscarModelo);
        cor = (TextView) findViewById(R.id.textViewBuscarCor);
        data = (TextView) findViewById(R.id.textViewBuscarData);
        hora = (TextView) findViewById(R.id.textViewBuscarHora);
        placaEntrada = (EditText) findViewById(R.id.editTxtBuscarPlacaEntrada);
        park = new Estacionamento();

        try{
            carro = park.sendGetPlaca(placaEntrada.getText().toString());
            proprietario.setText(carro.getProprietario());
            placa.setText(carro.getPlaca());
            modelo.setText(carro.getEsp().getModelo());
            cor.setText(carro.getEsp().getCor());
            data.setText(carro.getEsp().getData());
            hora.setText(carro.getEsp().getHora());

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }

    }
}
