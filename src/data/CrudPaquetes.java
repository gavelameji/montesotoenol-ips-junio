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
						resultSet.getDate("fecha_emision_paquete"), resultSet.getString("estado_paquete"),
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
	                resultSet.getDate("fecha_emision_paquete"),
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
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return paquetes;
	}
	
	public static List<PaqueteDTO> obtenerPaquetesEnRutaPorIdDeOficina(String idOficina) {
	    String sql = "SELECT * FROM paquete WHERE "
	    		+ "estado_paquete = 'en ruta' AND id_oficina = ?"
	    		+ "ORDER BY fecha_emision_paquete";
	    List<PaqueteDTO> paquetes = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, idOficina);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            PaqueteDTO paquete = new PaqueteDTO(
	                resultSet.getString("id_paquete"),
	                resultSet.getString("descripcion_paquete"),
	                resultSet.getDate("fecha_emision_paquete"),
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
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return paquetes;
	}
	
	public static PaqueteDTO obtenerPaquetePorLocalizador(String idLocalizador) {
	    String sql = "SELECT * FROM paquete p, localizador l"
	    		+ " WHERE l.id_localizador = ? AND p.id_paquete = l.id_paquete";

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, idLocalizador);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            PaqueteDTO paquete = new PaqueteDTO(
	                resultSet.getString("id_paquete"),
	                resultSet.getString("descripcion_paquete"),
	                resultSet.getDate("fecha_emision_paquete"),
	                resultSet.getString("estado_paquete"),
	                resultSet.getString("direccion_origen_paquete"),
	                resultSet.getString("direccion_destino_paquete"),
	                resultSet.getBoolean("recoger_a_domicilio"),
	                resultSet.getString("dni_cliente"),
	                resultSet.getString("id_ruta"),
	                resultSet.getString("id_oficina")
	            );
	            return paquete;
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void insertarPaquete(PaqueteDTO paquete) {
		Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete,"
        		+ " recoger_a_domicilio, estado_paquete,"
        		+ " direccion_origen_paquete, direccion_destino_paquete, "
        		+ " dni_cliente, id_oficina, id_ruta)\r\n"
        		+ " VALUES (?,?,?,?,?,?,?,?,?,?);";
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            statement = connection.prepareStatement(sql);
            statement.setString(1, paquete.getId());
            statement.setString(2, paquete.getDescripcion());
            statement.setDate(3, paquete.getFechaEmision());
            statement.setBoolean(4, paquete.paraRecogerADomicilio());
            statement.setString(5, paquete.getEstado());
            statement.setString(6, paquete.getDireccionOrigen());
            statement.setString(7, paquete.getDireccionDestino());
            statement.setString(8, paquete.getDniCliente());
            statement.setString(9, paquete.getIdOficina());
            statement.setString(10, paquete.getIdRuta());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
