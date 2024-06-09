package model;

import java.util.ArrayList;
import java.util.List;

import data.CrudLocalizador;
import data.CrudPaquetes;
import model.dto.PaqueteDTO;
import model.dto.UbicacionDTO;

public class Localizador {
	
	private String id;
	private String idPaquete;
	private PaqueteDTO paquete;
	private List<UbicacionDTO> ubicaciones;
	
	public Localizador(String id, String idPaquete) {
		this.id = id;
		this.idPaquete = idPaquete;
		this.paquete = CrudPaquetes.obtenerPaquetePorLocalizador(id);
		this.ubicaciones = CrudLocalizador.obtenerUbicacionesPorLocalizador(id);
	}

	public String getId() {
		return id;
	}

	public String getIdPaquete() {
		return idPaquete;
	}

	public List<UbicacionDTO> getUbicaciones() {
		return new ArrayList<UbicacionDTO>(ubicaciones);
	}
	
	public PaqueteDTO getPaquete() {
		return paquete;
	}
	
	public String obtenerDetallesDePaquete() {
		StringBuilder sb = new StringBuilder();
		sb.append("Descripción: ").append(paquete.getDescripcion()).append("\n");
		sb.append("Fecha Emision: ").append(paquete.getFechaEmision()).append("\n");
		sb.append("Dirección Origen: ").append(paquete.getDireccionDestino()).append("\n");
		sb.append("Dirección Destino: ").append(paquete.getDireccionOrigen()).append("\n");
		sb.append("Peso: ").append(paquete.getPeso()).append(" Precio: ").append(paquete.getPrecioFinal());
		return sb.toString();
	}

	@Override
	public String toString() {
		return id + " - " + paquete.getDescripcion();
	}
	
	
	
	

}
