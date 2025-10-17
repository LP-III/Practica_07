import javax.swing.*;
import java.io.File;
public class FileChooserExample {
    public static void main(String[] args) {
        // Crear un JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        // Mostrar el diálogo de selección de archivos
        int result = fileChooser.showOpenDialog(null);
        // Procesar la selección
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " +
            selectedFile.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }
}