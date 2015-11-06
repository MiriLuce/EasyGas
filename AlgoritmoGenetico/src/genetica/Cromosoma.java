/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetica;

import algoritmogenetico.Constantes;
import static genetica.AlgoritmoGenetico.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
 
    public Cromosoma(Cromosoma crom){
        int cantCadena = crom.cadena.size();
        cadena =  new ArrayList<Ruta>();
        for(int i= 0; i< cantCadena; i++){
            Ruta ruta = new Ruta(crom.cadena.get(i));
            cadena.add(ruta);
        }
        costo = crom.getCosto();
        aberracion = crom.isAberracion();      
        distanciaTotal = crom.getDistanciaTotal();
        cantTiempoTotal = crom.getCantTiempoTotal();
        cantGLPTotal = crom.getCantGLPTotal();
        cantDieselTotal = crom.getCantDieselTotal();
        sumatoriaTEntrega = crom.getSumatoriaTEntrega();
    }
    
    public void guardarEnMapa(){
        int cantCadena = cadena.size();
        for(int i= 0; i< cantCadena; i++){
            ArrayList<Arista> camino = new ArrayList();
            Ruta ruta =cadena.get(i);
            for(int j=0; j< ruta.getListaPedido().size(); j++){
                Arista tramo = new Arista();
                Nodo inicio, fin;
                if(j == 0){
                    inicio = new Nodo(Constantes.posCentralX, Constantes.posCentralY, true);
                    fin = new Nodo(ruta.getListaPedido().get(i).getPosX(), 
                            ruta.getListaPedido().get(i).getPosY(), true);
                }
                else if(j == ruta.getListaPedido().size()-1){
                    
                }
            }
        }
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
    public void asignarPedidos(ArrayList<Pedido> pedidos){
    
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
            while(!estaAsignado){
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
                estaAsignado = rutaAleatoria.agregarPedido(camiones, pedidoAleatorio);
                if(!estaAsignado && cantRutas==0) break;
            }
            if (aberracion) break;
            listaPedidos.remove(indicePedidoAleatorio);
            cantPedidos--;
        }        
    }
    
    public void generar(ArrayList<Pedido> pedidos, ArrayList<Camion> camiones){
    
        ArrayList<Pedido> listaPedidos = (ArrayList<Pedido>) pedidos.clone(); 
        ArrayList<Camion> listaCamiones = (ArrayList<Camion>) camiones.clone();        
        int indicePedidoAleatorio, indiceRutaAleatoria;
        int cantPedidos = listaPedidos.size(), cantRutas = cadena.size();;  
        Pedido pedidoAleatorio;  
        Ruta rutaAleatoria;
        
        while(cantPedidos > 0){
            
            indicePedidoAleatorio =  generaNumRandom(0, cantPedidos - 1);
            pedidoAleatorio = listaPedidos.get(indicePedidoAleatorio);
            boolean estaAsignado = false;
            int[] listaEscogido = new int [cantRutas];
            Arrays.fill(listaEscogido, 0);
            
            // Intento incrustar el pedido en la lista de rutas existente 
            // sera asignado a la primera ruta disponible que encuentre
            while(!estaAsignado){
                if (cantRutas == 0)break;
                else{
                    int contador = 1;
                    indiceRutaAleatoria = generaNumRandom(0, cantRutas - 1);
                    while(listaEscogido[indiceRutaAleatoria]== 1){
                        if (contador == cantRutas ) break; // ya todas estan escogidas
                        indiceRutaAleatoria = generaNumRandom(0, cantRutas - 1);
                        contador++;                     
                    } 
                    if (listaEscogido[indiceRutaAleatoria]== 1) break;
                    listaEscogido[indiceRutaAleatoria] = 1;

                    rutaAleatoria = getCadena().get(indiceRutaAleatoria);
                    estaAsignado = rutaAleatoria.agregarPedido(camiones, pedidoAleatorio);
                }
            }
            if(!estaAsignado){ // Crear una nueva ruta
                rutaAleatoria = new Ruta();
                rutaAleatoria.agregarPedido(camiones, pedidoAleatorio);
                cadena.add(rutaAleatoria);
                cantRutas++;
            }            
            
            listaPedidos.remove(indicePedidoAleatorio);
            cantPedidos--;
        }        
    }
     
    public void intercambiarPedidos(Ruta ruta){
        int cantPedidosHijo = 0;
        for (int i= 0; i< cadena.size(); i++)
                cantPedidosHijo += cadena.get(i).getListaPedido().size();
            
        Ruta tmpRuta = new Ruta(ruta);
        ArrayList<Pedido> listaRuta = (ArrayList<Pedido>) ruta.getListaPedido().clone();
        int cantPedidoRuta = listaRuta.size();
        int cantCadena = this.cadena.size();
        int indiceCadenaRuta = 0, indicePedidos, indicePedRuta;
        boolean verificar = true;
        
        String[] arrRutas = new String[cadena.size()];
        for (int i= 0; i< cadena.size(); i++){
            arrRutas[i] = "";
            for (int j= 0; j< cadena.get(i).getListaPedido().size(); j++)
                arrRutas[i] = arrRutas[i] + "-" + cadena.get(i).getListaPedido().get(j).getIdPedido();
        }
        String pedEliminar = "" ;
        for (int i= 0; i< cantPedidoRuta; i++){
            pedEliminar = pedEliminar +"-" + listaRuta.get(i).getIdPedido();
        }
        
        // Se eliminan todos los pedidos de las rutas
        while(indiceCadenaRuta < cantCadena){
            int cantPedidos = this.cadena.get(indiceCadenaRuta).getListaPedido().size();
            indicePedidos = 0;
            while(indicePedidos < cantPedidos){ 
                indicePedRuta = 0;
                while(indicePedRuta <cantPedidoRuta){
                    int indicePedido = this.cadena.get(indiceCadenaRuta).getListaPedido().get(indicePedidos).getIdPedido();
                    if(indicePedido == listaRuta.get(indicePedRuta).getIdPedido()){
                        verificar = this.cadena.get(indiceCadenaRuta).quitarPedido(indicePedidos); 
                        cantPedidosHijo = 0;
                        for (int i= 0; i< cadena.size(); i++)
                                cantPedidosHijo += cadena.get(i).getListaPedido().size();
                        
                        // Si la ruta queda vacia se elimina
                        if(this.cadena.get(indiceCadenaRuta).getListaPedido().isEmpty()){
                            this.cadena.remove(indiceCadenaRuta);
                            cantCadena--;
                            if (indiceCadenaRuta != cantCadena -1)indiceCadenaRuta--;
                        }
                        arrRutas = new String[cadena.size()];
                        for (int i= 0; i< cadena.size(); i++){
                            for (int j= 0; j< cadena.get(i).getListaPedido().size(); j++)
                                arrRutas[i] = arrRutas[i] + "-" + cadena.get(i).getListaPedido().get(j).getIdPedido();
                        }
                        if (indicePedidos != cantPedidos -1)indicePedidos--;
                        if (indicePedRuta != cantPedidoRuta -1)indicePedRuta--;
                        cantPedidoRuta--;
                        cantPedidos--;
                        listaRuta.remove(indicePedRuta);
                       if(!verificar)break;
                    }
                    indicePedRuta++;
                }
                indicePedidos++;
                if(!verificar || cantPedidoRuta == 0)break;                
            }
            indiceCadenaRuta++;
            if(!verificar  || cantPedidoRuta == 0)break;            
        }
        aberracion = !verificar;        
        this.cadena.add(tmpRuta);
        cantPedidosHijo = 0;
        for (int i= 0; i< cadena.size(); i++)
                cantPedidosHijo += cadena.get(i).getListaPedido().size();
        
    }
    
    public void agregarRuta(Ruta ruta){
        /*
        int cantPedidosHijo = 0;
        for (int i= 0; i< cadena.size(); i++)
                cantPedidosHijo += cadena.get(i).getListaPedido().size();
        if(cantPedidosHijoA != cantPedidosHijo){
            //System.out.println("error");
        }*/
        this.imprimir();
        System.out.println("Ruta: ");
        ruta.imprimir();
        Ruta tmpRuta = new Ruta(ruta);
        ArrayList<Pedido> listaRuta = (ArrayList<Pedido>) ruta.getListaPedido().clone();
        int cantPedidoRuta = listaRuta.size();
        int cantCadena = this.cadena.size(), cantPedidos;
        
        int indiceCadenaRuta = 0, indicePedidos, indicePedRuta;
        boolean verificar = false;
        int exito;
        /*
        String[] arrRutas = new String[cadena.size()];
        for (int i= 0; i< cadena.size(); i++){
            arrRutas[i] = "";
            for (int j= 0; j< cadena.get(i).getListaPedido().size(); j++)
                arrRutas[i] = arrRutas[i] + "-" + cadena.get(i).getListaPedido().get(j).getIdPedido();
        }
        String pedEliminar = "" ;
        for (int i= 0; i< cantPedidoRuta; i++){
            pedEliminar = pedEliminar +"-" + listaRuta.get(i).getIdPedido();
        }
        */
        // Se eliminan todos los pedidos de las rutas
        while(indiceCadenaRuta < cantCadena){
            cantPedidos = this.cadena.get(indiceCadenaRuta).getListaPedido().size();
            indicePedidos = 0;
            
            while(indicePedidos < cantPedidos){ 
                indicePedRuta = 0;
                
                while(indicePedRuta <cantPedidoRuta){
                    exito = quitarPedido(indiceCadenaRuta, indicePedidos, 
                            this.cadena.get(indiceCadenaRuta).getListaPedido().get(indicePedidos).getIdPedido(),
                            listaRuta.get(indicePedRuta).getIdPedido());
                    
                    if (exito == 1) {
                        // verificar si se elimino una ruta de la cadena
                        if (this.cadena.size() != cantCadena) cantCadena--;
                        if (indicePedidos != cantPedidos -1) indicePedidos--;
                        verificar = true;
                        cantPedidos--;
                        cantPedidoRuta--;
                        listaRuta.remove(indicePedRuta);   
                    }
                    else if (exito == -1) aberracion = true;  
                    indicePedRuta++;
                    if(verificar) break; // se elimino el pedido
                }
                indicePedidos++;
                if(aberracion || cantPedidoRuta == 0) break;                
            }
            indiceCadenaRuta++;
            if(aberracion  || cantPedidoRuta == 0) break;           
            /*for (int i= 0; i< cadena.size(); i++){
            arrRutas[i] = "";
            for (int j= 0; j< cadena.get(i).getListaPedido().size(); j++)
                arrRutas[i] = arrRutas[i] + "-" + cadena.get(i).getListaPedido().get(j).getIdPedido();
            }*/
        }       
        this.cadena.add(tmpRuta);      
        this.imprimir();
        /*for (int i= 0; i< cadena.size(); i++){
            arrRutas[i] = "";
            for (int j= 0; j< cadena.get(i).getListaPedido().size(); j++)
                arrRutas[i] = arrRutas[i] + "-" + cadena.get(i).getListaPedido().get(j).getIdPedido();
        }*/
        //this.cadena = (ArrayList<Ruta>)this.cadena.clone();
        /*
        cantPedidosHijo=0;
        for (int i= 0; i< cadena.size(); i++)
                cantPedidosHijo += cadena.get(i).getListaPedido().size();
        */
    }
    
    private int  quitarPedido(int indiceRuta, int indicePedido, int idPedido, int idEliminar){
        
        if (idPedido ==  idEliminar){
            boolean verificar = this.cadena.get(indiceRuta).quitarPedido(indicePedido);
            if (verificar){ // true: se quito con exito
                // Si la ruta queda vacia se elimina
                if(this.cadena.get(indiceRuta).getListaPedido().isEmpty())
                    this.cadena.remove(indiceRuta);
                return 1; // 1: exito
            }
            else return -1; // -1: es una aberracion
        }
        else return 0; // 0: no paso nada
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
    
    public void imprimir() {
        System.out.println("--------------------------------------------------");
        System.out.println("Costo: " + costo);
        for (int i= 0; i< cadena.size(); i++){
            System.out.print("Nro Ruta: " + i);
            cadena.get(i).imprimir();
        }
    }
    
    public void mutar() {
        
        int n1 = generaNumRandom(0, cadena.size() - 1);
        int n2 = generaNumRandom(0, cadena.size() - 1);

        ArrayList<Pedido> e1 = (ArrayList<Pedido>) cadena.get(n1).getListaPedido().clone();
        ArrayList<Pedido> e2 = (ArrayList<Pedido>) cadena.get(n2).getListaPedido().clone();

        if (n1 != n2) {
            this.asignarPedidos(e1);
            this.asignarPedidos(e2);
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
