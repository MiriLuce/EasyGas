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
    }
    
    public Ruta(Camion nuevoCamion){
        camion = nuevoCamion;
        listaPedido = new ArrayList<Pedido>();
        cantDiesel = 0;
        cantGLP = 0;
        distancia = 0;
        difTiempo = 0;
    }
    
    public Ruta(Ruta ruta){
        camion = ruta.getCamion();
        listaPedido = (ArrayList<Pedido>) ruta.getListaPedido().clone();
        cantDiesel = ruta.getCantDiesel();
        cantGLP = ruta.getCantGLP();
        distancia = ruta.getDistancia();
        salida = ruta.getSalida();
        llegada = ruta.getLlegada();
        difTiempo = ruta.getDifTiempo();
    }
   
    public void imprimir(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        System.out.print(" GLP: " + camion.getTipoCamion().getCapacidadGLP());
        System.out.println(" Distancia: " + distancia + " Cantidad: " + listaPedido.size());
        System.out.println("Salida: " + sdf.format(salida) + " Llegada: " + sdf.format(llegada));
            
        for (int j= 0; j< listaPedido.size(); j++){
            System.out.print( " -> " + listaPedido.get(j).getIdPedido());
            System.out.print(" S: " + sdf.format(listaPedido.get(j).getHoraSolicitada()));
            System.out.print(" E: " + sdf.format(listaPedido.get(j).getHoraEntregada()));
        }
        System.out.println();
    }
    
    private Date obtenerTiempo(int distanciaPedido, Date inicio, int accion ){
        
        double tiempo = distanciaPedido / Constantes.velCamion; 
        Calendar cal = Calendar.getInstance();
        cal.setTime(inicio);
        cal.add(Calendar.MILLISECOND, (int) (accion*tiempo * 3600000));
        return cal.getTime();
    }
    
    private Camion seleccionarCamion(ArrayList<Camion> camiones, double cantGLP, Date salida, Date llegada){
        int cantCamiones = camiones.size();
        Camion camion, camionEscogido = null;
        Disponibilidad dispon;
        
        for (int c = 0; c < cantCamiones; c++){
            camion = camiones.get(c);
            boolean verificar = true;
            // camion.getEstado().compareTo("DISPONIBLE")== 0
            if (true || camion.getTipoCamion().getCapacidadGLP() >= cantGLP){
                
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
        camionEscogido.getListaDisponibilidad().add(disp);
        return camionEscogido; 
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
                    double pesoTotal = ( tp.getTara() + cantGLP + pedido.getCantGLP() ) * 310.79; // galones
                    double cantDiesel = 0.05 *( (1.0 * pesoTotal)/ 52) * ( ( 1.0 * distanciaRuta )/ 1000 );  

                    if(cantDiesel <= tp.getCapacidadDiesel() ){
                        estaAsignado = true;
                    }
                }                
            }
            if(estaAsignado){
                cantGLP += pedido.getCantGLP();
                distancia += distanciaPedido;
                llegada = horaLlegada;
                listaPedido.add(pedido);
                pedido.setHoraEntregada(horaEntrega);
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
                llegada = hora;
            }
            double pesoTotal = camion.getTipoCamion().getTara() + cantGLP;
            cantDiesel = 0.05 *( pesoTotal/ 52) * ( distancia / 1000 ); 
            return true;
        }
    }
    
    public boolean quitarPedido(int indicePedido){
        int cantPedidos = listaPedido.size(); 
        if (indicePedido == 0 && cantPedidos == 1){ // unico pedido en la ruta
            listaPedido.remove(indicePedido);
            return true;
        }        
        Pedido pedido = listaPedido.get(indicePedido);
        int posXAnt, posYAnt, posXPos, posYPos, dist = 0;
        boolean verificar = true;
        //System.out.println("-------- inicio: ");
        if (indicePedido == 0){ // si es el primero
            posXAnt = Constantes.posCentralX;
            posYAnt = Constantes.posCentralY; 
            posXPos = listaPedido.get(1).getPosX();
            posYPos = listaPedido.get(1).getPosY();
            
            int diffAntigua, diffNueva;
            diffAntigua = (int)listaPedido.get(1).getHoraEntregada().getTime() - (int)listaPedido.get(1).getHoraSolicitada().getTime();
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            salida = obtenerTiempo(dist, listaPedido.get(1).getHoraSolicitada(), -1);
            listaPedido.get(1).setHoraEntregada(listaPedido.get(1).getHoraSolicitada());
            diffNueva = (int)listaPedido.get(1).getHoraEntregada().getTime() - (int)listaPedido.get(1).getHoraSolicitada().getTime();
            difTiempo += (diffNueva / 6000) - (diffAntigua / 6000)  ;
            verificar = false;
        } 
        else if (indicePedido == cantPedidos -1){ // si es el ultimo     
            posXAnt = listaPedido.get(cantPedidos - 2).getPosX();
            posYAnt = listaPedido.get(cantPedidos - 2).getPosY();
            posXPos = Constantes.posCentralX;
            posYPos = Constantes.posCentralY; 
            
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            llegada = obtenerTiempo(dist, listaPedido.get(cantPedidos - 2).getHoraEntregada(), 1);
            verificar = false;
        } 
        else{
            posXAnt = listaPedido.get(indicePedido - 1).getPosX();
            posYAnt = listaPedido.get(indicePedido - 1).getPosX();
            posXPos = listaPedido.get(indicePedido + 1).getPosX();
            posYPos = listaPedido.get(indicePedido + 1).getPosX();
        }
        cantGLP -= pedido.getCantGLP();
        distancia -= mapa.distanciaMinima(posXAnt, posYAnt, pedido.getPosX(), pedido.getPosY());
        distancia -= mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(), posXPos, posYPos);
        distancia += dist!=0 ? dist : mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
        //System.out.println("-------- fin: ");
        if (!verificar){
            listaPedido.remove(indicePedido);
            int diff = (int)pedido.getHoraEntregada().getTime() - (int)pedido.getHoraSolicitada().getTime();
            difTiempo -= diff / 6000;
            return true;
        }
        // Calcular hora de entrega de cada pedido
        Pedido pedidoAnterior = listaPedido.get(indicePedido - 1);
        Pedido pedidoActual = null;
        int diffAntigua, diffNueva;
        
        for(int i = indicePedido + 1; i < cantPedidos; i++){
            pedidoActual = listaPedido.get(i);
            int tramo = mapa.distanciaMinima(pedidoAnterior.getPosX(), pedidoAnterior.getPosY(),
                pedidoActual.getPosX(), pedidoActual.getPosY());         
            
            diffAntigua = (int)pedidoActual.getHoraEntregada().getTime() - (int)pedidoActual.getHoraSolicitada().getTime();
            pedidoActual.setHoraEntregada(obtenerTiempo(tramo, pedidoAnterior.getHoraEntregada(), 1));
            diffNueva = (int)pedidoActual.getHoraEntregada().getTime() - (int)pedidoActual.getHoraSolicitada().getTime();
            
            if (pedidoActual.getHoraEntregada().before(pedidoActual.getHoraSolicitada()))
                return false;
            difTiempo += (diffNueva / 6000) - (diffAntigua / 6000)  ;
            pedidoAnterior = pedidoActual;
        }
        listaPedido.remove(indicePedido);
        int diff = (int)pedido.getHoraEntregada().getTime() - (int)pedido.getHoraSolicitada().getTime();
        if (diff < 0 ) System.out.println("Negativo");
        difTiempo -= diff / 6000  ;
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
