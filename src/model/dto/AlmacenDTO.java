package model.dto;

public class AlmacenDTO {
	
	private String id;
	private String ciudad;
	private String direccion;
	
	public AlmacenDTO(String id, String ciudad, String direccion) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.direccion = direccion;
	}

	public String getId() {
		return id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	@Override
	public String toString() {
		return ciudad + " - " + direccion;
	}

}
