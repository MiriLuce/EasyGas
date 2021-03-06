/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class Principal extends javax.swing.JFrame {

    private Pantalla_TipoCamion ventanaTipoCamion = null;
    private Pantalla_CamionCarga ventanaCamionCarga = null;
    private Pantalla_Camion ventanaCamion = null;
    private Pantalla_Pedido ventanaPedido = null;
    private Pantalla_Mantenimiento ventanaMantenimiento = null;
    private Pantalla_Accidente ventanaAccidente = null;
    private Pantalla_Empleado ventanaEmpleado = null;
    private Pantalla_Usuario ventanaUsuario = null;
    private Pantalla_Simulacion ventanaSimulacion=null;
    private Pantalla_Ruta ventanaRuta=null;
    private Pantalla_Perfil ventanaPerfil=null;
    private Pantalla_Cliente ventanaCliente=null;
    private Pantalla_PedidosPorCliente ventanaPedidosPorCliente=null;
    private Pantalla_ClienteCarga ventanaClienteCarga = null;

    public Principal() throws IOException {
        
        initComponents();
        //java.net.URL url= ClassLoader.getSystemResource("recursos/logo_toolkit.png");
        //Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage img = ImageIO.read(getClass().getResource("/Recursos/logo_toolkit.png"));
        this.setIconImage(img);
        this.setVisible(true);
        nombreUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font font = nombreUsuario.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nombreUsuario.setFont(font.deriveFont(attributes));
        
         
    }
    @Override
    public void paintComponents(Graphics g){
        
        Image backgroundImg=null;
        try {
            super.paintComponents(g);            
            backgroundImg=ImageIO.read(new File("recursos/logo_fondo.png"));
            
            
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g.drawImage(backgroundImg, 0, 0, this.jPanel1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSesion = new javax.swing.JPanel();
        nombreUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        menuPlanilla = new javax.swing.JMenu();
        mantEmpleado = new javax.swing.JMenuItem();
        mantUsuario = new javax.swing.JMenuItem();
        menuLogistica = new javax.swing.JMenu();
        mantTipoCamion = new javax.swing.JMenuItem();
        mantCamion = new javax.swing.JMenuItem();
        mantCamionCargaMasiva = new javax.swing.JMenuItem();
        mantMantenimiento = new javax.swing.JMenuItem();
        menuReparto = new javax.swing.JMenu();
        mantCliente = new javax.swing.JMenuItem();
        mantClienteCargaMasiva = new javax.swing.JMenuItem();
        mantPedido = new javax.swing.JMenuItem();
        mantAccidente = new javax.swing.JMenuItem();
        mantRuta = new javax.swing.JMenuItem();
        mantSimulacion = new javax.swing.JMenuItem();
        menuReporte = new javax.swing.JMenu();
        mantPedidosPorCliente = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setResizable(false);

        pnlSesion.setBackground(new java.awt.Color(189, 209, 190));

        nombreUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nombreUsuario.setForeground(new java.awt.Color(0, 51, 204));
        nombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nombreUsuario.setText("Usuario");
        nombreUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreUsuarioMouseClicked(evt);
            }
        });

        btnCerrarSesion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setVerifyInputWhenFocusTarget(false);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSesionLayout = new javax.swing.GroupLayout(pnlSesion);
        pnlSesion.setLayout(pnlSesionLayout);
        pnlSesionLayout.setHorizontalGroup(
            pnlSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSesionLayout.createSequentialGroup()
                .addContainerGap(1046, Short.MAX_VALUE)
                .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSesionLayout.setVerticalGroup(
            pnlSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCerrarSesion)
                .addComponent(nombreUsuario))
        );

        jPanel1.setBackground(new java.awt.Color(240, 240, 225));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1152, Short.MAX_VALUE)
        );

        menuPlanilla.setText("Planilla");

        mantEmpleado.setText("Empleado");
        mantEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantEmpleadoActionPerformed(evt);
            }
        });
        menuPlanilla.add(mantEmpleado);

        mantUsuario.setText("Usuario");
        mantUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantUsuarioActionPerformed(evt);
            }
        });
        menuPlanilla.add(mantUsuario);

        jMenuBar.add(menuPlanilla);

        menuLogistica.setText("Logistica");

        mantTipoCamion.setText("Tipo Camión");
        mantTipoCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantTipoCamionActionPerformed(evt);
            }
        });
        menuLogistica.add(mantTipoCamion);

        mantCamion.setText("Camión");
        mantCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantCamionActionPerformed(evt);
            }
        });
        menuLogistica.add(mantCamion);

        mantCamionCargaMasiva.setText("Camión Carga Masiva");
        mantCamionCargaMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantCamionCargaMasivaActionPerformed(evt);
            }
        });
        menuLogistica.add(mantCamionCargaMasiva);

        mantMantenimiento.setText("Mantenimiento de Camiones");
        mantMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantMantenimientoActionPerformed(evt);
            }
        });
        menuLogistica.add(mantMantenimiento);

        jMenuBar.add(menuLogistica);

        menuReparto.setText("Reparto");

        mantCliente.setText("Cliente");
        mantCliente.setToolTipText("");
        mantCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantClienteActionPerformed(evt);
            }
        });
        menuReparto.add(mantCliente);

        mantClienteCargaMasiva.setText("Cliente Carga");
        mantClienteCargaMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantClienteCargaMasivaActionPerformed(evt);
            }
        });
        menuReparto.add(mantClienteCargaMasiva);

        mantPedido.setText("Pedido");
        mantPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantPedidoActionPerformed(evt);
            }
        });
        menuReparto.add(mantPedido);

        mantAccidente.setText("Accidente");
        mantAccidente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantAccidenteActionPerformed(evt);
            }
        });
        menuReparto.add(mantAccidente);

        mantRuta.setText("Ruta de Reparto");
        mantRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantRutaActionPerformed(evt);
            }
        });
        menuReparto.add(mantRuta);

        mantSimulacion.setText("Simulación");
        mantSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantSimulacionActionPerformed(evt);
            }
        });
        menuReparto.add(mantSimulacion);

        jMenuBar.add(menuReparto);

        menuReporte.setText("Reporte");

        mantPedidosPorCliente.setText("Pedidos Por Cliente");
        mantPedidosPorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantPedidosPorClienteActionPerformed(evt);
            }
        });
        menuReporte.add(mantPedidosPorCliente);

        jMenuBar.add(menuReporte);

        menuAyuda.setText("Ayuda");
        jMenuBar.add(menuAyuda);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    private void ActualizaVentanas() {
       
       // jLabel1.setPreferredSize(new Dimension(0, 0));
        ventanaTipoCamion = null;
        ventanaCamionCarga = null;
        ventanaCamion = null;
        ventanaPedido = null;
        ventanaMantenimiento = null;
        ventanaAccidente = null;
        ventanaEmpleado = null;
        ventanaUsuario=null;   
        ventanaSimulacion=null;
        ventanaRuta=null;
        ventanaPerfil=null;
        ventanaCliente=null;
         ventanaPedidosPorCliente=null;
        ventanaClienteCarga=null;

    }

    private void mantTipoCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantTipoCamionActionPerformed

        ActualizaVentanas();
        
        if (ventanaTipoCamion == null) {
            ventanaTipoCamion = new Pantalla_TipoCamion();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaTipoCamion);
            try {
                ventanaTipoCamion.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaTipoCamion.setVisible(true);
    }//GEN-LAST:event_mantTipoCamionActionPerformed

    private void mantCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantCamionActionPerformed

        ActualizaVentanas();
        
        if (ventanaCamion == null) {
            ventanaCamion = new Pantalla_Camion();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaCamion);
            try {
                ventanaCamion.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaCamion.setVisible(true);
    }//GEN-LAST:event_mantCamionActionPerformed

    private void mantMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantMantenimientoActionPerformed
        
        ActualizaVentanas();
        
        if (ventanaMantenimiento == null) {
            ventanaMantenimiento = new Pantalla_Mantenimiento();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaMantenimiento);
            try {
                ventanaMantenimiento.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaMantenimiento.setVisible(true);
    }//GEN-LAST:event_mantMantenimientoActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        JOptionPane.showMessageDialog(null, "Hasta luego!");
        try {
            Pantalla_Inicio pInicio = new Pantalla_Inicio();
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void mantPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantPedidoActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ActualizaVentanas();
        
        if (ventanaPedido == null) {
            ventanaPedido = new Pantalla_Pedido();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaPedido);
            try {
                ventanaPedido.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaPedido.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_mantPedidoActionPerformed

    private void mantAccidenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantAccidenteActionPerformed
        
        ActualizaVentanas();
        
        if (ventanaAccidente == null) {
            ventanaAccidente = new Pantalla_Accidente();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaAccidente);
            try {
                ventanaAccidente.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaAccidente.setVisible(true);
    }//GEN-LAST:event_mantAccidenteActionPerformed

    private void mantEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantEmpleadoActionPerformed
        
        ActualizaVentanas();
        
        if (ventanaEmpleado == null) {
            ventanaEmpleado = new Pantalla_Empleado();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaEmpleado);
            try {
                ventanaEmpleado.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaEmpleado.setVisible(true);
    }//GEN-LAST:event_mantEmpleadoActionPerformed

    private void mantUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantUsuarioActionPerformed
        ActualizaVentanas();
        
        if (ventanaUsuario == null) {
            ventanaUsuario = new Pantalla_Usuario();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaUsuario);
            try {
                ventanaUsuario.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaUsuario.setVisible(true);
    }//GEN-LAST:event_mantUsuarioActionPerformed

    private void mantCamionCargaMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantCamionCargaMasivaActionPerformed
        ActualizaVentanas();
        
        if (ventanaCamionCarga == null) {
            ventanaCamionCarga = new Pantalla_CamionCarga();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaCamionCarga);
            try {
                ventanaCamionCarga.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaCamionCarga.setVisible(true);
    }//GEN-LAST:event_mantCamionCargaMasivaActionPerformed

    private void mantSimulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantSimulacionActionPerformed
        // TODO add your handling code here:
        ActualizaVentanas();
        
        if (ventanaSimulacion == null) {
            ventanaSimulacion = new Pantalla_Simulacion();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaSimulacion);
            try {
                ventanaSimulacion.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaSimulacion.setVisible(true);
    }//GEN-LAST:event_mantSimulacionActionPerformed

    private void mantRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantRutaActionPerformed
        // TODO add your handling code here:
        ActualizaVentanas();
        
        if (ventanaRuta == null) {
            ventanaRuta = new Pantalla_Ruta();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaRuta);
            try {
                ventanaRuta.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaRuta.setVisible(true);
    }//GEN-LAST:event_mantRutaActionPerformed

    private void nombreUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreUsuarioMouseClicked
        // TODO add your handling code here:
        ActualizaVentanas();
        
        if (ventanaPerfil == null) {
            ventanaPerfil = new Pantalla_Perfil();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaPerfil);
            try {
                ventanaPerfil.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaPerfil.setVisible(true);
    }//GEN-LAST:event_nombreUsuarioMouseClicked

    private void mantClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantClienteActionPerformed
        // TODO add your handling code here:
        ActualizaVentanas();
        
        if (ventanaCliente == null) {
            ventanaCliente = new Pantalla_Cliente();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaCliente);
            try {
                ventanaCliente.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaCliente.setVisible(true);
    }//GEN-LAST:event_mantClienteActionPerformed

    private void mantPedidosPorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantPedidosPorClienteActionPerformed
        // TODO add your handling code here:
                ActualizaVentanas();
        
        if (ventanaPedidosPorCliente == null) {
            ventanaPedidosPorCliente = new Pantalla_PedidosPorCliente();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaPedidosPorCliente);
            try {
                ventanaPedidosPorCliente.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaPedidosPorCliente.setVisible(true);
    }//GEN-LAST:event_mantPedidosPorClienteActionPerformed

    private void mantClienteCargaMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantClienteCargaMasivaActionPerformed
        // TODO add your handling code here:
        ActualizaVentanas();
        
        if (ventanaClienteCarga == null) {
            ventanaClienteCarga = new Pantalla_ClienteCarga();
            this.jPanel1.removeAll();
            this.jPanel1.add(ventanaClienteCarga);
            try {
                ventanaClienteCarga.setMaximum(rootPaneCheckingEnabled);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ventanaClienteCarga.setVisible(true);
    }//GEN-LAST:event_mantClienteCargaMasivaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem mantAccidente;
    private javax.swing.JMenuItem mantCamion;
    private javax.swing.JMenuItem mantCamionCargaMasiva;
    private javax.swing.JMenuItem mantCliente;
    private javax.swing.JMenuItem mantClienteCargaMasiva;
    private javax.swing.JMenuItem mantEmpleado;
    private javax.swing.JMenuItem mantMantenimiento;
    private javax.swing.JMenuItem mantPedido;
    private javax.swing.JMenuItem mantPedidosPorCliente;
    private javax.swing.JMenuItem mantRuta;
    private javax.swing.JMenuItem mantSimulacion;
    private javax.swing.JMenuItem mantTipoCamion;
    private javax.swing.JMenuItem mantUsuario;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuLogistica;
    private javax.swing.JMenu menuPlanilla;
    private javax.swing.JMenu menuReparto;
    private javax.swing.JMenu menuReporte;
    public javax.swing.JLabel nombreUsuario;
    private javax.swing.JPanel pnlSesion;
    // End of variables declaration//GEN-END:variables
}
