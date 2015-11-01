/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetica;

import algoritmogenetico.Constantes;
import static genetica.AlgoritmoGenetico.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import modeloNecesario.*;

/**
 *
 * @author MiriamLucero
 */
public class Cromosoma {
    
    private ArrayList<Ruta> cadena; 
    private double costo;
    private boolean aberracion;

    private int distanciaTotal; // en metros
    private int cantTiempoTotal; // en minutos
    private double cantGLPTotal;    
    private double cantDieselTotal;
    private double sumatoriaTEntrega; // preguntar a Chucky q es esto

    public Cromosoma(){
        cadena = new ArrayList<Ruta>();
        costo = 0;
        aberracion = false;      
        distanciaTotal = 0;
        cantTiempoTotal = 0;
        cantGLPTotal = 0;
        cantDieselTotal = 0;
        sumatoriaTEntrega = 0;
    }
 
    private int generaNumRandom(int min, int max) {

        int numRandom = ThreadLocalRandom.current().nextInt(min, max + 1);
        return numRandom;
    }
    
    // Crea las cadenas para el cromosoma : la lista de ruta vacia para la solucion
    // Le asigna a cada ruta un camion al azar
    public void asignarCamiones() {
                
        ArrayList<Camion> listaCamiones = (ArrayList<Camion>) camiones.clone(); //se copian valores
        int cantCamiones = listaCamiones.size();
        int indiceCamion;
                
        while (cantCamiones > 0) {
            indiceCamion = generaNumRandom(0, cantCamiones - 1);
            Ruta ruta = new Ruta(listaCamiones.get(indiceCamion));
            getCadena().add(ruta);
            listaCamiones.remove(indiceCamion);
            cantCamiones--;
        }
    }
    
    // Asignar cada pedido a una ruta de la cadena del cromosoma
    // considerando la capacidad del camion de la ruta
    // considerando la hora solicitada del pedido con la hora de salida de la ruta
    // considerando la capacidad de diesel incluudo el regreso  
    public void asignarPedidos(){
    
        ArrayList<Pedido> listaPedidos = (ArrayList<Pedido>) pedidos.clone(); 
        int indicePedidoAleatorio, indiceRutaAleatoria;
        int cantPedidos = listaPedidos.size();        
        int cantRutas = getCadena().size();
        int[] listaEscogido = new int [cantRutas];
        Pedido pedidoAleatorio;  
        Ruta rutaAleatoria;
        
        while(cantPedidos > 0){
            
            indicePedidoAleatorio =  generaNumRandom(0, cantPedidos - 1);
            pedidoAleatorio = listaPedidos.get(indicePedidoAleatorio);
            boolean estaAsignado = false;
            Arrays.fill(listaEscogido, 0);
            
            // sera asignado a la primera ruta disponible que encuentre
            while(estaAsignado){
                int contador = 1;
                indiceRutaAleatoria = generaNumRandom(0, cantRutas - 1);
                while(listaEscogido[indiceRutaAleatoria]== 1){
                    if (contador == cantRutas ){ aberracion= true; break;}
                    indiceRutaAleatoria = generaNumRandom(0, cantRutas - 1);
                    contador++;                     
                } 
                if (aberracion) break;
                listaEscogido[indiceRutaAleatoria] = 1;
                
                rutaAleatoria = getCadena().get(indiceRutaAleatoria);
                estaAsignado = rutaAleatoria.agregarPedido(pedidoAleatorio);
                if(!estaAsignado && cantRutas==0) break;
            }
            if (aberracion) break;
            listaPedidos.remove(indicePedidoAleatorio);
            cantPedidos--;
        }        
    }
    
    public void condensarCromosoma(){
        
        ArrayList<Ruta> listaRutas = new ArrayList<Ruta>();
        Ruta rutaEscogida = null;
        int cantRutas = cadena.size();
        boolean verificar = false;
        
        for(int i = 0; i < cantRutas; i++){
            rutaEscogida = cadena.get(i);
            verificar = rutaEscogida.cerrarRuta();
            if (verificar){  
                distanciaTotal += rutaEscogida.getDistancia();
                cantGLPTotal += rutaEscogida.getCantGLP();
                cantDieselTotal += rutaEscogida.getCantDiesel();
                cantTiempoTotal += rutaEscogida.getDistancia() / Constantes.velCamion;
                listaRutas.add(rutaEscogida);
            }
        }
        // q es cada parametro para calcular costo
        costo += ((cantGLPTotal * distanciaTotal) / (1 )); //FO
        
        cadena = listaRutas;
    }
    
    public void mutar() {
        
        int n1 = generaNumRandom(0, cadena.size() - 1);
        int n2 = generaNumRandom(0, cadena.size() - 1);

        ArrayList<Pedido> e1 = (ArrayList<Pedido>) cadena.get(n1).getListaPedido().clone();
        ArrayList<Pedido> e2 = (ArrayList<Pedido>) cadena.get(n2).getListaPedido().clone();

        if (n1 != n2) {
            cadena.get(n1).setListaPedido(e2);
            cadena.get(n2).setListaPedido(e1);
        }
    }
    
    
    /**
     * @param cadena the cadena to set
     */
    public void setCadena(ArrayList<Ruta> cadena) {
        this.cadena = cadena;
        // se debe calcular todo
    }
    
    /*
    * ------------------------- GET AND SET----------------------------------
    */

    /**
     * @return the cadena
     */
    public ArrayList<Ruta> getCadena() {
        return cadena;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the esAberracion
     */
    public boolean isAberracion() {
        return aberracion;
    }

    /**
     * @param esAberracion the esAberracion to set
     */
    public void setAberracion(boolean esAberracion) {
        this.aberracion = esAberracion;
    }

    /**
     * @return the distanciaTotal
     */
    public int getDistanciaTotal() {
        return distanciaTotal;
    }

    /**
     * @param distanciaTotal the distanciaTotal to set
     */
    public void setDistanciaTotal(int distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    /**
     * @return the cantTiempoTotal
     */
    public int getCantTiempoTotal() {
        return cantTiempoTotal;
    }

    /**
     * @param cantTiempoTotal the cantTiempoTotal to set
     */
    public void setCantTiempoTotal(int cantTiempoTotal) {
        this.cantTiempoTotal = cantTiempoTotal;
    }

    /**
     * @return the cantGLPTotal
     */
    public double getCantGLPTotal() {
        return cantGLPTotal;
    }

    /**
     * @param cantGLPTotal the cantGLPTotal to set
     */
    public void setCantGLPTotal(double cantGLPTotal) {
        this.cantGLPTotal = cantGLPTotal;
    }

    /**
     * @return the cantDieselTotal
     */
    public double getCantDieselTotal() {
        return cantDieselTotal;
    }

    /**
     * @param cantDieselTotal the cantDieselTotal to set
     */
    public void setCantDieselTotal(double cantDieselTotal) {
        this.cantDieselTotal = cantDieselTotal;
    }

    /**
     * @return the sumatoriaTEntrega
     */
    public double getSumatoriaTEntrega() {
        return sumatoriaTEntrega;
    }

    /**
     * @param sumatoriaTEntrega the sumatoriaTEntrega to set
     */
    public void setSumatoriaTEntrega(double sumatoriaTEntrega) {
        this.sumatoriaTEntrega = sumatoriaTEntrega;
    }
    
    
    
}
