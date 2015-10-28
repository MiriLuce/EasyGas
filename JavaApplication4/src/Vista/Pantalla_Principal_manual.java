/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.*;
import javax.swing.*;

/* 
* Ultima modificacion: 12/09/15
* Descripcion del Cambio: Creación de la clase
* Autor: Chuky
*/
public class Pantalla_Principal_manual extends JFrame{
    
    //Componentes de la pantalla principal
    private JMenuBar barraMenu = new JMenuBar();
    private JMenu menuArchivo = new JMenu("Archivo");
    private JMenu menuMant = new JMenu("Mantenimientos");
    private JMenu menuMapa = new JMenu("Ver Mapa");
    private JMenu menuAyuda =  new JMenu("Ayuda");
    private JMenu menuCerrarSesion = new JMenu("Cerrar Sesion");
    private JLabel textoSaludo = new JLabel("Bienvenido sr. Eduardo");
    private JPanel cabecera = new JPanel( new BorderLayout() );
    private JPanel barraEstado = new JPanel(new BorderLayout());
    private JPanel pantallaFuncional = new JPanel(new BorderLayout());
    
    private void inicializa_Componentes(){
        setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
        setTitle("EasyGas - Principal");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        barraMenu = new JMenuBar();
        menuArchivo = new JMenu("Archivo");
        menuMant = new JMenu("Mantenimientos");
        menuMapa = new JMenu("Ver Mapa");
        menuAyuda =  new JMenu("Ayuda");
        menuCerrarSesion = new JMenu("Cerrar Sesion");
        textoSaludo = new JLabel();
        cabecera = new JPanel( new BorderLayout() );
        barraEstado = new JPanel();
        pantallaFuncional = new JPanel(new BorderLayout());
 
        //Texto mostrado en la barra de estado
        textoSaludo.setFont(new Font("Arial",Font.PLAIN,18));
        textoSaludo.setForeground(Color.white);
        textoSaludo.setText("Bienvenido Sr. Eduardo");
        
        barraEstado.setBackground(new Color(0xab8200));
        
        GroupLayout barraEstadoLayout = new GroupLayout(barraEstado);
        barraEstado.setLayout(barraEstadoLayout);
        barraEstadoLayout.setHorizontalGroup(barraEstadoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                    .addGroup(GroupLayout.Alignment.TRAILING, barraEstadoLayout.createSequentialGroup()
                                                                                                                                                .addContainerGap(448,Short.MAX_VALUE)
                                                                                                                                                .addComponent(textoSaludo)
                                                                                                                                                .addGap(21,21,21)));
        barraEstadoLayout.setVerticalGroup(barraEstadoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                    .addGroup(barraEstadoLayout.createSequentialGroup()
                                                                                                                .addContainerGap()
                                                                                                                .addComponent(textoSaludo)
                                                                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)));
        
        //componentes de la barra de menu
        menuArchivo.add(new JMenuItem("Cambiar contraseña"));
        
        barraMenu.add(menuArchivo);
        barraMenu.add(menuMant);
        barraMenu.add(menuMapa);
        barraMenu.add(menuAyuda);
        barraMenu.add(Box.createHorizontalGlue());
        barraMenu.add(menuCerrarSesion);
        
        //componentes de la cabecera
        cabecera.add(barraEstado, BorderLayout.NORTH);
        cabecera.add(barraMenu, BorderLayout.CENTER);
        
        add(cabecera, BorderLayout.NORTH);
        add(pantallaFuncional, BorderLayout.CENTER);
        
        menuMapa.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMapaActionPerformed(evt);
            }
        });
    }
    
    public Pantalla_Principal_manual(){        
        inicializa_Componentes();
    }
    
    private void menuMapaActionPerformed(java.awt.event.ActionEvent evt) {  
        
    } 
}
