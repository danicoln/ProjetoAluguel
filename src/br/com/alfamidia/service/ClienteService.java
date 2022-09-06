package br.com.alfamidia.service;

import java.util.List;
import java.util.Scanner;

import br.com.alfamidia.exception.SistemaException;
import br.com.alfamidia.model.Cliente;
import br.com.alfamidia.model.Veiculo;
import br.com.alfamidia.repository.ClienteRepository;
import br.com.alfamidia.util.Normaliza;

public class ClienteService {

	/* injeção de dependências */
	Scanner sc;
	ClienteRepository repository = new ClienteRepository();

	public ClienteService(Scanner sc) {
		this.sc = sc;
		this.repository.salvar(new Cliente("Lincoln", "daniel@hot.com.br", "São Paulo", "1234"));
	}
	
	/*Metodo que confere se o email existe*/
	public Cliente confereEmail(String email) {
		List<Cliente> clientesCadastrados = repository.buscarTodos();
		for (Cliente cliente : clientesCadastrados) {
			if(cliente.getEmail().equals(Normaliza.normalizaEmail(email))) {
				return cliente;
			}
		}
		return this.cadastrarCliente(email);
	}

	/* Metodo que cadastra o cliente */
	private Cliente cadastrarCliente(String email) {
		System.out.println("Digite seu nome: ");
		String name = sc.nextLine();
		System.out.println("Digite sua cidade: ");
		String city = sc.nextLine();
		System.out.println("Digite uma senha: ");
		String password = sc.nextLine();

		Cliente client = new Cliente(name, email, city, password);
		this.repository.salvar(client);

		return client;
	}
	
	/* Metodo que confere a senha */
	public boolean conferirSenha(Cliente client, String senha) throws SistemaException {
		Cliente cliente = repository.buscarPorId(client.getId());
		if(cliente == null) {
			throw new SistemaException("Cliente não encontrado");
		}
		return cliente.getSenha().equals(senha);
	}
	
	public void alugarVeiculo(Cliente cliente, Veiculo veiculo) {
		cliente.getVeiculos().add(veiculo);
		this.repository.salvar(cliente);
	}
	
	public void buscarVeiculosAlugados(Cliente cliente) {
		List<Veiculo> veiculosAlugados = cliente.getVeiculos();
		for (Veiculo veiculo : veiculosAlugados) {
			System.out.println(veiculo);
		}
	}
	
	public void removerVeiculo(Cliente clienteParam, Veiculo veiculoParam) throws SistemaException {
		Cliente cliente = this.repository.buscarPorId(clienteParam.getId());
		if(cliente == null) {
			throw new SistemaException("Cliente não encontrado");
		}
		
		cliente.getVeiculos().remove(veiculoParam);
		
		this.repository.salvar(cliente);
	}
}
