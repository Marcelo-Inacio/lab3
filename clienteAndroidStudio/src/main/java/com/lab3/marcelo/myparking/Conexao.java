package com.lab3.marcelo.myparking;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marcelo on 09/12/2015.
 */
public class Conexao {

    public List<Carro> sendGet() throws Exception { //pega todos objetos do servidor e guarda em lista

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //linha chave para execução das treds
        StrictMode.setThreadPolicy(policy);

        String url = "http://192.168.1.22:4567/mostrarBase";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        con.setDoInput(true);
        con.connect();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        //System.out.println(response.toString());
        List<Carro> found = findAllItems(new JSONArray(response.toString()));

        return found;
    }

    public List<Carro> sendBuscarPlaca(String placa) throws Exception { //busca a placa no servidor
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //linha chave para execução das treds
        StrictMode.setThreadPolicy(policy);

        String url = "http://192.168.1.22:4567/buscar/placa/"+placa;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        //System.out.println(response.toString());
        List<Carro> found = findAllItems(new JSONArray(response.toString()));

        return found;
    }

    public Status sendAddCarro(Carro carro) throws Exception { //envia novo objeto ao servidor

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //linha chave para execução das treds
        StrictMode.setThreadPolicy(policy);

        String url = "http://192.168.1.22:4567/addCarro/"+carro.getProprietario()+"/"+carro.getPlaca()+"/"+carro.getEsp().getModelo()+"/"+carro.getEsp().getCor();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        //System.out.println(response.toString());
        Status found = findStatus(new JSONArray(response.toString()));

        return found;
    }

    public Status sendUpdateCarro(Carro carro, String placa) throws Exception { //envia alteração do objeto ao servidor

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //linha chave para execução das treds
        StrictMode.setThreadPolicy(policy);

        String url = "http://192.168.1.22:4567/updateCarro/"+carro.getProprietario()+"/"+carro.getPlaca()+"/"+carro.getEsp().getModelo()+"/"+carro.getEsp().getCor()+"/"+placa;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
        con.setDoInput(true);
        con.connect();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        //System.out.println(response.toString());
        Status found = findStatus(new JSONArray(response.toString()));

        return found;
    }

    public Status sendDelCarro(String placa) throws Exception { //deleta objeto do servidor
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //linha chave para execução das treds
        StrictMode.setThreadPolicy(policy);

        String url = "http://192.168.1.22:4567/deleteCarro/"+placa;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        con.setDoInput(true);
        con.connect();
        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        //System.out.println(response.toString());
        Status found = findStatus(new JSONArray(response.toString()));

        return found;
    }

    public List<Carro> findAllItems(JSONArray response) { //objeto carro

        List<Carro> found = new LinkedList<Carro>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                found.add( new Carro(obj.getString("Dono"), obj.getString("Placa"),
                        new Espec( obj.getString("Modelo"), obj.getString("Cor") )));
            }

        } catch (JSONException e) {
            // handle exception
        }
        return found;
    }

    public Status findStatus(JSONArray response) { //objeto status

        Status found = new Status();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                found.setStatus( obj.getString("status") );
                found.setObs( obj.getString("obs") );
            }

        } catch (JSONException e) {
            // handle exception
        }
        return found;
    }


}
