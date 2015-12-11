package com.lab3.marcelo.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    TextView txtView;
//    Carro veic;
//    Estacionamento est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        est = new Estacionamento();
//        txtView = (TextView) findViewById(R.id.tela);
//        try {
//            veic = est.sendGetPlaca("cpl-7777");
//            txtView.setText(veic.getEsp().getModelo());
//        } catch (Exception e) {
//            e.printStackTrace();
//            txtView.setText("deu ruim");
//        }
    }

    public void startAdicionarActivity(View view) {
        Intent adicionarActivity = new Intent(this, AdicionarActivity.class);
        startActivity(adicionarActivity);
    }

    public void startBuscarActivity(View view) {
        Intent buscarActivity = new Intent(this, BuscarActivity.class);
        startActivity(buscarActivity);
    }

    public void startDeletarActivity(View view) {
        Intent deletarActivity = new Intent(this, DeletarActivity.class);
        startActivity(deletarActivity);
    }

    public void startAlterarActivity(View view) {
        Intent alterarActivity = new Intent(this, AlterarActivity.class);
        startActivity(alterarActivity);
    }

    public void startEntreDatasActivity(View view) {
        Intent entreDatasActivity = new Intent(this, EntreDatasActivity.class);
        startActivity(entreDatasActivity);
    }
}
