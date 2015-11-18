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

public class Cliente {
    
    private Nodo direccion;
    private String tipoDocumento;
    
    public Cliente(){}
    
    /**
     * @return the direccion
     */
    public Nodo getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(Nodo direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the tipoDocuemto
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocuemto the tipoDocuemto to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    
    
}
