package modelo;
// Generated 30/10/2015 12:28:13 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Accidente generated by hbm2java
 */
public class Accidente  implements java.io.Serializable {


     private Integer idAccidente;
     private Nodo nodo;
     private Date fecha;
     private Date hora;
     private String estado;
     private String observacion;
     private String solucion;
     private Set rutas = new HashSet(0);

    public Accidente() {
    }

	
    public Accidente(Nodo nodo, Date fecha, Date hora, String estado) {
        this.nodo = nodo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }
    public Accidente(Nodo nodo, Date fecha, Date hora, String estado, String observacion, String solucion, Set rutas) {
       this.nodo = nodo;
       this.fecha = fecha;
       this.hora = hora;
       this.estado = estado;
       this.observacion = observacion;
       this.solucion = solucion;
       this.rutas = rutas;
    }
   
    public Integer getIdAccidente() {
        return this.idAccidente;
    }
    
    public void setIdAccidente(Integer idAccidente) {
        this.idAccidente = idAccidente;
    }
    public Nodo getNodo() {
        return this.nodo;
    }
    
    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getHora() {
        return this.hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getSolucion() {
        return this.solucion;
    }
    
    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }
    public Set getRutas() {
        return this.rutas;
    }
    
    public void setRutas(Set rutas) {
        this.rutas = rutas;
    }




}

