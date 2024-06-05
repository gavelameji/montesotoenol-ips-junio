package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {
	
	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	//private final static String DBURL = "jdbc:hsqldb:mem:testdb"; // Base de datos en memoria
	private final static String DBUSER = "SA";
	private final static String DBPW = "";
	
	public static void ejecutarSQLUpdate(String sql) {
		Connection connection = null;
        Statement statement = null;
        try {
            // Cargar el controlador JDBC de HSQLDB
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            // Conectarse a la base de datos
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            statement = connection.createStatement();
            
            // Ejecutar sentencia
            statement.executeUpdate(sql);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
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
