package edu.fatec.parking_android;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Carro {
	
	private String proprietario;
	private String placa;
	private Espec  esp;
	
	
	public Carro (String pro, String placa, Espec sp){
		proprietario = pro;
		this.placa = placa;
		esp = sp;
		
	}
	
	public String getProprietario(){
		return proprietario;
	}
	
	public String getPlaca(){
		return placa;
	}
		
	public Espec getEsp(){
		return esp;
	}
	
	public void setProprietario(String nome){
		proprietario = nome;
	}
	
	public void setPlaca(String placa){
		this.placa = placa;
	}
	
	public void setEsp(Espec esp) {
		this.esp = esp;
	}
	
	public String toString(){
		return proprietario+" "+placa+" "+esp;
	}

	

}
