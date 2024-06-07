package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.dto.ClienteDTO;

public class CrudClientes {
	
	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	// private final static String DBURL = "jdbc:hsqldb:mem:testdb"; // Base de
	// datos en memoria
	private final static String DBUSER = "SA";
	private final static String DBPW = "";
	
	public static ClienteDTO obtenerClientePorDNI(String dni) {
	    String sql = "SELECT * FROM cliente WHERE dni_cliente = ?";

	    try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, dni);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if(resultSet.next()) {
	        	ClienteDTO cliente = new ClienteDTO(
	                resultSet.getString("dni_cliente"),
	                resultSet.getString("nombre_cliente"),
	                resultSet.getString("telefono_cliente"),
	                resultSet.getString("direccion_cliente")
	            );
	            return cliente;
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void insertarCliente(ClienteDTO cliente) {
		Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO cliente (dni_cliente, nombre_cliente, telefono_cliente, direccion_cliente)"
        		+ " VALUES (?,?,?,?);";
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getDni());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getDireccion());
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
