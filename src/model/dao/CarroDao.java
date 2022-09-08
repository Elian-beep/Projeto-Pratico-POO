package model.dao;

import java.util.List;

import model.entities.Carro;
import model.entities.Categoria;

public interface CarroDao {
	void insert(Carro carro);
	void deleteById(Integer id);
	void update(Carro carro);
	Carro findById(Integer id);
	List<Carro> findAll();
	List<Carro> findByIdCategoria(Categoria categoria);
	
	
}
