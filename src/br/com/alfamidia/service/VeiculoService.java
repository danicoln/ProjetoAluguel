package br.com.alfamidia.service;

import java.util.List;
import java.util.Scanner;

import br.com.alfamidia.exception.SistemaException;
import br.com.alfamidia.model.Cliente;
import br.com.alfamidia.model.Status;
import br.com.alfamidia.model.Veiculo;
import br.com.alfamidia.repository.VeiculoRepository;

public class VeiculoService {

	Scanner sc;
	VeiculoRepository repository = new VeiculoRepository();

	public VeiculoService(Scanner sc) {
		this.sc = sc;
		repository.salvar(new Veiculo("i30", "Hyundai", "prata", "ASD1598", "carro", 135));
		repository.salvar(new Veiculo("A200", "Mercedes", "prata", "QWE4526", "carro", 1200));
		repository.salvar(new Veiculo("F450 Italia", "Ferrari", "Vermelho", "TER1568", "carro", 15000));
		repository.salvar(new Veiculo("Porsche Carrera", "Porsche", "amarelo", "BVC5148", "carro", 14000));
		repository.salvar(new Veiculo("Fusca Turbo", "Volkswagen", "branco", "BUS1665", "carro", 18000));
	}

	public void cadastrarVeiculo() {
		System.out.println("Digite o modelo do veiculo: ");
		String modelo = sc.nextLine();

		System.out.println("Digite o marca do veiculo: ");
		String marca = sc.nextLine();

		System.out.println("Digite o cor do veiculo: ");
		String cor = sc.nextLine();

		System.out.println("Digite o placa do veiculo: ");
		String placa = sc.nextLine();

		System.out.println("Digite o segmento do veiculo: ");
		String tipo = sc.nextLine();

		System.out.println("Digite o valor de locação do veiculo: ");
		double valor = sc.nextDouble();

		Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, tipo, valor);

		this.repository.salvar(veiculo);

		System.out.println("Veículo cadastrado com sucesso! ");
	}

	public void buscarTodosVeiculosLivres() {
		List<Veiculo> todosVeiculos = this.repository.buscarTodos();

		for (Veiculo veiculo : todosVeiculos) {
			if (veiculo.getStatus() == Status.LIVRE) {
				System.out.println(veiculo);
			}
		}
	}

	public Veiculo alugarVeiculoPorId(int id) throws SistemaException {

		Veiculo veiculo = this.repository.buscarPorId(id);

		if (veiculo == null) {
			throw new SistemaException("Veículo não encontrado");
		}
		veiculo.setStatus(Status.ALUGADO);

		this.repository.salvar(veiculo);

		return veiculo;
	}

	public Veiculo devolverVeiculo(Cliente cliente, int id) throws SistemaException {
		Veiculo veiculo = this.repository.buscarPorId(id);
		if (veiculo == null) {
			throw new SistemaException("Veículo não encontrado");
		}
		// verifica se o cliente contem o veiculo
		if (!cliente.getVeiculos().contains(veiculo)) {
			throw new SistemaException("Você não possui este veículo!");
		}

		veiculo.setStatus(Status.LIVRE);
		this.repository.salvar(veiculo);
		return veiculo;
	}
}
