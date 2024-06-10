package model;

import java.util.ArrayList;
import java.util.List;

import data.CrudClientes;
import data.CrudOficinas;
import data.CrudPaquetes;
import model.dto.ClienteDTO;
import model.dto.OficinaDTO;
import model.dto.PaqueteDTO;

public class TarifasController {
	
	private List<OficinaDTO> oficinas;
	
	private float precioFinal;
	private static final float PRECIO_BASE = 5.0f;
	
	public TarifasController() {
		oficinas = CrudOficinas.obtenerOficinas();
	}
	
	public List<OficinaDTO> getOficinas() {
		return new ArrayList<OficinaDTO>(oficinas);
	}
	
	public List<PaqueteDTO> getPaquetesPorIdOficina(String idOficina) {
		return CrudPaquetes.obtenerPaquetesEnRutaPorIdDeOficina(idOficina);
	}
	
	public float getPrecioFinal() {
		return precioFinal;
	}
	
	public void calcularPrecioFinal(float distancia, float peso) {
	    float tarifa = ((distancia + peso) / 2) * 0.2f;
	    precioFinal = PRECIO_BASE + tarifa;
	}
	
	public String getInfoPaquete(PaqueteDTO p) {
		ClienteDTO c = CrudClientes.obtenerClientePorDNI(p.getDniCliente());
		if(p == null || c == null) {
			return "Nada que mostrar.";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Descripción: ").append(p.getDescripcion()).append("\n");
		sb.append("Fecha Emisión: ").append(p.getFechaEmision()).append("\n");
		sb.append("Dirección De Recogida: ").append(p.getDireccionOrigen()).append("\n");
		sb.append("Dirección Destino: ").append(p.getDireccionDestino()).append("\n");
		sb.append("Cliente: " + c.getNombre() + ", " + c.getDni()).append("\n");
		sb.append("Teléfono: ").append(c.getTelefono()).append("\n");
		return sb.toString();
	}

}
