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

public class TipoCamion {
    
    private double capacidadGLP;
    private double capacidadDiesel;
    private int tara;
    
    public TipoCamion(double capacidadGLP, double capacidadDiesel, int tara){
        this.capacidadGLP = capacidadGLP;
        this.capacidadDiesel = capacidadDiesel;
        this.tara = tara;
    }

    public TipoCamion() {
        
    }

    /**
     * @return the capacidadGLP
     */
    public double getCapacidadGLP() {
        return capacidadGLP;
    }

    /**
     * @param capacidadGLP the capacidadGLP to set
     */
    public void setCapacidadGLP(double capacidadGLP) {
        this.capacidadGLP = capacidadGLP;
    }

    /**
     * @return the capacidadDiesel
     */
    public double getCapacidadDiesel() {
        return capacidadDiesel;
    }

    /**
     * @param capacidadDiesel the capacidadDiesel to set
     */
    public void setCapacidadDiesel(double capacidadDiesel) {
        this.capacidadDiesel = capacidadDiesel;
    }

    /**
     * @return the tara
     */
    public int getTara() {
        return tara;
    }

    /**
     * @param tara the tara to set
     */
    public void setTara(int tara) {
        this.tara = tara;
    }
    
    
    
}
