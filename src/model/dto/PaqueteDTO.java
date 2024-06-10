package model.dto;

import java.sql.Date;

public class PaqueteDTO {
	
	private String id;
	private String descripcion;
	private Date fechaEmision;
	private String estado;
	private String direccionOrigen;
	private String direccionDestino;
	private boolean recogerADomicilio;
	
	private String dniCliente;
	private String idRuta;
	private String idOficina;
	private String idLocalizador;
	
	private float peso;
	private float precioFinal;
	
	public PaqueteDTO(String id, String descripcion, Date fechaEmision, String estado, String direccionOrigen,
			String direccionDestino, boolean recogerADomicilio, String dniCliente, String idRuta, String idOficina) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaEmision = fechaEmision;
		this.estado = estado;
		this.direccionOrigen = direccionOrigen;
		this.direccionDestino = direccionDestino;
		this.recogerADomicilio = recogerADomicilio;
		this.dniCliente = dniCliente;
		this.idRuta = idRuta;
		this.idOficina = idOficina;
	}
	
	

	public PaqueteDTO(String id, String descripcion, Date fechaEmision, String estado, String direccionOrigen,
			String direccionDestino, boolean recogerADomicilio, String dniCliente, String idRuta, String idOficina,
			String idLocalizador, float peso, float precioFinal) {
		this.id = id;
		this.descripcion = descripcion;
		this.fechaEmision = fechaEmision;
		this.estado = estado;
		this.direccionOrigen = direccionOrigen;
		this.direccionDestino = direccionDestino;
		this.recogerADomicilio = recogerADomicilio;
		this.dniCliente = dniCliente;
		this.idRuta = idRuta;
		this.idOficina = idOficina;
		this.idLocalizador = idLocalizador;
		this.peso = peso;
		this.precioFinal = precioFinal;
	}



	public String getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public String getEstado() {
		return estado;
	}
	public String getIdRuta() {
		return idRuta;
	}
	
	public String getDireccionOrigen() {
		return direccionOrigen;
	}
	public String getDireccionDestino() {
		return direccionDestino;
	}
	
	public boolean paraRecogerADomicilio() {
		return recogerADomicilio;
	}
	public String getDniCliente() {
		return dniCliente;
	}
	public String getIdOficina() {
		return idOficina;
	}

	public String getIdLocalizador() {
		return idLocalizador;
	}

	public float getPeso() {
		return peso;
	}

	public float getPrecioFinal() {
		return precioFinal;
	}



	@Override
	public String toString() {
		return id + " - " + descripcion + " - " + fechaEmision;
	}
	
	
	
	
	
	

}
