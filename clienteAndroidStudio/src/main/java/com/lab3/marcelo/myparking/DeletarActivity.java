package com.lab3.marcelo.myparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DeletarActivity extends AppCompatActivity {

    private TextView viewProprietario;
    private TextView viewPlaca;
    private TextView viewModelo;
    private TextView viewCor;
    private TextView viewData;
    private TextView viewHora;
    private TextView viewResultado;
    private EditText placaEntrada;
    private Carro carro;
    private Status status;
    private Estacionamento park;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar);
    }

    public void btnSearchDeletar (View v){
        ImageButton button = (ImageButton) v;

        viewProprietario = (TextView) findViewById(R.id.viewDeletarProprietario);
        viewPlaca = (TextView) findViewById(R.id.viewDeletarPlaca);
        viewModelo = (TextView) findViewById(R.id.viewDeletarModelo);
        viewCor = (TextView) findViewById(R.id.viewDeletarCor);
        viewData = (TextView) findViewById(R.id.viewDeletarData);
        viewHora = (TextView) findViewById(R.id.viewDeletarHora);
        viewResultado = (TextView) findViewById(R.id.viewDeletarResultado);
        placaEntrada = (EditText) findViewById(R.id.txtPlacaDeletar);
        park = new Estacionamento();

        try{
            carro = park.sendGetPlaca(placaEntrada.getText().toString());
            viewProprietario.setText(carro.getProprietario());
            viewPlaca.setText(carro.getPlaca());
            viewModelo.setText(carro.getEsp().getModelo());
            viewCor.setText(carro.getEsp().getCor());
            viewData.setText(carro.getEsp().getData());
            viewHora.setText(carro.getEsp().getHora());
            viewResultado.setText("  ");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDeletar (View v){
        Button excluir = (Button) v;

        try{
            status = park.sendDeletar(placaEntrada.getText().toString());
            viewResultado.setText(status.getStatus()+" "+status.getObs());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }

    }
}
