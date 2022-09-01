package br.com.alfamidia.service;

import java.util.List;
import java.util.Scanner;

import br.com.alfamidia.model.Status;
import br.com.alfamidia.model.Veiculo;
import br.com.alfamidia.repository.VeiculoRepository;

public class VeiculoService {

	Scanner sc;
	VeiculoRepository repository = new VeiculoRepository();

	public VeiculoService(Scanner sc) {
		this.sc = sc;
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

		Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, valor, tipo);

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

	public Veiculo alugarVeiculoPorId(int id) {

		Veiculo veiculo = this.repository.buscarPorId(id);

		veiculo.setStatus(Status.ALUGADO);

		this.repository.salvar(veiculo);

		return veiculo;
	}

}
