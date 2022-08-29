package br.com.alfamidia.model;

public class Veiculo {

	
	private Integer id;
	private String modelo;
	private String marca;
	private String cor;
	private String placa;
	private double valorLocacao;
	private Tipo tipo;
	private Status status;
	
	public Veiculo(String modelo, String marca, String cor, String placa, double valorLocacao, String tipo) {
		this.id = Contador.proximoId();
		
		this.modelo = modelo;
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.valorLocacao = valorLocacao;
		this.tipo = Tipo.valueOf(tipo.toUpperCase()); // converte String no Enum.
		this.status = Status.LIVRE;
	}
}