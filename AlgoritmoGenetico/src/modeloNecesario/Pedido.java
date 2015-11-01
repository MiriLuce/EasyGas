/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNecesario;

import java.util.Date;

/**
 *
 * @author Eduardo
 */

public class Pedido {
    
    private int idPedido;
    private double cantGLP;    
    private boolean tienePrioridad;
    private Date horaSolicitada;
    private Date horaEntregada;
    
    private Cliente cliente;
    
    public Pedido(){}
    
    public Pedido(int idPedido,int nuevoX, int nuevoY, double nuevaCant, Date nuevaHora, boolean priori){
        this.idPedido=idPedido;
        
        this.cliente.getDireccion().setCoordX(nuevoX); 
        this.cliente.getDireccion().setCoordY(nuevoY); 
        cantGLP = nuevaCant;
        horaSolicitada = nuevaHora;
        tienePrioridad = priori;
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return getCliente().getDireccion().getCoordX();
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.getCliente().getDireccion().setCoordX(posX); 
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return getCliente().getDireccion().getCoordY();
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.getCliente().getDireccion().setCoordY(posY); 
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
     * @return the tienePrioridad
     */
    public boolean isTienePrioridad() {
        return tienePrioridad;
    }

    /**
     * @param tienePrioridad the tienePrioridad to set
     */
    public void setTienePrioridad(boolean tienePrioridad) {
        this.tienePrioridad = tienePrioridad;
    }

    /**
     * @return the horaSolicitada
     */
    public Date getHoraSolicitada() {
        return horaSolicitada;
    }

    /**
     * @param horaSolicitada the horaSolicitada to set
     */
    public void setHoraSolicitada(Date horaSolicitada) {
        this.horaSolicitada = horaSolicitada;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the horaEntregada
     */
    public Date getHoraEntregada() {
        return horaEntregada;
    }

    /**
     * @param horaEntregada the horaEntregada to set
     */
    public void setHoraEntregada(Date horaEntregada) {
        this.horaEntregada = horaEntregada;
    }
    
    
}
