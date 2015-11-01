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
public class Nodo {
    
    private int coordX;
    private int coordY;
    private boolean habilitada;

    public Nodo(int coordX, int coordY, boolean habilitada){
        this.coordX = coordX;
        this.coordY = coordY;
        this.habilitada = habilitada;
    }
    
   public int getCoordX() {
        return this.coordX;
    }
    
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }
    public int getCoordY() {
        return this.coordY;
    }
    
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * @return the habilitada
     */
    public boolean isHabilitada() {
        return habilitada;
    }

    /**
     * @param habilitada the habilitada to set
     */
    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
    
    
    
}
