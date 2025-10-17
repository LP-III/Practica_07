import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L; // Versión de serialización
    private String nombre;
    private int edad;
    // Constructor
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    // Getters
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    // Método toString
    @Override
    public String toString() {
        return "Persona{" + "nombre='" + nombre + '\'' + ", edad=" + edad +'}';
    }
}