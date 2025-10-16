import java.io.*;
import java.util.*;
 
public class MatrizArchivo {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número de filas: ");
        int filas = sc.nextInt();
        System.out.print("Número de columnas: ");
        int columnas = sc.nextInt();
        double[][] matriz = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Elemento [" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextDouble();
            }
        }
        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream("matriz.dat"))) {
            salida.writeInt(filas);
            salida.writeInt(columnas);
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    salida.writeDouble(matriz[i][j]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e);
        }
    }
}
