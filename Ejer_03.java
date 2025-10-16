package mvcpq;
import java.io.*;
import java.util.*;

class Empleado{
    private int numero;
    private String nombre;
    private double sueldo;
    public Empleado(int numero,String nombre,double sueldo){
        this.numero=numero;
        this.nombre=nombre;
        this.sueldo=sueldo;
    }
    public int getNumero(){
        return numero;
    }
    public String getNombre(){
        return nombre;
    }
    public double getSueldo(){
        return sueldo;
    }
    public void setNumero(int n){
        if(n>0)this.numero=n;
    }
    public void setNombre(String nom){
        this.nombre=nom;
    }
    public void setSueldo(double s){
        if(s>0){
            this.sueldo=s;
        }
    }
    public String toString(){
        return numero+","+nombre+","+sueldo;
    }
}


class Modelo{
    private List<Empleado> lista;
    private File archivo;
    public Modelo(String ruta){
        archivo=new File(ruta);
        lista=new ArrayList<>();
        leerEmpleados();
    }
    public void leerEmpleados(){
        if(!archivo.exists()){
            return;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea=br.readLine())!=null){
                String[] p=linea.split(",");
                if(p.length==3){
                    int num=Integer.parseInt(p[0]);
                    String nom=p[1];
                    double sue=Double.parseDouble(p[2]);
                    lista.add(new Empleado(num,nom,sue));
                }
            }
        }
        catch(Exception e){
            System.out.println("error leyendo archivo");
        }
    }
    private void guardar(){
        try(PrintWriter pw=new PrintWriter(new FileWriter(archivo))){
            for(Empleado e:lista){
                pw.println(e.toString());
            }
        }catch(Exception e){
            System.out.println("error guardando archivo");
        }
    }
    public List<Empleado> getLista(){
        return lista;
    }
    public void agregarEmpleado(Empleado e){
        for(Empleado x:lista){
            if(x.getNumero()==e.getNumero()){
                System.out.println("numero ya usado");
                return;
            }
        }
        lista.add(e);
        guardar();
        System.out.println("empleado agregado");
    }

    public Empleado buscarEmpleado(int num){
        for(Empleado e:lista){
            if(e.getNumero()==num){
                return e;
            }
        }
        return null;
    }

    public void eliminarEmpleado(int num){
        Iterator<Empleado> it=lista.iterator();
        while(it.hasNext()){
            Empleado e=it.next();
            if(e.getNumero()==num){
                it.remove();
                guardar();
                System.out.println("empleado eliminado");
                return;
            }
        }
        System.out.println("empleado no encontrado");
    }
}

class Vista{
    private Scanner sc=new Scanner(System.in);

    public int menu(){
        System.out.println("\nMenu suer moderno (=0)");
        System.out.println("1. asi	stencia");
        System.out.println("2. contrata");
        System.out.println("3. mas buscados");
        System.out.println("4. descartar");
        System.out.println("5. exit");
        System.out.print("opcion: ");
        return sc.nextInt();
    }

    public void mostrarEmpleados(List<Empleado> lista){
        if(lista.isEmpty()){
            System.out.println("no hay nadie trabajando");
        }else{
            for(Empleado e:lista){
                System.out.println(e.toString());
            }
        }
    }

    public Empleado nuevoEmpleado(){
        System.out.print("numero: ");
        int num=sc.nextInt();
        sc.nextLine();
        System.out.print("nombre: ");
        String nom=sc.nextLine();
        System.out.print("sueldo: ");
        double sue=sc.nextDouble();
        return new Empleado(num,nom,sue);
    }

    public int pedirNumero(){
        System.out.print("numero empleado: ");
        return sc.nextInt();
    }

    public void mostrarEmpleado(Empleado e){
        if(e==null){
            System.out.println("no encontrado");
        }else{
            System.out.println(e.toString());
        }
    }

    public void mensaje(String m){
        System.out.println(m);
    }
}


class Vista{
    private Scanner sc=new Scanner(System.in);

    public int menu(){
        System.out.println("\nMenu suer moderno (=0)");
        System.out.println("1. asi	stencia");
        System.out.println("2. contrata");
        System.out.println("3. mas buscados");
        System.out.println("4. descartar");
        System.out.println("5. exit");
        System.out.print("opcion: ");
        return sc.nextInt();
    }

    public void mostrarEmpleados(List<Empleado> lista){
        if(lista.isEmpty()){
            System.out.println("no hay nadie trabajando");
        }else{
            for(Empleado e:lista){
                System.out.println(e.toString());
            }
        }
    }

    public Empleado nuevoEmpleado(){
        System.out.print("numero: ");
        int num=sc.nextInt();
        sc.nextLine();
        System.out.print("nombre: ");
        String nom=sc.nextLine();
        System.out.print("sueldo: ");
        double sue=sc.nextDouble();
        return new Empleado(num,nom,sue);
    }

    public int pedirNumero(){
        System.out.print("numero empleado: ");
        return sc.nextInt();
    }

    public void mostrarEmpleado(Empleado e){
        if(e==null){
            System.out.println("no encontrado");
        }else{
            System.out.println(e.toString());
        }
    }

    public void mensaje(String m){
        System.out.println(m);
    }
}

class Controlador{
    private Modelo modelo;
    private Vista vista;
    public Controlador(Modelo m,Vista v){
        modelo=m;
        vista=v;
    }
    public void iniciar(){
        int op;
        do{
            op=vista.menu();
            switch(op){
                case 1:
                    vista.mostrarEmpleados(modelo.getLista());
                    break;
                case 2:
                    Empleado e=vista.nuevoEmpleado();
                    modelo.agregarEmpleado(e);
                    break;
                case 3:
                    int n=vista.pedirNumero();
                    vista.mostrarEmpleado(modelo.buscarEmpleado(n));
                    break;
                case 4:
                    int d=vista.pedirNumero();
                    modelo.eliminarEmpleado(d);
                    break;
                case 5:
                    vista.mensaje("saliendo ... .. .");
                    break;
                default:
                    vista.mensaje("opcion invalida");
            }
        }while(op!=5);
    }

}
