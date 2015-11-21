package Modelo.Hibernate;
// Generated 10/11/2015 08:33:55 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Turno generated by hbm2java
 */
public class Turno  implements java.io.Serializable {


     private Integer idTurno;
     private Date horaInicio;
     private Date horaFin;
     private Set empleados = new HashSet(0);

    public Turno() {
    }

	
    public Turno(Date horaInicio, Date horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    public Turno(Integer idTurno,Date horaInicio, Date horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    public Turno(Date horaInicio, Date horaFin, Set empleados) {
       this.horaInicio = horaInicio;
       this.horaFin = horaFin;
       this.empleados = empleados;
    }
   
    public Integer getIdTurno() {
        return this.idTurno;
    }
    
    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }
    public Date getHoraInicio() {
        return this.horaInicio;
    }
    
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }
    public Date getHoraFin() {
        return this.horaFin;
    }
    
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    public Set getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }




}


