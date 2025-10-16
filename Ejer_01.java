import java.io.*;  
import java.util.*;  
class Personaje{  
    private String nombre;  
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    public Personaje(String nombre,int vida,int ataque,int defensa,int alcance){
        if(vida<=0||ataque<=0||defensa<=0||alcance<=0){
            throw new IllegalArgumentException("mayor a 0 >:/");
        }else{
            this.nombre=nombre;  
            this.vida=vida;  
            this.ataque=ataque;  
            this.defensa=defensa;  
            this.alcance=alcance;
        }
    }
    public String getNombre(){
        return nombre;
    }
    public int getVida(){
        return vida;
    }
    public int getAtaque(){
        return ataque;
    }
    public int getDefensa(){
        return defensa;
    }
    public int getAlcance(){
        return alcance;
    }
    public void setVida(int v){
        if(v>0){
            this.vida=v;
        }
    }
    public void setAtaque(int a){
        if(a>0){
            this.ataque=a;
        }
    }
    public void setDefensa(int d){
        if(d>0)this.defensa=d;
    }  
    public void setAlcance(int al){
        if(al>0)this.alcance=al;
    }  
    public String toString(){
        return nombre+","+vida+","+ataque+","+defensa+","+alcance;  
    }  
}  
class Gestor{  
    private List<Personaje> lista;  
    private File archivo;  
    public Gestor(String ruta){  
        archivo=new File(ruta);  
        lista=new ArrayList<>();  
        cargar();  
    }
    private void cargar(){  
        if(!archivo.exists()){
            return;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(archivo))){  
            String linea;  
            while((linea=br.readLine())!=null){  
                String[] p=linea.split(",");  
                if(p.length==5){  
                    String n=p[0].trim();  
                    int v=Integer.parseInt(p[1]);  
                    int a=Integer.parseInt(p[2]);  
                    int d=Integer.parseInt(p[3]);  
                    int al=Integer.parseInt(p[4]);  
                    lista.add(new Personaje(n,v,a,d,al));  
                }  
            }  
        }catch(Exception e){  
            System.out.println("error cargando archivo");  
        }  
    } 
    private void guardar(){  
        try(PrintWriter pw=new PrintWriter(new FileWriter(archivo))){  
            for(Personaje p:lista){  
                pw.println(p.toString());  
            }  
        }catch(Exception e){  
            System.out.println("error guardando archivo");  
        }  
    }
    public void agregar(String n,int v,int a,int d,int al){  
        for(Personaje p:lista){  
            if(p.getNombre().equalsIgnoreCase(n)){  
                System.out.println("elige otro nombre crack");  
                return;  
            }  
        }  
        lista.add(new Personaje(n,v,a,d,al));  
        guardar();  
        System.out.println("nuevo viajero llego a teyvat");  
    }
    public void mostrar(){  
        if(lista.isEmpty())System.out.println("estas solo");  
        else for(Personaje p:lista)System.out.println(p.toString());  
    }
    public void modificar(String n,int v,int a,int d,int al){  
        for(Personaje p:lista){  
            if(p.getNombre().equalsIgnoreCase(n)){  
                p.setVida(v);  
                p.setAtaque(a);  
                p.setDefensa(d);  
                p.setAlcance(al);  
                guardar();  
                System.out.println("reestructuracion de vida");  
                return;  
            }  
        }  
        System.out.println("viajero errante no encontrado");  
    }
    public void eliminar(String n){  
        Iterator<Personaje> it=lista.iterator();  
        while(it.hasNext()){  
            Personaje p=it.next();  
            if(p.getNombre().equalsIgnoreCase(n)){  
                it.remove();  
                guardar();  
                System.out.println("las estrellas de desvanecen en el cosmos");  
                return;  
            }  
        }  
        System.out.println("quisiste decir otro nombre??????");  
    }  
}  
public class Ejer1Genshinv0p4{  
    public static void main(String[] args){  
        Gestor g=new Gestor("viajeros.txt");  
        g.agregar("Aether",100,30,20,5);  
        g.agregar("Lumine",90,25,15,6);  
        g.mostrar();  
        g.modificar("Lumine",120,40,20,8);  
        g.mostrar();  
        g.eliminar("Aether");  
        g.mostrar();  
    }  
}  
