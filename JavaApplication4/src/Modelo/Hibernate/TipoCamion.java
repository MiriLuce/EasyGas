package Modelo.Hibernate;
// Generated Oct 26, 2015 10:41:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TipoCamion generated by hbm2java
 */
public class TipoCamion  implements java.io.Serializable {


     private Integer idTipoCamion;
     private String nombre;
     private int capacidadDiesel;
     private int capacidadGlp;
     private int tara;
     private Set camions = new HashSet(0);

    public TipoCamion() {
    }

	
    public TipoCamion(int capacidadDiesel, int capacidadGlp, int tara) {
        this.capacidadDiesel = capacidadDiesel;
        this.capacidadGlp = capacidadGlp;
        this.tara = tara;
    }
    public TipoCamion(String nombre, int capacidadDiesel, int capacidadGlp, int tara, Set camions) {
       this.nombre = nombre;
       this.capacidadDiesel = capacidadDiesel;
       this.capacidadGlp = capacidadGlp;
       this.tara = tara;
       this.camions = camions;
    }
   
    public Integer getIdTipoCamion() {
        return this.idTipoCamion;
    }
    
    public void setIdTipoCamion(Integer idTipoCamion) {
        this.idTipoCamion = idTipoCamion;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCapacidadDiesel() {
        return this.capacidadDiesel;
    }
    
    public void setCapacidadDiesel(int capacidadDiesel) {
        this.capacidadDiesel = capacidadDiesel;
    }
    public int getCapacidadGlp() {
        return this.capacidadGlp;
    }
    
    public void setCapacidadGlp(int capacidadGlp) {
        this.capacidadGlp = capacidadGlp;
    }
    public int getTara() {
        return this.tara;
    }
    
    public void setTara(int tara) {
        this.tara = tara;
    }
    public Set getCamions() {
        return this.camions;
    }
    
    public void setCamions(Set camions) {
        this.camions = camions;
    }

    public String toString(){
        return nombre;
    }


}


