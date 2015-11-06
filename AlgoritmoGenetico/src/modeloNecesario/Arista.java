/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNecesario;

import java.util.Date;

/**
 *
 * @author MiriamLucero
 */
public class Arista {
    
    private int distancia ;
    private int cantCuadras;
    private Date horaInicio;
    private Date horaFin;
    private Nodo origen;
    private Nodo destino;
    
    public Arista(){}

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
     * @return the cantCuadras
     */
    public int getCantCuadras() {
        return cantCuadras;
    }

    /**
     * @param cantCuadras the cantCuadras to set
     */
    public void setCantCuadras(int cantCuadras) {
        this.cantCuadras = cantCuadras;
    }

    /**
     * @return the horaInicio
     */
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * @return the origen
     */
    public Nodo getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Nodo getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Nodo destino) {
        this.destino = destino;
    }
    
    
}
