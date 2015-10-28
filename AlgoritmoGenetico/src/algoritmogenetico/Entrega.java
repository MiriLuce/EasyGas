/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class Entrega {
    int posX;
    int posY;
    double cantGLP;
    Date horaSolicitada;
    boolean tienePrioridad;
    int idPedido;
    
    public Entrega(int idPedido,int nuevoX, int nuevoY, double nuevaCant, Date nuevaHora, boolean priori){
        this.idPedido=idPedido;
        
        posX = nuevoX;
        posY = nuevoY;
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
}
