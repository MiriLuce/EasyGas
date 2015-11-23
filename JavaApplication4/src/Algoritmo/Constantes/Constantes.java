/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmo.Constantes;

import Modelo.Hibernate.Turno;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MiriamLucero
 */
public class Constantes {
    
    public static int cantPoblacion = 6; //debe ser 100 o multiplos de 100
    public static double probSeleccion = 0.5; //no debe ser mayor a 0.5
    public static double probEmparejamiento = 0.75;
    public static double probMutacion = 0.05;
    public static int velCamion=50;
    public static ArrayList<Turno> lTurnos = new ArrayList<Turno>();
    // todos los camiones empezaran en la central
    public static int posInicialX = 100;
    public static int posInicialY = 100;
    public static int posCentralX = 100;
    public static int posCentralY = 100;
    /*
    public static Mapa obtenMapa() throws IOException{
        Mapa mapa = new Mapa(201, 301);
        return mapa;
    }
    */

     public static Date obtenHoraActual() {
       
        Date ahora= new Date();
        Date horaActual= new Date();
        String horaActualString = new SimpleDateFormat("yyyy-MM-dd").format(ahora);
        String horaActualStringFinal= horaActualString + " 06:40:00";
        Date finalHoraActual = new Date();
        try {
            finalHoraActual= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(horaActualStringFinal);
        } catch (ParseException ex) {
            Logger.getLogger(Constantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finalHoraActual;
    }
    

     /*
    public static void leeDataset(int n) {
        Scanner s = new Scanner(new File("dataset-" + n + ".txt"));

        Principal.lstCamiones = new ArrayList<Camion>();
        Principal.lstPedidos = new ArrayList<Pedido>();

        velCamion = s.nextInt(); //velocidad

        int[][] tipos = new int[4][4];
        ArrayList<TipoCamion> listaTipos = new ArrayList<TipoCamion>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tipos[i][j] = s.nextInt(); //lee matriz
            }
        }

        for (int i = 0; i < 4; i++) { //nuevo Tipo
            TipoCamion nuevoTipo = new TipoCamion();
            nuevoTipo.setTara(tipos[i][1]);
            nuevoTipo.setCapacidadGLP(tipos[i][2]);
            nuevoTipo.setCapacidadDiesel(tipos[i][3]);

            for (int j = 0; j < tipos[i][0]; j++) { //se añaden camiones
                Camion nuevoCamion = new Camion();
                nuevoCamion.setTipoCamion(nuevoTipo);
                Principal.lstCamiones.add(nuevoCamion);
            }
        }

        posCentralX = s.nextInt();
        posCentralY = s.nextInt();

        int cantPedidos = s.nextInt();

        for (int i = 0; i < cantPedidos; i++) {
            Pedido nuevoPedido = new Pedido();

            String formatoHora = "HH:mm:ss";
            Date nuevaHora = new SimpleDateFormat(formatoHora).parse(s.next());

            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, nuevaHora.getHours());
            c.set(Calendar.MINUTE, nuevaHora.getMinutes());
            c.set(Calendar.SECOND, nuevaHora.getSeconds());

            double nuevaCantGLP = Double.parseDouble(s.next());

            int nuevoX = s.nextInt();
            int nuevoY = s.nextInt();

            int tipoCliente = s.nextInt();

            Nodo nuevoNodo = new Nodo(nuevoX, nuevoY, true);

            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setDireccion(nuevoNodo);

            if (tipoCliente == 1) {
                nuevoCliente.setTipoDocumento("DNI");
            } else if (tipoCliente == 2) {
                nuevoCliente.setTipoDocumento("RUC");
            }
            nuevoPedido.setIdPedido(i);
           
            nuevoPedido.setCantGLP(nuevaCantGLP);
            nuevoPedido.setHoraSolicitada(c.getTime());
            nuevoPedido.setCliente(nuevoCliente);

            Principal.lstPedidos.add(nuevoPedido);
           
        }
       
        
    }*/

}
