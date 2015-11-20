/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.*;
import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Usuario;
import Util.HibernateUtil;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/* 
 * Ultima modificacion: 23/10/15
 * Descripcion del Cambio: Creación de la clase
 * Autor: Carlos Chuquilin
 * 
 * Modificación: Luis Llanos - Conecciones de Iniciar Sesion con pantalla principal
 */
public class Pantalla_Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    UsuarioControlador usuarioControlador = new UsuarioControlador();

    public Pantalla_Inicio() {
        
        initComponents();
        java.net.URL url= ClassLoader.getSystemResource("recursos/logo_toolkit.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img= kit.createImage(url);
        this.setIconImage(img);
        this.setVisible(true);
    }

    //funcion para validar los datos de ingreso
    public void validaIngreso() {
        String nombreDeUsuario = textoUsuario.getText();
        String contrasenhaUsuario = textoContrasenha.getText();

        if (nombreDeUsuario.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario");
            textoUsuario.setText("");
            textoContrasenha.setText("");
            return;
        } else {
            if (nombreDeUsuario.equalsIgnoreCase("admin")) {
                if (!contrasenhaUsuario.equalsIgnoreCase("admin")) {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    textoUsuario.setText("");
                    textoContrasenha.setText("");
                    return;
                } else {
                    //this.setVisible(false); para que se muestre el cambio del cursos a WAIT la pantalla inicial debe estar visible
                    final ImageIcon icon = new ImageIcon("src/Recursos/img_correcto.png");
                    JOptionPane.showMessageDialog(null, "Bienvenido \nNivel de acceso: Administrador", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    EasyGas.sesion = EasyGas.sesFact.openSession();
                    Principal main = new Principal(); //esto es lo que hace demorar
                    main.nombreUsuario.setText("Administrador");
                    this.setCursor(Cursor.getDefaultCursor());
                    this.dispose();
                }

            } else {
                //validar con bd
                Usuario usuario = new Usuario();
                usuario = usuarioControlador.obtenerUsuario(contrasenhaUsuario, nombreDeUsuario);
                if (usuario != null) {
                    EasyGas.usuarioActual = usuario;
                    this.setVisible(false);
                    final ImageIcon icon = new ImageIcon("src/Recursos/img_correcto.png");
                    JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombreUsuario() + " \nNivel de acceso: " + usuario.getPerfil().getNombre(), "", JOptionPane.INFORMATION_MESSAGE, icon);

                    Principal main = new Principal();
                    main.nombreUsuario.setText(usuario.getEmpleado().getNombres() + " " + usuario.getEmpleado().getApellidoPat() );
                    this.dispose();
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña invalidos");
                    textoUsuario.setText("");
                    textoContrasenha.setText("");
                    return;
                }
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();
        textoContrasenha = new javax.swing.JPasswordField();
        botonIngresar = new javax.swing.JButton();
        linkOlvideCont = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EasyGas - Iniciar Sesión");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(240, 240, 225));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 250));

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        textoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoUsuarioKeyPressed(evt);
            }
        });

        textoContrasenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoContrasenhaKeyPressed(evt);
            }
        });

        botonIngresar.setText("INGRESAR");
        botonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIngresarActionPerformed(evt);
            }
        });

        linkOlvideCont.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        linkOlvideCont.setForeground(new java.awt.Color(0, 0, 255));
        linkOlvideCont.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linkOlvideCont.setText("Olvidé la contraseña");
        linkOlvideCont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkOlvideContMouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/logo_proyecto.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(botonIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(linkOlvideCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoContrasenha, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(textoUsuario))
                        .addGap(36, 36, 36))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(166, 166, 166)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addGap(10, 10, 10)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoContrasenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addComponent(botonIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(linkOlvideCont)
                        .addGap(7, 7, 7)))
                .addGap(45, 45, 45))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(0, 228, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(202, 202, 202)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIngresarActionPerformed
        validaIngreso();
    }//GEN-LAST:event_botonIngresarActionPerformed

    private void linkOlvideContMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkOlvideContMouseClicked
        Pantalla_RecuperarContrasenha pantRecupera = new Pantalla_RecuperarContrasenha();
    }//GEN-LAST:event_linkOlvideContMouseClicked

    private void textoUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validaIngreso();
        }
    }//GEN-LAST:event_textoUsuarioKeyPressed

    private void textoContrasenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoContrasenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validaIngreso();
        }
    }//GEN-LAST:event_textoContrasenhaKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel linkOlvideCont;
    private javax.swing.JPasswordField textoContrasenha;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
