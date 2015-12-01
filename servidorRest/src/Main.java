import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {
	
	//private static final ObjectSet<Carro> NULL = null;

	public static void main(String[] args) throws JSONException, ParseException {
		
//		Estacionamento estacionamento = new Estacionamento();
		
//		estacionamento.mostrar();
		
//		ObjectSet<Carro> carro = estacionamento.mostrarCarros(); 
//		for (Carro i: carro){
//			System.out.println(i.getPlaca());
//		}
		
//		if (estacionamento.addCarro(new Carro("nelson","str-2015",new Espec("celta","vermelho")))==true) System.out.println("SUCESSO");
//		else System.out.println("FALHA");
						
		
//		if (estacionamento.deleteCarro("khl-9299") == true) System.out.println("REMOVIDO SUCESSO!");
//		else System.out.println("FALHA");
		
//		boolean sit = estacionamento.alterar(new Carro("ws", "sss-2015", new Espec("hb20","vermelho")), "str-9900");
//		if( sit == true){
//			System.out.println("ALTERADO COM SUCESSO !");
//		}
//		else{ System.out.println("ERRO, SEM SUCESSO !"); }
		
				
//		LinkedList<Carro> lista = estacionamento.buscarData("06-10-2015"); //quero pesquisar por data
//		for(Carro i : lista){
//			System.out.println(i.getPlaca() + " " + i.getEsp().getCor());
//		}
		
//		LinkedList<Carro> lista = estacionamento.buscarEntreData("24-08-2015", "28-08-2015"); //pesquiso entre datas
//		for(Carro i : lista){
//			System.out.println(i.getPlaca() + " " + i.getEsp().getData());
//		}
		
		
		
		final Rest rest = new Rest();
		
//		rest.initializePark();
		
		rest.buscarPlaca(); /*	BUSCO O CARRO PELA PLACA, EX.: new-2015		OK*/
		rest.buscarData();	/*	BUSCO OS CARROS PELA DATA, EX.: 24-08-2015 	OK*/
		rest.addCarro();	/*	ADICIONO UM CARRO NA BASE DE DADOS, PASSANDO UM CARRO	*/
		rest.deleteCarro();	/*	REMOVO UM CARRO DA BASE DE DADOS PASSANDO A PLACA		*/
		rest.alterarCarro();/* 	FAÇO UM UPDATE EM ALGUM OBJETO (CARRO) NA BASE DE DADOS PASSANDO TODOS ATRIBUTOS E A PLACA DO CARRO*/
		rest.mostrarBd();	/*	MOSTRO TODOS OS CLIENTES CADASTRADOS NA BASE DE DADOS	*/
//		rest.buscarEntreDatas(); /* MOSTRO CARROS PESQUISADOR ENTRE DATAS*/
	}

}
