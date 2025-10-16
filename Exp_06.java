import java.io.*;
 
class Fecha implements Serializable {
    int dia, mes, anio;
 
    Fecha(int d, int m, int a) {
        dia = d;
        mes = m;
        anio = a;
    }
}
 
class Persona implements Serializable {
    String nombre;
 
    Persona(String n) {
        nombre = n;
    }
}
 
class Alumno extends Persona {
    Fecha nacimiento;
 
    Alumno(String n, Fecha f) {
        super(n);
        nacimiento = f;
    }
}
 
public class Serializacion {
    public static void main(String[] args) {
        Alumno a1 = new Alumno("Carlos", new Fecha(1, 2, 2000));
        Alumno a2 = new Alumno("Lucía", new Fecha(15, 5, 1999));
        Alumno a3 = new Alumno("Mario", new Fecha(30, 8, 2001));
 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("alumnos.dat"))) {
            oos.writeObject(a1);
            oos.writeObject(a2);
            oos.writeObject(a3);
            System.out.println("Objetos serializados correctamente.");
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e);
        }
    }
}
