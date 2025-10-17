import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GuardarPersonas {
public static void main(String[] args) {
    // Crear objetos de Persona
    Persona p1 = new Persona("Alice", 30);
    Persona p2 = new Persona("Bob", 25);
    // Guardar los objetos en un archivo
    try (FileOutputStream fos = new FileOutputStream("personas.ser");
    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(p1);
        oos.writeObject(p2);
        System.out.println("Personas guardadas en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar las personas: " + e.getMessage());
        }
    }
}