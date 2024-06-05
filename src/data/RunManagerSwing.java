package data;

import org.hsqldb.util.DatabaseManagerSwing;

public class RunManagerSwing {
	
	public static void main(String[] args) {
        // Configurar los argumentos para DatabaseManagerSwing
        DatabaseManagerSwing.main(new String[] {
            "--url", "jdbc:hsqldb:file:data/basedatos", // o "jdbc:hsqldb:mem:testdb" para base de datos en memoria
            "--user", "SA",
            "--password", ""
        });
    }

}
