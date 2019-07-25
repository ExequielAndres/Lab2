
package ejerciciop;
import java.util.ArrayList;
import java.util.Scanner;


public class Ejerciciop {
    
   //METODO MAIN PRECARGADO CON 10
    public static void main(String[] args) {
      
        Scanner entrada=new Scanner(System.in);
        ArrayList<pila> pilas=new ArrayList();
        pilas.add(new pila(1,999999));
        pilas.add(new pila(1000000,1999999));
        pilas.add(new pila(2000000,2999999));
        pilas.add(new pila(3000000,3999999));
        pilas.add(new pila(4000000,4999999));
        pilas.add(new pila(5000000,5999999));
        pilas.add(new pila(6000000,6999999));
        pilas.add(new pila(7000000,7999999));
        pilas.add(new pila(8000000,8999999));
        pilas.add(new pila(9000000,9999999));
        String nombre;
        String rut;
        int edad;
        int d=0;
        while(d!=10){
            System.out.println("1.-ingresar persona");
            System.out.println("3.-mostrar");
            System.out.println("10.-salir");
            d=entrada.nextInt();
            while(d!=1 && d!=10 && d!=3){
                System.out.println("opcion invalida");
                d=entrada.nextInt();
            }
            if(d==3){
                mostrar(pilas);
            }
            if(d==1){
                System.out.println("ingrese nombre");
                entrada.skip("\n");
                nombre=entrada.nextLine();
                
                System.out.println("ingrese rut ej:11.111.111-1");
                rut=entrada.nextLine();
                
                while(!validarRut(rut)){
                    System.out.println("rut invalido volver a ingresar... ");
                    rut=entrada.nextLine();
                }
            
                System.out.println("ingrese edad");
                edad=entrada.nextInt();
                persona agregar=new persona(nombre,rut,edad);
                if(existeRango(rut,pilas,agregar)){
                    System.out.println("persona agregada!!!!!!");
                    mostrar(pilas);
                }
                else{
                    int rango=encontrarRango(rut);
                    pilas.add(new pila(rango-999999,rango));
                    pilas=ordenar(pilas);
                    if(existeRango(rut,pilas,agregar)){
                        System.out.println("persona agregada!!!!!!");
                        mostrar(pilas);
                    }
                    
                }
            }
            
        }
        
    }
    
    // VALIDAR RUT
    public static boolean validarRut(String rut) {
 
        boolean validacion = false;
        try {
        rut =  rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

        char dv = rut.charAt(rut.length() - 1);

        int m = 0, s = 1;
        for (; rutAux != 0; rutAux /= 10) {
            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
        }
        if (dv == (char) (s != 0 ? s + 47 : 75)) {
            validacion = true;
        }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
    //COMPROBAR SI EXISTE LA COLA
    public static boolean existeRango(String rut,ArrayList<pila> aca,persona a){
        String [] aux=rut.split("-");
        rut=aux[0];
        rut=rut.replace(".", "");
        int busca=Integer.parseInt(rut);
        for(int x=0;x<aca.size();x++){
            if(aca.get(x).fin>busca){
                aca.get(x).columna.add(aca.get(x).columna.size(),a);
                return true;
            }
        }
        return false;
        
    }
    
    
    //ORDENAMIENTO
    public static ArrayList<pila> ordenar(ArrayList<pila> pilas){
        pila[] array=new pila[pilas.size()];
        for(int x=0;x<pilas.size();x++){
           array[x]=pilas.get(x);
        }
        for(int i=0; i<array.length;i++){
            for(int j=i; j<array.length;j++){
                if(array[i].inicio>array[j].inicio){
                    pila aux=array[i];
                    array[i]=array[j];
                    array[j]=aux;
                }
            }
        }
        
        for(int x=0;x<array.length;x++){
            pilas.add(x,array[x]);
        }
        
        return pilas;
    }
    //IMPRIMIR LAS COLAS
    public static void mostrar(ArrayList<pila> pilas){
        String texto="";
        for(int x=0;x<pilas.size();x++){
            System.out.print(pilas.get(x).inicio+"-"+pilas.get(x).fin);
            for(int y=0;y<pilas.get(x).columna.size();y++){
                System.out.print("-->"+pilas.get(x).columna.get(y).nombre);
                
            }
            System.out.println();
        }
    }
    
    //ENCONTRAR UN RANGO DETERMINADO
    public static int encontrarRango(String rut){
        String [] aux=rut.split("-");
        rut=aux[0];
        rut=rut.replace(".", "");
        int resulta=(Integer.parseInt(rut)/1000000);
        resulta=(resulta*1000000)+999999;
        return resulta;
    
    }
}
