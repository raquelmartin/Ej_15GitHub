package es.curso.controllers.ejb;

import es.curso.controllers.DarAltaClienteController;
import es.curso.model.entity.cliente;

public class DarAltaClienteControllerEjb implements DarAltaClienteController{

	@Override
	public void agregar(cliente cliente) {
		// Lógica del negocio para agregar un cliente
		//1.Verificar datos
		//2.Agregarlo--> llamar a la capa DAO para que se dé de alta
		//3. Enviar email al jefe de Obra
		//4.Enviar un email al cliente
	}

}
