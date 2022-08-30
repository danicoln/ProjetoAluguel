import java.util.Scanner;

import br.com.alfamidia.menu.Menu;
import br.com.alfamidia.repository.AdministradorRepository;
import br.com.alfamidia.repository.ClienteRepository;
import br.com.alfamidia.repository.VeiculoRepository;
import br.com.alfamidia.repository.VendedorRepository;

public class Principal {

	public static void main(String[] args) {

		boolean continua = true;
		
		Scanner sc = new Scanner(System.in);
		AdministradorRepository admRepository = new AdministradorRepository();
		ClienteRepository clienteRepository = new ClienteRepository();
		VendedorRepository vendedorRepository = new VendedorRepository();
		VeiculoRepository veiculoRepository = new VeiculoRepository();
		do {
			Menu.menu1();
			int opcao1 = sc.nextInt();
			
			switch(opcao1) {
			case 1:
				Menu.menuCliente();
				int opcao2 = sc.nextInt();
				break;
			case 2:
				Menu.menuVendedor();
				opcao2 = sc.nextInt();
				break;
			case 3:
				Menu.menuAdministrador();
				opcao2 = sc.nextInt();
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
