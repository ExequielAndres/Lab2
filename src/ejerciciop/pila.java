
package ejerciciop;

import java.util.ArrayList;

//CLASE PILA Y SUS ATRIBUTOS
public class pila {
    
    public int inicio;
    public int fin;
    public ArrayList<persona> columna;
//CONSTRUCTOR
    public pila(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.columna = new ArrayList();
    }
    
    
}
