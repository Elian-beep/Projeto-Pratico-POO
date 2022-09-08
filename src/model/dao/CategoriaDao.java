package model.dao;

import java.util.List;

import model.entities.Categoria;
import model.entities.Cliente;

public interface CategoriaDao {
	void insert(Categoria obj);
	void update(Categoria obj);
	void deleteById(Integer id);
	Categoria findById(Integer id);
	List<Categoria> findAll();
}
