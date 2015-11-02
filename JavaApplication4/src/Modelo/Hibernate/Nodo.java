package Modelo.Hibernate;
// Generated 02/11/2015 06:39:48 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Nodo generated by hbm2java
 */
public class Nodo  implements java.io.Serializable {


     private Integer idNodo;
     private int coordX;
     private int coordY;
     private String habilitado;
     private Set clientes = new HashSet(0);
     private Set accidentes = new HashSet(0);
     private Set aristasForIdOrigen = new HashSet(0);
     private Set aristasForIdDestino = new HashSet(0);

    public Nodo() {
    }

	
    public Nodo(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }
    public Nodo(int coordX, int coordY, String habilitado, Set clientes, Set accidentes, Set aristasForIdOrigen, Set aristasForIdDestino) {
       this.coordX = coordX;
       this.coordY = coordY;
       this.habilitado = habilitado;
       this.clientes = clientes;
       this.accidentes = accidentes;
       this.aristasForIdOrigen = aristasForIdOrigen;
       this.aristasForIdDestino = aristasForIdDestino;
    }
   
    public Integer getIdNodo() {
        return this.idNodo;
    }
    
    public void setIdNodo(Integer idNodo) {
        this.idNodo = idNodo;
    }
    public int getCoordX() {
        return this.coordX;
    }
    
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }
    public int getCoordY() {
        return this.coordY;
    }
    
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    public String getHabilitado() {
        return this.habilitado;
    }
    
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
    public Set getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set clientes) {
        this.clientes = clientes;
    }
    public Set getAccidentes() {
        return this.accidentes;
    }
    
    public void setAccidentes(Set accidentes) {
        this.accidentes = accidentes;
    }
    public Set getAristasForIdOrigen() {
        return this.aristasForIdOrigen;
    }
    
    public void setAristasForIdOrigen(Set aristasForIdOrigen) {
        this.aristasForIdOrigen = aristasForIdOrigen;
    }
    public Set getAristasForIdDestino() {
        return this.aristasForIdDestino;
    }
    
    public void setAristasForIdDestino(Set aristasForIdDestino) {
        this.aristasForIdDestino = aristasForIdDestino;
    }




}


