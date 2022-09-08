package model.dao;

import java.util.List;

import model.entities.Cliente;

public interface ClienteDao {
	void insert(Cliente cliente);
	void update(Cliente cliente);
	void deleteById(Integer id);
	Cliente findById(Integer id);
	List<Cliente> findAll();
}
