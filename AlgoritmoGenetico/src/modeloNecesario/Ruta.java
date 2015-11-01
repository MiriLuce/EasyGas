/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNecesario;

import algoritmogenetico.Constantes;
import static genetica.AlgoritmoGenetico.mapa;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private Date salida;
    private Date llegada;
    
    
    public Ruta(Camion nuevoCamion){
        camion = nuevoCamion;
        listaPedido = new ArrayList<Pedido>();
        cantDiesel = 0;
        cantGLP = 0;
        distancia = 0;
    }
   
    private Date obtenerTiempo(int distanciaPedido, Date inicio, int accion ){
        
        double tiempo = distanciaPedido / Constantes.velCamion; 
        Calendar cal = Calendar.getInstance();
        cal.setTime(inicio);
        cal.add(Calendar.MILLISECOND, (int) (accion*tiempo * 3600000));
        return cal.getTime();
    }
    
    
    public boolean agregarPedido(Pedido pedido){
       
        TipoCamion tp = camion.getTipoCamion();
        int cantPedidoRuta = listaPedido.size();
        boolean estaAsignado = false; 
        int distanciaPedido = 0;
        Date horaEntrega = null;
        
        // Verifica si la capacidad restante de GLP es suficiente
        if(cantGLP + pedido.getCantGLP() <= tp.getCapacidadGLP()){
                    
            if(cantPedidoRuta == 0){
                distanciaPedido = mapa.distanciaMinima(Constantes.posCentralX,Constantes.posCentralY,
                                pedido.getPosX(), pedido.getPosY());
                horaEntrega = obtenerTiempo(distanciaPedido,pedido.getHoraSolicitada(), -1);
                salida  = horaEntrega;
                estaAsignado = true;
            }
            else{
                Pedido ultimoPedido =  listaPedido.get(cantPedidoRuta-1);
                int regreso = mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(),
                                Constantes.posCentralX,Constantes.posCentralY);
                distanciaPedido = mapa.distanciaMinima(ultimoPedido.getPosX(), ultimoPedido.getPosY(),
                                pedido.getPosX(), pedido.getPosY());
                horaEntrega = obtenerTiempo(distanciaPedido,ultimoPedido.getHoraEntregada(), 1);

                if(horaEntrega.after(pedido.getHoraSolicitada())){

                    int distanciaRuta = distancia + distanciaPedido + regreso;
                    double pesoTotal = tp.getTara() + cantGLP + pedido.getCantGLP();
                    double cantDiesel = 0.05 *( pesoTotal/ 52) * ( distanciaRuta / 1000 );  

                    if(cantDiesel <= tp.getCapacidadDiesel() ){
                        estaAsignado = true;
                    }
                }                
            }
            if(estaAsignado){
                cantGLP += pedido.getCantGLP();
                distancia += distanciaPedido;
                listaPedido.add(pedido);
                pedido.setHoraEntregada(horaEntrega);
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
            distancia  += regreso;
            double pesoTotal = camion.getTipoCamion().getTara() + cantGLP;
            cantDiesel = 0.05 *( pesoTotal/ 52) * ( distancia / 1000 ); 
            llegada = obtenerTiempo(regreso,ultimoPedido.getHoraEntregada(), 1);
            return true;
        }
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

    

    
    
}
