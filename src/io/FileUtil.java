package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
	
	public static String cargarTextoDeFichero(String directorio) {
        Path path = Paths.get(directorio);
        try {
            return new String(Files.readAllBytes(path));
        } catch (NoSuchFileException e) {
            return "Error: El archivo no existe.";
        } catch (IOException e) {
            return "Error: No se pudo leer el archivo.";
        }
    }

}
