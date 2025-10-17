import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class BinaryFileOutputStream {
    public static void main(String[] args) {
        // Especifica la ruta y el nombre del archivo binario
        String filePath = "datos.bin";
        // Usa FileOutputStream y DataOutputStream para escribir en el archivo binario
        try (FileOutputStream fos = new FileOutputStream(filePath);
        DataOutputStream dos = new DataOutputStream(fos)) {
            // Escribir diferentes tipos de datos en el archivo
            dos.writeInt(100); // Escribir un entero
            dos.writeDouble(3.14); // Escribir un doble
            dos.writeUTF("Hola, mundo!"); // Escribir una cadena
            System.out.println("Datos escritos en el archivo binario: " + filePath);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}