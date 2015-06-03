package es.curso.controllers.ejb;

import es.curso.controllers.DarAltaClienteController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class DarAltaClienteControllerEjb implements DarAltaClienteController{
	private ClienteDao clienteDao;
	@Override
	public void agregar(Cliente cliente) {
		// Lógica del negocio para agregar un cliente
		//1.Verificar datos
		//2.Agregarlo--> llamar a la capa DAO para que se dé de alta
		//3. Enviar email al jefe de Obra
		//4.Enviar un email al cliente
		clienteDao= new ClienteDaoJdbc();
		clienteDao.create(cliente);
	}

}
