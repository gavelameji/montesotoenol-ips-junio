package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import io.FileUtil;

public class GestorBD {
	
	private final static String DBURL = "jdbc:hsqldb:file:data/basedatos;shutdown=true";
	//private final static String DBURL = "jdbc:hsqldb:mem:testdb"; // Base de datos en memoria
	private final static String DBUSER = "SA";
	private final static String DBPW = "";
	
	public static void generarBaseDeDatosEnBlanco() {
		String SQL = FileUtil.cargarTextoDeFichero("scripts/esquema.sql");
		ejecutarSQLUpdate(SQL);
		System.out.println("Operación Finalizada");
	}
	
	public static void ejecutarSQLUpdate(String sql) {
		Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
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
