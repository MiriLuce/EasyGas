/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import modelo.Camion;
import java.util.ArrayList;

/**
 *
 * @author Eduardo
 */
public class MiRuta {
    Camion camion;
    ArrayList<Entrega> lstEntrega;
    
    public MiRuta(Camion nuevoCamion){
        camion = nuevoCamion;
        lstEntrega = new ArrayList<Entrega>();
    }
}
