package com.marcelo.parking;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class BuscarActivity extends AppCompatActivity {

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private EditText txtIn;
    private Carro carro;
    private Estacionamento park;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        txt1 = (TextView) findViewById(R.id.textView1);
        txt2 = (TextView) findViewById(R.id.textView2);
        txt3 = (TextView) findViewById(R.id.textView3);
        txt4 = (TextView) findViewById(R.id.textView4);
        txt5 = (TextView) findViewById(R.id.textView5);
        txt6 = (TextView) findViewById(R.id.textView6);
        txtIn = (EditText) findViewById(R.id.txtBuscarPlaca);
        park = new Estacionamento();


        ImageButton buscar = (ImageButton) findViewById(R.id.btnBuscarPlaca);

        buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                try{
                    carro = park.sendGetPlaca(txtIn.getText().toString());
                    txt1.setText(carro.getProprietario());
                    txt2.setText(carro.getPlaca());
                    txt3.setText(carro.getEsp().getModelo());
                    txt4.setText(carro.getEsp().getCor());
                    txt5.setText(carro.getEsp().getData());
                    txt6.setText(carro.getEsp().getHora());
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    public void buscarPlaca (){

    }
}
