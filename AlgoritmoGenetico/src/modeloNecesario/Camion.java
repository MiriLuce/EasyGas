/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNecesario;

import java.util.ArrayList;

/**
 *
 * @author MiriamLucero
 */
public class Camion {
    
    private TipoCamion tipoCamion;
    private String estado;
    private ArrayList<Disponibilidad> listaDisponibilidad;

    public Camion(){ 
        this.listaDisponibilidad = new ArrayList<Disponibilidad>(); 
    }
    
    public Camion(TipoCamion tipoCamion, String estado){
        this.listaDisponibilidad = new ArrayList<Disponibilidad>(); 
        this.tipoCamion = tipoCamion;
        this.estado = estado;
    }
    
    /**
     * @return the tipoCamion
     */
    public TipoCamion getTipoCamion() {
        return tipoCamion;
    }

    /**
     * @param tipoCamion the tipoCamion to set
     */
    public void setTipoCamion(TipoCamion tipoCamion) {
        this.tipoCamion = tipoCamion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the listaDisponibilidad
     */
    public ArrayList<Disponibilidad> getListaDisponibilidad() {
        return listaDisponibilidad;
    }

    /**
     * @param listaDisponibilidad the listaDisponibilidad to set
     */
    public void setListaDisponibilidad(ArrayList<Disponibilidad> listaDisponibilidad) {
        this.listaDisponibilidad = listaDisponibilidad;
    }
    
    
}
