import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.alfamidia.exception.SistemaException;
import br.com.alfamidia.menu.Menu;
import br.com.alfamidia.model.Administrador;
import br.com.alfamidia.model.Cliente;
import br.com.alfamidia.model.Veiculo;
import br.com.alfamidia.model.Vendedor;
import br.com.alfamidia.service.AdminService;
import br.com.alfamidia.service.ClienteService;
import br.com.alfamidia.service.VeiculoService;
import br.com.alfamidia.service.VendedorService;

public class Principal {

	public static void main(String[] args) throws InterruptedException {

		Scanner sc = new Scanner(System.in);

		ClienteService clientService = new ClienteService(sc);
		VeiculoService veiculoService = new VeiculoService(sc);
		VendedorService vendedorService = new VendedorService(sc);
		AdminService adminService = new AdminService(sc, veiculoService, vendedorService);

		boolean continua = true;
		do {
			try {
				Menu.menu1();
				int opcao1 = sc.nextInt();
				sc.nextLine();

				switch (opcao1) {
				case 1:
					Menu.menu2();
					String email = sc.nextLine();
					Cliente client = clientService.confereEmail(email);
					boolean senhaCorreta = false;
					for (int i = 0; i < 3; i++) {
						System.out.println("Agora digite sua senha: ");
						String password = sc.nextLine();

						senhaCorreta = clientService.conferirSenha(client, password);
						if (!senhaCorreta) {
							System.out.println("Senha inválida, tente novamente! ");
						} else {
							break;
						}
					}
					if (!senhaCorreta) {
						break;
					}

					Menu.menuCliente2();
					int opcao2 = sc.nextInt();

					if (opcao2 == 1) {
						System.out.println("Digite o numero referente ao carro desejado: ");
						veiculoService.buscarTodosVeiculosLivres();
						int opcaoCarro = sc.nextInt();
						Veiculo veiculo = veiculoService.alugarVeiculoPorId(opcaoCarro);
						clientService.alugarVeiculo(client, veiculo);

						// Escolher vendedor que atendeu
						System.out.println("Escolha o vendedor que lhe atendeu: ");
						vendedorService.retornaVendedores();
						int opcaoVendedor = sc.nextInt();
						vendedorService.salvarVeiculo(veiculo, opcaoVendedor);
					} else if (opcao2 == 2) {
						if (client.getVeiculos().size() <= 0) {
							throw new SistemaException("Você não tem veículos a ser devolvido!");
						}
						System.out.println("Escolha o veículo a ser devolvido: ");
						clientService.buscarVeiculosAlugados(client);

						int opcaoCarro = sc.nextInt();

						Veiculo veiculoDevolvido = veiculoService.devolverVeiculo(client, opcaoCarro);
						clientService.removerVeiculo(client, veiculoDevolvido);

					}

					break;
				case 2:
					Menu.menu2();
					email = sc.nextLine();

					Vendedor vendedor = vendedorService.confereEmail(email);
					if (vendedor == null) {
						throw new SistemaException("Vendedor não encontrado! ");
					}
					senhaCorreta = false;
					for (int i = 0; i < 3; i++) {
						System.out.println("Agora digite sua senha: ");
						String password = sc.nextLine();

						senhaCorreta = vendedorService.conferirSenha(vendedor, password);
						if (!senhaCorreta) {
							System.out.println("Senha inválida, tente novamente! ");
						} else {
							break;
						}
					}
					if (!senhaCorreta) {
						break;
					}

					Menu.menuVendedor();
					opcao2 = sc.nextInt();

					if (opcao2 == 1) {
						vendedorService.mostrarVeiculosAlugados(vendedor);
					} else if (opcao2 == 2) {
						vendedorService.verSalario(vendedor);
					} else if (opcao2 == 3) {
						vendedorService.verSalarioComComissao(vendedor);
					}

					break;
				case 3:
					Menu.menu2();
					email = sc.nextLine();

					Administrador admin = adminService.confereEmail(email);
					if (admin == null) {
						throw new SistemaException("Administrador não encontrado!");
					}
					senhaCorreta = false;
					for (int i = 0; i < 3; i++) {
						System.out.println("Agora digite sua senha: ");
						String password = sc.nextLine();

						senhaCorreta = adminService.conferirSenha(admin, password);
						if (!senhaCorreta) {
							System.out.println("Senha inválida, tente novamente! ");
						} else {
							break;
						}
					}
					if (!senhaCorreta) {
						break;
					}

					Menu.menuAdministrador();
					opcao2 = sc.nextInt();
					adminService.confereEntrada(opcao2);

					break;
				case 0:
					continua = false;
					break;

				default:
					System.out.println("Alternativa inválida! Tente novamente");
					break;
				}

			} catch (SistemaException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida!");
				sc.nextInt();
			} finally {
				Thread.sleep(1500l);
			}
		} while (continua);

		sc.close();
	}
}
