/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Mapa.Ciudades.CiudadXYZ;
import Mapa.Utilidades.Pantalla;
import Modelo.Hibernate.Ruta;
import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Cliente;
import Modelo.Hibernate.Nodo;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

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
    private boolean enPausa;
    private float camX, camY;

    private final ArrayList<Ruta> rutas;
    private final ArrayList<CamionMapa> camiones;
    private final ArrayList<ClienteMapa> clientes;
    private ArrayList<Cliente> listaClientes;
    CiudadXYZ ciudadXYZ;
    Camara camara;
    CentralMapa central;
    private Teclado teclado;
    private JPanel panel;

    public Mapa(String t, int an, int al, ArrayList<Ruta> sol, ArrayList<Cliente> lista) {
        titulo = t;
        ancho = an;
        alto = al;
        teclado = new Teclado();
        rutas = (ArrayList<Ruta>) sol.clone();
        listaClientes = (ArrayList<Cliente>) lista.clone();
        camiones = new ArrayList<CamionMapa>();
        clientes = new ArrayList<ClienteMapa>();
        enPausa = false;
    }

    private void CargaCamiones() {
        for (int i = 0; i < rutas.size(); i++) {
            camiones.add(new CamionMapa(this, i, 50, rutas.get(i)));
        }
    }

    private void CargaClientes() {
        for (int i = 0; i < listaClientes.size(); i++) {
            Nodo n = listaClientes.get(i).getNodo();
            clientes.add(new ClienteMapa(this, n.getCoordX(), n.getCoordY(), listaClientes.get(i).getTipoDocumento()));
        }
    }

    private void PreparaLeyenda() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(camiones.size()+3, 1));
        
        for (int i = 0; i < camiones.size(); i++) {
            JLabel label = new JLabel(String.valueOf(i), new ImageIcon(camiones.get(i).getImagen()), SwingConstants.LEFT);
            panel.add(label);
        }
        
        JLabel labelCentral = new JLabel("Central de EasyGas", new ImageIcon(EasyGas.mapaCentral), SwingConstants.LEFT);
        JLabel labelClienteNat = new JLabel("Persona Natural", new ImageIcon(EasyGas.mapaClienteNat), SwingConstants.LEFT);
        JLabel labelClienteJur = new JLabel("Empresa", new ImageIcon(EasyGas.mapaClienteJur), SwingConstants.LEFT);

        panel.add(labelCentral);
        panel.add(labelClienteNat);
        panel.add(labelClienteJur);
    }
    
    private void Inicializa() throws IOException {
        pantalla = new Pantalla(titulo, ancho, alto);
        pantalla.ObtenFrame().addKeyListener(teclado);
        EasyGas.Inicializa();

        camara = new Camara(this, 0, 0);
        camX = 2f; //auxiliar
        camY = 2f; //auxiliar

        ciudadXYZ = new CiudadXYZ(this);
        central = new CentralMapa(this, 20, 10);

        CargaCamiones();
        CargaClientes();
        
        PreparaLeyenda();
    }

    private void ActualizaCamiones() {
        for (int i = 0; i < camiones.size(); i++) {
            camiones.get(i).Actualiza();
        }
    }
    
    private boolean teclaActualPausa, teclaAntiguaPausa; //para manejar que no se presione pausa mas de una vez
    private boolean teclaActualLeyenda, teclaAntiguaLeyenda; //para manejar que no se presione pausa mas de una vez
    private void Actualiza() {
        teclaActualPausa = teclado.barraEspaciadora;
        teclaActualLeyenda = teclado.ele;

        if (teclaActualPausa && !teclaAntiguaPausa && !enPausa) {
            enPausa = true;
        } else if (teclaActualPausa && !teclaAntiguaPausa && enPausa) {
            enPausa = false;
        }

        if (teclaActualLeyenda && !teclaAntiguaLeyenda && !enPausa) { //mostrar leyenda cuando se presiona L 
            JOptionPane.showMessageDialog(null, panel, "Leyenda", JOptionPane.PLAIN_MESSAGE);
        }

        teclado.Actualiza();
        ciudadXYZ.Actualiza();

        if (!enPausa) {
            ActualizaCamiones();
            camara.Mueve(camX, camY);
        }

        teclaAntiguaPausa = teclaActualPausa;
        teclaAntiguaLeyenda = teclaActualLeyenda;
    }

    private void DibujaRutas(Graphics g) {
        for (int i = 0; i < camiones.size(); i++) {
            CamionMapa c = camiones.get(i);
            c.DibujaRuta(g);
        }
    }

    private void DibujaCamiones(Graphics g) {
        for (int i = 0; i < camiones.size(); i++) {
            CamionMapa c = camiones.get(i);
            c.DibujaCamion(g);
        }
    }

    private void DibujaClientes(Graphics g) {
        for (int i = 0; i < clientes.size(); i++) {
            ClienteMapa c = clientes.get(i);
            c.Dibuja(g);
        }
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
        DibujaRutas(g);
        DibujaCamiones(g);
        central.Dibuja(g);
        DibujaClientes(g);

        if (enPausa) {
            g.drawImage(EasyGas.imagenPausa, ancho / 2 - EasyGas.imagenPausa.getWidth() / 2, alto / 2 - EasyGas.imagenPausa.getHeight() / 2, null);
        }

        /////////// FIN DIBUJO //////////////
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        try {
            Inicializa();
        } catch (IOException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public Teclado ObtenTeclado() {
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
