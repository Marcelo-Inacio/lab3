import static spark.Spark.get;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONWriter;

import spark.Request;
import spark.Response;
import spark.Route;

import com.db4o.ObjectSet;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import static spark.Spark.*;
import spark.*;


public class Rest {

	private Estacionamento park;
	
	public Rest(){
		park = new Estacionamento();
	}
	
		
	public void addCarro() throws JSONException{	// localhost:4567/addCarro/:pro/:pla/:mod/:cor
		
		get(new Route("/addCarro/:pro/:pla/:mod/:cor") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	 response.header("Access-Control-Allow-Origin", "*");
	        	 
	        	boolean situacao = park.addCarro(new Carro(request.params(":pro"), request.params(":pla"), new Espec(request.params(":mod"), request.params(":cor"))));
	     		
	     	    JSONArray jsonResult = new JSONArray();
	     	    JSONObject jsonObj = new JSONObject();
	     	    
	     	   try {
	     	    	if(situacao == true){
	     	    		jsonObj.put("status","OK !");
	     	    		jsonObj.put("obs","ADICIONADO COM SUCESSO !");
	     	    	}
	     	    	else {
	     	    		jsonObj.put("status", "FALHA !");
	     	    		jsonObj.put("obs","PLACA JÁ CADASTRADA !");
	     	    	}
    	    		jsonResult.put(jsonObj);
    	    	} catch (JSONException e) {
    	    		e.printStackTrace();
    	    	  }
	     	    return jsonResult;	
	     	    //return jsonObj;
	         }
	      });
	}
	
	public void deleteCarro() throws JSONException{	// localhost:4567/deleteCarro/:placa
		
		get(new Route("/deleteCarro/:placa") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	 response.header("Access-Control-Allow-Origin", "*");
	        	 
	        	boolean situacao = park.deleteCarro(request.params(":placa"));
	     		
	     	    JSONArray jsonResult = new JSONArray();
	     	    
	     	    JSONObject jsonObj = new JSONObject();
	     	    	
	     	    try {
	     	    	if(situacao == true){
	     	    		jsonObj.put("status","OK !");
	     	    		jsonObj.put("obs","REMOVIDO COM SUCESSO !");
	     	    	}
	     	    	else {
	     	    		jsonObj.put("status", "FALHA !");
	     	    		jsonObj.put("obs","PLACA NÃO ENCONTRADA !");
	     	    	}
    	    		jsonResult.put(jsonObj);
     	    	} catch (JSONException e) {
     	    		e.printStackTrace();
     	    	  }
	     	    	
	     	    return jsonResult; //jsonResult;
	     	    
	         }
	      });
	}

	public void buscarData() throws JSONException{	// localhost:4567/buscar/data/:data
		
		get(new Route("/buscar/data/:data") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	 response.header("Access-Control-Allow-Origin", "*");
	        	
	     	    LinkedList<Carro> equ = park.buscarData(request.params(":data"));
	     		
	     	    JSONArray jsonResult = new JSONArray();
	     	    
		     	for(Carro car: equ){
		     		
		     		JSONObject jsonObj = new JSONObject();
		     		try {
	     	    		jsonObj.put("Dono", car.getProprietario());
						jsonObj.put("Placa", (car.getPlaca()));
						jsonObj.put("Modelo", car.getEsp().getModelo());
		     	    	jsonObj.put("Cor", car.getEsp().getCor());
		     	    	jsonObj.put("Data", car.getEsp().getData());
		     	    	jsonObj.put("Hora", car.getEsp().getHora());
		     	    	jsonResult.put(jsonObj);
					} catch (JSONException e) {
						e.printStackTrace();
					  }
		     	}
	     	      	    
	     	    return jsonResult;
	     	    
	         }
	      });
	}
	
	public void buscarPlaca() throws JSONException{	// localhost:4567/buscar/placa/:pl
		
		get(new Route("/buscar/placa/:pl") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	 response.header("Access-Control-Allow-Origin", "*");
	        	
	     	    Carro car = park.buscarPlaca(request.params(":pl"));
	     		
	     	    JSONArray jsonResult = new JSONArray();
	     	    
	     	    	JSONObject jsonObj = new JSONObject();
	     	    	
	     	    	try {
						jsonObj.put("Dono", car.getProprietario());
						jsonObj.put("Placa", car.getPlaca());
		     	    	jsonObj.put("Modelo", car.getEsp().getModelo());
		     	    	jsonObj.put("Cor", car.getEsp().getCor());
		     	    	jsonObj.put("Data", car.getEsp().getData());
		     	    	jsonObj.put("Hora", car.getEsp().getHora());
		     	    	jsonResult.put(jsonObj);
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
	     	    
	     	    return jsonResult;
	     	    
	         }
	      });
	}

	public void alterarCarro() throws JSONException{//
		
//		get(new Route("/update/:dono/:newPlaca/:modelo/:cor/:placaCad"){
		get(new Route("/updateCarro/:var1/:var2/:var3/:var4/:var5"){
			@Override
			public Object handle(Request request, Response response){
				response.header("Access-Control-Allow-Origin", "*");
				
				boolean situacao;
				situacao = park.alterar(new Carro(request.params(":var1"), request.params(":var2"), new Espec(request.params(":var3"), request.params(":var4"))), request.params(":var5"));
				
				JSONArray jsonResult = new JSONArray();
				
				JSONObject jsonObj = new JSONObject();
     	    	
				try {
	     	    	if(situacao != false){
	     	    		jsonObj.put("status","OK !");
	     	    		jsonObj.put("obs","ALTERADO COM SUCESSO !");
	     	    	}
	     	    	else {
	     	    		jsonObj.put("status","FALHA !");
	     	    		jsonObj.put("obs","FALHA AO ALTERAR !");
	     	    	}
    	    		jsonResult.put(jsonObj);
     	    	} catch (JSONException e) {
     	    		e.printStackTrace();
     	    	  }
	     	    	
	     	    return jsonResult; //jsonResult;
			}
		});
	}
	
	public void mostrarBd() throws JSONException{	// localhost:4567/mostrarBase
		
		get(new Route("/mostrarBase") {
			@Override
			public Object handle(Request request, Response response) {
        	 response.header("Access-Control-Allow-Origin", "*");
        	
     	    ObjectSet<Carro> result = park.mostrarCarros();
				
			JSONArray jsonResult = new JSONArray();
			
     	    for (Carro i:result){
     	    	JSONObject jsonObj = new JSONObject();
     	    	
     	    	try {
     	    		jsonObj.put("Dono",i.getProprietario());
     	    		jsonObj.put("Placa",i.getPlaca());
     	    		jsonObj.put("Modelo",i.getEsp().getModelo());
     	    		jsonObj.put("Cor",i.getEsp().getCor());
     	    		jsonObj.put("Data",i.getEsp().getData());
     	    		jsonObj.put("Hora",i.getEsp().getHora());
     	    		jsonResult.put(jsonObj);
     	         	    		
     	    	} catch (JSONException e) {
					e.printStackTrace();
     	    	  }
     	       }
     	         	    
     	    return jsonResult;
     	    
         }
      });
	
	} // fecha void mostrarBd()
	
	/*public void buscarEntreDatas() throws JSONException, ParseException{	// localhost:4567/buscar/entreDatas/:dataInicio/:dataFim
		
		get(new Route("/buscar/entreDatas/:dataI/:dataF") {
	         @Override
	         public Object handle(Request request, Response response) {
//	        	 response.header("Access-Control-Allow-Origin", "*");
	        	
	        	final LinkedList<Carro> veiculos = new LinkedList<Carro>();
				
				veiculos = park.buscarEntreData( request.params(":dataI"), request.params("dataF") );
				
		
	        	 JSONArray jsonResult = new JSONArray();
	     	   
	        	 for(Carro car: veiculos){
	        		 
	        		 JSONObject jsonObj = new JSONObject();
	        		 try {
	        			 jsonObj.put("Proprietario", car.getProprietario());
	        			 jsonObj.put("Placa", (car.getPlaca()));
	        			 jsonObj.put("Data", (car.getEsp().getData()));
	        			 jsonResult.put(jsonObj);
					} catch (JSONException e) {
							e.printStackTrace();
						  }
			     }
	     	     
	     	    return jsonResult; 
	     	   
	         }
	      });
	}
	*/

	public void initializePark(){
		park.addCarro(new Carro("pedro_campo","fgo-8767",new Espec("cobalt_ltz","vermelho")));
		park.addCarro(new Carro("ana_maria","fhk-4211",new Espec("corsa_lt","prata")));
		park.addCarro(new Carro("ws_fatec","erp-8809",new Espec("strada_1.8","preto")));
	}

	
} // fecha classe Rest
