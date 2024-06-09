package model.dto;

import data.CrudAlmacenes;

public class UbicacionDTO {
	
	private String id;
	private String idAlmacen;
	private String idLocalizador;
	private boolean esActual;
	private AlmacenDTO almacen;
	
	public UbicacionDTO(String id, String idAlmacen, String idLocalizador, boolean esActual) {
		this.id = id;
		this.idAlmacen = idAlmacen;
		this.idLocalizador = idLocalizador;
		this.esActual = esActual;
		this.almacen = CrudAlmacenes.obtenerAlmacenPorId(idAlmacen);
	}

	public String getId() {
		return id;
	}

	public String getIdAlmacen() {
		return idAlmacen;
	}

	public String getIdLocalizador() {
		return idLocalizador;
	}

	public boolean isEsActual() {
		return esActual;
	}
	
	public AlmacenDTO getAlmacen() {
		return almacen;
	}
	
	private String infoActual() {
		return esActual ? "Actual" : "Pasada";
	}

	@Override
	public String toString() {
		return "Almacén " + almacen.getCiudad() + " - " + infoActual();
	}
	
	
	

}
