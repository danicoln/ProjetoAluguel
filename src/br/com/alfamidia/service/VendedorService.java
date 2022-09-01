package br.com.alfamidia.service;

import java.util.List;
import java.util.Scanner;

import br.com.alfamidia.model.Vendedor;
import br.com.alfamidia.repository.VendedorRepository;

public class VendedorService {

	Scanner sc;
	VendedorRepository repository = new VendedorRepository();
	
	public VendedorService(Scanner sc) {
		this.sc = sc;
		
		repository.salvar(new Vendedor("João", "joao@vendas.com.br", "Americana", "1111", 2000));
		repository.salvar(new Vendedor("Jose", "jose@vendas.com.br", "Campinas", "1111", 3000));
		repository.salvar(new Vendedor("Maria", "maria@vendas.com.br", "Sumare", "1111", 2500));
		repository.salvar(new Vendedor("Antonio", "antonio@vendas.com.br", "Hortolandia", "1111", 2500));
	}
	
	public Vendedor confereEmail(String email) {
		List<Vendedor> vendedoresCadastrados = repository.buscarTodos();
		for (Vendedor vendedor : vendedoresCadastrados) {
			if(vendedor.getEmail().equals(email));
			return vendedor;
		}
		return null;
		
	}
	
	public boolean conferirSenha(Vendedor vendedorParam, String senha) {
		Vendedor vendedor = this.repository.buscarPorId(vendedorParam.getId());
		return vendedor.getSenha().equals(senha);
	}
	
	public void verSalario(Vendedor vendedor) {
		System.out.println("Seu salário é: " + vendedor.getSalario());
	}

}
