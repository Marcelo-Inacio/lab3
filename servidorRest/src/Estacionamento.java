import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Estacionamento {
	
	ObjectContainer carros = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "D:/carro.db4o");
	
	public boolean verificar(Carro c){ //AUXILIA O ADD CARRO NO BANCO DE DADOS
		Query query = carros.query();
		query.constrain(Carro.class);
	    ObjectSet <Carro> result = query.execute();
	    for (Carro kar:result){
	    	if(kar.getPlaca().equals(c.getPlaca())) return true;
	    } 
	    return false;
	}
	
	public boolean addCarro(Carro car){ //ADD CARRO NÃO REPETIDO POR PLACA NA BASE
		if(verificar(car) == false){
			carros.store(car);
			carros.commit();
			return true;
		}
		return false;
	}
	
	public Carro buscarPlaca(String str){ //PESQUISAR POR PLACA
		
		Query query = carros.query();
		query.constrain(Carro.class);
		ObjectSet <Carro> resp = query.execute();
		
		for (Carro c: resp){
			if(c.getPlaca().equals(str)) return c;
		}
		return null;
	}
	
	public LinkedList buscarData(String str){ //PESQUISAR POR DATA - FAZER FILTRO ARRUMAR
		
		Query query = carros.query();
		query.constrain(Carro.class);
		ObjectSet resp = query.execute();
		
		LinkedList lista = new LinkedList<>();
		for(Object i:resp){
			Carro ca = (Carro) i;
			if(ca.getEsp().getData().equals(str)) lista.add(ca);
		}
		
		return lista;
	}
			
	public boolean deleteCarro(String pla){  //REMOVE CARRO DO BANCO PASSANDO PLACA
		Carro remov = buscarPlaca(pla);
		
		if (remov != null){
			carros.delete(remov);
			carros.commit();
			return true;
		}
		return false;
	}
	
	public boolean alterar(Carro alt, String pla){
		Query que = carros.query();
		que.constrain(Carro.class);
		ObjectSet<Carro> res = que.execute();
		
		for(Carro car: res){
			if(car.getPlaca().equals(pla)){
				car.setProprietario(alt.getProprietario());
				car.setPlaca(alt.getPlaca());
				car.setEsp(alt.getEsp());
				carros.store(car);
				carros.commit();
				return true;
			}
		}
		return false;
	}
	
	public ObjectSet mostrarCarros(){
		ObjectSet<Carro> resultado = carros.queryByExample(Carro.class);
		return resultado;
	}
	
	public LinkedList<Carro> buscarEntreData(String dt1, String dt2) throws ParseException{ //PESQUISAR POR PLACA
		
		Query query = carros.query();
		query.constrain(Carro.class);
		ObjectSet resp = query.execute();
		
		LinkedList <Carro>lista = new LinkedList<Carro>(); //INICIA LISTA VAZIA
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //DEFINE UM FORMATO DE DATA
		
		Date dataDe = new Date(sdf.parse(dt1).getTime()); //CRIA VARIAVEL E RECEBE CONVERTIDA DO TIPO String PARA TIPO Date
		Date dataAte = new Date(sdf.parse(dt2).getTime()); //CRIA VARIAVEL E RECEBE CENVERTIDA DO TIPO String PARA TIPO Date
		
		for (Object i: resp){ //PERCORRO TODOS OBJETOS DO BANCO
			Carro c = (Carro) i; //FAÇO VARIAVEL OBJETO RECEBER O OBJETC [i] DA LISTA
			Date dataCarro = new Date(sdf.parse(c.getEsp().getData()).getTime()); //CONVERTO A DATA DO CARRO DO TIPO STRING PARA DATE
			
			if((dataCarro.after(dataDe)) && (dataCarro.before(dataAte))) lista.add(c); //COMPARO AS DATAS SE ESTÃO ENTRE AS DATAS DO PARAMETRO
		}
		return lista; //RETORNO A LISTA COM OS CARROS
	}
			
}//fecha classe
