package br.com.alfamidia.repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.alfamidia.model.Administrador;

public class AdministradorRepository {

	Map<Integer, Administrador> administradorBD;

	public AdministradorRepository() {
		this.administradorBD = new TreeMap<>();
	}

	public void salvar(Administrador vendedor) {
		this.administradorBD.put(vendedor.getId(), vendedor);
	}

	public List<Administrador> buscarTodos() {
		return this.administradorBD.values().stream().collect(Collectors.toList());
	}

	public Administrador buscarPorId(Integer id) {
		return this.administradorBD.get(id);
	}

	public void removerPorId(Integer id) {
		this.administradorBD.remove(id);
	}
}
