package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	public static void cargarDatosIniciales() {
		String SQL = FileUtil.cargarTextoDeFichero("scripts/datos.sql");
		ejecutarSQLUpdate(SQL);
		System.out.println("Operación Finalizada");
	}
	public static void ejecutarSQLSelect(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Aquí puedes procesar los datos del ResultSet
                // Por ejemplo, si la consulta devuelve columnas 'id' y 'nombre':
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
