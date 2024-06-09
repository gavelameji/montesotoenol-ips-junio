package model;

import java.util.ArrayList;
import java.util.List;

import data.CrudLocalizador;

public class SeguimientoController {
	
	private String dni;
	private List<Localizador> localizadores;
	
	public SeguimientoController(String dni) {
		this.dni = dni;
		localizadores = CrudLocalizador.obtenerLocalizadoresPorDNI(dni);
	}
	
	public List<Localizador> getLocalizadores() {
		return new ArrayList<>(localizadores);
	}

}
