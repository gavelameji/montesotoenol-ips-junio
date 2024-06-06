package model;

import java.util.ArrayList;
import java.util.List;

import data.CrudPaquetes;
import model.dto.PaqueteDTO;

public class PaqueteController {
	
	private List<PaqueteDTO> paquetes;
	
	public PaqueteController() {
		paquetes = CrudPaquetes.obtenerPaquetes();
	}
	
	public List<PaqueteDTO> filtrarPaquetesPorDni(String dni) {
		List<PaqueteDTO> res = new ArrayList<>();
		for(PaqueteDTO p: paquetes) {
			if(p.getDniCliente().equals(dni)) res.add(p);
		}
		return res;
	}

}
