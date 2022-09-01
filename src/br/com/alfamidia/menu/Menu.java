package br.com.alfamidia.menu;

public class Menu {

	public static void menu1() {
		System.out.println("===========================================================");
		System.out.println("Bem vindo ao sistema de aluguel de veiculos! ");
		System.out.println("Digite a opção correspondente: ");
		System.out.println("1 - Cliente");
		System.out.println("2 - Vendedor");
		System.out.println("3 - Administrador");
		System.out.println("0 - Sair");
	}

	public static void menu2() {
		System.out.println("Digite seu email: ");
	}

	public static void menuCliente2() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Alugar um veiculo");
		System.out.println("2 - Devolver um veiculo");
	}

	public static void menuVendedor() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Ver os veiculos que você alugou");
		System.out.println("2 - Ver seu salário");
		System.out.println("3 - Ver seu salário com a comissão atual");
	}

	public static void menuAdministrador() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Cadastrar veículo");
		System.out.println("2 - Remover um veículo");
		System.out.println("3 - Cadastrar vendedor");
		System.out.println("4 - Remover vendedor");

	}

}
