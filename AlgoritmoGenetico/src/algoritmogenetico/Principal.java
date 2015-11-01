/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import modeloNecesario.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Eduardo
 */
public class Principal {

    public static ArrayList<Camion> lstCamiones;
    public static ArrayList<Pedido> lstPedidos;

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        
        Constantes.leeDataset(0);
        Mapa mapa = Constantes.obtenMapa();
        
        AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(lstCamiones, lstPedidos, 1, mapa);
        Cromosoma solucion = algoritmo.empieza();
        solucion.calculaCosto();
        
        for(int i=0;i<solucion.cadena.size();i++){
            
             for(int j=0;j<solucion.cadena.get(i).getListaPedido().size();j++){
                 System.out.print(solucion.cadena.get(i).getListaPedido().get(j).getIdPedido() + " -> "); 
             }
             System.out.println();
        }
        System.out.println();
        System.out.println(solucion.costo);
    }
}
