package br.com.alfamidia.service;

import java.util.List;
import java.util.Scanner;

import br.com.alfamidia.exception.SistemaException;
import br.com.alfamidia.model.Veiculo;
import br.com.alfamidia.model.Vendedor;
import br.com.alfamidia.repository.VendedorRepository;
import br.com.alfamidia.util.Normaliza;

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
			if (vendedor.getEmail().equals(Normaliza.normalizaEmail(email))) {
				return vendedor;
			}
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

	public void retornaVendedores() {
		List<Vendedor> vendedores = repository.buscarTodos();
		// leia-se: para cada vendedor, imprimir a lista de vendedores
		for (Vendedor vendedor : vendedores) {
			System.out.println(vendedor.getId() + " - " + vendedor.getNome());
		}
	}

	/**/
	public void salvarVeiculo(Veiculo veiculo, Integer idVendedor) throws SistemaException {
		Vendedor vendedor = repository.buscarPorId(idVendedor);
		if (vendedor == null) {
			throw new SistemaException("Vendedor não encontrado");
		}
		vendedor.getVeiculosAlugado().add(veiculo);
		repository.salvar(vendedor);
	}

	public void mostrarVeiculosAlugados(Vendedor vendedor) {
		List<Veiculo> veiculos = vendedor.getVeiculosAlugado();
		for (Veiculo veiculo : veiculos) {
			System.out.println(veiculo);
		}
	}

	public void verSalarioComComissao(Vendedor vendedor) {
		List<Veiculo> veiculos = vendedor.getVeiculosAlugado();
		double totalVendas = 0;
		for (Veiculo veiculo : veiculos) {
			totalVendas += veiculo.getValorLocacao();
		}

		double comissao = totalVendas * Vendedor.COMISSAO;

		System.out.println("Seu salário atual é: " + vendedor.getSalario());
		System.out.println("Sua comissão é de: " + comissao);
		System.out.println("Total de recebíveis é de: " + (vendedor.getSalario() + comissao));
	}

	public void cadastrarVendedor() {
		System.out.println("Digite seu nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite seu email: ");
		String email = sc.nextLine();
		System.out.println("Digite sua cidade: ");
		String cidade = sc.nextLine();
		System.out.println("Digite sua senha: ");
		String senha = sc.nextLine();
		System.out.println("Digite o salario: ");
		double salario = sc.nextDouble();

		Vendedor vendedor = new Vendedor(nome, email, cidade, senha, salario);
		repository.salvar(vendedor);
	}

}
