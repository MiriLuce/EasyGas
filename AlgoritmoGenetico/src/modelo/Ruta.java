package modelo;
// Generated 30/10/2015 12:28:13 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ruta generated by hbm2java
 */
public class Ruta  implements java.io.Serializable {


     private Integer idRuta;
     private Accidente accidente;
     private Camion camion;
     private Empleado empleado;
     private Date fechaEntrega;
     private Date horaSalida;
     private Date horaLlegada;
     private int cantGlp;
     private int cantDiesel;
     private int idCopiloto;
     private Set aristas = new HashSet(0);

    public Ruta() {
    }

	
    public Ruta(Camion camion, Empleado empleado, Date fechaEntrega, Date horaSalida, int cantGlp, int cantDiesel, int idCopiloto) {
        this.camion = camion;
        this.empleado = empleado;
        this.fechaEntrega = fechaEntrega;
        this.horaSalida = horaSalida;
        this.cantGlp = cantGlp;
        this.cantDiesel = cantDiesel;
        this.idCopiloto = idCopiloto;
    }
    public Ruta(Accidente accidente, Camion camion, Empleado empleado, Date fechaEntrega, Date horaSalida, Date horaLlegada, int cantGlp, int cantDiesel, int idCopiloto, Set aristas) {
       this.accidente = accidente;
       this.camion = camion;
       this.empleado = empleado;
       this.fechaEntrega = fechaEntrega;
       this.horaSalida = horaSalida;
       this.horaLlegada = horaLlegada;
       this.cantGlp = cantGlp;
       this.cantDiesel = cantDiesel;
       this.idCopiloto = idCopiloto;
       this.aristas = aristas;
    }
   
    public Integer getIdRuta() {
        return this.idRuta;
    }
    
    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }
    public Accidente getAccidente() {
        return this.accidente;
    }
    
    public void setAccidente(Accidente accidente) {
        this.accidente = accidente;
    }
    public Camion getCamion() {
        return this.camion;
    }
    
    public void setCamion(Camion camion) {
        this.camion = camion;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Date getFechaEntrega() {
        return this.fechaEntrega;
    }
    
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    public Date getHoraSalida() {
        return this.horaSalida;
    }
    
    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }
    public Date getHoraLlegada() {
        return this.horaLlegada;
    }
    
    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    public int getCantGlp() {
        return this.cantGlp;
    }
    
    public void setCantGlp(int cantGlp) {
        this.cantGlp = cantGlp;
    }
    public int getCantDiesel() {
        return this.cantDiesel;
    }
    
    public void setCantDiesel(int cantDiesel) {
        this.cantDiesel = cantDiesel;
    }
    public int getIdCopiloto() {
        return this.idCopiloto;
    }
    
    public void setIdCopiloto(int idCopiloto) {
        this.idCopiloto = idCopiloto;
    }
    public Set getAristas() {
        return this.aristas;
    }
    
    public void setAristas(Set aristas) {
        this.aristas = aristas;
    }




}

