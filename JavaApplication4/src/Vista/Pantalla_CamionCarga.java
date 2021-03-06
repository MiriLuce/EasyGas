/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CamionControlador;
import Controlador.TipoCamionControlador;
import Modelo.Hibernate.Camion;
import Modelo.Hibernate.TipoCamion;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MiriamLucero
 */
public class Pantalla_CamionCarga extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pantalla_CamionCarga
     */
    
    private TipoCamionControlador tpCamionControlador = new TipoCamionControlador();   
    private CamionControlador camionControlador = new CamionControlador();
    private File archivo = null;
    
    public Pantalla_CamionCarga() {
        initComponents();
    }
    
    public void obtenerArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter( new FileNameExtensionFilter("*.txt", "txt"));
        selector.setFileFilter( new FileNameExtensionFilter("*.csv", "csv"));        
        
        if(JFileChooser.APPROVE_OPTION == selector.showOpenDialog(null)){
            //this.setVisible(false);
            archivo = selector.getSelectedFile();
        }
        else archivo = null;
    }
    
    private void VerDatos(String strCodigo){    
        int idCamionSel = Integer.parseInt(strCodigo);
        Camion camion = camionControlador.BuscarCamionPorCodigo(idCamionSel);
        txtCamionCodigo.setText("" + camion.getIdCamion());
        txtCamionPlaca.setText(camion.getPlaca());
        datCamionFecha.setDate(camion.getFechaRegistro());
        txtCamionEstado.setText(camion.getEstado());
        
        TipoCamion tpCamion = tpCamionControlador.BuscarTipoCamionPorCodigo(camion.getTipoCamion().getIdTipoCamion());
        txtCamionTipo.setText(tpCamion.getNombre());
        txtCamionGLP.setText("" + tpCamion.getCapacidadGlp());
        txtCamionDiesel.setText("" + tpCamion.getCapacidadDiesel());
        txtCamionTara.setText("" + tpCamion.getTara());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCamion = new javax.swing.JPanel();
        panelNuevo = new javax.swing.JPanel();
        label14 = new java.awt.Label();
        jPanel34 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        txtCamionCodigo = new javax.swing.JTextField();
        txtCamionPlaca = new javax.swing.JTextField();
        txtCamionEstado = new javax.swing.JTextField();
        txtCamionGLP = new javax.swing.JTextField();
        txtCamionDiesel = new javax.swing.JTextField();
        txtCamionTara = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        datCamionFecha = new com.toedter.calendar.JDateChooser();
        txtCamionTipo = new javax.swing.JTextField();
        panelBuscar = new javax.swing.JPanel();
        label15 = new java.awt.Label();
        panelManten = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        lblProcesados = new javax.swing.JLabel();
        lblInvalidos = new javax.swing.JLabel();
        txtNombreArchivo = new javax.swing.JTextField();
        btnBuscarArchivo = new javax.swing.JButton();
        btnCamionCargar = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblCamion = new javax.swing.JTable();

        panelCamion.setBackground(new java.awt.Color(240, 240, 225));

        panelNuevo.setBackground(new java.awt.Color(240, 240, 225));
        panelNuevo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelNuevo.setPreferredSize(new java.awt.Dimension(400, 620));

        label14.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label14.setForeground(new java.awt.Color(51, 0, 102));
        label14.setText("Camión");

        jPanel34.setBackground(new java.awt.Color(240, 240, 225));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel34.setPreferredSize(new java.awt.Dimension(360, 180));

        jLabel79.setText("Placa:");

        jLabel75.setText("Tipo de Camión:");

        jLabel76.setText("Capacidad Diesel (Gal):");

        jLabel81.setText("Código:");

        jLabel74.setText("Capacidad GLP (Ton):");

        jLabel135.setText("Tara (Ton):");

        txtCamionCodigo.setEnabled(false);

        txtCamionPlaca.setEnabled(false);

        txtCamionEstado.setEnabled(false);

        txtCamionGLP.setEnabled(false);

        txtCamionDiesel.setEnabled(false);

        txtCamionTara.setEnabled(false);

        jLabel136.setText("Estado:");

        jLabel137.setText("Fecha de Registro:");

        datCamionFecha.setEnabled(false);

        txtCamionTipo.setEnabled(false);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76)
                    .addComponent(jLabel74)
                    .addComponent(jLabel75)
                    .addComponent(jLabel79)
                    .addComponent(jLabel81)
                    .addComponent(jLabel135)
                    .addComponent(jLabel136)
                    .addComponent(jLabel137))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCamionTara, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtCamionPlaca, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCamionDiesel)
                    .addComponent(txtCamionGLP)
                    .addComponent(txtCamionCodigo)
                    .addComponent(txtCamionEstado)
                    .addComponent(datCamionFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCamionTipo))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(txtCamionCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtCamionPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel137)
                    .addComponent(datCamionFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(txtCamionEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(txtCamionTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(txtCamionGLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(txtCamionDiesel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(txtCamionTara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(175, 175, 175))
        );

        panelBuscar.setBackground(new java.awt.Color(240, 240, 225));
        panelBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBuscar.setPreferredSize(new java.awt.Dimension(750, 620));

        label15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label15.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label15.setForeground(new java.awt.Color(51, 0, 102));
        label15.setText("Carga Masiva");

        panelManten.setBackground(new java.awt.Color(240, 240, 225));
        panelManten.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Camión"));
        panelManten.setPreferredSize(new java.awt.Dimension(340, 125));

        jLabel73.setText("Registros invalidos:");

        jLabel70.setText("Nombre de Archivo:");

        jLabel80.setText("Registros procesados:");

        lblProcesados.setForeground(new java.awt.Color(255, 9, 3));
        lblProcesados.setText("0");

        lblInvalidos.setForeground(new java.awt.Color(255, 9, 3));
        lblInvalidos.setText("0");

        txtNombreArchivo.setEnabled(false);

        btnBuscarArchivo.setText("Buscar");
        btnBuscarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMantenLayout = new javax.swing.GroupLayout(panelManten);
        panelManten.setLayout(panelMantenLayout);
        panelMantenLayout.setHorizontalGroup(
            panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMantenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMantenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProcesados, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel73)
                        .addGap(18, 18, 18)
                        .addComponent(lblInvalidos, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(249, 249, 249))
                    .addGroup(panelMantenLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarArchivo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelMantenLayout.setVerticalGroup(
            panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMantenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarArchivo)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(lblInvalidos)
                    .addComponent(lblProcesados)
                    .addComponent(jLabel80))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btnCamionCargar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCamionCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_nuevo.png"))); // NOI18N
        btnCamionCargar.setText("Cargar");
        btnCamionCargar.setEnabled(false);
        btnCamionCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionCargarActionPerformed(evt);
            }
        });

        tblCamion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Placa", "Fecha de registro", "Tipo de Camion", "Ultimo Mantenimiento", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCamion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCamionMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblCamion);
        if (tblCamion.getColumnModel().getColumnCount() > 0) {
            tblCamion.getColumnModel().getColumn(0).setResizable(false);
            tblCamion.getColumnModel().getColumn(1).setResizable(false);
            tblCamion.getColumnModel().getColumn(2).setResizable(false);
            tblCamion.getColumnModel().getColumn(3).setResizable(false);
            tblCamion.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCamionCargar))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                    .addComponent(panelManten, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCamionCargar))
                .addGap(20, 20, 20)
                .addComponent(panelManten, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCamionLayout = new javax.swing.GroupLayout(panelCamion);
        panelCamion.setLayout(panelCamionLayout);
        panelCamionLayout.setHorizontalGroup(
            panelCamionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCamionLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(panelNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        panelCamionLayout.setVerticalGroup(
            panelCamionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCamionLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelCamionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelCamion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCamionCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionCargarActionPerformed
        List<Camion> listaCamion = new ArrayList<Camion>();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        int[] datos = new int[2];
        try {
            camionControlador.CargaMasiva(archivo.getAbsolutePath(), datos, listaCamion);
            if (listaCamion.size()!=0){
                DefaultTableModel modelo = (DefaultTableModel) tblCamion.getModel();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                modelo.setRowCount(0);
                for (int i = 0; i < listaCamion.size(); i++) {
                    Object[] fila = new Object[6];
                    fila[0] = listaCamion.get(i).getIdCamion();
                    fila[1] = listaCamion.get(i).getPlaca();
                    fila[2] = listaCamion.get(i).getTipoCamion().getNombre();
                    fila[3] = listaCamion.get(i).getEstado();
                    fila[4] = sdf.format(listaCamion.get(i).getFechaRegistro());
                    modelo.addRow(fila);
                }
                lblProcesados.setText("" + datos[1]);
                lblInvalidos.setText("" + datos[0]);
                btnCamionCargar.setEnabled(false);
                txtNombreArchivo.setText("");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pantalla_CamionCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCamionCargarActionPerformed

    private void btnBuscarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArchivoActionPerformed
        obtenerArchivo();
        if (archivo!= null){ 
            txtNombreArchivo.setText(archivo.getAbsolutePath());
            btnCamionCargar.setEnabled(true);
        }
    }//GEN-LAST:event_btnBuscarArchivoActionPerformed

    private void tblCamionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCamionMouseClicked
        if (evt.getSource() == tblCamion){
            int fila = tblCamion.getSelectedRow();
            VerDatos(tblCamion.getValueAt(fila, 0).toString());
        }
    }//GEN-LAST:event_tblCamionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarArchivo;
    private javax.swing.JButton btnCamionCargar;
    private com.toedter.calendar.JDateChooser datCamionFecha;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JScrollPane jScrollPane8;
    private java.awt.Label label14;
    private java.awt.Label label15;
    private javax.swing.JLabel lblInvalidos;
    private javax.swing.JLabel lblProcesados;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelCamion;
    private javax.swing.JPanel panelManten;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JTable tblCamion;
    private javax.swing.JTextField txtCamionCodigo;
    private javax.swing.JTextField txtCamionDiesel;
    private javax.swing.JTextField txtCamionEstado;
    private javax.swing.JTextField txtCamionGLP;
    private javax.swing.JTextField txtCamionPlaca;
    private javax.swing.JTextField txtCamionTara;
    private javax.swing.JTextField txtCamionTipo;
    private javax.swing.JTextField txtNombreArchivo;
    // End of variables declaration//GEN-END:variables
}
