/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Mapa.Tejas.Teja;
import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Arista;
import Modelo.Hibernate.Nodo;
import Modelo.Hibernate.Ruta;
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

    private boolean tuvoAccidente;

    private Ruta ruta;

    private int numArista; //para saber en cuál arista va
    private boolean recorriendo;

    public CamionMapa(Mapa m, int nid, int vel, Ruta nRuta) {
        id = nid;
        mapa = m;
        velocidad = vel;
        tuvoAccidente = false;
        imagen = EasyGas.camionNormal;
        ruta = nRuta;
        posX = ruta.getAristas().get(0).getNodoByIdOrigen().getCoordX() * Teja.ANCHOTEJA;
        posY = ruta.getAristas().get(0).getNodoByIdOrigen().getCoordY() * Teja.ANCHOTEJA;
        numArista = 0;
        direccion = ruta.getAristas().get(0).getDireccion();
        recorriendo = true;
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
        if (!tuvoAccidente) {
            imagen = EasyGas.camionNormal;
        } else {
            imagen = EasyGas.camionAccidente;
        }
        g.drawImage(imagen, (int) (posX - mapa.ObtenCamara().getxOffSet() - 3), (int) (posY - mapa.ObtenCamara().getyOffSet() - 3), null);
    }

    private void DibujaRecta(Graphics g, Nodo origen, Nodo destino) {
        int xi = origen.getCoordX();
        int yi = origen.getCoordY();
        int xf = destino.getCoordX();
        int yf = destino.getCoordY();

        Teja teja = Teja.tejaRutaRojo;

        if (xi == xf) {//recta vertical
            if (yi < yf) {// recta de arriba hacia abajo
                for (int i = yi; i <= yf; i++) {
                    teja.Dibuja(g, (int) (xi * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet()), (int) (i * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet()));
                }
            } else {// recta de abajo hacia arriba
                for (int i = yi; i >= yf; i--) {
                    teja.Dibuja(g, (int) (xi * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet()), (int) (i * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet()));
                }
            }
        } else {//recta horizontal
            if (xi < xf) {// recta de izquierda a derecha
                for (int i = xi; i <= xf; i++) {
                    teja.Dibuja(g, (int) (i * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet()), (int) (yi * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet()));
                }
            } else {// recta de derecha a izquierda
                for (int i = xi; i >= xf; i--) {
                    teja.Dibuja(g, (int) (i * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet()), (int) (yi * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet()));
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

}
