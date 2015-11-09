/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import modeloNecesario.*;
import genetica.*;
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
        
        Constantes.leeDataset(20);
        Mapa mapa = Constantes.obtenMapa();
        
        genetica.AlgoritmoGenetico algoritmo = new genetica.AlgoritmoGenetico(lstCamiones, lstPedidos, 1, mapa);
        genetica.Cromosoma solucion = algoritmo.empieza();
        //solucion.calculaCosto();
        
        System.out.println("--- Mejor Cromosoma ----");
        solucion.imprimir();
        /*
        for(int i=0;i<solucion.getCadena().size();i++){
            
             for(int j=0;j<solucion.getCadena().get(i).getListaPedido().size();j++){
                 System.out.print(solucion.getCadena().get(i).getListaPedido().get(j).getIdPedido() + " -> "); 
             }
             System.out.println();
        }
        System.out.println();
        */
        //System.out.println(solucion.costo);
    }
}
