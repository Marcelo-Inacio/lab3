package com.marcelo.parking;

import android.text.Editable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Marcelo on 14/11/2015.
 */
public class Espec {
    private String modelo;
    private String cor;
    private String data;
    private String hora;

    public Espec(String modeloVeiculo, String corVeiculo){
        modelo = modeloVeiculo;
        cor = corVeiculo;
        getDate();
        getOclock();
    }


    public String getModelo(){
        return modelo;
    }

    public String getCor(){
        return cor;
    }

    public void getDate(){ // PEGA DATA LOCAL DO SISTEMA NO FORMATO DIA/MES/ANO
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d = Calendar.getInstance().getTime();
        data = sdf.format(d);
    }

    public String getData(){
        return data;
    }

    public void getOclock(){  // PEGA HORA LOCAL DO SISTEMA NO FORMATO HORA:MINUTOS
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date h = Calendar.getInstance().getTime();
        hora = sdf.format(h);
    }

    public String getHora(){
        return hora;
    }

    public void setModelo(String mod){
        modelo = mod;
    }

    public void setCor(String c){
        cor = c;
    }

    public String toString(){
        return modelo+" "+cor+" "+data+" "+hora;
    }
}
