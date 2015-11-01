package modelo;
// Generated 30/10/2015 12:28:13 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Mantenimiento generated by hbm2java
 */
public class Mantenimiento  implements java.io.Serializable {


     private Integer idMantenimiento;
     private Camion camion;
     private Date fecha;
     private Date hora;
     private String estado;
     private String idTipoMantenimiento;
     private int cantTurnos;

    public Mantenimiento() {
    }

    public Mantenimiento(Camion camion, Date fecha, Date hora, String estado, String idTipoMantenimiento, int cantTurnos) {
       this.camion = camion;
       this.fecha = fecha;
       this.hora = hora;
       this.estado = estado;
       this.idTipoMantenimiento = idTipoMantenimiento;
       this.cantTurnos = cantTurnos;
    }
   
    public Integer getIdMantenimiento() {
        return this.idMantenimiento;
    }
    
    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }
    public Camion getCamion() {
        return this.camion;
    }
    
    public void setCamion(Camion camion) {
        this.camion = camion;
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
    public String getIdTipoMantenimiento() {
        return this.idTipoMantenimiento;
    }
    
    public void setIdTipoMantenimiento(String idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }
    public int getCantTurnos() {
        return this.cantTurnos;
    }
    
    public void setCantTurnos(int cantTurnos) {
        this.cantTurnos = cantTurnos;
    }




}


