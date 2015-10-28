/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import modelo.Camion;

/**
 *
 * 
 * @author Eduardo
 */
public class Cromosoma {

    ArrayList<MiRuta> cadena; //representa una lista de  //representa el orden en que se atenderan los pedidos

    Mapa mapa;
    Date horaActual;

    double cantDiesel;

    double costo;
    boolean esAberracion;

    int distancia;
    double sumatoriaGLP;
    double sumatoriaTEntrega;
    double cantGLPEntregado;
    int cantPedidosPrioridad;

    public Cromosoma(Mapa nuevoMapa) throws FileNotFoundException {
        cadena = new ArrayList<MiRuta>();
        costo = 0;
        esAberracion = false;
        mapa = nuevoMapa;
    }

    private Date horaLlegada(int xi, int yi, int xf, int yf, int v) {

        int d = mapa.distanciaMinima(xi, yi, xf, yf);

        double t = d / v; //tiempo en horas que se demora en recorrer de un punto a otro

        Calendar cal = Calendar.getInstance();
        cal.setTime(horaActual);

        cal.add(Calendar.MILLISECOND, (int) (t * 3600000));

        return cal.getTime();
    }

    public double obtenCarga(ArrayList<Entrega> r) {
        double c = 0;

        for (int i = 0; i < r.size(); i++) {
            c += r.get(i).cantGLP;
        }

        return c;
    }

    private boolean sobrepasaCarga(MiRuta mr) {
        Camion c = mr.camion;
        ArrayList<Entrega> r = mr.lstEntrega;

        double sum = obtenCarga(r);

        if (sum > c.getIdTipoCamion().getCapacidadGLP()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean esSuficienteDiesel(int actualX, int actualY, int xf, int yf, double cantGLP, double pesoTara) {
        double d = mapa.distanciaMinima(actualX, actualY, xf, yf);

        double c = 0.05 * (pesoTara + cantGLP) / 52;

        double d2 = cantDiesel / c;

        if (d2 >= d) {
            cantDiesel -= c * d;
            return true;
        } else {
            return false;
        }
    }

    public boolean alcanzaCombustible(MiRuta mr) {
        Camion c = mr.camion;
        ArrayList<Entrega> r = mr.lstEntrega;

        int actualX = Constantes.posInicialX;
        int actualY = Constantes.posInicialY;
        double cantGLP = obtenCarga(r);
        cantDiesel = c.getIdTipoCamion().getCapacidadDiesel();
        double pesoTara = c.getIdTipoCamion().getTara();

        for (int i = 0; i < r.size(); i++) {

            if (!esSuficienteDiesel(actualX, actualY, r.get(i).posX, r.get(i).posY, cantGLP, pesoTara)) {
                return false;
            }
            actualX = r.get(i).posX;
            actualY = r.get(i).posY;
            cantGLP -= r.get(i).cantGLP;
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

    private void calculaDistanciaTotal(int xi, int yi, ArrayList<Entrega> lista) {
        int actualX = xi;
        int actualY = yi;

        for (int i = 0; i < lista.size(); i++) {
            Entrega e = lista.get(i);
            distancia += mapa.distanciaMinima(actualX, actualY, e.posX, e.posY);
            actualX = e.posX;
            actualY = e.posY;
        }

        //distancia += mapa.distanciaMinima(actualX, actualY, Constantes.posCentralX, Constantes.posCentralY); //distancia hasta la base
    }

    private void calculaSumatoriaDifGLP(Camion c, ArrayList<Entrega> lista) {
        double capacidadGLP = c.getIdTipoCamion().getCapacidadGLP();

        for (int i = 0; i < lista.size(); i++) {
            sumatoriaGLP += lista.get(i).cantGLP;
        }
        sumatoriaGLP=capacidadGLP-sumatoriaGLP;
    }

    private void calculaSumatoriaDifTiemposEntrega(int xi, int yi, Camion c, ArrayList<Entrega> lista) {

        Date horaf;
        long dif;

        for (int i = 0; i < lista.size(); i++) {
            horaf = horaLlegada(xi, yi, lista.get(i).posX, lista.get(i).posY, Constantes.velCamion);
            
            dif = horaf.getTime() - lista.get(i).horaSolicitada.getTime();

            /*if (dif < 0) //si llega antes de la hora solicitada
            {
                sumatoriaTEntrega += (-1*dif); //en minutos
            }
            else{
                sumatoriaTEntrega += dif / 60000; //en minutos
            }
            */
            sumatoriaTEntrega += dif / 60000; //en minutos
            
            
            xi = lista.get(i).posX;
            yi = lista.get(i).posY;
            horaActual = horaf;
        }

    }

    private void calculaCantPedidosPrioridad(ArrayList<Entrega> lista) {

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).tienePrioridad) {
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

            MiRuta nuevaRuta = cadena.get(j);
            distancia = 0;
            sumatoriaGLP = 0;
            sumatoriaTEntrega = 0;
            cantGLPEntregado = 0;
            horaActual = Constantes.obtenHoraActual(); // hora inicio
            cantPedidosPrioridad = 0;

            calculaDistanciaTotal(Constantes.posInicialX, Constantes.posInicialY, nuevaRuta.lstEntrega);
            calculaSumatoriaDifGLP(nuevaRuta.camion, nuevaRuta.lstEntrega);
            calculaSumatoriaDifTiemposEntrega(Constantes.posInicialX, Constantes.posInicialY, nuevaRuta.camion, nuevaRuta.lstEntrega);
            cantGLPEntregado = obtenCarga(nuevaRuta.lstEntrega);
            calculaCantPedidosPrioridad(nuevaRuta.lstEntrega);
            
            if (cantGLPEntregado == 0) {
                cantGLPEntregado = 1;
            }
            
            
                                                                                  

            costo += ((sumatoriaTEntrega * sumatoriaGLP * distancia) / (0.5 * cantPedidosPrioridad + cantGLPEntregado*1000 )); //FO
        }
       // System.out.println("costo calculado " + costo);
        return true;
    }
}
