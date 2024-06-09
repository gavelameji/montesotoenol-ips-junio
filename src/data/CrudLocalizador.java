package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Localizador;
import model.dto.UbicacionDTO;

public class CrudLocalizador {
	
	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	private final static String DBUSER = "SA";
	private final static String DBPW = "";
	
	public static List<Localizador> obtenerLocalizadoresPorDNI(String dni) {
	    String sql = "SELECT * FROM paquete p, localizador l "
	    		+ " WHERE p.id_paquete = l.id_paquete"
	    		+ " AND p.dni_cliente = ?";
	    
	    List<Localizador> localizadores = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, dni);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Localizador localizador = new Localizador(
	                resultSet.getString("id_localizador"),
	                resultSet.getString("id_paquete")
	            );
	            localizadores.add(localizador);
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return localizadores;
	}
	
	public static List<UbicacionDTO> obtenerUbicacionesPorLocalizador(String idLocalizador) {
	    String sql = "SELECT * FROM ubicacion WHERE id_localizador = ?";
	    
	    List<UbicacionDTO> ubicaciones = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, idLocalizador);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            UbicacionDTO ubicacion = new UbicacionDTO(
	                resultSet.getString("id_ubicacion"),
	                resultSet.getString("id_almacen"),
	                resultSet.getString("id_localizador"),
	                resultSet.getBoolean("es_actual")
	            );
	            ubicaciones.add(ubicacion);
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ubicaciones;
	}

}
