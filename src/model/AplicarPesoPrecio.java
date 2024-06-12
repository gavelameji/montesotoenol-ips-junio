package model;

import data.CrudPaquetes;
import data.CrudRutas;
import model.dto.PaqueteDTO;

public class AplicarPesoPrecio {
	
	private static final float PRECIO_BASE = 5.00f;
	
	private PaqueteDTO p;
	private float precioFinal;
	private float tarifa;
	private float distancia;
	private float peso;

	public AplicarPesoPrecio(PaqueteDTO p, float peso) {
		this.p = p;
		this.distancia = CrudRutas.obtenerDistanciaPorRuta(p.getIdRuta());
		this.peso = peso;
	}
	
	public void calcularPrecioFinal() {
	    tarifa = ((distancia + peso) / 2) * 0.2f;
	    precioFinal = PRECIO_BASE + tarifa;
	}
	
	public void grabarPeso() {
		CrudPaquetes.actualizarPesoPaquete(p.getId(), peso);
	}
	
	public void grabarPrecioFinal() {
		CrudPaquetes.actualizarPrecioPaquete(p.getId(), precioFinal);
	}

	public PaqueteDTO getPaquete() {
		return p;
	}

	public float getPrecioFinal() {
		return precioFinal;
	}

	public float getTarifa() {
		return tarifa;
	}
	
	public String mostrarInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Distancia Ruta: ").append(distancia).append(" km").append("\n");
		sb.append("Peso Paquete: ").append(peso).append(" kg\n");
		sb.append("Precio Base: 5.00€").append("\n");
		sb.append("Tarifa Aplicada: ").append(tarifa).append(" €\n");
		sb.append("Precio Final: ").append(precioFinal).append(" €\n");
		return sb.toString();
	}
	
	
	

}
