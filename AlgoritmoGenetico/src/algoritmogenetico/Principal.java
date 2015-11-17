/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import modeloNecesario.*;
import genetica.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class Principal {

    public static ArrayList<Camion> lstCamiones;
    public static ArrayList<Pedido> lstPedidos;

    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        
        Constantes.leeDataset(2);
        Mapa mapa = Constantes.obtenMapa();
        
        genetica.AlgoritmoGenetico algoritmo = new genetica.AlgoritmoGenetico(lstCamiones, lstPedidos, 1, mapa);
        genetica.Cromosoma solucion = algoritmo.empieza();
        //solucion.calculaCosto();
        
        System.out.println("-------- Mejor Cromosoma --------");
        solucion.imprimir();
        
        System.out.println("------------- Camino ------------");
        List<modeloCompleto.Ruta> ruta = solucion.guardarEnMapa();
        
        for(int i=0; i< ruta.size(); i++){
            System.out.println("------------- Ruta N° " + i + " ------------");
            for(int j=0; j < ruta.get(i).getAristaList().size(); j++){
                System.out.print("Distancia: " + ruta.get(i).getAristaList().get(j).getDistancia());
                System.out.print("  Origen: (" + ruta.get(i).getAristaList().get(j).getIdOrigen().getCoordX());
                System.out.print(", " + ruta.get(i).getAristaList().get(j).getIdOrigen().getCoordY() + ")  ");
                System.out.print("Destino: (" + ruta.get(i).getAristaList().get(j).getIdDestino().getCoordX());
                System.out.println(", " + ruta.get(i).getAristaList().get(j).getIdDestino().getCoordY() + ")  ");
            }
        }
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
