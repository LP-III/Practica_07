import javax.swing.*;
import java.io.*;
 
public class SelectorArchivo {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
 
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            System.out.println("Nombre del archivo: " + archivo.getName());
            System.out.println("Ruta: " + archivo.getAbsolutePath());
            System.out.println("Tamaño: " + archivo.length() + " bytes");
            System.out.println("¿Es archivo?: " + archivo.isFile());
            System.out.println("¿Es directorio?: " + archivo.isDirectory());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }
}
