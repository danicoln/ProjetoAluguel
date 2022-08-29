package br.com.alfamidia.model;

public class Contador {

	private static Integer cont = 0;
	
	public static Integer proximoId() {
		cont++;
		return cont;
	}
}
