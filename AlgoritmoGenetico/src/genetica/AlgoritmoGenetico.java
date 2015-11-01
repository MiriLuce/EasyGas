/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetica;

import algoritmogenetico.Constantes;
import algoritmogenetico.Mapa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        
        camiones = listaCamiones;
        pedidos = listaPedidos;
        mapa = nuevoMapa;        
    }
    
    public Cromosoma empieza(){

        generaCromosomasAleatorio(Constantes.cantPoblacion - poblacion.size());
        seleccionaElite();
        emparejaPoblacion();
        //eliminaAberraciones();
        
        return mejorCromosoma;
    }
    
    //Crea n nuevos cromosomas requeridos de manera aleatoria
    private void generaCromosomasAleatorio(int cantRequerida){
        int cantActual = 0;
        
        while(cantActual < cantRequerida){
            Cromosoma cromosoma = new Cromosoma();
            // Asignar cada camion a una ruta que este incluida en la cadena del cromosoma
            cromosoma.asignarCamiones();
            // Asignar cada pedido a una ruta y evitar aberraciones
            cromosoma.asignarPedidos();
            // cerrar cromosoma con 
            cromosoma.condensarCromosoma();
            
            if(cromosoma.isAberracion()){
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
        for (int i = 1; i <= poblacionFinal; i++) {
            poblacion.remove(poblacion.size() - 1);
        }
        
        if (poblacion.get(0).getCosto() < mejorCromosoma.getCosto()) {
            mejorCromosoma = poblacion.get(0);
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

        while (seguirEmparejando) {
            ArrayList<Cromosoma> hijos = intercambiaRutas(poblacion.get(indAux), poblacion.get(indAux + 1));
            
            if (!hijos.get(0).isAberracion()) poblacion.add(hijos.get(0));
            if (!hijos.get(1).isAberracion()) poblacion.add(hijos.get(1));
            
            if (indAux == cantAux || indAux == (cantAux - 1)) {
                seguirEmparejando = false;
            } else indAux += 2;
        }
    }
    
    private ArrayList<Cromosoma> intercambiaRutas(Cromosoma crom1, Cromosoma crom2) {
        
        ArrayList<Cromosoma> hijos = new ArrayList<Cromosoma>();
        Cromosoma hijo1 = new Cromosoma();
        Cromosoma hijo2 = new Cromosoma();
        
        ArrayList<Ruta> rutasCrom1 = (ArrayList<Ruta>) crom1.getCadena().clone();
        ArrayList<Ruta> rutasCrom2 = (ArrayList<Ruta>) crom2.getCadena().clone();
        
        // mejorar: solo intercambia las rutas entre cromosomas
        // esto puede duplicar pedidos
        // si intercambias pedidos debe considerar la hora solicitada
        for (int i = 0; i < camiones.size(); i++) {
            int par = i%2;
            ArrayList<Pedido> aux = new ArrayList<Pedido>();
            if(par == 0){
                aux = (ArrayList<Pedido>) rutasCrom2.get(i).getListaPedido().clone();
                rutasCrom1.get(i).setListaPedido(aux);
            }
            else {
                aux = (ArrayList<Pedido>) rutasCrom1.get(i).getListaPedido().clone();
                rutasCrom2.get(i).setListaPedido(aux);
            }            
        }

        hijo1.setCadena((ArrayList<Ruta>) rutasCrom1.clone());
        hijo2.setCadena((ArrayList<Ruta>) rutasCrom2.clone());

        hijos.add(hijo1);
        hijos.add(hijo2);

        return hijos;
    }

}
