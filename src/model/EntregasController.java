package model;

import java.util.ArrayList;
import java.util.List;

import data.CrudPaquetes;
import model.dto.PaqueteDTO;

public class EntregasController {
	
	private List<PaqueteDTO> paquetesAEntregar;
	
	public EntregasController() {
		this.paquetesAEntregar = CrudPaquetes.obtenerPaquetesParaEntregar();
	}
	
	public List<PaqueteDTO> getPaquetesAEntregar() {
		return new ArrayList<PaqueteDTO>(paquetesAEntregar);
	}
	
	public void actualizarEstadoARecibido(String idPaquete) {
		CrudPaquetes.actualizarEstadoPaquete(idPaquete, "recibido");
		paquetesAEntregar = CrudPaquetes.obtenerPaquetesParaEntregar();
	}
	
	public boolean actualizarEstadoABloqueado(PaqueteDTO p) {
		if(p.getVecesBloqueado() == 3) {
			return false;
		}
		CrudPaquetes.actualizarEstadoPaquete(p.getId(), "bloqueado");
		CrudPaquetes.incrementarNumeroDeBloqueosPaquete(p.getId(), p.getVecesBloqueado() + 1);
		paquetesAEntregar = CrudPaquetes.obtenerPaquetesParaEntregar();
		return true;
	}

}
