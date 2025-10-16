import java.io.*;  
import java.util.*;  
class Personaje{  
    private String nombre;  
    private int vida;  
    private int ataque;  
    private int defensa;  
    private int alcance;  
    private int nivel;  
    public Personaje(String nombre,int vida,int ataque,int defensa,int alcance){  
        if(vida<=0||ataque<=0||defensa<=0||alcance<=0){  
            throw new IllegalArgumentException("mayor a 0 >:/");  
        }else{  
            this.nombre=nombre;  
            this.vida=vida;  
            this.ataque=ataque;  
            this.defensa=defensa;  
            this.alcance=alcance;  
            this.nivel=1;  
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
    public int getNivel(){
        return nivel;
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
        if(d>0){
            this.defensa=d;
        }
    }  
    public void setAlcance(int al){
        if(al>0){
            this.alcance=al;
        }
    }  
    public void subirNivel(){  
        nivel++;  
        vida+=10;  
        ataque+=5;  
        defensa+=3;  
        alcance+=1;  
    }  
    public String toString(){  
        return nombre+","+vida+","+ataque+","+defensa+","+alcance+","+nivel;  
    }  
}  




class Gestor{  
    private List<Personaje> lista;  
    private File archivo;  
    public Gestor(String ruta){  
        archivo=new File(ruta);  
        lista=new ArrayList<>();  
        cargar();  
        cargarAleatorios();  
    }  
    private void cargar(){  
        if(!archivo.exists())return;  
        try(BufferedReader br=new BufferedReader(new FileReader(archivo))){  
            String linea;  
            while((linea=br.readLine())!=null){  
                String[] p=linea.split(",");  
                if(p.length>=5){  
                    String n=p[0].trim();  
                    int v=Integer.parseInt(p[1]);  
                    int a=Integer.parseInt(p[2]);  
                    int d=Integer.parseInt(p[3]);  
                    int al=Integer.parseInt(p[4]);  
                    lista.add(new Personaje(n,v,a,d,al));  
                }  
            }  
        }
        catch(Exception e){
        System.out.println("error archivo");
        }  
    }  
    private void guardar(){  
        try(PrintWriter pw=new PrintWriter(new FileWriter(archivo))){  
            for(Personaje p:lista){
                pw.println(p.toString());
            }  
        }
        catch(Exception e){
            System.out.println("error archivo");
        }  
    }  
    private void cargarAleatorios(){  
        if(lista.isEmpty()){  
            agregar("Navia",80,25,15,8);  
            agregar("Nahida",100,30,20,6);  
            agregar("Kadeehara",70,35,10,9);  
            System.out.println("Decienden almas del mar de estrellas");  
        }  
    }  
    public void agregar(String n,int v,int a,int d,int al){  
        for(Personaje p:lista){  
            if(p.getNombre().equalsIgnoreCase(n)){  
                System.out.println("Nombre usado");  
                return;  
            }  
        }  
        lista.add(new Personaje(n,v,a,d,al));  
        guardar();  
        System.out.println("Nuevo viajero llego a teyvat");  
    }  
    public void mostrar(){  
        if(lista.isEmpty())System.out.println("Estas solo");  
        else for(Personaje p:lista)System.out.println(p.toString());  
    }  
    public void filtrarAtributo(String tipo){  
        if(lista.isEmpty()){System.out.println("Sin viajeros");return;}  
        Comparator<Personaje> cmp=null;  
        switch(tipo.toLowerCase()){  
            case "vida": 
                cmp=(x,y)->y.getVida()-x.getVida();
                break;  
            case "ataque":
                cmp=(x,y)->y.getAtaque()-x.getAtaque();
                break;  
            case "defensa":
                cmp=(x,y)->y.getDefensa()-x.getDefensa();
                break;  
            case "alcance":
                cmp=(x,y)->y.getAlcance()-x.getAlcance();
                break;  
            case "nivel": 
                cmp=(x,y)->y.getNivel()-x.getNivel();
                break;  
            default:
                System.out.println("Atributo invalido");
                return;
        }
        lista.sort(cmp);  
        mostrar();  
    }  
    public void modificar(String n,int v,int a,int d,int al){  
        for(Personaje p:lista){  
            if(p.getNombre().equalsIgnoreCase(n)){  
                p.setVida(v);p.setAtaque(a);p.setDefensa(d);p.setAlcance(al);  
                guardar();  
                System.out.println("Reestructuracion de vida");  
                return;  
            }  
        }  
        System.out.println("Viajero errante no encontrado");  
    }  
    public void actualizarAtributo(String n,String atr,int val){  
        for(Personaje p:lista){  
            if(p.getNombre().equalsIgnoreCase(n)){  
                switch(atr.toLowerCase()){  
                    case "Vida":
                        p.setVida(val);
                        break;  
                    case "Ataque":
                        p.setAtaque(val);
                        break;  
                    case "Defensa":
                        p.setDefensa(val);
                        break;  
                    case "Alcance":
                        p.setAlcance(val);
                        break;  
                    default:
                        System.out.println("No existe ese atributo");
                        return;  
                }  
                guardar();  
                System.out.println("Atributo actualizado");  
                return;  
            }  
        }  
        System.out.println("Personaje no existe");  
    }  
    public void eliminar(String n){  
        Iterator<Personaje> it=lista.iterator();  
        while(it.hasNext()){  
            Personaje p=it.next();  
            if(p.getNombre().equalsIgnoreCase(n)){  
                it.remove();  
                guardar();  
                System.out.println("Las estrellas de desvanecen en el cosmos");  
                return;  
            }  
        }  
        System.out.println("¿Quisiste decir otro nombre?");  
    }  
    public void mostrarEstadisticas(){  
        if(lista.isEmpty()){
            System.out.println("No hay viajeros");
            return;
        }  
        int total=lista.size();  
        double vida=0,ataque=0,defensa=0,alcance=0;  
        for(Personaje p:lista){  
            vida+=p.getVida();  
            ataque+=p.getAtaque();  
            defensa+=p.getDefensa();  
            alcance+=p.getAlcance();  
        }  
        System.out.println("Total personajes:"+total);  
        System.out.println("Vida promedio:"+(vida/total));  
        System.out.println("Ataque promedio:"+(ataque/total));  
        System.out.println("Defensa promedio:"+(defensa/total));  
        System.out.println("Alcance promedio:"+(alcance/total));  
    }  
    public void importarDesdeArchivo(String ruta){  
        File otro=new File(ruta);  
        if(!otro.exists()){
            System.out.println("Archivo externo no existe");
            return;
        }  
        try(BufferedReader br=new BufferedReader(new FileReader(otro))){  
            String linea;  
            while((linea=br.readLine())!=null){  
                String[] p=linea.split(",");  
                if(p.length>=5){  
                    String n=p[0];  
                    int v=Integer.parseInt(p[1]);  
                    int a=Integer.parseInt(p[2]);  
                    int d=Integer.parseInt(p[3]);  
                    int al=Integer.parseInt(p[4]);  
                    agregar(n,v,a,d,al);  
                }  
            }  
            System.out.println("Importacion completada desde "+ruta);  
        }
        catch(Exception e){
            System.out.println("Error importando archivo");
        }  
    }  
    public void subirNivel(String n){  
        for(Personaje p:lista){  
            if(p.getNombre().equalsIgnoreCase(n)){  
                p.subirNivel();  
                guardar();  
                System.out.println(p.getNombre()+" Subio al nivel "+p.getNivel());  
                return;  
            }  
        }  
        System.out.println("Personaje no encontrado");  
    }  
}  

public class Ejer_02{  
    public static void main(String[] args){  
        Gestor g=new Gestor("viajeros.txt");  
        g.mostrar();  
        g.subirNivel("Nahida");  
        g.actualizarAtributo("Kadeehara","ataque",90);  
        g.mostrarEstadisticas();  
        g.filtrarAtributo("ataque");  
        g.importarDesdeArchivo("gimi.txt");  
        g.mostrar();  
    }  
} 
