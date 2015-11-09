/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetica;

import algoritmogenetico.Constantes;
import algoritmogenetico.Mapa;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import modeloNecesario.*;

/**
 *
 * @author MiriamLucero
 */
public class AlgoritmoGenetico {
    
    private ArrayList<Cromosoma> poblacion;
   
    private ArrayList<Pedido> pedidosConPrioridad;
    private ArrayList<Pedido> pedidosSinPrioridad;
    
    private Cromosoma mejorCromosoma;
    private int cantRepiteMejor;
    public static Mapa mapa;
    public static ArrayList<Camion> camiones;
    public static ArrayList<Pedido> pedidos;
    
    public AlgoritmoGenetico(ArrayList<Camion> listaCamiones, ArrayList<Pedido> listaPedidos,
            int nuevaFase, Mapa nuevoMapa) {
        
        poblacion = new ArrayList<Cromosoma>();
        pedidosConPrioridad = new ArrayList<Pedido>();
        pedidosSinPrioridad = new ArrayList<Pedido>();
        mejorCromosoma = new Cromosoma();
        mejorCromosoma.setCosto(-1);
        camiones = listaCamiones;
        pedidos = listaPedidos;
        mapa = nuevoMapa;      
    }
    
    public Cromosoma empieza(){

        int cant = 0;
        while(cant < 10){
            generaCromosomasAleatorio(Constantes.cantPoblacion - poblacion.size());
            seleccionaElite();
            emparejaPoblacion();
            eliminaAberraciones();
            mutaPoblacion();
            eliminaAberraciones();
            cant++;
        }
        
        return mejorCromosoma;
    }
    
    //Crea n nuevos cromosomas requeridos de manera aleatoria
    private void generaCromosomasAleatorio(int cantRequerida){
        int cantActual = 0;
        
        while(cantActual < cantRequerida){
            Cromosoma cromosoma = new Cromosoma();
            // Asignar cada camion a una ruta que este incluida en la cadena del cromosoma
            //cromosoma.asignarCamiones();
            // Asignar cada pedido a una ruta y evitar aberraciones
            //cromosoma.asignarPedidos(pedidos);
            // cerrar cromosoma con 
            cromosoma.generar(pedidos, camiones);
            cromosoma.condensarCromosoma();
            //cromosoma.imprimir();
            if(!cromosoma.isAberracion()){
                poblacion.add(cromosoma);
                cantActual++;
            }
        }
        
    }
    
    //me quedo con un porcentaje mejor de la poblacion
    private void seleccionaElite(){
        
        ordenaPoblacion(); 
        
        int poblacionFinal = (int) (Constantes.cantPoblacion * Constantes.probSeleccion);
        // como estan ordenados remuevo los ultimos ya que son los peores
        for (int i = poblacionFinal; i < poblacion.size(); i++) {
            poblacion.remove(i);
        }
        
        if (mejorCromosoma.getCosto()==-1 || poblacion.get(0).getCosto() < mejorCromosoma.getCosto()) {
            mejorCromosoma = new Cromosoma(poblacion.get(0));
            cantRepiteMejor = 0; 
        }
        else if(poblacion.get(0).getCosto() == mejorCromosoma.getCosto())
            cantRepiteMejor++;
    }
    
    //para ordenar los cromosomas segun su costo
    private void ordenaPoblacion() {
        Collections.sort(poblacion, new Comparator<Cromosoma>() {
            @Override
            public int compare(Cromosoma crom1, Cromosoma crom2) {
                if (crom1.getCosto() > crom2.getCosto()) {
                    return 1; //para ordenar de manera ascendente
                } else if (crom1.getCosto() == crom2.getCosto()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }

    //mezcla las rutas de los camiones
    private void emparejaPoblacion(){
        int indAux = 0;
        int cantAux = poblacion.size() - 1;
        boolean seguirEmparejando = true;
        //System.out.println("Empareja");
        while (seguirEmparejando) {
            ArrayList<Cromosoma> hijos = intercambiaRutas(poblacion.get(indAux), poblacion.get(indAux + 1));
            
            if (!hijos.get(0).isAberracion()){ poblacion.add(hijos.get(0)); hijos.get(0).imprimir();}
            if (!hijos.get(1).isAberracion()) { poblacion.add(hijos.get(1)); hijos.get(1).imprimir();}
            //System.out.println(indAux);
            if (indAux == cantAux || indAux == (cantAux - 1)) {
                seguirEmparejando = false;
            } else indAux += 2;
        }
    }
    
    private ArrayList<Cromosoma> intercambiaRutas(Cromosoma crom1, Cromosoma crom2) {
        
        ArrayList<Cromosoma> hijos = new ArrayList<Cromosoma>();
        Cromosoma hijo1 = new Cromosoma(crom1);
        Cromosoma hijo2 = new Cromosoma(crom2);        
        int cantHijo1 = hijo1.getCadena().size();
        int cantHijo2 = hijo2.getCadena().size();
        
        int cant = cantHijo1 < cantHijo2 ? cantHijo1 : cantHijo2;
        for (int i = 0; i < cant; i++) {
            int par = i%2;
            if(par == 0){ 
                hijo1.agregarRuta(hijo2.getCadena().get(i));                
                if (hijo1.isAberracion()) break; 
            }
            else{ 
                hijo2.agregarRuta(hijo1.getCadena().get(i));               
                if (hijo2.isAberracion()) break;
            }   
            
            // Se puede cambiar la cantidad de rutas
            cantHijo1 = hijo1.getCadena().size();
            cantHijo2 = hijo2.getCadena().size();
            cant = cantHijo1 < cantHijo2 ? cantHijo1 : cantHijo2;
        }
        
        if (!hijo1.isAberracion()) hijo1.condensarCromosoma();
        if (!hijo2.isAberracion()) hijo2.condensarCromosoma();
        hijos.add(hijo1);
        hijos.add(hijo2);
        
        return hijos;
    }

    private int generaNumRandom(int min, int max) {

        int numRandom = ThreadLocalRandom.current().nextInt(min, max + 1);
        return numRandom;
    }
    
    //cambia puntos de entrega entre cada ruta por cromosoma
    private void mutaPoblacion() {
        int tam = poblacion.size();
        int prob = (int) (Constantes.probMutacion * tam);
        for (int i = 0; i < tam; i++) {
            int r = generaNumRandom(0, tam - 1);

            if (i != 0 && r <= prob) { 
                poblacion.get(r).mutar();
                int cantPedido = 0, cantCad = poblacion.get(r).getCadena().size();
                for (int k = 0; k< cantCad; k++)
                    cantPedido += poblacion.get(r).getCadena().get(k).getListaPedido().size();
                if (cantPedido != pedidos.size()) poblacion.get(r).setAberracion(true);
                                
            }
        }
    }
    
    private void eliminaAberraciones(){
        int i = 0;

        while (i < poblacion.size()) {
            if (poblacion.get(i).isAberracion()) {
                poblacion.remove(i);
            } else {
                i++;
            }
        }
    }
}
