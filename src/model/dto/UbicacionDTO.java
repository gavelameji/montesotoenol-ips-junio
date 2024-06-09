package model.dto;

public class UbicacionDTO {
	
	private String id;
	private String idAlmacen;
	private String idLocalizador;
	private boolean esActual;
	
	public UbicacionDTO(String id, String idAlmacen, String idLocalizador, boolean esActual) {
		this.id = id;
		this.idAlmacen = idAlmacen;
		this.idLocalizador = idLocalizador;
		this.esActual = esActual;
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
	
	

}
