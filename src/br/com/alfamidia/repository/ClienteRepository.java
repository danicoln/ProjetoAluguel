package br.com.alfamidia.repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.alfamidia.model.Cliente;

public class ClienteRepository {

	Map<Integer, Cliente> clienteBD;

	public ClienteRepository() {
		this.clienteBD = new TreeMap<>();
	}

	public void salvar(Cliente cliente) {
		this.clienteBD.put(cliente.getId(), cliente);
	}

	public List<Cliente> buscarTodos() {
		return this.clienteBD.values().stream().collect(Collectors.toList());
	}

	public Cliente buscarPorId(Integer id) {
		return this.clienteBD.get(id);
	}

	public void removerPorId(Integer id) {
		this.clienteBD.remove(id);
	}
}
