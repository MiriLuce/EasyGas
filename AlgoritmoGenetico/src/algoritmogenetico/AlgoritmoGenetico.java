/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.io.FileNotFoundException;
import java.text.ParseException;
import modelo.*;
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
    ArrayList<Camion> camiones;
    ArrayList<Pedido> pedidos;
    ArrayList<Cromosoma> poblacion;
    Cromosoma mejorCromosoma;

    //variables auxiliares
    ArrayList<Pedido> pedidosConPrioridad;
    ArrayList<Pedido> pedidosSinPrioridad;
    int fase;
    int k;
    int turno;

    Mapa mapa;

    Cromosoma cromosoma;

    // constructor
    public AlgoritmoGenetico(ArrayList<Camion> lstCam, ArrayList<Pedido> lstPed, int nuevaFase, Mapa nuevoMapa) {
        camiones = lstCam;
        pedidos = lstPed;
        poblacion = new ArrayList<Cromosoma>();

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

    //funciones auxiliares
    private int generaNumRandom(int min, int max) {

        int numRandom = ThreadLocalRandom.current().nextInt(min, max + 1);
        return numRandom;
    }

    private void anhadeCamiones() {
        ArrayList<Camion> aux = (ArrayList<Camion>) camiones.clone(); //se copian valores

        while (aux.size() > 0) {
            int r = generaNumRandom(0, aux.size() - 1);
            MiRuta cadAux = new MiRuta(aux.get(r));
            cromosoma.cadena.add(cadAux);
            aux.remove(r);
        }
    }

    private double espacioCamion(int i) {
        double carga = cromosoma.obtenCarga(cromosoma.cadena.get(i).lstEntrega);
        double capac = cromosoma.cadena.get(i).camion.getIdTipoCamion().getCapacidadGLP();

        return capac - carga;
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
                Entrega nuevaE = new Entrega(nuevoX, nuevoY, cantPedida, horaSol, true);
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
                Entrega nuevaE = new Entrega(nuevoX, nuevoY, cantPedida, horaSol, false);
                cromosoma.cadena.get(n).lstEntrega.add(nuevaE);
                auxSinPrioridad.remove(0); //ya se atendio
            }
            else{
                Entrega nuevaE = new Entrega(nuevoX, nuevoY, espacio, horaSol, false);
                cromosoma.cadena.get(n).lstEntrega.add(nuevaE);
                auxSinPrioridad.get(0).setCantGLP(cantPedida-espacio); //aun no se atiende
            }

        }
        */
        ArrayList<Pedido> aux = (ArrayList<Pedido>) pedidos.clone(); 
       
       

        int s = camiones.size();
        int nPedido = generaNumRandom(0, aux.size() - 1);

        while (aux.size() > 0 && hayEspacio(aux)) {
           
            int n = generaNumRandom(0, s - 1); //camion aleatorio
            
            while(aux.size() > 0 ){
                //System.out.println("Faltan " + aux.size());
                nPedido = generaNumRandom(0, aux.size() - 1);
                //System.out.println("tengo el "+ aux.get(nPedido).getIdPedido() + " con GLP "+ aux.get(nPedido).getCantGLP() + " camion "+n );
                double cantPedida = aux.get(nPedido).getCantGLP();
                Date horaSol = aux.get(nPedido).getHoraSolicitada();
                double espacio = espacioCamion(n); //espacio que queda en el camion
                int nuevoX = aux.get(nPedido).getIdCliente().getIdNodo().getCoordX();
                int nuevoY = aux.get(nPedido).getIdCliente().getIdNodo().getCoordY();

                if (espacio >= cantPedida && cromosoma.alcanzaCombustible( cromosoma.cadena.get(n) )) {
                    boolean prioridad=((aux.get(nPedido).getIdCliente().getTipoDocumento().equalsIgnoreCase("DNI") && turno == 1) || (aux.get(nPedido).getIdCliente().getTipoDocumento().equalsIgnoreCase("RUC") && turno == 3)) ;
                    Entrega nuevaE = new Entrega(aux.get(nPedido).getIdPedido(),nuevoX, nuevoY, cantPedida, horaSol, prioridad);
                    cromosoma.cadena.get(n).lstEntrega.add(nuevaE);
                    aux.remove(nPedido); //ya se atendio
                } else break;
            }
        }

       

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
    //crea n nuevos cromosomas de manera aleatoria
    private void generaCromosomasAleatorio(int n) throws FileNotFoundException, ParseException {
        while(poblacion.size()<n){
            
            cromosoma = new Cromosoma(mapa);
            anhadeCamiones();
            anhadeEntregas();
            cromosoma.verificaAberracion();
            //System.out.println(" tamanho " +poblacion.size() + " costo " + cromosoma.costo );
            if(!cromosoma.esAberracion){
               // System.out.println("Solucion econtrada");
                poblacion.add(cromosoma);
            }
        }
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
        mejorCromosoma.mapa = c.mapa;
        mejorCromosoma.esAberracion = c.esAberracion;
        mejorCromosoma.cadena = (ArrayList<MiRuta>) c.cadena.clone();
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

    private ArrayList<Cromosoma> intercambiaRutas(Cromosoma c1, Cromosoma c2) throws FileNotFoundException {
        Cromosoma nuevo1 = new Cromosoma(mapa);
        Cromosoma nuevo2 = new Cromosoma(mapa);
        ArrayList<Cromosoma> hijos = new ArrayList<Cromosoma>();
        
        ArrayList<MiRuta> aux1 = (ArrayList<MiRuta>) c1.cadena.clone();
        ArrayList<MiRuta> aux2 = (ArrayList<MiRuta>) c2.cadena.clone();

        for (int i = 0; i < camiones.size(); i++) {
            int par= i%2;
            ArrayList<Entrega> aux = new ArrayList<Entrega>();
            if(par==0){
                aux = (ArrayList<Entrega>) aux2.get(i).lstEntrega.clone();
                aux1.get(i).lstEntrega = aux;
            }
            else {
                aux = (ArrayList<Entrega>) aux1.get(i).lstEntrega.clone();
                aux2.get(i).lstEntrega =aux;
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

        nuevo1.cadena = (ArrayList<MiRuta>) aux1.clone();
        nuevo2.cadena = (ArrayList<MiRuta>) aux2.clone();

        hijos.add(nuevo1);
        hijos.add(nuevo2);

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

        ArrayList<Entrega> e1 = (ArrayList<Entrega>) poblacion.get(indice).cadena.get(n1).lstEntrega.clone();
        ArrayList<Entrega> e2 = (ArrayList<Entrega>) poblacion.get(indice).cadena.get(n2).lstEntrega.clone();

        if (n1 != n2) {
            poblacion.get(indice).cadena.get(n1).lstEntrega = e2;
            poblacion.get(indice).cadena.get(n2).lstEntrega = e1;
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

}
