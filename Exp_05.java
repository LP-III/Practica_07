import java.io.*;
import java.util.*;
 
class Persona {
    String nombre;
    String telefono;
    String direccion;
 
    Persona(String n, String t, String d) {
        nombre = n;
        telefono = t;
        direccion = d;
    }
}
 
public class Agenda {
    public static void main(String[] args) {
        ArrayList<Persona> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("agenda.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    lista.add(new Persona(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e);
        }
 
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un nombre: ");
        String nombreBuscado = sc.nextLine();
 
        boolean encontrado = false;
        for (Persona p : lista) {
            if (p.nombre.equalsIgnoreCase(nombreBuscado)) {
                System.out.println("Teléfono: " + p.telefono);
                System.out.println("Dirección: " + p.direccion);
                encontrado = true;
                break;
            }
        }
 
        if (!encontrado) {
            System.out.println("No se encontró a la persona.");
        }
    }
}
