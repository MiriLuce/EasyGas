/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNecesario;

import algoritmogenetico.Constantes;
import static genetica.AlgoritmoGenetico.mapa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Eduardo
 */
public class Ruta {
    
    private Camion camion;
    private ArrayList<Pedido> listaPedido; //representa el orden en que se atenderan los pedidos
    
    private double cantDiesel;
    private double cantGLP;
    private int distancia;
    private int difTiempo; // en minutos (HEntrega - HSolicitada)
    private Date salida;
    private Date llegada;
    
    public Ruta(){
        camion = null;
        listaPedido = new ArrayList<Pedido>();
        cantDiesel = 0;
        cantGLP = 0;
        distancia = 0;
        difTiempo = 0;
        salida = null;
        llegada = null;
    }
    
    public Ruta(Camion nuevoCamion){
        camion = nuevoCamion;
        listaPedido = new ArrayList<Pedido>();
        cantDiesel = 0;
        cantGLP = 0;
        distancia = 0;
        difTiempo = 0;
        salida = null;
        llegada = null;
    }
    
    public Ruta(Ruta ruta){
        camion = ruta.getCamion();
        listaPedido = new ArrayList<Pedido>();
        for(int i=0; i< ruta.getListaPedido().size(); i++){
            Pedido ped = new Pedido(ruta.getListaPedido().get(i));
            listaPedido.add(ped);
        }
        
        //listaPedido = (ArrayList<Pedido>) ruta.getListaPedido().clone();
        cantDiesel = ruta.getCantDiesel();
        cantGLP = ruta.getCantGLP();
        distancia = ruta.getDistancia();
        difTiempo = ruta.getDifTiempo();
        salida = ruta.getSalida();
        llegada = ruta.getLlegada();        
    }
   
    public boolean verificar(){
        if(listaPedido.size()!=0){
        
            // Primer pedido
            Pedido pedAnt = listaPedido.get(0), pedPos;
            int tramo = mapa.distanciaMinima(Constantes.posCentralX, Constantes.posCentralY,
                    pedAnt.getPosX(), pedAnt.getPosY());
            Date hora = obtenerTiempo(tramo, pedAnt.getHoraEntregada(), -1);
            if(!salida.equals(hora)) return false;

            for (int i= 1; i< listaPedido.size(); i++){
                pedPos = listaPedido.get(i);
                tramo = mapa.distanciaMinima(pedAnt.getPosX(), pedAnt.getPosY(),
                    pedPos.getPosX(), pedPos.getPosY());
                hora = obtenerTiempo(tramo, pedAnt.getHoraEntregada(), 1);
                if(!pedPos.getHoraEntregada().equals(hora)) return false;
                pedAnt = pedPos;
            }
            // Ultimo pedido
            tramo = mapa.distanciaMinima(pedAnt.getPosX(), pedAnt.getPosY(),
                Constantes.posCentralX, Constantes.posCentralY);
            hora = obtenerTiempo(tramo, pedAnt.getHoraEntregada(), 1);
            if(!llegada.equals(hora)) return false;
        }
        return true;
    }
    
    public void imprimir(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        System.out.print("Tara: " + camion.getTipoCamion().getTara());
        System.out.print("  CapGLP: " + camion.getTipoCamion().getCapacidadGLP());
        System.out.println("  CapDiesel: " + camion.getTipoCamion().getCapacidadDiesel());
        
        System.out.print("Distancia: " + distancia + " Cantidad: " + listaPedido.size());
        System.out.format("  CantGLP:  %.3f", cantGLP);
        System.out.format("  CantDiesel %.3f\n", cantDiesel);
        
        System.out.println("Salida: " + sdf.format(salida) + "  Llegada: " + sdf.format(llegada));
        
        
        int cantDispon = camion.getListaDisponibilidad().size();
        Disponibilidad dispon = null;
        boolean verificar = false;
        
        for(int i= 0; i< cantDispon; i++){
            dispon = camion.getListaDisponibilidad().get(i);
            if(dispon.getHoraInicio().equals(salida) && dispon.getHoraFin().equals(llegada)){
                verificar = true;
                break;
            }
        }
        System.out.println("Disponibilidad de camion: " + verificar);
        System.out.println("Salida: " + sdf.format(dispon.getHoraInicio()) + "  Llegada: " +
                sdf.format(dispon.getHoraFin()));
        System.out.println();
        
        for (int j= 0; j< listaPedido.size(); j++){
            //System.out.print( " -> " + listaPedido.get(j).getIdPedido());
            System.out.format ("-> Pedido N° %03d",  listaPedido.get(j).getIdPedido());
            System.out.format("  X: %03d", listaPedido.get(j).getPosX());
            System.out.format("  Y: %03d\n", listaPedido.get(j).getPosY());
            System.out.print("   S: " + sdf.format(listaPedido.get(j).getHoraSolicitada()));
            System.out.print(" E: " + sdf.format(listaPedido.get(j).getHoraEntregada()));
            System.out.println();
        }
        System.out.println();
    }
    
    public ArrayList<modeloCompleto.Arista> buscarCamino(modeloCompleto.Nodo inicio, modeloCompleto.Nodo fin){
        
        ArrayList<modeloCompleto.Arista> camino = new ArrayList();
        int xi =  inicio.getCoordX(), yi = inicio.getCoordY();
        int xf = fin.getCoordX(), yf = fin.getCoordY();
        int dx = Math.abs(xf - xi); //diferencia de puntos X
        int dy = Math.abs(yf - yi); //diferencia de puntos Y
        
        // Caso 1: esta en la misma coordenada x
        if (xi == xf && yi != yf) { 
            // No hay barreras en el camino
            if (!mapa.hayBarrerasY(xi, yi, yf)) {
                modeloCompleto.Arista arista = new modeloCompleto.Arista(dy, inicio, fin);
                camino.add(arista);
                return camino;
            } 
            // Hay barreras en el camino
            else {
                // da la distancia que se tiene q mover en la coordenada x
                // para pasar la barrera
                //int menorBloque = calculaMenorY(xi, yi, yf); 
                //return (menorBloque * 2) + dy;
                return camino;
            }
        }
        
        // Caso 2: esta en la misma coordenada y
        if (yi == yf && xi != xf) { 
            // No hay barreras en el camino
            if (!mapa.hayBarrerasX(yi, xi, xf)) {
                modeloCompleto.Arista arista = new modeloCompleto.Arista(dx, inicio, fin);
                camino.add(arista);
                return camino;
            } 
            // Hay barreras en el camino
            else {
                // da la distancia que se tiene q mover en la coordenada x
                // para pasar la barrera
                //int menorBloque = calculaMenorX(yi, xi, xf);
                //return (menorBloque * 2) + dx;
                return camino;
            }
        }
        
        // Caso 3: nodos no alineados
        if (xi != xf && yi != yf) { 
            
            // No hay barreras en el camino directo (L)
            int aux1X = xf, aux1Y = yi, aux2X = xi, aux2Y = yf;
            
            boolean verficarAux1x = mapa.hayBarrerasX(yi, xi, aux1X);
            boolean verficarAux1y = mapa.hayBarrerasY(xf, yf, aux1Y);
            if (verficarAux1x && verficarAux1y){
                modeloCompleto.Nodo aux = new modeloCompleto.Nodo(aux1X, aux1Y);
                modeloCompleto.Arista arista1 = new modeloCompleto.Arista(dx, inicio, aux);
                modeloCompleto.Arista arista2 = new modeloCompleto.Arista(dy, aux, fin);
                camino.add(arista1);
                camino.add(arista2);
                return camino;
            }
            
            boolean verficarAux2x = mapa.hayBarrerasX(yf, xf, aux2X);
            boolean verficarAux2y = mapa.hayBarrerasY(xi, yi, aux2Y);
            if (verficarAux2x && verficarAux2y){
                modeloCompleto.Nodo aux = new modeloCompleto.Nodo(aux2X, aux2Y);
                modeloCompleto.Arista arista1 = new modeloCompleto.Arista(dy, inicio, aux);
                modeloCompleto.Arista arista2 = new modeloCompleto.Arista(dx, aux, fin);
                camino.add(arista1);
                camino.add(arista2);
                return camino;
            }
             
            // Hay barreras en el camino directo (L)
        }
        return camino;
    }

    public modeloCompleto.Ruta guardarEnMapa(){
        
        ArrayList<modeloCompleto.Arista> camino = new ArrayList();
        int cantPedido = listaPedido.size();
        modeloCompleto.Ruta nuevaRuta = new modeloCompleto.Ruta();
        modeloCompleto.Nodo inicio, fin;
        
        for(int i= 0; i< cantPedido; i++){
            if (i == 0){
                inicio = new modeloCompleto.Nodo(Constantes.posCentralX, Constantes.posCentralY);
                fin = new modeloCompleto.Nodo(listaPedido.get(i).getPosX(), listaPedido.get(i).getPosY());
            }
            else if ( i == cantPedido -1 ){
                inicio = new modeloCompleto.Nodo(listaPedido.get(i).getPosX(), listaPedido.get(i).getPosY());
                fin = new modeloCompleto.Nodo(Constantes.posCentralX, Constantes.posCentralY);
            }
            else {
                inicio = new modeloCompleto.Nodo(listaPedido.get(i).getPosX(), listaPedido.get(i).getPosY());
                fin = new modeloCompleto.Nodo(listaPedido.get(i+1).getPosX(), listaPedido.get(i+1).getPosY());
            }
            ArrayList<modeloCompleto.Arista> tramo = buscarCamino(inicio, fin);
            for(int j = 0; j< tramo.size(); j++) camino.add(tramo.get(j));
        }
        
        nuevaRuta.setCantDiesel((int)cantDiesel);
        nuevaRuta.setAristaList((List<modeloCompleto.Arista>)camino);
        return nuevaRuta;
    }
    
    private Date obtenerTiempo(int distanciaPedido, Date inicio, int accion ){
        double tiempo = 1.0 * distanciaPedido / Constantes.velCamion; // en horas
        Calendar cal = Calendar.getInstance();
        cal.setTime(inicio);
        cal.add(Calendar.MILLISECOND, (int) (accion*tiempo * 3600000)); // 60min * 60 seg * 1000 miliseg
        return cal.getTime();
    }
    
    private Camion seleccionarCamion(ArrayList<Camion> camiones, double cantGLP, Date salida, Date llegada){
        int cantCamiones = camiones.size();
        Camion camion, camionEscogido = null;
        Disponibilidad dispon;
        
        for (int c = 0; c < cantCamiones; c++){
            camion = camiones.get(c);
            boolean verificar = true;
            
            // Si tiene capcidad suficiente
            if (true || camion.getTipoCamion().getCapacidadGLP() >= cantGLP){
                
                // Si tiene disponibilidad suficiente
                int cantDispon = camion.getListaDisponibilidad().size();
                for(int i= 0; i< cantDispon; i++){
                    dispon = camion.getListaDisponibilidad().get(i);
                    if (dispon.getHoraInicio().before(salida) && dispon.getHoraFin().after(salida)){
                        verificar = false;
                        break;
                    }
                    if (dispon.getHoraInicio().before(llegada) && dispon.getHoraFin().after(llegada)){
                        verificar = false;
                        break;
                    }
                }
                if (verificar){ //hay disponibilidad en el camion
                    if (camionEscogido == null || camion.getTipoCamion().getCapacidadGLP() <
                        camionEscogido.getTipoCamion().getCapacidadGLP()) 
                        camionEscogido = camion;
                }
            }              
        }
        Disponibilidad disp = new Disponibilidad(camionEscogido);
        disp.setHoraInicio(salida);
        disp.setHoraFin(llegada);
        //System.out.println("Camion ");
        //if (camionEscogido==null )
        //    System.out.println("Error");
        if (camionEscogido== null) return null;
        camionEscogido.getListaDisponibilidad().add(disp);
        return camionEscogido; 
    }
    
    private boolean expandirDisponibilidad(Date inicio, Date fin){
    
        int cantDispon = camion.getListaDisponibilidad().size();
        Disponibilidad dispon, actual = null;
        boolean verificar = true;

        for(int i= 0; i< cantDispon; i++){
            dispon = camion.getListaDisponibilidad().get(i);
            if (dispon.getHoraInicio().before(inicio) && dispon.getHoraFin().after(inicio)){
                verificar = false;
                break;
            }
            if (dispon.getHoraInicio().before(fin) && dispon.getHoraFin().after(fin)){
                verificar = false;
                break;
            }
            if(dispon.getHoraInicio().equals(salida) && dispon.getHoraFin().equals(llegada)){
                actual = dispon;
            }
        }
        if(verificar) actual.setHoraFin(fin);
        //else System.out.println("no añade"); 
        return verificar;
    }   
    
    private void reducirDisponibilidad(Date inicio, Date fin){
    
        int cantDispon = camion.getListaDisponibilidad().size();
        Disponibilidad dispon = null;
        boolean verificar = false;
        
        for(int i= 0; i< cantDispon; i++){
            dispon = camion.getListaDisponibilidad().get(i);
            if(dispon.getHoraInicio().equals(inicio) && dispon.getHoraFin().equals(fin)){
                
                verificar = true;
                break;
            }
        }
        if(verificar){ 
            dispon.setHoraInicio(salida);
            dispon.setHoraFin(llegada);
        }
        else {
            System.out.println("no esta"); 
            //this.imprimir();
        }
    } 
    
    public boolean agregarPedido(ArrayList<Camion> camiones, Pedido pedido){
       
        int cantPedidoRuta = listaPedido.size();
        boolean estaAsignado = false; 
        int distanciaPedido = 0, regreso;
        Date horaEntrega = null, horaLlegada;
        TipoCamion tp = camion == null ? null : camion.getTipoCamion();
        
        // Verifica si la capacidad restante de GLP es suficiente
        if(camion == null || cantGLP + pedido.getCantGLP() <= tp.getCapacidadGLP()){
                    
            if(cantPedidoRuta == 0){
                // La ruta no tiene pedidos asignados
                distanciaPedido = mapa.distanciaMinima(Constantes.posCentralX,Constantes.posCentralY,
                    pedido.getPosX(), pedido.getPosY());
                regreso = mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(),
                    Constantes.posCentralX,Constantes.posCentralY);
                
                distancia  += regreso;
                salida = obtenerTiempo(distanciaPedido,pedido.getHoraSolicitada(), -1);
                horaLlegada = obtenerTiempo(regreso,pedido.getHoraSolicitada(), 1);
                horaEntrega = pedido.getHoraSolicitada();
                estaAsignado = true;
                
                // como no hay pedidos no hay camion asignado
                camion = seleccionarCamion(camiones, pedido.getCantGLP(), salida, horaLlegada);
                if (camion == null ) return false;
            }
            else{
                Pedido ultimoPedido =  listaPedido.get(cantPedidoRuta-1);
                distanciaPedido = mapa.distanciaMinima(ultimoPedido.getPosX(), ultimoPedido.getPosY(),
                                pedido.getPosX(), pedido.getPosY());
                int regresoUltimo = mapa.distanciaMinima(ultimoPedido.getPosX(), ultimoPedido.getPosY(),
                                Constantes.posCentralX,Constantes.posCentralY);
                regreso = mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(),
                                Constantes.posCentralX,Constantes.posCentralY);
                horaEntrega = obtenerTiempo(distanciaPedido,ultimoPedido.getHoraEntregada(), 1);
                horaLlegada = obtenerTiempo(regreso,horaEntrega, 1);
                
                if(horaEntrega.after(pedido.getHoraSolicitada())){

                    distanciaPedido += (regreso - regresoUltimo);
                    int distanciaRuta = distancia + distanciaPedido;
                    double pesoTotal = ( tp.getTara() + cantGLP + pedido.getCantGLP() ); // galones
                    double cantDiesel = 0.05 *( (1.0 * pesoTotal)/ 52) *  distanciaRuta ; // galones y km  

                    if(cantDiesel <= tp.getCapacidadDiesel() ){
                        estaAsignado =  expandirDisponibilidad(llegada, horaLlegada);;
                    }
                }                
            }
            if(estaAsignado){
                cantGLP += pedido.getCantGLP();
                distancia += distanciaPedido;
                llegada = horaLlegada;
                pedido.setHoraEntregada(horaEntrega);
                listaPedido.add(pedido);                
                int diff = (int) pedido.getHoraEntregada().getTime() - (int)pedido.getHoraSolicitada().getTime();
                difTiempo += (diff) / 6000;
                //System.out.println("H: " + diff);
            }
        }
        
        return estaAsignado;
    }
    
    public boolean cerrarRuta(){
        
        if(listaPedido.size() == 0) return false;
        else{
            Pedido ultimoPedido =  listaPedido.get(listaPedido.size()-1);
            int regreso = mapa.distanciaMinima(ultimoPedido.getPosX(), ultimoPedido.getPosY(),
                    Constantes.posCentralX,Constantes.posCentralY);
            Date hora = obtenerTiempo(regreso,ultimoPedido.getHoraEntregada(), 1);
            if(llegada== null || llegada.compareTo(hora) != 0){
                distancia  += regreso;
                Date antLlegada = llegada;
                llegada = hora;
                reducirDisponibilidad(salida, antLlegada);                                
                System.out.println("cerrar");
            }
            double pesoTotal = ( camion.getTipoCamion().getTara() + cantGLP ) ; // galones
            cantDiesel = 0.05 *( pesoTotal / 52 * 1.0) * distancia ;  // galones y km
            return true;
        }
    }
    /*
    private void tiempo(Pedido pedido,  int posXAnt, int posYAnt, int posXPos,  int posYPos, int accion){
    
        int entregaAntigua = (int)pedido.getHoraEntregada().getTime();
        int distancia = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
        salida = obtenerTiempo(distancia, pedido.getHoraSolicitada(), accion);
        pedido.setHoraEntregada(pedido.getHoraSolicitada());
        int entregaNueva = (int)pedido.getHoraEntregada().getTime();
        difTiempo += ( ( entregaAntigua - entregaNueva ) / 6000 )  ;
    }
    */
    
    
    public boolean quitarPedido(int indicePedido){
        
        int cantPedidos = listaPedido.size(); 
        // cuando el pedido es el unico en la ruta
        if (indicePedido == 0 && cantPedidos == 1){
            listaPedido.remove(indicePedido);
            return true;
        }        
        
        Pedido pedido = listaPedido.get(indicePedido);
        int posXAnt, posYAnt, posXPos, posYPos, dist = 0;
        boolean verificar = true;
        int entregaAntigua, entregaNueva;
        Date finRuta = llegada, inicioRuta = salida;
        
        // cuando el pedido primer pedido de la ruta
        if (indicePedido == 0){ 
            posXAnt = Constantes.posCentralX;
            posYAnt = Constantes.posCentralY; 
            posXPos = listaPedido.get(1).getPosX();
            posYPos = listaPedido.get(1).getPosY();
            
            entregaAntigua = (int)listaPedido.get(1).getHoraEntregada().getTime();
            
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            salida = obtenerTiempo(dist, listaPedido.get(1).getHoraSolicitada(), -1);
            listaPedido.get(1).setHoraEntregada(listaPedido.get(1).getHoraSolicitada());
            
            entregaNueva = (int)listaPedido.get(1).getHoraEntregada().getTime();
            difTiempo -= ( ( entregaAntigua - entregaNueva ) / 60000 )  ;
        } 
        // cuando el pedido ultimo pedido de la ruta
        else if (indicePedido == cantPedidos -1){  
            posXAnt = listaPedido.get(cantPedidos - 2).getPosX();
            posYAnt = listaPedido.get(cantPedidos - 2).getPosY();
            posXPos = Constantes.posCentralX;
            posYPos = Constantes.posCentralY; 
            
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            llegada = obtenerTiempo(dist, listaPedido.get(cantPedidos - 2).getHoraEntregada(), 1);
            verificar = false;
        } 
        // cuando el pedido esta en medio de la lista de la ruta
        else{
            posXAnt = listaPedido.get(indicePedido - 1).getPosX();
            posYAnt = listaPedido.get(indicePedido - 1).getPosY();
            posXPos = listaPedido.get(indicePedido + 1).getPosX();
            posYPos = listaPedido.get(indicePedido + 1).getPosY();
            
            entregaAntigua = (int)listaPedido.get(indicePedido + 1).getHoraEntregada().getTime();
            
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            Date entrega = obtenerTiempo(dist, listaPedido.get(indicePedido - 1).getHoraEntregada(), 1);
            listaPedido.get(indicePedido + 1).setHoraEntregada(entrega);
            
            entregaNueva = (int)entrega.getTime();
            difTiempo -= ( ( entregaAntigua - entregaNueva ) / 60000 )  ;
        }
        
        //System.out.println("Distancia: " + dist);
        cantGLP -= pedido.getCantGLP();
        distancia -= mapa.distanciaMinima(posXAnt, posYAnt, pedido.getPosX(), pedido.getPosY());
        distancia -= mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(), posXPos, posYPos);
        distancia += dist; //!=0 ? dist : mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
        int diff = (int)pedido.getHoraEntregada().getTime() - (int)pedido.getHoraSolicitada().getTime();
        difTiempo -= ( diff / 6000 );
        listaPedido.remove(indicePedido);
        cantPedidos--;
        
        if (!verificar){
            reducirDisponibilidad(inicioRuta, finRuta);
            return true;
        }
        
        // Calcular hora de entrega de cada pedido
        Pedido pedidoAnterior = listaPedido.get(indicePedido);
        Pedido pedidoActual = null;
        
        for(int i = indicePedido + 1 ; i < cantPedidos; i++){
            pedidoActual = listaPedido.get(i);
            int tramo = mapa.distanciaMinima(pedidoAnterior.getPosX(), pedidoAnterior.getPosY(),
                pedidoActual.getPosX(), pedidoActual.getPosY());         
            
            entregaAntigua = (int)pedidoActual.getHoraEntregada().getTime(); 
            Date entregaActual = obtenerTiempo(tramo, pedidoAnterior.getHoraEntregada(), 1);
            if (entregaActual.before(pedidoActual.getHoraSolicitada()))
                return false;
            
            listaPedido.get(i).setHoraEntregada(entregaActual);
            entregaNueva = (int)entregaActual.getTime(); 
            difTiempo -= ( ( entregaAntigua - entregaNueva ) / 6000 )  ;
            pedidoAnterior = pedidoActual;
        }
        int tramo = mapa.distanciaMinima(pedidoAnterior.getPosX(), pedidoAnterior.getPosY(),
                Constantes.posCentralX, Constantes.posCentralY); 
        llegada = obtenerTiempo(tramo, pedidoAnterior.getHoraEntregada(), 1);
        
        reducirDisponibilidad(inicioRuta, finRuta);
        return true;
    }
        
    /**
     * @return the listaPedido
     */
    public ArrayList<Pedido> getListaPedido() {
        return listaPedido;
    }
    
    /**
     * @param listaPedido the listaPedido to set
     */
    public void setListaPedido(ArrayList<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }
    
    /**
     * @return the camion
     */
    public Camion getCamion() {
        return camion;
    }

    /**
     * @param camion the camion to set
     */
    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    /**
     * @return the cantDiesel
     */
    public double getCantDiesel() {
        return cantDiesel;
    }

    /**
     * @param cantDiesel the cantDiesel to set
     */
    public void setCantDiesel(double cantDiesel) {
        this.cantDiesel = cantDiesel;
    }

    /**
     * @return the cantGLP
     */
    public double getCantGLP() {
        return cantGLP;
    }

    /**
     * @param cantGLP the cantGLP to set
     */
    public void setCantGLP(double cantGLP) {
        this.cantGLP = cantGLP;
    }

    /**
     * @return the distancia
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the salida
     */
    public Date getSalida() {
        return salida;
    }

    /**
     * @param salida the salida to set
     */
    public void setSalida(Date salida) {
        this.salida = salida;
    }

    /**
     * @return the llegada
     */
    public Date getLlegada() {
        return llegada;
    }

    /**
     * @param llegada the llegada to set
     */
    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }

    /**
     * @return the difTiempo
     */
    public int getDifTiempo() {
        return difTiempo;
    }

    /**
     * @param difTiempo the difTiempo to set
     */
    public void setDifTiempo(int difTiempo) {
        this.difTiempo = difTiempo;
    }

    

    
    
}
