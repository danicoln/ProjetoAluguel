package br.com.alfamidia.repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.alfamidia.model.Vendedor;

public class VendedorRepository {


	Map<Integer, Vendedor> vendedorBD;

	public VendedorRepository() {
		this.vendedorBD = new TreeMap<>();
	}
	
	public void salvar(Vendedor vendedor){
		this.vendedorBD.put(vendedor.getId(), vendedor);
	}
	
	public List<Vendedor> buscarTodos(){
		return this.vendedorBD.values().stream().collect(Collectors.toList());
	}
	
	public Vendedor buscarPorId(Integer id) {
		return this.vendedorBD.get(id);
	}
	
	public void removerPorId(Integer id) {
		this.vendedorBD.remove(id);
	}
}
