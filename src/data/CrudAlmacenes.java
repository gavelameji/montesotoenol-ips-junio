package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.AlmacenDTO;

public class CrudAlmacenes {

	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	private final static String DBUSER = "SA";
	private final static String DBPW = "";

	public static List<AlmacenDTO> obtenerAlmacenes() {
		String sql = "SELECT * FROM almacen";
		List<AlmacenDTO> almacenes = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				AlmacenDTO almacen = new AlmacenDTO(resultSet.getString("id_almacen"),
						resultSet.getString("ciudad_almacen"), resultSet.getString("direccion_almacen"));

				almacenes.add(almacen);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return almacenes;
	}

	public static AlmacenDTO obtenerAlmacenPorId(String idAlmacen) {
	    String sql = "SELECT * FROM almacen WHERE id_almacen = ?";

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, idAlmacen);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            AlmacenDTO almacen = new AlmacenDTO(
	                resultSet.getString("id_almacen"),
	                resultSet.getString("ciudad_almacen"),
	                resultSet.getString("direccion_almacen")
	            );
	            return almacen;
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
