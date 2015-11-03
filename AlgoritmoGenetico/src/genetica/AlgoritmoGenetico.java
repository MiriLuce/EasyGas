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
        while(cant < 5){
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
        for (int i = 1; i <= poblacionFinal; i++) {
            poblacion.remove(poblacion.size() - 1);
        }
        
        if (mejorCromosoma.getCosto()==-1 || poblacion.get(0).getCosto() < mejorCromosoma.getCosto()) {
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
        
        int[] cantPedidosPoblacion = new int [poblacion.size()];
            for (int i= 0; i< poblacion.size(); i++){
                cantPedidosPoblacion[i] = 0;
                for (int j= 0; j< poblacion.get(i).getCadena().size(); j++)
                    cantPedidosPoblacion[i] += poblacion.get(i).getCadena().get(j).getListaPedido().size();
            }
        int indAux = 0;
        int cantAux = poblacion.size() - 1;
        boolean seguirEmparejando = true;

        while (seguirEmparejando) {
            ArrayList<Cromosoma> hijos = intercambiaRutas(poblacion.get(indAux), poblacion.get(indAux + 1));
            
            if (!hijos.get(0).isAberracion()) poblacion.add(hijos.get(0));
            if (!hijos.get(1).isAberracion()) poblacion.add(hijos.get(1));
            
            int cantPedidosHijo1 = 0, cantPedidosHijo2 = 0;
            for (int i= 0; i< hijos.get(0).getCadena().size(); i++)
                cantPedidosHijo1 += hijos.get(0).getCadena().get(i).getListaPedido().size();
            
            for (int i= 0; i< hijos.get(1).getCadena().size(); i++)
                cantPedidosHijo2 += hijos.get(1).getCadena().get(i).getListaPedido().size();
            
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
        String[] arrRutas;
        
        int cantPedidosHijoA = 0, cantPedidosHijoD = 0;
        
        boolean verificar1 = true, verificar2 = true;
        int cant = cantHijo1 < cantHijo2 ? cantHijo1 : cantHijo2;
        for (int i = 0; i < cant; i++) {
            int par = i%2;
            if(par == 0){ 
                cantPedidosHijoA = 0;
                for (int k= 0; k< hijo1.getCadena().size(); k++)
                    cantPedidosHijoA += hijo1.getCadena().get(k).getListaPedido().size();
                if (cantPedidosHijoA != pedidos.size()) hijo1.setAberracion(true);
                else 
                    hijo1.agregarRuta(hijo2.getCadena().get(i), cantPedidosHijoA);                
                
                cantPedidosHijoD = 0;
                for (int k= 0; k< hijo1.getCadena().size(); k++)
                    cantPedidosHijoD += hijo1.getCadena().get(k).getListaPedido().size();
                
                if (cantPedidosHijoA != cantPedidosHijoD) {
                    //System.out.println("error");
                    hijo1.setAberracion(true);
                }
                if (hijo1.isAberracion()) break;
                
                arrRutas = new String[hijo1.getCadena().size()];
                for (int k= 0; k< hijo1.getCadena().size(); k++){
                    arrRutas[k] = "";
                    for (int j= 0; j< hijo1.getCadena().get(k).getListaPedido().size(); j++)
                        arrRutas[k] = arrRutas[k] + "-" + hijo1.getCadena().get(k).getListaPedido().get(j).getIdPedido();
                }
                
            }
            else{ 
                cantPedidosHijoA = 0;
                for (int k= 0; k< hijo1.getCadena().size(); k++)
                    cantPedidosHijoA += hijo1.getCadena().get(k).getListaPedido().size();
                            
                if (cantPedidosHijoA != pedidos.size()) hijo2.setAberracion(true);
                else
                    hijo2.agregarRuta(hijo1.getCadena().get(i), cantPedidosHijoA);                
                
                cantPedidosHijoD = 0;
                for (int k= 0; k< hijo2.getCadena().size(); k++)
                    cantPedidosHijoD += hijo2.getCadena().get(k).getListaPedido().size();
                
                if (cantPedidosHijoA != cantPedidosHijoD){
                    System.out.println("error");
                    hijo2.setAberracion(true);
                }
                if (hijo2.isAberracion()) break;
                
                arrRutas = new String[hijo1.getCadena().size()];
                for (int k= 0; k< hijo1.getCadena().size(); k++){
                    arrRutas[k] = "";
                    for (int j= 0; j< hijo1.getCadena().get(k).getListaPedido().size(); j++)
                        arrRutas[k] = arrRutas[k] + "-" + hijo1.getCadena().get(k).getListaPedido().get(j).getIdPedido();
                }
            }           
            arrRutas=arrRutas;
        }
        
        /*while(verificar1){
            if(hijo1.getCadena().get(0).getListaPedido().isEmpty()){
                verificar1 = false;
            }
        }*/
        hijo1.condensarCromosoma();
        hijo2.condensarCromosoma();
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
            int r = generaNumRandom(0, tam);

            if (i != 0 && r <= prob) { 
                poblacion.get(r).mutar();
                int cantPedido = 0, cantCad = poblacion.get(r).getCadena().size();
                for (int k= 0; k< cantCad; k++)
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
