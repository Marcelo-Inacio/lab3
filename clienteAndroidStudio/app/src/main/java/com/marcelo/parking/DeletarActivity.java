package com.marcelo.parking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DeletarActivity extends AppCompatActivity {

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private TextView textView;
    private EditText txtIn;
    private Carro carro;
    private Status status;
    private Estacionamento park;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar);
    }

    public void btnSearchExcluir (View v){
        ImageButton button = (ImageButton) v;

        txt1 = (TextView) findViewById(R.id.textView01);
        txt2 = (TextView) findViewById(R.id.textView02);
        txt3 = (TextView) findViewById(R.id.textView03);
        txt4 = (TextView) findViewById(R.id.textView04);
        txt5 = (TextView) findViewById(R.id.textView05);
        txt6 = (TextView) findViewById(R.id.textView06);
        textView = (TextView) findViewById(R.id.textViewDeletar);
        txtIn = (EditText) findViewById(R.id.txtBuscarDeletar);
        park = new Estacionamento();

        try{
            carro = park.sendGetPlaca(txtIn.getText().toString());
            txt1.setText(carro.getProprietario());
            txt2.setText(carro.getPlaca());
            txt3.setText(carro.getEsp().getModelo());
            txt4.setText(carro.getEsp().getCor());
            txt5.setText(carro.getEsp().getData());
            txt6.setText(carro.getEsp().getHora());
            textView.setText(" ");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnExcluir (View v){
        Button excluir = (Button) v;

        try{
            status = park.sendDeletar("abc-0908");
            textView.setText(status.getStatus()+" "+status.getObs());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }

    }

}