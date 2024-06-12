package model;

import java.util.ArrayList;
import java.util.List;

import data.CrudPaquetes;
import model.dto.PaqueteDTO;

public class RecogidasController {
	
	private List<PaqueteDTO> paquetesARecoger;

	public RecogidasController() {
		this.paquetesARecoger = CrudPaquetes.obtenerPaquetesPendientesDeRecogida();
	}
	
	public List<PaqueteDTO> getPaquetesARecoger() {
		return new ArrayList<PaqueteDTO>(paquetesARecoger);
	}
	
	public void actualizarEstadoPaquete(String idPaquete) {
		CrudPaquetes.actualizarEstadoPaquete(idPaquete, "en ruta");
		paquetesARecoger = CrudPaquetes.obtenerPaquetesPendientesDeRecogida();
	}
	
	
}
