/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa.Utilidades;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Eduardo
 */
public class Pantalla {
    private JFrame frame;
    private Canvas canvas;
    
    private String titulo;
    private int ancho, alto;
    
    public Pantalla(String t, int an, int al){
        titulo = t;
        ancho = an;
        alto = al;
        
        CreaPantalla();
    }
    
    private void CreaPantalla(){
        frame = new JFrame(titulo);
        frame.setSize(ancho,alto);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(ancho,alto));
        canvas.setMaximumSize(new Dimension(ancho,alto));
        canvas.setMinimumSize(new Dimension(ancho,alto));
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
    }
    
    public Canvas ObtenCanvas(){
        return canvas;
    }
    
    public JFrame ObtenFrame(){
        return frame;
    }
}
