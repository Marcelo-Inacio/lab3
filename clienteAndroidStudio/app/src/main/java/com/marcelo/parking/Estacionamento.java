package com.marcelo.parking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marcelo on 14/11/2015.
 */
public class Estacionamento {
    private Conexao connection = new Conexao();

    private List<Carro> allVeiculos = new LinkedList<Carro>();	//contem todos veiculos local

    public List<Carro> getAllCarros(){
        try {
            allVeiculos = connection.sendGet();
            return allVeiculos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Carro sendGetPlaca(String placa){	//buscar placa no servidor
        try {
            return connection.sendBuscarPlaca(placa).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status sendAddCarro (Carro veic){ //add novo carro no servidor e na lista local
        Status result = new Status();
        boolean situ;
        try {
            result = connection.sendAddCarro(veic); //envia objeto servidor
            situ = addCarro(veic); //add lista local

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Status sendUpdate (Carro car, String pla){ //alterar carro no servidor e lista local
        Status result = new Status();
        boolean situ;
        try {
            result = connection.sendUpdateCarro(car, pla);
            situ = alterar(car, pla);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Status sendDeletar (String placa){ //remove carro no servidor e lista local
        Status result = new Status("FALHA !","PROBLEMA CONEXÃO");
        boolean situ = false;
        try {
            result = connection.sendDelCarro(placa);
            situ = deleteCarro(placa);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

/*  AQUI MANIPULA SOMENTE A LISTA LOCAL*/

    public List<Carro> getBase (){
        return allVeiculos;
    }

    public boolean verificar(Carro c){ //AUXILIA O ADD CARRO NO BANCO DE DADOS
        for (Carro kar:allVeiculos){
            if(kar.getPlaca().equals(c.getPlaca())) return true;
        }
        return false;
    }

    public boolean addCarro(Carro car){ //ADD CARRO NÃO REPETIDO POR PLACA NA BASE
        if(verificar(car) == false){
            allVeiculos.add(car);
            return true;
        }
        return false;
    }

    public Carro buscarPlaca(String str){ //PESQUISAR POR PLACA
        for (Carro c: allVeiculos){
            if(c.getPlaca().equals(str)) return c;
        }
        return null;
    }

    public LinkedList buscarData(String str){ //PESQUISAR POR DATA - FAZER FILTRO ARRUMAR

        LinkedList lista = new LinkedList<Carro>();
        for(Carro i:allVeiculos){
            if(i.getEsp().getData().equals(str)) lista.add(i);
        }
        return lista;
    }

    public boolean deleteCarro(String pla){  //REMOVE CARRO DO BANCO PASSANDO PLACA
        Carro remov = buscarPlaca(pla);

        if (remov != null){
            allVeiculos.remove(remov);
            return true;
        }
        return false;
    }

    public boolean alterar(Carro alt, String pla){
        for(Carro car:allVeiculos){
            if(car.getPlaca().equals(pla)){
                car.setProprietario(alt.getProprietario());
                car.setPlaca(alt.getPlaca());
                car.setEsp(alt.getEsp());
                return true;
            }
        }
        return false;
    }

    public List mostrarCarros(){
        return allVeiculos;
    }

    public LinkedList<Carro> buscarEntreData(String dt1, String dt2) throws ParseException { //PESQUISAR POR PLACA

        LinkedList <Carro>lista = new LinkedList<Carro>(); //INICIA LISTA VAZIA

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //DEFINE UM FORMATO DE DATA

        Date dataDe = new Date(sdf.parse(dt1).getTime()); //CRIA VARIAVEL E RECEBE CONVERTIDA DO TIPO String PARA TIPO Date
        Date dataAte = new Date(sdf.parse(dt2).getTime()); //CRIA VARIAVEL E RECEBE CENVERTIDA DO TIPO String PARA TIPO Date

        for (Carro c: allVeiculos){ //PERCORRO TODOS OBJETOS DO BANCO
            Date dataCarro = new Date(sdf.parse(c.getEsp().getData()).getTime()); //CONVERTO A DATA DO CARRO DO TIPO STRING PARA DATE

            if((dataCarro.after(dataDe)) && (dataCarro.before(dataAte))){
                lista.add(c); //COMPARO AS DATAS SE ESTÃO ENTRE AS DATAS DO PARAMETRO
            }
        }
        return lista; //RETORNO A LISTA COM OS CARROS
    }
}
