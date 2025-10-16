import java.io.*;
import java.util.*;
import javax.swing.*;

public class ContadorPalabras{
    public static void main(String[] args){
        JFileChooser fc=new JFileChooser();
        File archivo=null;
        int r=fc.showOpenDialog(null);
        if(r==JFileChooser.APPROVE_OPTION){
            archivo=fc.getSelectedFile();
        }else{
            System.out.println("eligie... rapido");
            return;
        }
        if(archivo==null || !archivo.exists()){
            System.out.println("elige bien!");
            return;
        }
        int lineas=0;
        int palabras=0;
        int caracteres=0;
        Map<String,Integer> mapa=new HashMap<>();

        try(BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea=br.readLine())!=null){
                lineas++;
                caracteres+=linea.replace("\n","").length();
                StringBuilder palabra=new StringBuilder();
                for(char c: linea.toCharArray()){
                    if(Character.isLetterOrDigit(c)){
                        palabra.append(Character.toLowerCase(c));
                    }else{
                        if(palabra.length()>0){
                            String w=palabra.toString();
                            mapa.put(w,mapa.getOrDefault(w,0)+1);
                            palabras++;
                            palabra.setLength(0);
                        }
                    }
                }
                if(palabra.length()>0){
                    String w=palabra.toString();
                    mapa.put(w,mapa.getOrDefault(w,0)+1);
                    palabras++;
                }
            }
        }catch(Exception e){
            System.out.println("error no se lee");
            return;
        }
        double promedio= lineas>0 ? (double)palabras/lineas : 0;
        int max=0;
        for(int v: mapa.values()){
            if(v>max)max=v;
        }
        List<String> frecuentes=new ArrayList<>();
        for(String k: mapa.keySet()){
            if(mapa.get(k)==max){
                frecuentes.add(k);
            }
        }
        System.out.println("archivo: "+archivo.getName());
        System.out.println("lineas: "+lineas);
        System.out.println("palabras: "+palabras);
        System.out.println("caracteres: "+caracteres);
        System.out.println("promedio palabras por linea: "+promedio);
        System.out.println("frecuentes ("+max+" veces): "+frecuentes);
    }

}
