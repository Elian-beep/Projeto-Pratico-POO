package model.dao;

import java.util.List;

import model.entities.Cliente;
import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.LocacaoLongoPeriodo;

public interface LocacaoDao {
	void insert(LocacaoDiaria locacaoDiaria, LocacaoLongoPeriodo locacaoLongo, boolean tipoDiaria);
	Double Devolucao(LocacaoDiaria locacaoDiaria, LocacaoLongoPeriodo locacaoLongo, Double valorDiaria, boolean tipoDiaria);
	Locacao findById(Integer id, boolean tipoDiaria);
	List<Locacao> findAll();
	Locacao findByIdCliente(Cliente cliente);
	void deleteById(Integer id);
}
