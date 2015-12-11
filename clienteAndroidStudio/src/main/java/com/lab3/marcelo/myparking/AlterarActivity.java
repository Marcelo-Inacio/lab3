package com.lab3.marcelo.myparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AlterarActivity extends AppCompatActivity {

    private Estacionamento park;
    private Carro carro;
    private Carro carroUp;
    private Status status;
    private EditText proprietario;
    private EditText placa;
    private EditText modelo;
    private EditText cor;
    private EditText placaAlterar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        resultado = (TextView) findViewById(R.id.tvAltResultado);
    }

    public void btnAltSearch (View v){
        ImageButton button = (ImageButton) v;

        proprietario = (EditText) findViewById(R.id.etAltProprietario);
        placa = (EditText) findViewById(R.id.etAltPlaca);
        modelo = (EditText) findViewById(R.id.etAltModelo);
        cor = (EditText) findViewById(R.id.etAltCor);
        placaAlterar = (EditText) findViewById(R.id.etAltPlacaBuscar);
        park = new Estacionamento();

        resultado.setText("     ");

        try{
            carro = park.sendGetPlaca(placaAlterar.getText().toString());
            proprietario.setText(carro.getProprietario());
            placa.setText(carro.getPlaca());
            modelo.setText(carro.getEsp().getModelo());
            cor.setText(carro.getEsp().getCor());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnAlterar (View v){
        Button botao = (Button) v;

        try{
            carroUp = new Carro(proprietario.getText().toString(), placa.getText().toString(),
                    new Espec(modelo.getText().toString(), cor.getText().toString()));

            status = park.sendUpdate(carroUp, placaAlterar.getText().toString());


            resultado.setText(status.getStatus()+" "+status.getObs());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

}
