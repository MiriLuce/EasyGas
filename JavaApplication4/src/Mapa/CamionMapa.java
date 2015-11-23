/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Mapa.Tejas.Teja;
import Mapa.Utilidades.Imagen;
import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Arista;
import Modelo.Hibernate.Nodo;
import Modelo.Hibernate.Ruta;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class CamionMapa {

    private int id;
    private int posX;
    private int posY;
    private int velocidad;
    private Mapa mapa;
    private int direccion;

    private BufferedImage imagen;
    private Color colorCamion;

    //private boolean tuvoAccidente;
    private Ruta ruta;

    private int numArista; //para saber en cuál arista va
    private boolean recorriendo;

    private String placa;

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
    
    public CamionMapa(Mapa m, int nid, int vel, Ruta nRuta) {
        id = nid;
        mapa = m;
        velocidad = vel; //(Km/h)
        //tuvoAccidente = false;
        imagen = Imagen.CargaImagen("/mapa_camion_16.png");
        ruta = nRuta;
        posX = ruta.getAristas().get(0).getNodoByIdOrigen().getCoordX() * Teja.ANCHOTEJA;
        posY = ruta.getAristas().get(0).getNodoByIdOrigen().getCoordY() * Teja.ANCHOTEJA;
        numArista = 0;
        direccion = ruta.getAristas().get(0).getDireccion();
        recorriendo = true;
        placa = ruta.getCamion().getPlaca();

        CambiaColor();
    }

    private void CambiaColor() {
        //generando color aleatorio del mapa
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);
        colorCamion = new Color(R, G, B);
        
        int transparente = 16777215; //sacado manualmente de un proyecto de prueba

        for (int x = 0; x < imagen.getWidth(); x++) {
            for (int y = 0; y < imagen.getHeight(); y++) {
                if (imagen.getRGB(x, y) == transparente) {
                    imagen.setRGB(x, y, colorCamion.getRGB());
                }
            }
        }
    }

    public void Actualiza() {

        if (recorriendo) {
            Arista aux = ruta.getAristas().get(numArista);
            int xf = aux.getNodoByIdDestino().getCoordX() * Teja.ANCHOTEJA;
            int yf = aux.getNodoByIdDestino().getCoordY() * Teja.ANCHOTEJA;

            if (direccion == 0) { //derecha
                if (posX >= xf) {
                    numArista++;
                    if (numArista < ruta.getAristas().size()) {
                        direccion = ruta.getAristas().get(numArista).getDireccion();
                    }
                } else {
                    posX++;
                }
            } else if (direccion == 1) { //izquierda
                if (posX <= xf) {
                    numArista++;
                    if (numArista < ruta.getAristas().size()) {
                        direccion = ruta.getAristas().get(numArista).getDireccion();
                    }
                } else {
                    posX--;
                }
            } else if (direccion == 2) { //abajo
                if (posY >= yf) {
                    numArista++;
                    if (numArista < ruta.getAristas().size()) {
                        direccion = ruta.getAristas().get(numArista).getDireccion();
                    }
                } else {
                    posY++;
                }
            } else if (direccion == 3) { //arriba
                if (posY <= yf) {
                    numArista++;
                    if (numArista < ruta.getAristas().size()) {
                        direccion = ruta.getAristas().get(numArista).getDireccion();
                    }
                } else {
                    posY--;
                }
            }

            if (numArista == ruta.getAristas().size()) {
                recorriendo = false;
            }
        }
    }

    public void DibujaCamion(Graphics g) {
        g.drawImage(imagen, (int) (posX - mapa.ObtenCamara().getxOffSet() - 3), (int) (posY - mapa.ObtenCamara().getyOffSet() - 3), null);
    }

    private void DibujaRecta(Graphics g, Nodo origen, Nodo destino) {
        int xi = origen.getCoordX();
        int yi = origen.getCoordY();
        int xf = destino.getCoordX();
        int yf = destino.getCoordY();

        g.setColor(colorCamion);

        if (xi == xf) {//recta vertical
            if (yi < yf) {// recta de arriba hacia abajo
                for (int i = yi; i <= yf; i++) {
                    g.fillRect((int) (xi * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet() + 1), (int) (i * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet() + 1), Teja.ANCHOTEJA - 2, Teja.ANCHOTEJA - 2);
                }
            } else {// recta de abajo hacia arriba
                for (int i = yi; i >= yf; i--) {
                    g.fillRect((int) (xi * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet() + 1), (int) (i * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet() + 1), Teja.ANCHOTEJA - 2, Teja.ANCHOTEJA - 2);
                }
            }
        } else {//recta horizontal
            if (xi < xf) {// recta de izquierda a derecha
                for (int i = xi; i <= xf; i++) {
                    g.fillRect((int) (i * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet() + 1), (int) (yi * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet() + 1), Teja.ANCHOTEJA - 2, Teja.ANCHOTEJA - 2);
                }
            } else {// recta de derecha a izquierda
                for (int i = xi; i >= xf; i--) {
                    g.fillRect((int) (i * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet() + 1), (int) (yi * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet() + 1), Teja.ANCHOTEJA - 2, Teja.ANCHOTEJA - 2);
                }
            }
        }

    }

    public void DibujaRuta(Graphics g) {
        List lista = ruta.getAristas();

        for (Iterator i = lista.iterator(); i.hasNext();) {
            Arista arista = (Arista) i.next();
            DibujaRecta(g, arista.getNodoByIdOrigen(), arista.getNodoByIdDestino());
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    
}
