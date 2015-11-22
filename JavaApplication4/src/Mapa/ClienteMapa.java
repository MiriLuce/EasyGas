/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Mapa.Tejas.Teja;
import Modelo.Constantes.EasyGas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Eduardo
 */
public class ClienteMapa {

    //ubicacion de la central, nunca variará

    private final int posX;
    private final int posY;

    private Mapa mapa;
    
    private BufferedImage imagen;

    public ClienteMapa(Mapa m, int nx, int ny, String tipoDoc) {
        posX = nx * Teja.ANCHOTEJA;
        posY = ny * Teja.ANCHOTEJA;
        mapa = m;
        
        if(tipoDoc.equalsIgnoreCase("DNI")){
            imagen=EasyGas.mapaClienteNat;
        }
        else{
            imagen=EasyGas.mapaClienteJur;
        }
    }

    public void Actualiza() {

    }

    public void Dibuja(Graphics g) {
        g.drawImage(imagen, (int) (posX - mapa.ObtenCamara().getxOffSet() - 3), (int) (posY - mapa.ObtenCamara().getyOffSet() - 3), 16, 16, null);
    }
}
