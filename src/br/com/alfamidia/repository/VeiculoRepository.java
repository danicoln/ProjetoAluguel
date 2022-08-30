package br.com.alfamidia.repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.alfamidia.model.Veiculo;

public class VeiculoRepository {

	Map<Integer, Veiculo> veiculoBD;

	public VeiculoRepository() {
		this.veiculoBD = new TreeMap<>();
	}
	
	public void salvar(Veiculo veiculo){
		this.veiculoBD.put(veiculo.getId(), veiculo);
	}
	
	public List<Veiculo> buscarTodos(){
		return this.veiculoBD.values().stream().collect(Collectors.toList());
	}
	
	public Veiculo buscarPorId(Integer id) {
		return this.veiculoBD.get(id);
	}
	
	public void removerPorId(Integer id) {
		this.veiculoBD.remove(id);
	}
}
