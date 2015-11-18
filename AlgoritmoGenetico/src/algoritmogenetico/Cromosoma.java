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
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * 
 * @author Eduardo
 */
public class Cromosoma {

    //representa una lista de  //representa el orden en que se atenderan los pedidos
    ArrayList<Ruta> cadena; 
    //Mapa mapa;
    Date horaActual;
    double costo;
    boolean esAberracion;

    int distancia;
    double sumatoriaGLP;
    double sumatoriaTEntrega;
    
    double cantGLPEntregado;
    double cantDiesel;
    
    int cantPedidosPrioridad;

    public Cromosoma(Mapa nuevoMapa) throws FileNotFoundException {
        cadena = new ArrayList<Ruta>();
        costo = 0;
        esAberracion = false;
        //mapa = nuevoMapa;
    }
    
    public Cromosoma(){
        //mapa = AlgoritmoGenetico.mapa;
        cadena = new ArrayList<Ruta>();
        esAberracion = false;      
        costo = 0;          
    }
    
    private Date horaLlegada(int xi, int yi, int xf, int yf, int v) {

        int d = AlgoritmoGenetico.mapa.distanciaMinima(xi, yi, xf, yf);

        double t = d / v; //tiempo en horas que se demora en recorrer de un punto a otro

        Calendar cal = Calendar.getInstance();
        cal.setTime(horaActual);

        cal.add(Calendar.MILLISECOND, (int) (t * 3600000));

        return cal.getTime();
    }

    public double obtenCarga(ArrayList<Pedido> listaPedido) {
        
        double cantGLP = 0;
        for (int i = 0; i < listaPedido.size(); i++) {
            cantGLP += listaPedido.get(i).getCantGLP();
        }
        return cantGLP;
    }

    private boolean sobrepasaCarga(Ruta mr) {
        Camion c = mr.getCamion();
        ArrayList<Pedido> r = mr.getListaPedido();

        double sum = obtenCarga(r);

        if (sum > c.getTipoCamion().getCapacidadGLP()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean esSuficienteDiesel(int actualX, int actualY, int xf, int yf, double cantGLP, double pesoTara) {
        double d = AlgoritmoGenetico.mapa.distanciaMinima(actualX, actualY, xf, yf);

        double c = 0.05 * (pesoTara + cantGLP) / 52;

        double d2 = cantDiesel / c;

        if (d2 >= d) {
            cantDiesel -= c * d;
            return true;
        } else {
            return false;
        }
    }

    public boolean alcanzaCombustible(Ruta mr) {
        Camion c = mr.getCamion();
        ArrayList<Pedido> r = mr.getListaPedido();

        int actualX = Constantes.posInicialX;
        int actualY = Constantes.posInicialY;
        double cantGLP = obtenCarga(r);
        cantDiesel = c.getTipoCamion().getCapacidadDiesel();
        double pesoTara = c.getTipoCamion().getTara();

        for (int i = 0; i < r.size(); i++) {

            if (!esSuficienteDiesel(actualX, actualY, r.get(i).getPosX(), r.get(i).getPosY(), cantGLP, pesoTara)) {
                return false;
            }
            actualX = r.get(i).getPosX();
            actualY = r.get(i).getPosY();
            cantGLP -= r.get(i).getCantGLP();
        }

        if (!esSuficienteDiesel(actualX, actualY, Constantes.posCentralX, Constantes.posCentralY, cantGLP, pesoTara)) {
            return false;
        }

        return true;
    }

    public boolean verificaAberracion() {
        calculaCosto();
        esAberracion=false;
        if (costo<0) esAberracion=true;
        else {
            for (int i = 0; i < cadena.size(); i++) {
                //verifica que la carga total de la ruta de un camion no sobrepase su capacidad de GLP
                if (sobrepasaCarga(cadena.get(i))) {
                    esAberracion = true;
                }

                //verifica que a cada camion le alcanza gasolina para toda la ruta
                if (!alcanzaCombustible(cadena.get(i))) {
                    esAberracion = true;
                }
            }
        }
        return esAberracion;
       

    }

    private void calculaDistanciaTotal(int xi, int yi, ArrayList<Pedido> lista) {
        int actualX = xi;
        int actualY = yi;

        for (int i = 0; i < lista.size(); i++) {
            Pedido e = lista.get(i);
            distancia += AlgoritmoGenetico.mapa.distanciaMinima(actualX, actualY, e.getPosX(), e.getPosY());
            actualX = e.getPosX();
            actualY = e.getPosY();
        }

        //distancia += mapa.distanciaMinima(actualX, actualY, Constantes.posCentralX, Constantes.posCentralY); //distancia hasta la base
    }

    private void calculaSumatoriaDifGLP(Camion c, ArrayList<Pedido> lista) {
        double capacidadGLP = c.getTipoCamion().getCapacidadGLP();

        for (int i = 0; i < lista.size(); i++) {
            sumatoriaGLP += lista.get(i).getCantGLP();
        }
        sumatoriaGLP=capacidadGLP-sumatoriaGLP;
    }

    private void calculaSumatoriaDifTiemposEntrega(int xi, int yi, Camion c, ArrayList<Pedido> lista) {

        Date horaf;
        long dif;

        for (int i = 0; i < lista.size(); i++) {
            horaf = horaLlegada(xi, yi, lista.get(i).getPosX(), lista.get(i).getPosY(), Constantes.velCamion);
            
            dif = horaf.getTime() - lista.get(i).getHoraSolicitada().getTime();

            /*if (dif < 0) //si llega antes de la hora solicitada
            {
                sumatoriaTEntrega += (-1*dif); //en minutos
            }
            else{
                sumatoriaTEntrega += dif / 60000; //en minutos
            }
            */
            sumatoriaTEntrega += dif / 60000; //en minutos
            
            
            xi = lista.get(i).getPosX();
            yi = lista.get(i).getPosY();
            horaActual = horaf;
        }

    }

    private void calculaCantPedidosPrioridad(ArrayList<Pedido> lista) {

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).isTienePrioridad()) {
                cantPedidosPrioridad++;
            }
        }

    }

    public boolean calculaCosto() {
        /*
         double arriba=distancia*difGLP*diferenciaTiemposEntregas;
           // double abajo=(p.getCantGLP()*1000);
            double abajo=(p.getCantGLP()*1000+0.5*cantPrioridades);
            double costo=arriba/abajo;*/
        for (int j = 0; j < cadena.size(); j++) { //para cada ruta de cada camion

            Ruta nuevaRuta = cadena.get(j);
            distancia = 0;
            sumatoriaGLP = 0;
            sumatoriaTEntrega = 0;
            cantGLPEntregado = 0;
            horaActual = Constantes.obtenHoraActual(); // hora inicio
            cantPedidosPrioridad = 0;

            calculaDistanciaTotal(Constantes.posInicialX, Constantes.posInicialY, nuevaRuta.getListaPedido());
            calculaSumatoriaDifGLP(nuevaRuta.getCamion(), nuevaRuta.getListaPedido());
            calculaSumatoriaDifTiemposEntrega(Constantes.posInicialX, Constantes.posInicialY, nuevaRuta.getCamion(), nuevaRuta.getListaPedido());
            cantGLPEntregado = obtenCarga(nuevaRuta.getListaPedido());
            calculaCantPedidosPrioridad(nuevaRuta.getListaPedido());
            
            if (cantGLPEntregado == 0) {
                cantGLPEntregado = 1;
            }
            
            
                                                                                  

            costo += ((sumatoriaTEntrega * sumatoriaGLP * distancia) / (0.5 * cantPedidosPrioridad + cantGLPEntregado*1000 )); //FO
        }
       // System.out.println("costo calculado " + costo);
        return true;
    }
}
