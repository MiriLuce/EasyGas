/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNecesario;

/**
 *
 * @author MiriamLucero
 */
public class Camion {
    
    private TipoCamion tipoCamion;
    private String estado;

    public Camion(){}
    
    public Camion(TipoCamion tipoCamion, String estado){
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
    
    
}
