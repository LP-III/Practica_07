import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class PrintWriterExample {
    public static void main(String[] args) {
        String fileName = "texto_printwriter.txt";
        String[] lines = {
            "Primera línea del archivo.",
            "Segunda línea del archivo.",
            "Tercera línea del archivo."
        };
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (String line : lines) {
                writer.println(line);
            }
            System.out.println("Contenido escrito en el archivo: " + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
public class WriterExample {
    public static void main(String[] args) {
        String fileName1 = "ejemplo_filewriter.txt";
        String fileName2 = "ejemplo_printwriter.txt";
        String content = "Hola, esto es un ejemplo de escritura.\n";
        int number = 42;
        // Usando FileWriter
        try (FileWriter fileWriter = new FileWriter(fileName1)) {
            fileWriter.write(content);
            fileWriter.write("El número es: " + number);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Usando PrintWriter
        try (PrintWriter printWriter = new PrintWriter(fileName2)) {
            printWriter.println(content);
            printWriter.printf("El número es: %d%n", number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
