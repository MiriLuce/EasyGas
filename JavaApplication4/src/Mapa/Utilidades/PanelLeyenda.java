/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa.Utilidades;

import Mapa.CamionMapa;
import Modelo.Constantes.EasyGas;
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
        main.setLayout(new GridLayout(lista.size()+3, 1));

        for (int i = 0; i < lista.size(); i++) {            
            ImageIcon icono = new ImageIcon(lista.get(i).getImagen());

            JLabel label = new JLabel(lista.get(i).getPlaca(), icono, SwingConstants.CENTER);

            main.add(label);
        }
        
        JLabel labelNat = new JLabel("Persona Natural", new ImageIcon(EasyGas.mapaClienteNat), SwingConstants.CENTER);
        JLabel labelJur = new JLabel("Empresa", new ImageIcon(EasyGas.mapaClienteJur), SwingConstants.CENTER);
        JLabel labelCentral = new JLabel("Central de EasyGas", new ImageIcon(EasyGas.mapaCentral), SwingConstants.CENTER);
        
        main.add(labelCentral);
        main.add(labelNat);
        main.add(labelJur);

        this.add(main);
    }
}
