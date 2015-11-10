/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Modelo.Constantes.EasyGas;

/**
 *
 * @author Eduardo
 */
public class Camara {

    private float xOffSet, yOffSet;
    private Mapa mapa;
    private int limiteX, limiteY;

    public Camara(Mapa m, float x, float y) {
        xOffSet = x;
        yOffSet = y;
        mapa = m;
        limiteX = (EasyGas.anchoMapa * EasyGas.tamCuadMapa) - mapa.ancho;
        limiteY = (EasyGas.altoMapa * EasyGas.tamCuadMapa) - mapa.alto;
    }

    public void CentraCamara(int x, int y) {
        xOffSet = x - mapa.ancho / 2;
        yOffSet = y - mapa.alto / 2;
    }

    public void Mueve(float nx, float ny) {
        if (mapa.ObtenTeclado().arriba && yOffSet >= 0) {
            yOffSet -= ny;
        }
        if (mapa.ObtenTeclado().abajo && yOffSet <= limiteY) {
            yOffSet += ny;
        }
        if (mapa.ObtenTeclado().izquierda && xOffSet >= 0) {
            xOffSet -= nx;
        }
        if (mapa.ObtenTeclado().derecha && yOffSet <= limiteX) {
            xOffSet += nx;
        }
    }

    public float getxOffSet() {
        return xOffSet;
    }

    public void setxOffSet(float xOffSet) {
        this.xOffSet = xOffSet;
    }

    public float getyOffSet() {
        return yOffSet;
    }

    public void setyOffSet(float yOffSet) {
        this.yOffSet = yOffSet;
    }

}
