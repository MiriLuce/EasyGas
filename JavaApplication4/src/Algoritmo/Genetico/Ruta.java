/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmo.Genetico;

import Algoritmo.Constantes.Constantes;
import static Algoritmo.Genetico.AlgoritmoGenetico.mapa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import Modelo.Hibernate.Camion;
import Modelo.Hibernate.Pedido;
import Modelo.Hibernate.TipoCamion;
import Modelo.Hibernate.Disponibilidad;
import Modelo.Hibernate.Nodo;
import Modelo.Hibernate.Arista;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Eduardo
 */
public class Ruta {
    
    private Camion camion;
    private ArrayList<Pedido> listaPedido; //representa el orden en que se atenderan los pedidos
    
    private double cantDieselGlp;
    private double cantDiesel;
    private double cantGLP;
    private int distancia;
    private int difTiempo; // en minutos (HEntrega - HSolicitada)
    private Date salida;
    private Date llegada;
    
    public Ruta(){
        camion = null;
        listaPedido = new ArrayList<Pedido>();
        cantDieselGlp = 0;
        cantDiesel = 0;
        cantGLP = 0;
        distancia = 0;
        difTiempo = 0;
        salida = null;
        llegada = null;
    }
    
    public Ruta(Camion nuevoCamion){
        camion = nuevoCamion;
        listaPedido = new ArrayList<Pedido>();
        cantDieselGlp = 0;
        cantDiesel = 0;
        cantGLP = 0;
        distancia = 0;
        difTiempo = 0;
        salida = null;
        llegada = null;
    }
    
    public Ruta(Ruta ruta){
        
        camion = new Camion(ruta.getCamion());
        
        listaPedido = new ArrayList<Pedido>();
        int cantPedido = ruta.getListaPedido().size(); 
        for(int i=0; i< cantPedido; i++){
            Pedido ped = new Pedido(ruta.getListaPedido().get(i));
            listaPedido.add(ped);
        } 
        
        cantDieselGlp = ruta.getCantDieselGlp();
        cantDiesel = ruta.getCantDiesel();
        cantGLP = ruta.getCantGLP();
        distancia = ruta.getDistancia();
        difTiempo = ruta.getDifTiempo();
        salida = ruta.getSalida();
        llegada = ruta.getLlegada();        
    }
   
    public boolean verificar(){
        if(getListaPedido().size()!=0){
        
            // Primer pedido
            Pedido pedAnt = getListaPedido().get(0), pedPos;
            int tramo = mapa.distanciaMinima(Constantes.posCentralX, Constantes.posCentralY,
                    pedAnt.getPosX(), pedAnt.getPosY());
            Date hora = obtenerTiempo(tramo, pedAnt.getFechaEntrega(), -1);
            if(!salida.equals(hora)) return false;

            for (int i= 1; i< getListaPedido().size(); i++){
                pedPos = getListaPedido().get(i);
                tramo = mapa.distanciaMinima(pedAnt.getPosX(), pedAnt.getPosY(),
                    pedPos.getPosX(), pedPos.getPosY());
                hora = obtenerTiempo(tramo, pedAnt.getFechaEntrega(), 1);
                if(!pedPos.getFechaEntrega().equals(hora)) return false;
                pedAnt = pedPos;
            }
            // Ultimo pedido
            tramo = mapa.distanciaMinima(pedAnt.getPosX(), pedAnt.getPosY(),
                Constantes.posCentralX, Constantes.posCentralY);
            hora = obtenerTiempo(tramo, pedAnt.getFechaEntrega(), 1);
            if(!llegada.equals(hora)) return false;
        }
        return true;
    }
    
    public void imprimir(){
        
        int cantDispon = camion.getDisponibilidads().size();
        Disponibilidad dispon = null;
        boolean verificar = false;
        
        for(int i= 0; i< cantDispon; i++){
            dispon = (Disponibilidad) camion.getDisponibilidads().get(i);
            if(dispon.getHoraInicio().equals(salida) && dispon.getHoraFin().equals(llegada)){
                verificar = true;
                break;
            }
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        System.out.println("Camion " + camion.getEstado());

        System.out.print("  Tara: " + camion.getTipoCamion().getTara());
        System.out.print("  CapGLP: " + camion.getTipoCamion().getCapacidadGlp());
        System.out.println("  CapDiesel: " + camion.getTipoCamion().getCapacidadDiesel());

        System.out.print("Distancia: " + distancia + " Cantidad: " + getListaPedido().size());
        System.out.format("  CantGLP:  %.3f", cantGLP);
        System.out.format("  CantDiesel %.3f\n", cantDiesel);

        System.out.println("Salida: " + sdf.format(salida) + "  Llegada: " + sdf.format(llegada));

        System.out.println("Disponibilidad de camion: " + verificar);

        if (dispon != null)
            System.out.println("Salida: " + sdf.format(dispon.getHoraInicio()) + "  Llegada: " +
                    sdf.format(dispon.getHoraFin()));
        System.out.println();

        for (int j= 0; j< getListaPedido().size(); j++){
            //System.out.print( " -> " + listaPedido.get(j).getIdPedido());
            System.out.format ("-> Pedido N° %03d",  getListaPedido().get(j).getIdPedido());
            System.out.format("  X: %03d", getListaPedido().get(j).getPosX());
            System.out.format("  Y: %03d\n", getListaPedido().get(j).getPosY());
            System.out.print("   S: " + sdf.format(getListaPedido().get(j).getHoraSolicitada()));
            System.out.print(" E: " + sdf.format(getListaPedido().get(j).getFechaEntrega()));
            System.out.println();
        }
        System.out.println();        
    }
    
    private Date obtenerTiempo(int distanciaPedido, Date inicio, int accion ){
        
        double tiempo = distanciaPedido / Constantes.velCamion; 
        Calendar cal = Calendar.getInstance();
        cal.setTime(inicio);
        cal.add(Calendar.MILLISECOND, (int) (accion*tiempo * 3600000));
        return cal.getTime();
    }
    
    public ArrayList<Arista> buscarCamino(Date horaInicio,Nodo inicio, Nodo fin,Pedido pedido){
        // se cambio la logica con la distancia
        Date horaActual=horaInicio;
        ArrayList<Arista> camino=new ArrayList<Arista>();
        int xi,xf,yi,yf;
        xi=inicio.getCoordX();
        xf=fin.getCoordX();
        yi=inicio.getCoordY();
        yf= fin.getCoordY();
        mapa.distanciaMinima(xi,yi,xf,yf);
        if(mapa.caso==3 &&  mapa.ultimoIndice!=-1){
            if(mapa.caminoX[1]!=-1){
                if(yi>yf || xi>xf) {
                    int intercambio=mapa.caminoY[0]; 
                    mapa.caminoY[0]=mapa.caminoY[1];
                    mapa.caminoY[1]=intercambio;
                    intercambio=mapa.caminoX[0];
                    mapa.caminoX[0]=mapa.caminoX[1];
                    mapa.caminoX[1]=intercambio;
                }
                //System.out.print("y:"+m.caminoY[0]  + ", x:" + m.caminoX[0] + " ->" );
                //System.out.print("y:"+m.caminoY[1]  + ", x:" + m.caminoX[1] + " -> " );
            }
            else {
                //System.out.print("y:"+m.caminoY[0]  + ", x:" + m.caminoX[0] + " ->" );
            }
        }
        int tamanho= mapa.ultimoIndice!=-1?mapa.ultimoIndice+3:2;
        int finalCamino[][] = new int [tamanho][2];
        finalCamino[0][0]=xi;
        finalCamino[0][1]=yi;
        if (mapa.ultimoIndice!=-1){
            for(int j=0;j<mapa.ultimoIndice+1;j++){
                finalCamino[j+1][0]=mapa.caminoX[j];
                finalCamino[j+1][1]=mapa.caminoY[j];
            }
        }
        finalCamino[tamanho-1][0]=xf;
        finalCamino[tamanho-1][1]=yf;
       // System.out.println("y:"+yf  + ", x:" + xf + " ->");
       // para guardar en las coordenadas solo hay que recorrer finalCamino con el tamanho
        for(int j=0;j<tamanho-1;j++){
          // System.out.print("y:"+ finalCamino[j][1]  + ", x:" + finalCamino[j][0] + " ->");  
            Nodo nodoA = new Nodo();
            nodoA.setCoordX(finalCamino[j][0]);
            nodoA.setCoordY(finalCamino[j][1]);
            Nodo nodoB = new Nodo();
            nodoB.setCoordX(finalCamino[j+1][0]);
            nodoB.setCoordY(finalCamino[j+1][1]);
            int distancia=0;
            distancia +=nodoA.getCoordX()==nodoB.getCoordX()?Math.abs(nodoA.getCoordY()-nodoB.getCoordY()):0;
            distancia +=nodoA.getCoordY()==nodoB.getCoordY()?Math.abs(nodoA.getCoordX()-nodoB.getCoordX()):0;
            Arista a= new Arista(distancia,nodoA,nodoB);
            a.setHoraInicio(horaActual);
            horaActual=obtenerTiempo(distancia,horaActual,1);
            a.setHoraFin(horaActual);
            if(pedido!=null){
                if(nodoB.getCoordX()==pedido.getPosX() && nodoB.getCoordX()==pedido.getPosY() ) 
                    a.setPedido(new Pedido(pedido));
            }
            camino.add(a);
        }
        return camino;
    }
    
    public Modelo.Hibernate.Ruta guardarEnMapa(){
        ArrayList<Arista> camino = new ArrayList();
        int cantPedido = getListaPedido().size();
        Modelo.Hibernate.Ruta nuevaRuta = new Modelo.Hibernate.Ruta();
        Nodo inicio, fin;
        nuevaRuta.setCamion(camion);
        inicio = new Nodo(Constantes.posCentralX, Constantes.posCentralY);
        fin = new Nodo(getListaPedido().get(0).getPosX(), getListaPedido().get(0).getPosY());
        ArrayList<Arista> tramo = buscarCamino(this.getSalida(),inicio, fin,getListaPedido().get(0));
        for(int j = 0; j< tramo.size(); j++) camino.add(tramo.get(j));   
        
        for(int i= 0; i< cantPedido; i++){
            /*if (i == 0){
                inicio = new modeloCompleto.Nodo(Constantes.posCentralX, Constantes.posCentralY);
                fin = new modeloCompleto.Nodo(listaPedido.get(i).getPosX(), listaPedido.get(i).getPosY());
            }
            else */
            tramo.clear();
            if ( i == cantPedido -1 ){
                inicio = new Nodo(getListaPedido().get(i).getPosX(), getListaPedido().get(i).getPosY());
                fin = new Nodo(Constantes.posCentralX, Constantes.posCentralY);
                tramo = buscarCamino( getListaPedido().get(i).getHoraSolicitada(),inicio, fin,null);
                
            }
            else {
                inicio = new Nodo(getListaPedido().get(i).getPosX(), getListaPedido().get(i).getPosY());
                fin = new Nodo(getListaPedido().get(i+1).getPosX(), getListaPedido().get(i+1).getPosY());
                tramo = buscarCamino(getListaPedido().get(i).getFechaEntrega(),inicio, fin,getListaPedido().get(i+1));
                
            }
            
           
            for(int j = 0; j< tramo.size(); j++) camino.add(tramo.get(j));
        }
        
        nuevaRuta.setCantDiesel((int)cantDiesel);
        nuevaRuta.setAristas(camino);
        nuevaRuta.setCamion(camion);
        nuevaRuta.setHoraLlegada(llegada);
        nuevaRuta.setCantGlp((int)cantDieselGlp);
        nuevaRuta.setDistancia(distancia);
        nuevaRuta.setDiffTiempo(difTiempo);
        nuevaRuta.setHoraLlegada(llegada);
        nuevaRuta.setHoraSalida(salida);
        nuevaRuta.setFechaEntrega(salida);
        return nuevaRuta;
    }
    
    
    
        
    private Camion seleccionarCamion(ArrayList<Camion> camiones, double cantGLP, Date salida, Date llegada){
        int cantCamiones = camiones.size();
        Camion camion, camionEscogido = null;
        Disponibilidad dispon;
        
        for (int c = 0; c < cantCamiones; c++){
            camion = camiones.get(c);
            boolean verificar = true;
            
            // Si tiene capcidad suficiente
            if (camion.getTipoCamion().getCapacidadGlp() >= cantGLP){
                
                // Si tiene disponibilidad suficiente
                int cantDispon = camion.getDisponibilidads().size();
                for(int i= 0; i< cantDispon; i++){
                    dispon = (Disponibilidad) camion.getDisponibilidads().get(i);
                    if (dispon.getHoraInicio().before(salida) && dispon.getHoraFin().after(salida)){
                        verificar = false;
                        break;
                    }
                    else if (dispon.getHoraInicio().before(llegada) && dispon.getHoraFin().after(llegada)){
                        verificar = false;
                        break;
                    }
                    else if (salida.before(dispon.getHoraInicio()) && llegada.after(dispon.getHoraInicio())){
                        verificar = false;
                        break;
                    }
                    else if (salida.before(dispon.getHoraFin()) && llegada.after(dispon.getHoraFin())){
                        verificar = false;
                        break;
                    }
                }
                if (verificar){ //hay disponibilidad en el camion
                    if (camionEscogido == null || camion.getTipoCamion().getCapacidadGlp() <
                        camionEscogido.getTipoCamion().getCapacidadGlp()) 
                        camionEscogido = camion;
                }
            }              
        }
        Disponibilidad disp = new Disponibilidad();
        disp.setCamion(camionEscogido);
        disp.setHoraInicio(salida);
        disp.setHoraFin(llegada);
        //System.out.println("Camion ");
        //if (camionEscogido==null )
        //    System.out.println("Error");
        if (camionEscogido== null) return null;
        camionEscogido.getDisponibilidads().add(disp);
        return camionEscogido; 
    }
    
    private boolean expandirDisponibilidad(Date inicio, Date fin){
    
        int cantDispon = camion.getDisponibilidads().size();
        Disponibilidad dispon, actual = null;
        boolean verificar = true;

        for(int i= 0; i< cantDispon; i++){
            dispon = (Disponibilidad) camion.getDisponibilidads().get(i);
            if (dispon.getHoraInicio().before(inicio) && dispon.getHoraFin().after(inicio)){
                verificar = false;
                break;
            }
            if (dispon.getHoraInicio().before(fin) && dispon.getHoraFin().after(fin)){
                verificar = false;
                break;
            }
            if(dispon.getHoraInicio().equals(salida) && dispon.getHoraFin().equals(llegada)){
                actual = dispon;
            }
        }
        if(verificar) actual.setHoraFin(fin);
        //else System.out.println("no añade"); 
        return verificar;
    }   
    
    private boolean reducirDisponibilidad(Date inicio, Date fin){
    
        int cantDispon = camion.getDisponibilidads().size();
        Disponibilidad dispon, actual = null;
        boolean verificarActual = false, verificarDispon = true;
        
        for(int i= 0; i< cantDispon; i++){
            dispon = (Disponibilidad) camion.getDisponibilidads().get(i);
            
            if(dispon.getHoraInicio().equals(inicio) && dispon.getHoraFin().equals(fin)){
                actual = dispon;
                verificarActual = true;
            }
            else if (!inicio.equals(salida)){ // buscar disponibilidad si se ha movido
                if (dispon.getHoraInicio().before(inicio) && dispon.getHoraFin().after(inicio))
                    verificarDispon = false;
                else if (dispon.getHoraInicio().before(fin) && dispon.getHoraFin().after(fin))
                    verificarDispon = false;
                else if (inicio.before(dispon.getHoraInicio()) && fin.after(dispon.getHoraInicio())){
                    verificarDispon = false;
                }
                else if (inicio.before(dispon.getHoraFin()) && fin.after(dispon.getHoraFin())){
                    verificarDispon = false;
                }
            }
        }
        
        if(verificarActual && verificarDispon){ 
            actual.setHoraInicio(salida);
            actual.setHoraFin(llegada);
            return true;
        }
        return false;
    }  
    
    private boolean verificarDiesel(TipoCamion tp, int distancia, int tramo, double glp){
        double diesel = tp.getTara() * (distancia) ;
        double dieselPeso =  tramo * glp;
        diesel = Algoritmo.Constantes.Constantes.factorDiesel * (diesel + dieselPeso);
        return diesel <= tp.getCapacidadDiesel();
    }
    
    public boolean agregarPedido(ArrayList<Camion> camiones, Pedido pedido){
       
        int cantPedidoRuta = getListaPedido().size();
        boolean estaAsignado = false; 
        int distanciaPedido = 0, regreso;
        Date horaEntrega = null, horaLlegada;
        TipoCamion tp = camion == null ? null : camion.getTipoCamion();
        
        // Verifica si la capacidad restante de GLP es suficiente
        if(camion == null || cantGLP + pedido.getCantGlp() <= tp.getCapacidadGlp()){
                    
            if(cantPedidoRuta == 0){
                // La ruta no tiene pedidos asignados
                
               
                distanciaPedido =  mapa.distanciaMinima(Constantes.posCentralX,Constantes.posCentralY,pedido.getPosX(), pedido.getPosY());
                regreso = mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(), Constantes.posCentralX,Constantes.posCentralY);
                
                horaLlegada = obtenerTiempo(regreso, pedido.getHoraSolicitada(), 1);
                horaEntrega = pedido.getHoraSolicitada();
                Date horaSalida = obtenerTiempo(distanciaPedido,pedido.getHoraSolicitada(), -1);
                
                // como no hay pedidos no hay camion asignado
                camion = seleccionarCamion(camiones, pedido.getCantGlp(), horaSalida, horaLlegada);
                
                if (camion == null ) return false;
                tp = camion.getTipoCamion();
                
                boolean verificarDiesel = verificarDiesel(tp, distancia + distanciaPedido + regreso,
                        distancia + distanciaPedido, pedido.getCantGlp());
                
                if(verificarDiesel){
                    estaAsignado = true;
                    salida = horaSalida;
                }
            }
            else{
                Pedido ultimoPedido =  getListaPedido().get(cantPedidoRuta-1);
                distanciaPedido = mapa.distanciaMinima(ultimoPedido.getPosX(), ultimoPedido.getPosY(),
                                pedido.getPosX(), pedido.getPosY());
                int regresoUltimo = mapa.distanciaMinima(ultimoPedido.getPosX(), ultimoPedido.getPosY(),
                                Constantes.posCentralX,Constantes.posCentralY);
                regreso = mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(),
                                Constantes.posCentralX,Constantes.posCentralY);
                
                horaEntrega = obtenerTiempo(distanciaPedido, ultimoPedido.getFechaEntrega(), 1);
                horaLlegada = obtenerTiempo(regreso, horaEntrega, 1);
                
                if(horaEntrega.after(pedido.getHoraSolicitada())){

                    boolean verificarDiesel = verificarDiesel(tp, distancia - regresoUltimo + distanciaPedido + regreso,
                        distancia - regresoUltimo + distanciaPedido, pedido.getCantGlp());
                    
                    if(verificarDiesel){
                        estaAsignado =  expandirDisponibilidad(salida, horaLlegada);
                        if(estaAsignado) distancia -= regresoUltimo;
                    }
                }                
            }
            if(estaAsignado){
                cantGLP += pedido.getCantGlp();
                cantDieselGlp += (distancia + distanciaPedido) * pedido.getCantGlp(); // acumula peso total
                distancia += distanciaPedido + regreso;
                llegada = horaLlegada;
                pedido.setFechaEntrega(horaEntrega);
                getListaPedido().add(pedido);                
                int diff = (int) pedido.getFechaEntrega().getTime() - (int)pedido.getHoraSolicitada().getTime(); // milisegundos
                difTiempo += (diff) / 60000; // a minutos
            }
        }
        
        return estaAsignado;
    }
    
    public boolean cerrarRuta(){
        
        if(getListaPedido().size() == 0) return false;
        else{
            /*
            Pedido ultimoPedido =  listaPedido.get(listaPedido.size()-1);
            int regreso = mapa.distanciaMinima(ultimoPedido.getPosX(), ultimoPedido.getPosY(),
                    Constantes.posCentralX,Constantes.posCentralY);
            Date hora = obtenerTiempo(regreso,ultimoPedido.getFechaEntrega(), 1);
            if(llegada== null || llegada.compareTo(hora) != 0){
                distancia  += regreso;
                Date antLlegada = llegada;
                llegada = hora;
                reducirDisponibilidad(salida, antLlegada);                                
                System.out.println("cerrar");
            }
            double pesoTotal = ( camion.getTipoCamion().getTara() + cantGLP ) ; // galones
            cantDiesel = 0.05 *( pesoTotal / 52 * 1.0) * distancia ;  // galones y km
            return true;
            */
            double diselTara = camion.getTipoCamion().getTara() * distancia;
            cantDiesel = Algoritmo.Constantes.Constantes.factorDiesel * (diselTara + cantDieselGlp); 
            return true;
        }
    }
    
    
    public boolean quitarPedido(int indicePedido){
        
        int cantPedidos = getListaPedido().size(); 
        // cuando el pedido es el unico en la ruta
        if (indicePedido == 0 && cantPedidos == 1){
            getListaPedido().remove(indicePedido);
            return true;
        }        
        
        Pedido pedido = getListaPedido().get(indicePedido);
        int posXAnt, posYAnt, posXPos, posYPos, dist = 0;
        boolean verificar = true;
        int entregaAntigua, entregaNueva;
        Date finRuta = llegada, inicioRuta = salida;
        
        // cuando el pedido primer pedido de la ruta
        if (indicePedido == 0){ 
            posXAnt = Constantes.posCentralX;
            posYAnt = Constantes.posCentralY; 
            posXPos = getListaPedido().get(1).getPosX();
            posYPos = getListaPedido().get(1).getPosY();
            
            entregaAntigua = (int)getListaPedido().get(1).getFechaEntrega().getTime();
            
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            salida = obtenerTiempo(dist, getListaPedido().get(1).getHoraSolicitada(), -1);
            getListaPedido().get(1).setFechaEntrega(getListaPedido().get(1).getHoraSolicitada());
            
            entregaNueva = (int)getListaPedido().get(1).getFechaEntrega().getTime();
            difTiempo -= ( ( entregaAntigua - entregaNueva ) / 60000 )  ;
        } 
        // cuando el pedido ultimo pedido de la ruta
        else if (indicePedido == cantPedidos -1){  
            posXAnt = getListaPedido().get(cantPedidos - 2).getPosX();
            posYAnt = getListaPedido().get(cantPedidos - 2).getPosY();
            posXPos = Constantes.posCentralX;
            posYPos = Constantes.posCentralY; 
            
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            llegada = obtenerTiempo(dist, getListaPedido().get(cantPedidos - 2).getFechaEntrega(), 1);
            verificar = false;
        } 
        // cuando el pedido esta en medio de la lista de la ruta
        else{
            posXAnt = getListaPedido().get(indicePedido - 1).getPosX();
            posYAnt = getListaPedido().get(indicePedido - 1).getPosY();
            posXPos = getListaPedido().get(indicePedido + 1).getPosX();
            posYPos = getListaPedido().get(indicePedido + 1).getPosY();
            
            entregaAntigua = (int)getListaPedido().get(indicePedido + 1).getFechaEntrega().getTime();
            
            dist = mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
            Date entrega = obtenerTiempo(dist, getListaPedido().get(indicePedido - 1).getFechaEntrega(), 1);
            getListaPedido().get(indicePedido + 1).setFechaEntrega(entrega);
            
            entregaNueva = (int)entrega.getTime();
            difTiempo -= ( ( entregaAntigua - entregaNueva ) / 60000 )  ;
        }
        
        //System.out.println("Distancia: " + dist);
        cantGLP -= pedido.getCantGlp();
        distancia -= mapa.distanciaMinima(posXAnt, posYAnt, pedido.getPosX(), pedido.getPosY());
        distancia -= mapa.distanciaMinima(pedido.getPosX(), pedido.getPosY(), posXPos, posYPos);
        distancia += dist; //!=0 ? dist : mapa.distanciaMinima(posXAnt, posYAnt, posXPos, posYPos);
        int diff = (int)pedido.getFechaEntrega().getTime() - (int)pedido.getHoraSolicitada().getTime();
        difTiempo -= ( diff / 6000 );
        getListaPedido().remove(indicePedido);
        cantPedidos--;
        
        if (!verificar){
            reducirDisponibilidad(inicioRuta, finRuta);
            return true;
        }
        
        // Calcular hora de entrega de cada pedido
        Pedido pedidoAnterior = getListaPedido().get(indicePedido);
        Pedido pedidoActual = null;
        
        for(int i = indicePedido + 1 ; i < cantPedidos; i++){
            pedidoActual = getListaPedido().get(i);
            int tramo = mapa.distanciaMinima(pedidoAnterior.getPosX(), pedidoAnterior.getPosY(),
                pedidoActual.getPosX(), pedidoActual.getPosY());         
            
            entregaAntigua = (int)pedidoActual.getFechaEntrega().getTime(); 
            Date entregaActual = obtenerTiempo(tramo, pedidoAnterior.getFechaEntrega(), 1);
            if (entregaActual.before(pedidoActual.getHoraSolicitada()))
                return false;
            
            getListaPedido().get(i).setFechaEntrega(entregaActual);
            entregaNueva = (int)entregaActual.getTime(); 
            difTiempo -= ( ( entregaAntigua - entregaNueva ) / 6000 )  ;
            pedidoAnterior = pedidoActual;
        }
        int tramo = mapa.distanciaMinima(pedidoAnterior.getPosX(), pedidoAnterior.getPosY(),
                Constantes.posCentralX, Constantes.posCentralY); 
        llegada = obtenerTiempo(tramo, pedidoAnterior.getFechaEntrega(), 1);
        
        reducirDisponibilidad(inicioRuta, finRuta);
        return true;
    }
       
    public void permutarPedidos(){
        
        int cantPedidos = getListaPedido().size();
        int[] arrayPedidos = new int[cantPedidos];
        Arrays.fill(arrayPedidos, 0);
        
        arrayPedidos[0] = 1;
        Pedido pedAnterior = getListaPedido().get(0);
        long diffActual = pedAnterior.getFechaEntrega().getTime() - pedAnterior.getHoraSolicitada().getTime();
        boolean verificar = ordenarPedidos(arrayPedidos, cantPedidos, 1, pedAnterior, diffActual);
        
        if (verificar){
            int contador = 0;
            ArrayList<Pedido> listaPedidoOrdenado = new ArrayList<Pedido>();
            while(contador < cantPedidos){
                contador++;
                for(int i = 0; i < cantPedidos; i++){
                    if (contador == arrayPedidos[i]){
                        listaPedidoOrdenado.add(getListaPedido().get(i));
                        break;
                    }
                }
            }
            listaPedido = listaPedidoOrdenado;
            calcularRuta();
        }
    } 
    
    public void calcularRuta(){
        int cantPedidos = getListaPedido().size();
        if(cantPedidos != 0){
            distancia = 0;
            difTiempo = 0;
            cantGLP = 0;
            cantDiesel = 0;
            // Primer pedido
            Pedido pedAnt = getListaPedido().get(0), pedPos;
            int tramo = mapa.distanciaMinima(Constantes.posCentralX, Constantes.posCentralY,
                    pedAnt.getPosX(), pedAnt.getPosY());
            Date hora = obtenerTiempo(tramo, pedAnt.getFechaEntrega(), -1);
            distancia += tramo;
            salida = hora;
            cantGLP += pedAnt.getCantGlp();
            cantDiesel += pedAnt.getCantGlp()* distancia; // toneladas / KM
            pedAnt.setFechaEntrega(pedAnt.getHoraSolicitada());
            
            
            for (int i= 1; i< cantPedidos; i++){
                pedPos = getListaPedido().get(i);
                tramo = mapa.distanciaMinima(pedAnt.getPosX(), pedAnt.getPosY(),
                    pedPos.getPosX(), pedPos.getPosY());
                hora = obtenerTiempo(tramo, pedAnt.getFechaEntrega(), 1);
                distancia += tramo;
                pedPos.setFechaEntrega(hora);
                cantGLP += pedPos.getCantGlp();
                cantDiesel += pedPos.getCantGlp()* distancia; 
                difTiempo += pedPos.getFechaEntrega().getTime() - hora.getTime();
                pedAnt = pedPos;
            }
            // Ultimo pedido
            tramo = mapa.distanciaMinima(pedAnt.getPosX(), pedAnt.getPosY(),
                Constantes.posCentralX, Constantes.posCentralY);
            hora = obtenerTiempo(tramo, pedAnt.getFechaEntrega(), 1);
            distancia += tramo;
            cantDiesel += camion.getTipoCamion().getTara()*distancia;
            cantDiesel = 0.05*cantDiesel/52.0;
            llegada = hora;
        }
    }
    
    private int seleccionarPedido(int[] arrayPedidos, int cantPedidos, Pedido pedAnterior){
        
        boolean verificar = false;
        int indice, tramo;
        Pedido pedActual;
        Date hora;
        
        do{
            indice = seleccionarElemento(arrayPedidos, cantPedidos); 
            if (indice != -1) break;                
            arrayPedidos[indice] = -1;
            pedActual = getListaPedido().get(indice);
            tramo = mapa.distanciaMinima(pedAnterior.getPosX(), pedAnterior.getPosY(),
                pedActual.getPosX(), pedActual.getPosY());
            hora = obtenerTiempo(tramo, pedAnterior.getFechaEntrega(), 1);  
            if (hora.after(pedActual.getHoraSolicitada()))
                verificar = true;
        } while (!verificar);
        
        if (indice != -1) return -1;
        return indice;
    }
    
    private boolean ordenarPedidos(int[] arrayPedidos, int cantPedidos, int cantSeleccionados,
            Pedido pedAnterior,  long diffActual){
        
        int[] arrayPedidosRestantes = new int[cantPedidos];
        for (int i = 0; i < cantPedidos; i++)
            arrayPedidos[i] = arrayPedidosRestantes[i]; 
        
        int indice, tramo; 
        boolean verificar = false;
        long diffPedido = 0;
        Pedido pedActual=null;
        Date hora=null;
        
        // Si aun falta seleccionar pedidos
        while (!verificar){ 
            
            indice = seleccionarPedido(arrayPedidosRestantes, cantPedidos, pedAnterior);
            if (indice == -1) break;
            cantSeleccionados++;
            arrayPedidos[indice] = cantSeleccionados;
            
            pedActual = getListaPedido().get(indice);
            tramo = mapa.distanciaMinima(pedAnterior.getPosX(), pedAnterior.getPosY(),
                pedActual.getPosX(), pedActual.getPosY());
            hora = obtenerTiempo(tramo, pedAnterior.getFechaEntrega(), 1);
            diffPedido = hora.getTime() - pedActual.getHoraSolicitada().getTime();
            
            // Se selecciono ultimo pedido
            if (cantPedidos == cantSeleccionados){
                if (diffActual + diffPedido < this.difTiempo) return true;
                else return false;
            }
            verificar = ordenarPedidos(arrayPedidos, cantSeleccionados, cantPedidos, pedActual, diffActual + diffPedido);
        }        
        // Ya todos los pedidos fueron seleccionados
        if (verificar)return true;
        else return false;      
    }
    
    private int generaNumRandom(int min, int max) {

        int numRandom = ThreadLocalRandom.current().nextInt(min, max + 1);
        return numRandom;
    }
    
    private int seleccionarElemento(int[] arreglo, int cantidad){
        
        int indice = generaNumRandom(0, cantidad - 1);     
        int contador = 0;
        
        // Verifico su el cromosoma no ha sido seleccionado
        while (arreglo[indice] != 0){                 
            indice =  generaNumRandom(0, cantidad - 1);
            contador ++;
            if (contador == cantidad - 1) break;
        }
         // Verifico si no es el ultimo
        if (arreglo[indice] != 0)  return -1;
        else return indice;
    }
    
    /**
     * @return the listaPedido
     */
    public ArrayList<Pedido> getListaPedido() {
        return listaPedido;
    }
    
    /**
     * @param listaPedido the listaPedido to set
     */
    public void setListaPedido(ArrayList<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }
    
    /**
     * @return the camion
     */
    public Camion getCamion() {
        return camion;
    }

    /**
     * @param camion the camion to set
     */
    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    /**
     * @return the cantDiesel
     */
    public double getCantDiesel() {
        return cantDiesel;
    }

    /**
     * @param cantDiesel the cantDiesel to set
     */
    public void setCantDiesel(double cantDiesel) {
        this.cantDiesel = cantDiesel;
    }

    /**
     * @return the cantGLP
     */
    public double getCantGLP() {
        return cantGLP;
    }

    /**
     * @param cantGLP the cantGLP to set
     */
    public void setCantGLP(double cantGLP) {
        this.cantGLP = cantGLP;
    }

    /**
     * @return the distancia
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the salida
     */
    public Date getSalida() {
        return salida;
    }

    /**
     * @param salida the salida to set
     */
    public void setSalida(Date salida) {
        this.salida = salida;
    }

    /**
     * @return the llegada
     */
    public Date getLlegada() {
        return llegada;
    }

    /**
     * @param llegada the llegada to set
     */
    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }

    /**
     * @return the difTiempo
     */
    public int getDifTiempo() {
        return difTiempo;
    }

    /**
     * @param difTiempo the difTiempo to set
     */
    public void setDifTiempo(int difTiempo) {
        this.difTiempo = difTiempo;
    }

    /**
     * @return the cantDieselGlp
     */
    public double getCantDieselGlp() {
        return cantDieselGlp;
    }

    /**
     * @param cantDieselGlp the cantDieselGlp to set
     */
    public void setCantDieselGlp(double cantDieselGlp) {
        this.cantDieselGlp = cantDieselGlp;
    }

    

    
    
}
