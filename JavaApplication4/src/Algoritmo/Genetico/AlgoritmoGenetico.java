/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmo.Genetico;

import Modelo.Hibernate.*;
import Algoritmo.Constantes.Constantes;
import Algoritmo.Constantes.Mapa;
import Util.RelojAlgoritmo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


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
    public static ArrayList<Modelo.Hibernate.Ruta> lsrutas;
    public static ArrayList<Camion> camiones;
    public static ArrayList<Pedido> pedidos;
    
    public AlgoritmoGenetico(ArrayList<Camion> listaCamiones, ArrayList<Pedido> listaPedidos,
            int nuevaFase, Mapa nuevoMapa) throws IOException {
        
        poblacion = new ArrayList<Cromosoma>();
        pedidosConPrioridad = new ArrayList<Pedido>();
        pedidosSinPrioridad = new ArrayList<Pedido>();
        mejorCromosoma = new Cromosoma();
        mejorCromosoma.setCosto(-1);
        camiones = listaCamiones;
        pedidos = listaPedidos;
        mapa = new Mapa(201,301);
    }
    
    public ArrayList<Cromosoma> empieza(Date horaInicio){

        int cant = 0;
        while(cant < 50){
            generaCromosomasAleatorio(Constantes.cantPoblacion - poblacion.size(), horaInicio);
            seleccionaElite();
            emparejaPoblacion();
            eliminaAberraciones();
            mutaPoblacion();
            eliminaAberraciones();
            cant++;
        }
        this.ordenaPoblacion();
        ArrayList<Cromosoma> soluciones = new ArrayList<Cromosoma>();
        soluciones.add(poblacion.get(0));
        soluciones.add(poblacion.get(1));
        soluciones.add(poblacion.get(2));
        
        //poblacion.get(0).imprimir();
        return soluciones;
    }
    
    public Cromosoma recalcula(){

        int cant = 0;
        while(cant < 50){
            generaCromosomasAleatorioRecalcular(Constantes.cantPoblacion - poblacion.size());
            seleccionaElite();
            emparejaPoblacion();
            eliminaAberraciones();
            mutaPoblacion();
            eliminaAberraciones();
            cant++;
        }
        this.ordenaPoblacion();
       
        //poblacion.get(0).imprimir();
        return poblacion.get(0);
    }
    
    //Crea n nuevos cromosomas requeridos de manera aleatoria
    private void generaCromosomasAleatorio(int cantRequerida, Date horaInicio){
        int cantActual = 0;
        while(cantActual < cantRequerida){
            Cromosoma cromosoma = new Cromosoma();
            // Asignar cada camion a una ruta que este incluida en la cadena del cromosoma
            //cromosoma.asignarCamiones();
            // Asignar cada pedido a una ruta y evitar aberraciones
            //cromosoma.asignarPedidos(pedidos);
            // cerrar cromosoma con 
            cromosoma.generar(pedidos, camiones, horaInicio);
            cromosoma.condensarCromosoma();
            System.out.println("Cantidad Actual: " + cantActual + " -----------------------------");
            //cromosoma.imprimir();
            if(!cromosoma.isAberracion()){
                poblacion.add(cromosoma);
                cantActual++;
            }
        }        
    }
    
    public boolean perteneceATurno(Date hora, Turno turno) {
        //return hora.after(turno.getHoraInicio()) && hora.before(turno.getHoraFin());
        return hora.compareTo(turno.getHoraInicio()) >= 0 && hora.compareTo(turno.getHoraFin()) <= 0;
    }
    
    public Turno obtenerTurnoActual() {
        //String originalString = "2010-07-14 09:00:02";
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
        //String newString = new SimpleDateFormat("H:mm").format(date); // 9:00
        Date horaActual = RelojAlgoritmo.horaActual.getTime();
        ArrayList<Turno> lturnos = Algoritmo.Constantes.Constantes.lTurnos;
        //System.out.println(horaActual);
        for (int i = 0; i < 3; i++) {
            if (perteneceATurno(horaActual, lturnos.get(i))) {
                return lturnos.get(i);
            }
}
        return null;

    }
    private void generaCromosomasAleatorioRecalcular(int cantRequerida){
        int cantActual = 0;
        Turno t = obtenerTurnoActual();
        //unir los pedidos que aun no han sido entregados en este momento
        while(cantActual < cantRequerida){
            Cromosoma cromosoma = new Cromosoma();
            //camiones ya tiene asignada la estructura Ruta del algoritmo
            cromosoma.generarRecalcular(pedidos, camiones,lsrutas, t.getHoraInicio());
            cromosoma.condensarCromosoma();
            //System.out.println("Cantidad Actual: " + cantActual + " -----------------------------");
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
        for (int i = poblacionFinal; i < poblacion.size(); i++) poblacion.remove(i);
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
        int cantAux = poblacion.size() - 1, contador;
        boolean seguirEmparejando = true;
        
        int[] arrayPoblacion = new int[poblacion.size()];
        Arrays.fill(arrayPoblacion, 0);
        
        while (seguirEmparejando) {
            
            int indAux1 = generaNumRandom(0, cantAux);
            int indAux2 = generaNumRandom(0, cantAux);
            
            // Verifico su el cromosoma 1 no ha sido seleccionado
            contador = 0;
            while (arrayPoblacion[indAux1] == 1){                 
                indAux1 = generaNumRandom(0, cantAux);
                contador ++;
                if (contador == cantAux) break;
            }
            if (arrayPoblacion[indAux1] == 1) break;
            
            // Verifico su el cromosoma 2 no ha sido seleccionado
            contador = 0;
            while (arrayPoblacion[indAux2] == 1){                 
                indAux2 = generaNumRandom(0, cantAux);
                contador ++;
                if (contador == cantAux) break;
            }
            if (arrayPoblacion[indAux2] == 1) break;
            
            // Selecciono cromosoma 1 y 2
            arrayPoblacion[indAux1] = arrayPoblacion[indAux2] = 1;
            
            ArrayList<Cromosoma> hijos = intercambiaRutas(poblacion.get(indAux1), poblacion.get(indAux2));
            
            if (!hijos.get(0).isAberracion()) {poblacion.add(hijos.get(0)); hijos.get(0).imprimir();}
            if (!hijos.get(1).isAberracion()) {poblacion.add(hijos.get(1)); hijos.get(1).imprimir();}
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
        int cantPoblacion = poblacion.size();
        int prob = (int) (Constantes.probMutacion * cantPoblacion);
        
        for (int i = 0; i < cantPoblacion; i++) {
            int dado = generaNumRandom(0, cantPoblacion - 1);

            if (dado <= prob) { 
                //poblacion.get(i).mutar();
                poblacion.get(i).cambiarCamion();
                 poblacion.get(i).imprimir();
                if (poblacion.get(i).isAberracion()){
                    poblacion.remove(i);
                    cantPoblacion--;
                    i--;
                }       
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

    /**
     * @param pedidosConPrioridad the pedidosConPrioridad to set
     */
    public void setPedidosConPrioridad(ArrayList<Pedido> pedidosConPrioridad) {
        this.pedidosConPrioridad = pedidosConPrioridad;
    }

    /**
     * @param pedidosSinPrioridad the pedidosSinPrioridad to set
     */
    public void setPedidosSinPrioridad(ArrayList<Pedido> pedidosSinPrioridad) {
        this.pedidosSinPrioridad = pedidosSinPrioridad;
    }
}
