package com.marcelo.parking;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, " **Bem Vindo!** ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

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

    public void startTestActivity(View view) {
        Intent testActivity = new Intent(this, TestActivity.class);
        startActivity(testActivity);
    }
}
