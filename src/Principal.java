import java.util.Scanner;

import br.com.alfamidia.menu.Menu;
import br.com.alfamidia.model.Cliente;
import br.com.alfamidia.model.Veiculo;
import br.com.alfamidia.repository.VeiculoRepository;
import br.com.alfamidia.repository.VendedorRepository;
import br.com.alfamidia.service.AdminService;
import br.com.alfamidia.service.ClienteService;
import br.com.alfamidia.service.VeiculoService;

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ClienteService clientService = new ClienteService(sc);
		VeiculoService veiculoService = new VeiculoService(sc);
		AdminService adminService = new AdminService(sc, veiculoService);

		boolean continua = true;
		do {
			Menu.menu1();
			int opcao1 = sc.nextInt();
			sc.nextLine();

			switch (opcao1) {
			case 1:
				Menu.menuCliente();
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

				}

				break;
			case 2:
				Menu.menuVendedor();
				opcao2 = sc.nextInt();
				break;
			case 3:
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
		} while (continua);

		sc.close();
	}
}
