import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
 
public class InfoArchivos {
    public static void main(String[] args) throws IOException {
        Path ruta = Paths.get("C:/Users/Public"); 
        System.out.printf("Nombre: %s%n", ruta.getFileName());
        System.out.printf("¿Es directorio?: %s%n", Files.isDirectory(ruta));
        System.out.printf("¿Ruta absoluta?: %s%n", ruta.isAbsolute());
        System.out.printf("Última modificación: %s%n", Files.getLastModifiedTime(ruta));
        System.out.printf("Tamaño: %s bytes%n", Files.size(ruta));
        System.out.printf("Ruta absoluta: %s%n", ruta.toAbsolutePath());
 
        if (Files.isDirectory(ruta)) {
            System.out.println("\nContenido del directorio:");
            DirectoryStream<Path> contenido = Files.newDirectoryStream(ruta);
            for (Path p : contenido) {
                System.out.println(" - " + p);
            }
        }
    }
}
