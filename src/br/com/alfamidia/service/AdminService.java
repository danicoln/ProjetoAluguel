package br.com.alfamidia.service;

import java.util.Scanner;

import br.com.alfamidia.model.Administrador;
import br.com.alfamidia.repository.AdministradorRepository;

public class AdminService {

	Scanner sc;
	VeiculoService veiculoService;
	AdministradorRepository repository = new AdministradorRepository();
	
	
	public AdminService(Scanner sc, VeiculoService veiculoService) {
		this.sc = sc;
		this.repository.salvar(new Administrador("admin", "admin@admin.com", "poa", "1111"));
		this.veiculoService = veiculoService;
	}
	
	public void confereEntrada(int entrada) {
		sc.nextLine();
		
		if(entrada == 1) {
			veiculoService.cadastrarVeiculo();
		}
	}
	


}
