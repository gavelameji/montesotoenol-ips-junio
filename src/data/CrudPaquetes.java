package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.PaqueteDTO;

public class CrudPaquetes {

	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	// private final static String DBURL = "jdbc:hsqldb:mem:testdb"; // Base de
	// datos en memoria
	private final static String DBUSER = "SA";
	private final static String DBPW = "";

	public static List<PaqueteDTO> obtenerPaquetes() {
		String sql = "SELECT * FROM paquete";
		List<PaqueteDTO> paquetes = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				PaqueteDTO paquete = new PaqueteDTO(resultSet.getString("id_paquete"), resultSet.getString("descripcion_paquete"),
						resultSet.getDate("fecha_emision_paquete").toLocalDate(), resultSet.getString("estado_paquete"),
						resultSet.getString("direccion_origen_paquete"), resultSet.getString("direccion_destino_paquete"),
						resultSet.getBoolean("recoger_a_domicilio"), resultSet.getString("dni_cliente"),
						resultSet.getString("id_ruta"), resultSet.getString("id_oficina"));

				paquetes.add(paquete);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paquetes;
	}

	public static List<PaqueteDTO> obtenerPaquetesPorDNI(String dni) {
	    String sql = "SELECT * FROM paquete WHERE dni_cliente = ?";
	    List<PaqueteDTO> paquetes = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, dni);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            PaqueteDTO paquete = new PaqueteDTO(
	                resultSet.getString("id_paquete"),
	                resultSet.getString("descripcion_paquete"),
	                resultSet.getDate("fecha_emision_paquete").toLocalDate(),
	                resultSet.getString("estado_paquete"),
	                resultSet.getString("direccion_origen_paquete"),
	                resultSet.getString("direccion_destino_paquete"),
	                resultSet.getBoolean("recoger_a_domicilio"),
	                resultSet.getString("dni_cliente"),
	                resultSet.getString("id_ruta"),
	                resultSet.getString("id_oficina")
	            );
	            paquetes.add(paquete);
	        }
	        resultSet.close(); // Cierra el ResultSet explícitamente después del uso
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return paquetes;
	}
}
