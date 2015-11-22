/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa.Utilidades;

import Mapa.CamionMapa;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Eduardo
 */
public class PanelLeyenda extends JPanel {

    public PanelLeyenda(ArrayList<CamionMapa> lista) {
        PreparaPanel(lista);
    }

    private void PreparaPanel(ArrayList<CamionMapa> lista) {
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(4, 1));

        for (int i = 0; i < lista.size(); i++) {            
            ImageIcon icono = new ImageIcon(lista.get(i).getImagen());

            JLabel label = new JLabel(lista.get(i).getPlaca(), icono, SwingConstants.CENTER);

            main.add(label);
        }

        this.add(main);
    }
}
