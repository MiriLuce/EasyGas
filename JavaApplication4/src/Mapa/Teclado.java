/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Eduardo
 */
public class Teclado implements KeyListener {

    private boolean[] teclas;
    public boolean arriba, abajo, izquierda, derecha, barraEspaciadora, ele;

    public Teclado() {
        teclas = new boolean[256]; //exagerando 256 xD
    }

    public void Actualiza() {
        arriba = teclas[KeyEvent.VK_UP];
        abajo = teclas[KeyEvent.VK_DOWN];
        izquierda = teclas[KeyEvent.VK_LEFT];
        derecha = teclas[KeyEvent.VK_RIGHT];
        barraEspaciadora = teclas[KeyEvent.VK_SPACE];
        ele = teclas[KeyEvent.VK_L];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
    }

}
