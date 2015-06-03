package es.curso.persistence.model.dao;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;

public interface ClienteDao {
	
	public void create(Cliente cliente);
	
	public ArrayList<Cliente> findAll();
	
}
