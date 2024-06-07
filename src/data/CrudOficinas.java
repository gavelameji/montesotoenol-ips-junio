package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.OficinaDTO;

public class CrudOficinas {
	
	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	// private final static String DBURL = "jdbc:hsqldb:mem:testdb"; // Base de
	// datos en memoria
	private final static String DBUSER = "SA";
	private final static String DBPW = "";
	
	public static List<OficinaDTO> obtenerOficinas() {
		String sql = "SELECT * FROM oficina";
		List<OficinaDTO> oficinas = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				OficinaDTO oficina = new OficinaDTO(resultSet.getString("id_oficina"), 
						resultSet.getString("ciudad_oficina"),
						resultSet.getString("direccion_oficina"));

				oficinas.add(oficina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return oficinas;
	}

}
