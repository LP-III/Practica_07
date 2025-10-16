import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
 
public class MostrarArchivo { 
 
    public static void main(String[] args) {
        
        Scanner escaner = new Scanner(System.in);
        String cadena;
 
        try (PrintWriter salida = new PrintWriter("c:/ficheros/datos.txt")) { 
            System.out.println("Introduce texto. Para acabar introduce la cadena FIN:");
            cadena = escaner.nextLine();
 
            while (!cadena.equalsIgnoreCase("FIN")) { 
                salida.println(cadena);
                cadena = escaner.nextLine();
            }
            
        } catch (FileNotFoundException e) { 
 
            System.out.println(e.getMessage());
        }
    }
}
