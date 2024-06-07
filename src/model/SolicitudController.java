package model;

import data.CrudClientes;
import data.CrudPaquetes;
import model.dto.ClienteDTO;
import model.dto.PaqueteDTO;

public class SolicitudController {
	
	public boolean grabarPedido(PaqueteDTO paquete) {
		
		if(!existeCliente(paquete.getDniCliente())) {
			return false;
		}
		
		CrudPaquetes.insertarPaquete(paquete);
		return true;
	}
	
	public boolean grabarCliente(ClienteDTO cliente) {
		if(existeCliente(cliente.getDni())) {
			return false;
		}
		CrudClientes.insertarCliente(cliente);
		return true;
	}
	
	private boolean existeCliente(String dni) {
		return CrudClientes.obtenerClientePorDNI(dni) != null;
	}
	

}
