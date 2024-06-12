package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudRutas {
	
	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	private final static String DBUSER = "SA";
	private final static String DBPW = "";
	
	public static float obtenerDistanciaPorRuta(String idLocalizador) {
	    String sql = "SELECT distancia_ruta FROM ruta r WHERE id_ruta = ?";

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, idLocalizador);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	        	return resultSet.getFloat("distancia_ruta");
	        }
	        
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0.0f;
	}

}
