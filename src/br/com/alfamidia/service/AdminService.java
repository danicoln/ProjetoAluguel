package br.com.alfamidia.service;

import java.util.List;
import java.util.Scanner;

import br.com.alfamidia.exception.SistemaException;
import br.com.alfamidia.model.Administrador;
import br.com.alfamidia.model.Veiculo;
import br.com.alfamidia.model.Vendedor;
import br.com.alfamidia.repository.AdministradorRepository;
import br.com.alfamidia.util.Normaliza;

public class AdminService {

	Scanner sc;
	VeiculoService veiculoService;
	AdministradorRepository repository = new AdministradorRepository();
	private VendedorService vendedorService;

	public AdminService(Scanner sc, VeiculoService veiculoService, VendedorService vendedorService) {
		this.sc = sc;
		this.repository.salvar(new Administrador("admin", "admin@admin.com", "poa", "1111"));
		this.veiculoService = veiculoService;
		this.vendedorService = vendedorService;
	}

	public void confereEntrada(int entrada) throws SistemaException {
		sc.nextLine();

		if (entrada == 1) {
			veiculoService.cadastrarVeiculo();
		} else if (entrada == 2) {
			this.removerVeiculo();
		} else if (entrada == 3) {
			this.vendedorService.cadastrarVendedor();
		} else if (entrada == 4) {
			this.removerVendedor();
		}
	}

	public void removerVeiculo() throws SistemaException {
		System.out.println("Veiculos livres no sistema: ");
		veiculoService.buscarTodosVeiculosLivres();
		int opVeiculo = sc.nextInt();
		Veiculo veiculo = veiculoService.repository.buscarPorId(opVeiculo);
		if (veiculo == null) {
			throw new SistemaException("Veiculo não encontrado");
		}
		veiculoService.repository.removerPorId(opVeiculo);
		System.out.println("Veiculo removido com sucesso!");
	}

	public void removerVendedor() throws SistemaException {
		System.out.println("Vendedores cadastrados no sistema: ");
		vendedorService.retornaVendedores();
		int opVendedor = sc.nextInt();

		Vendedor vendedor = vendedorService.repository.buscarPorId(opVendedor);
		if (vendedor == null) {
			throw new SistemaException("Vendedor não encontrado");
		}
		vendedorService.repository.removerPorId(opVendedor);
		System.out.println("Vendedor removido com sucesso!");
	}

	public Administrador confereEmail(String email) {
		List<Administrador> adminCadastrados = repository.buscarTodos();
		for (Administrador admin : adminCadastrados) {
			if (admin.getEmail().equals(Normaliza.normalizaEmail(email))) {
				return admin;
			}
		}
		return null;
	}

	public boolean conferirSenha(Administrador adminParam, String senha) {
		Administrador admin = this.repository.buscarPorId(adminParam.getId());
		return admin.getSenha().equals(senha);
	}

}
