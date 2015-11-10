/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;


import Mapa.Ciudades.CiudadXYZ;
import Mapa.Utilidades.Pantalla;
import Modelo.Constantes.EasyGas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Eduardo
 */
public class Mapa implements Runnable {

    private Pantalla pantalla;
    public int ancho, alto;
    public String titulo;

    private Thread hilo;

    private BufferStrategy bs;
    private Graphics g;

    private boolean corriendo = false;

    private float camX, camY;

    CiudadXYZ ciudadXYZ;

    Camara camara;
    
    CamionMapa camion;
    
    ClienteMapa cliente;
    
    CentralMapa central;
    
    private Teclado teclado;

    public Mapa(String t, int an, int al) {
        titulo = t;
        ancho = an;
        alto = al;
        teclado = new Teclado();
    }

    private void Inicializa() {
        pantalla = new Pantalla(titulo,ancho, alto);
        pantalla.ObtenFrame().addKeyListener(teclado);
        EasyGas.Inicializa();

        camara = new Camara(this, 0, 0);

        camX = 2f; //auxiliar
        camY = 2f; //auxiliar

        ciudadXYZ = new CiudadXYZ(this);
        
        camion = new CamionMapa(this, 1, 50, EasyGas.rutaPrueba);
        
        central = new CentralMapa(this, 20, 10);
        
        cliente = new ClienteMapa(this, 60, 52);
    }

    private void Actualiza() {
        teclado.Actualiza();
        
        ciudadXYZ.Actualiza();

        camion.Actualiza();
        
        camara.Mueve(camX, camY);
    }

    private void Dibuja() { //draw
        bs = pantalla.ObtenCanvas().getBufferStrategy();
        if (bs == null) {
            pantalla.ObtenCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, ancho, alto); //clear screen

        //////////// PARTE DIBUJO //////////////
        
        ciudadXYZ.Dibuja(g);
        
        camion.DibujaRuta(g);
        camion.DibujaCamion(g);
        central.Dibuja(g);
        cliente.Dibuja(g);

        /////////// FIN DIBUJO //////////////
        
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        Inicializa();

        int fps = 60;
        double tiempoPorAct = 1000000000 / fps; //nanosegundos
        double delta = 0;
        long ahora;
        long ultimoTiempo = System.nanoTime(); //tiempo actual del programa en nanosegundos
        long reloj = 0;
        int actualizaciones = 0;

        while (corriendo) {
            ahora = System.nanoTime();
            delta += (ahora - ultimoTiempo) / tiempoPorAct;
            reloj += ahora - ultimoTiempo;
            ultimoTiempo = ahora;

            if (delta >= 1) {
                Actualiza();
                Dibuja();
                actualizaciones++;
                delta--;
            }

            if (reloj >= 1000000000) {
                System.out.println("FPS: " + actualizaciones); //sirve para ver que todo esté funcionando correctamente, el valor impreso debe ser 60 aprox
                actualizaciones = 0;
                reloj = 0;
            }
        }

        Para();
    }

    public Camara ObtenCamara() {
        return camara;
    }
    
    public Teclado ObtenTeclado(){
        return teclado;
    }

    public synchronized void Empieza() {
        if (corriendo) {
            return;
        }
        corriendo = true;
        hilo = new Thread(this);
        hilo.start();
    }

    public synchronized void Para() {
        if (!corriendo) {
            return;
        }
        corriendo = false;
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
