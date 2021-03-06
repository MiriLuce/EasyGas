package Modelo.Hibernate;
// Generated 10/11/2015 08:33:55 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private Empleado empleado;
     private Perfil perfil;
     private String nombreUsuario;
     private String contrasenha;
     private Date fechaRegistro;
     private String estado;
     private String correo;

    public Usuario() {
    }

	
    public Usuario(String nombreUsuario, String contrasenha, Date fechaRegistro, String estado, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.correo = correo;
    }
    public Usuario(Empleado empleado, Perfil perfil, String nombreUsuario, String contrasenha, Date fechaRegistro, String estado, String correo) {
       this.empleado = empleado;
       this.perfil = perfil;
       this.nombreUsuario = nombreUsuario;
       this.contrasenha = contrasenha;
       this.fechaRegistro = fechaRegistro;
       this.estado = estado;
       this.correo = correo;
    }
   
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasenha() {
        return this.contrasenha;
    }
    
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
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
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }




}


