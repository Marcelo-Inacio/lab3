package com.marcelo.parking;

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
    private EditText edText1;
    private EditText edText2;
    private EditText edText3;
    private EditText edText4;
    private EditText edText5;
    private TextView vTextR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

//        eText = (EditText) findViewById(R.id.edit001);
//
//        ImageButton buscar = (ImageButton) findViewById(R.id.btnSearch);
//
//        buscar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                eText.setText("tudoCerto");
//
//            }
//        });
    }

    public void btnSearchSign (View v){
        ImageButton button = (ImageButton) v;

        edText1 = (EditText) findViewById(R.id.edit001);
        edText2 = (EditText) findViewById(R.id.edit002);
        edText3 = (EditText) findViewById(R.id.edit003);
        edText4 = (EditText) findViewById(R.id.edit004);
        edText5 = (EditText) findViewById(R.id.edit005);
        park = new Estacionamento();

        try{
            carro = park.sendGetPlaca(edText5.getText().toString());
            edText1.setText(carro.getProprietario());
            edText2.setText(carro.getPlaca());
            edText3.setText(carro.getEsp().getModelo());
            edText4.setText(carro.getEsp().getCor());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnAlterar (View v){
        Button botao = (Button) v;

        try{
            carroUp = new Carro(edText1.getText().toString(), edText2.getText().toString(),
                    new Espec(edText3.getText().toString(), edText4.getText().toString()));

            status = park.sendUpdate(carroUp, edText5.getText().toString());

            vTextR = (TextView) findViewById(R.id.textViewAlterar);
            vTextR.setText(status.getStatus()+" "+status.getObs());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ops! Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }
}
