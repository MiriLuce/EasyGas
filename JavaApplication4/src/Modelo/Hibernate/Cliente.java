package Modelo.Hibernate;
// Generated 02/11/2015 06:39:48 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private Integer idCliente;
     private Nodo nodo;
     private String nombres;
     private Date fechaRegistro;
     private String estado;
     private String tipoDocumento;
     private String nroDocumento;
     private Set pedidos = new HashSet(0);

    public Cliente() {
    }

	
    public Cliente(Nodo nodo, String nombres, Date fechaRegistro, String estado, String tipoDocumento, String nroDocumento) {
        this.nodo = nodo;
        this.nombres = nombres;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
    }
    public Cliente(Nodo nodo, String nombres, Date fechaRegistro, String estado, String tipoDocumento, String nroDocumento, Set pedidos) {
       this.nodo = nodo;
       this.nombres = nombres;
       this.fechaRegistro = fechaRegistro;
       this.estado = estado;
       this.tipoDocumento = tipoDocumento;
       this.nroDocumento = nroDocumento;
       this.pedidos = pedidos;
    }
   
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public Nodo getNodo() {
        return this.nodo;
    }
    
    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTipoDocumento() {
        return this.tipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getNroDocumento() {
        return this.nroDocumento;
    }
    
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }
    public Set getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set pedidos) {
        this.pedidos = pedidos;
    }




}


