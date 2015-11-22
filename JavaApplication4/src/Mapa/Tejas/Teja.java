/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa.Tejas;

import Modelo.Constantes.EasyGas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Eduardo
 */
public class Teja {

    public static Teja[] tejas = new Teja[15];
    public static Teja tejaObstaculo = new TejaObstaculo(0);
    public static Teja tejaPista = new TejaPista(1);
    public static Teja tejaRutaRojo = new TejaRutaRojo(2);
    public static Teja tejaRutaVerde = new TejaRutaVerde(3);
    public static Teja tejaRutaAmarillo = new TejaRutaAmarillo(4);
    public static Teja tejaRutaAzul = new TejaRutaAzul(5);

    public static final int ANCHOTEJA = EasyGas.tamCuadMapa, ALTOTEJA = EasyGas.tamCuadMapa;

    protected BufferedImage textura;
    protected final int id;

    public Teja(BufferedImage t, int i) {
        textura = t;
        id = i;

        tejas[id] = this;
    }

    public void Actualiza() {

    }

    public void Dibuja(Graphics g, int x, int y, Color c) {
        if (c != null) {
            g.drawImage(textura, x, y, ANCHOTEJA, ALTOTEJA, c, null);
        } else {
            g.drawImage(textura, x, y, ANCHOTEJA, ALTOTEJA, null);
        }
    }

    public boolean esSolido() {
        return false;
    }

    public int ObtenId() {
        return id;
    }
}
