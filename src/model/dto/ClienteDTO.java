package model.dto;

public class ClienteDTO {
	
	private String dni;
	private String nombre;
	private String telefono;
	private String direccion;
	
	public ClienteDTO(String dni, String nombre, String telefono, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public String getDni() {
		return dni;
	}
	public String getNombre() {
		return nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	
}
