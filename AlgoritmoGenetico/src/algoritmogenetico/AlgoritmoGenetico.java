/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import modeloNecesario.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Eduardo
 */
public class AlgoritmoGenetico {

    //variables principales
    public ArrayList<Camion> camiones;
    public ArrayList<Pedido> pedidos;
    ArrayList<Cromosoma> poblacion;
    Cromosoma mejorCromosoma;

    //variables auxiliares
    ArrayList<Pedido> pedidosConPrioridad;
    ArrayList<Pedido> pedidosSinPrioridad;
    int fase;
    int k;
    int turno;

    public static Mapa mapa;

    Cromosoma cromosoma;

    
    // constructor
    public AlgoritmoGenetico(ArrayList<Camion> listaCamiones, ArrayList<Pedido> listaPedidos,
            int nuevaFase, Mapa nuevoMapa) {
        
        poblacion = new ArrayList<Cromosoma>();
        camiones = listaCamiones;
        pedidos = listaPedidos;
        mapa = nuevoMapa;

        pedidosConPrioridad = new ArrayList<Pedido>();
        pedidosSinPrioridad = new ArrayList<Pedido>();
        
        fase = nuevaFase;
        k = 0;

        //obteniendo turno
        Calendar calAux = Calendar.getInstance();
        
        //int horAux = calAux.get(Calendar.HOUR_OF_DAY);//devuelve la hora actual en formato de 24h
        int horAux= 18;

        if (horAux < 8) {
            turno = 1;
        } else if (horAux < 16) {
            turno = 2;
        } else {
            turno = 3;
        }
    /*     
        //priorizando pedidos por turno
        ArrayList<Pedido> aux = (ArrayList<Pedido>) pedidos.clone();

       while (aux.size() > 0) {
            if ((aux.get(0).getIdCliente().getTipoDocumento().equalsIgnoreCase("DNI") && turno == 1) || (aux.get(0).getIdCliente().getTipoDocumento().equalsIgnoreCase("RUC") && turno == 3)) {
                pedidosConPrioridad.add(aux.get(0));
            } else {
                pedidosSinPrioridad.add(aux.get(0));
            }

            aux.remove(0);
        }
            */
    }
    
     public Cromosoma empieza() throws FileNotFoundException, ParseException {

        //while (k < 5) {
        if (fase == 1) {
            generaCromosomasAleatorio(Constantes.cantPoblacion);
        } else {
            generaCromosomasAleatorio(Constantes.cantPoblacion - poblacion.size());
        }
       
        seleccionaElite();
        //emparejaPoblacion();
        //mutaPoblacion();
        //System.out.println(poblacion.size());
        eliminaAberraciones();
        fase++;
        //}
        //System.out.println(poblacion.size());
        return poblacion.get(0); //solucion optima
    }

     //crea n nuevos cromosomas de manera aleatoria
    private void generaCromosomasAleatorio(int cantRequerida){
        int cantActual = 0;
        
        while(cantActual < cantRequerida){
            Cromosoma cromosoma = new Cromosoma();
            anhadeCamiones(cromosoma);
            anhadeEntregas();
            //Cromosoma cromosoma = generarCromosoma();
            if(cromosoma.esAberracion){
                poblacion.add(cromosoma);
                cantActual++;
            }
        }
    }
    
    // Crea las cadenas para el cromosoma : la lista de ruta vacia para la solucion
    // Le asigna a cada ruta un camion al azar
    private void anhadeCamiones(Cromosoma cromosoma) {
                
        ArrayList<Camion> listaCamiones = (ArrayList<Camion>) camiones.clone(); //se copian valores
        int cantCamiones = listaCamiones.size();
        int indiceCamion;
                
        while (cantCamiones > 0) {
            indiceCamion = generaNumRandom(0, cantCamiones - 1);
            Ruta ruta = new Ruta(listaCamiones.get(indiceCamion));
            cromosoma.cadena.add(ruta);
            listaCamiones.remove(indiceCamion);
            cantCamiones--;
        }
    }
    
    private void anhadeEntregas() {
        
        
        /*
        ArrayList<Pedido> auxConPrioridad = (ArrayList<Pedido>) pedidosConPrioridad.clone(); //se copian valores del array
        ArrayList<Pedido> auxSinPrioridad = (ArrayList<Pedido>) pedidosSinPrioridad.clone();

        int s = camiones.size();

        while (auxConPrioridad.size() > 0) {

            double cantPedida = auxConPrioridad.get(0).getCantGLP();
            Date horaSol = auxConPrioridad.get(0).getHoraSolicitada();

            int n = generaNumRandom(0, s - 1); //camion aleatorio
            double espacio = espacioCamion(n); //espacio que queda en el camion
            int nuevoX = auxConPrioridad.get(0).getIdCliente().getIdNodo().getCoordX();
            int nuevoY = auxConPrioridad.get(0).getIdCliente().getIdNodo().getCoordY();

            if (espacio >= cantPedida) {
                Pedido nuevaE = new Pedido(nuevoX, nuevoY, cantPedida, horaSol, true);
                cromosoma.cadena.get(n).lstEntrega.add(nuevaE);
                auxConPrioridad.remove(0); //ya se atendio
            }

        }

        while (auxSinPrioridad.size() > 0) {

            double cantPedida = auxSinPrioridad.get(0).getCantGLP();
            Date horaSol = auxSinPrioridad.get(0).getHoraSolicitada();

            int n = generaNumRandom(0, s - 1); //camion aleatorio
            double espacio = espacioCamion(n); //espacio que queda en el camion
            int nuevoX = auxSinPrioridad.get(0).getIdCliente().getIdNodo().getCoordX();
            int nuevoY = auxSinPrioridad.get(0).getIdCliente().getIdNodo().getCoordY();

            if (espacio >= cantPedida) { //si hay espacio
                Pedido nuevaE = new Pedido(nuevoX, nuevoY, cantPedida, horaSol, false);
                cromosoma.cadena.get(n).lstEntrega.add(nuevaE);
                auxSinPrioridad.remove(0); //ya se atendio
            }
            else{
                Pedido nuevaE = new Pedido(nuevoX, nuevoY, espacio, horaSol, false);
                cromosoma.cadena.get(n).lstEntrega.add(nuevaE);
                auxSinPrioridad.get(0).setCantGLP(cantPedida-espacio); //aun no se atiende
            }

        }
        */
        
        ArrayList<Pedido> listaPedidos = (ArrayList<Pedido>) pedidos.clone(); 
        int cantPedidos = listaPedidos.size();
        int cantCamiones = camiones.size();
        int pedidoAleatorio, camionAleatorio; // = generaNumRandom(0, listaPedidos.size() - 1);
        Pedido pedido;
        
        while (cantPedidos > 0 && hayEspacio(listaPedidos)) {
           
            camionAleatorio = generaNumRandom(0, cantCamiones - 1); //camion aleatorio
            
            while(listaPedidos.size() > 0 ){
                //System.out.println("Faltan " + aux.size());
                pedidoAleatorio = generaNumRandom(0, listaPedidos.size() - 1);
                pedido = listaPedidos.get(pedidoAleatorio);
                
                //System.out.println("tengo el "+ aux.get(nPedido).getIdPedido() + " con GLP "+ aux.get(nPedido).getCantGLP() + " camion "+n );
                double cantPedida = pedido.getCantGLP();
                Date horaSol = pedido.getHoraSolicitada();
                
                double espacio = espacioCamion(camionAleatorio); //espacio que queda en el camion
                int nuevoX = pedido.getCliente().getDireccion().getCoordX();
                int nuevoY = pedido.getCliente().getDireccion().getCoordY();

                if (espacio >= cantPedida && cromosoma.alcanzaCombustible( cromosoma.cadena.get(camionAleatorio) )) {
                    boolean prioridad=((pedido.getCliente().getTipoDocumento().equalsIgnoreCase("DNI") && turno == 1) || (listaPedidos.get(pedidoAleatorio).getCliente().getTipoDocumento().equalsIgnoreCase("RUC") && turno == 3)) ;
                    Pedido nuevaE = new Pedido(pedido.getIdPedido(),nuevoX, nuevoY, cantPedida, horaSol, prioridad);
                    cromosoma.cadena.get(camionAleatorio).getListaPedido().add(nuevaE);
                    listaPedidos.remove(pedidoAleatorio); //ya se atendio
                } else break;
            }
        }
    }
    
    //funciones auxiliares
    private int generaNumRandom(int min, int max) {

        int numRandom = ThreadLocalRandom.current().nextInt(min, max + 1);
        return numRandom;
    }  

    private double espacioCamion(int i) {
        double carga = cromosoma.obtenCarga(cromosoma.cadena.get(i).getListaPedido());
        double capac = cromosoma.cadena.get(i).getCamion().getTipoCamion().getCapacidadGLP();

        return capac - carga;
    }

    public boolean hayEspacio(ArrayList<Pedido> pedidos){
        
        int cantCamiones= camiones.size();
        int cantPedidos=pedidos.size();
        
        for(int i=0;i<cantCamiones;i++){
            for(int j=0;j<cantPedidos;j++){               
                if(espacioCamion(i)>=pedidos.get(j).getCantGLP()){
                  //  System.out.println(" hay espacio "+pedidos.get(j).getIdPedido() + " con glp " +pedidos.get(j).getCantGLP()+ " camion " + j);
                    return true;
                }            
            }
        }
        return false;    
    }
    
    public boolean hayEspacioCamion(ArrayList<Pedido> pedidos, int indiceCamion){
        int cantCamiones= camiones.size();
        int cantPedidos=pedidos.size();
        
        for(int j=0;j<cantPedidos;j++){

            if(espacioCamion(indiceCamion)>=pedidos.get(j).getCantGLP()){
                
                return true;
            }
        }
        return false;    
    }
    
    //para ordenar los cromosomas segun su costo
    private void ordenaPoblacion() {
        Collections.sort(poblacion, new Comparator<Cromosoma>() {
            @Override
            public int compare(Cromosoma o1, Cromosoma o2) {
                if (o1.costo > o2.costo) {
                    return 1; //para ordenar de manera ascendente
                } else if (o1.costo == o2.costo) {
                    return 0;
                } else {
                    return -1;
                }
            }

        });
    }

    //se copia cada atributo del cromosoma al mejorCromosoma
    private void guardaMejor(Cromosoma c) throws FileNotFoundException {
        mejorCromosoma = new Cromosoma(mapa);

        mejorCromosoma.costo = c.costo;
        //mejorCromosoma.mapa = c.mapa;
        mejorCromosoma.esAberracion = c.esAberracion;
        mejorCromosoma.cadena = (ArrayList<Ruta>) c.cadena.clone();
    }

    private void calculaCostos() {
        int i = 0;
        /*
        while (i < poblacion.size()) {
            if (poblacion.get(i).costo == 0) {
                
                if (poblacion.get(i).calculaCosto()) {
                    i++;
                } else {
                    poblacion.remove(i);
                }
            } else {
                i++;
            }
        }
        */
        
        while (i < poblacion.size()) {
            if (poblacion.get(i).costo == 0) {
               
                if (!poblacion.get(i).verificaAberracion()) {
                    i++;
                } else {
                    poblacion.remove(i);
                }
            } else {
                i++;
            }
        }
    }

//me quedo con un porcentaje mejor de la poblacion
    private void seleccionaElite() throws FileNotFoundException {
        calculaCostos(); // poblacion sin aberraciones
        ordenaPoblacion(); // poblacion ordenada sin aberraciones
        
        int poblacionFinal = (int) (Constantes.cantPoblacion * Constantes.probSeleccion);
        // como estan ordenados remuevo los ultimos ya que son los peores
        for (int i = 1; i <= poblacionFinal; i++) {
            poblacion.remove(poblacion.size() - 1);
        }

        //se verifica si la mejor solucion es igual que la anterior
        if (fase != 1) {
            if (poblacion.get(0).costo < mejorCromosoma.costo) {
                guardaMejor(poblacion.get(0));
                k = 0;
            } else if (poblacion.get(0).costo == mejorCromosoma.costo) {
                k++;
            } else {
                k = 0;
            }
        } else {
            guardaMejor(poblacion.get(0)); //al principio si o si guarda el primer valor como el mejor
        }
    }

    private ArrayList<Cromosoma> intercambiaRutas(Cromosoma crom1, Cromosoma crom2) throws FileNotFoundException {
        
        ArrayList<Cromosoma> hijos = new ArrayList<Cromosoma>();
        Cromosoma hijo1 = new Cromosoma();
        Cromosoma hijo2 = new Cromosoma();
        
        ArrayList<Ruta> rutasCrom1 = (ArrayList<Ruta>) crom1.cadena.clone();
        ArrayList<Ruta> rutasCrom2 = (ArrayList<Ruta>) crom2.cadena.clone();

        for (int i = 0; i < camiones.size(); i++) {
            int par= i%2;
            ArrayList<Pedido> aux = new ArrayList<Pedido>();
            if(par==0){
                aux = (ArrayList<Pedido>) rutasCrom1.get(i).getListaPedido().clone();
                rutasCrom1.get(i).setListaPedido(aux);
            }
            else {
                aux = (ArrayList<Pedido>) rutasCrom1.get(i).getListaPedido().clone();
                rutasCrom2.get(i).setListaPedido(aux);
            }            
        }
        
        /*
            for (int i = 0; i < camiones.size(); i++) {
            int par= i%2;
            
            ArrayList<Entrega> aux = (ArrayList<Entrega>) aux1.get(i).lstEntrega.clone();
            // hijo 1
            aux1.get(i).lstEntrega = (ArrayList<Entrega>) aux2.get(i).lstEntrega.clone();
            // hijo 2
            aux2.get(i).lstEntrega = (ArrayList<Entrega>) aux.clone();
        }

        */

        hijo1.cadena = (ArrayList<Ruta>) rutasCrom1.clone();
        hijo2.cadena = (ArrayList<Ruta>) rutasCrom2.clone();

        hijos.add(hijo1);
        hijos.add(hijo2);

        return hijos;
    }

    //mezcla las rutas de los camiones
    private void emparejaPoblacion() throws FileNotFoundException {
        int indAux = 0;
        int cantAux = poblacion.size() - 1;
        boolean seguirEmparejando = true;

        while (seguirEmparejando) {
            ArrayList<Cromosoma> hijos = intercambiaRutas(poblacion.get(indAux), poblacion.get(indAux + 1));
            if (hijos.get(0).verificaAberracion()) {
                poblacion.add(hijos.get(0));
            }
            if (hijos.get(1).verificaAberracion()) {
                poblacion.add(hijos.get(1));
            }

            if (indAux == cantAux || indAux == (cantAux - 1)) {
                seguirEmparejando = false;
            } else {
                indAux += 2;
            }

        }
    }

    private void intercambiaAleatorio(int indice) {
        Cromosoma c = poblacion.get(indice);
        int n1 = generaNumRandom(0, c.cadena.size() - 1);
        int n2 = generaNumRandom(0, c.cadena.size() - 1);

        ArrayList<Pedido> e1 = (ArrayList<Pedido>) poblacion.get(indice).cadena.get(n1).getListaPedido().clone();
        ArrayList<Pedido> e2 = (ArrayList<Pedido>) poblacion.get(indice).cadena.get(n2).getListaPedido().clone();

        if (n1 != n2) {
            poblacion.get(indice).cadena.get(n1).setListaPedido(e2);
            poblacion.get(indice).cadena.get(n2).setListaPedido(e1);
        }
    }

    //cambia puntos de entrega entre cada camión por cromosoma
    private void mutaPoblacion() {
        int tam = poblacion.size();
        int prob = (int) (Constantes.probMutacion * 100);
        for (int i = 0; i < tam; i++) {
            int r = generaNumRandom(0, 100);

            if (i != 0 && r <= prob) { //si muta
                intercambiaAleatorio(i);
            }
        }
    }

    private void eliminaAberraciones() throws ParseException {
        int i = 0;

        while (i < poblacion.size()) {
            poblacion.get(i).verificaAberracion();
            if (poblacion.get(i).esAberracion) {
                poblacion.remove(i);
            } else {
                i++;
            }
        }
    }
}