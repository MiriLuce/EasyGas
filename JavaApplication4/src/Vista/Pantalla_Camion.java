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
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alulab14
 */
public class Pantalla_Camion extends javax.swing.JInternalFrame {

    /**
     * Creates new form InternalFrameCamion
     */
    
    private TipoCamionControlador tpCamionControlador = new TipoCamionControlador();   
    private CamionControlador camionControlador = new CamionControlador();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    //private List<TipoCamion> listaTipoCamion = null;
    
    public Pantalla_Camion() {
        initComponents();
        
        RefrescarCmbCamion(); 
        //LimpiarDatos();
    }

    private void RefrescarCmbCamion(){
        /*cmbCamionTipo.setEnabled(true);
        cmbCamionTipo.removeAllItems();
        cmbCamionBuscarTipo.removeAllItems();
        cmbCamionBuscarTipo.addItem("Todos");*/
        List<TipoCamion> listaTipoCamion = tpCamionControlador.ListarTipoCamion();
        if (listaTipoCamion!=null){
            //List<TipoCamion> listaTipoCamion = tpCamionControlador.ListarTipoCamion();
            int cantLista = listaTipoCamion.size();
            for(int i=0; i<cantLista; i++){
                cmbCamionTipo.addItem(listaTipoCamion.get(i).getNombre());
                cmbCamionBuscarTipo.addItem(listaTipoCamion.get(i).getNombre());
            }
        }
    }
    
    private void RefrescarTablaCamion(){
        
        String placa = txtCamionBuscarPlaca.getText();
        String estado = cmbCamionBuscarEstado.getSelectedItem().toString();
        String strTipo = cmbCamionBuscarTipo.getSelectedItem().toString();
        Date desde = datCamionBuscarDesde.getDate();
        Date hasta = datCamionBuscarHasta.getDate();
        
        int codTipo = 0;       
        if (estado.compareTo("Todos") == 0) estado = "";
        if (strTipo.compareTo("Todos") != 0){
            codTipo = tpCamionControlador.BuscarTipoCamionPorNombre(strTipo).getIdTipoCamion();
        }
        
        List<Camion> listaCamion= camionControlador.BuscarCamion(placa, estado, codTipo, desde, hasta);
        
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
        }
    }  
    
    private void VerDatos(String strCodigo){    
        int idCamionSel = Integer.parseInt(strCodigo);
        Camion camion = camionControlador.BuscarCamionPorCodigo(idCamionSel);
        txtCamionCodigo.setText("" + camion.getIdCamion());
        txtCamionPlaca.setText(camion.getPlaca());
        datCamionFecha.setDate(camion.getFechaRegistro());
        txtCamionEstado.setText(camion.getEstado());
        
        TipoCamion tpCamion = tpCamionControlador.BuscarTipoCamionPorCodigo(camion.getTipoCamion().getIdTipoCamion());
        cmbCamionTipo.setSelectedItem(tpCamion.getNombre());
        txtCamionGLP.setText("" + tpCamion.getCapacidadGlp());
        txtCamionDiesel.setText("" + tpCamion.getCapacidadDiesel());
        txtCamionTara.setText("" + tpCamion.getTara());
    }
    
    private void BotonesEditables(boolean valor){
        btnCamionNuevo.setEnabled(!valor);
        btnCamionEliminar.setEnabled(!valor);
        btnCamionEditar.setEnabled(!valor);
        btnCamionCancelar.setEnabled(valor);
        btnCamionGuardar.setEnabled(valor);
    }
    
    private void LimpiarDatos(){
        txtCamionCodigo.setText("");
        txtCamionPlaca.setText("");
        datCamionFecha.setDate(new Date());
        txtCamionEstado.setText("DISPONIBLE");
        //RefrescarCmbCamion();
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
        cmbCamionTipo = new javax.swing.JComboBox();
        btnCamionNuevo = new javax.swing.JButton();
        btnCamionEliminar = new javax.swing.JButton();
        btnCamionEditar = new javax.swing.JButton();
        btnCamionCancelar = new javax.swing.JButton();
        btnCamionGuardar = new javax.swing.JButton();
        panelBuscar = new javax.swing.JPanel();
        label15 = new java.awt.Label();
        panelDatos = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        datCamionBuscarDesde = new com.toedter.calendar.JDateChooser();
        datCamionBuscarHasta = new com.toedter.calendar.JDateChooser();
        panelManten = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        cmbCamionBuscarTipo = new javax.swing.JComboBox();
        txtCamionBuscarPlaca = new javax.swing.JTextField();
        cmbCamionBuscarEstado = new javax.swing.JComboBox();
        btnCamionBuscar = new javax.swing.JButton();
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

        txtCamionEstado.setEnabled(false);

        txtCamionGLP.setEnabled(false);

        txtCamionDiesel.setEnabled(false);

        txtCamionTara.setEnabled(false);

        jLabel136.setText("Estado:");

        jLabel137.setText("Fecha de Registro:");

        datCamionFecha.setEnabled(false);

        cmbCamionTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCamionTipoItemStateChanged(evt);
            }
        });

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
                    .addComponent(cmbCamionTipo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(cmbCamionTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btnCamionNuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCamionNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_nuevo.png"))); // NOI18N
        btnCamionNuevo.setText("Nuevo");
        btnCamionNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionNuevoActionPerformed(evt);
            }
        });

        btnCamionEliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCamionEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_eliminar.png"))); // NOI18N
        btnCamionEliminar.setText("Eliminar");
        btnCamionEliminar.setEnabled(false);
        btnCamionEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionEliminarActionPerformed(evt);
            }
        });

        btnCamionEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCamionEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_editar.png"))); // NOI18N
        btnCamionEditar.setText("Editar");
        btnCamionEditar.setEnabled(false);
        btnCamionEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionEditarActionPerformed(evt);
            }
        });

        btnCamionCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCamionCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_cancelar.png"))); // NOI18N
        btnCamionCancelar.setText("Cancelar");
        btnCamionCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionCancelarActionPerformed(evt);
            }
        });

        btnCamionGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCamionGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_guardar.png"))); // NOI18N
        btnCamionGuardar.setText("Guardar");
        btnCamionGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(btnCamionNuevo)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addComponent(btnCamionCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCamionGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addComponent(btnCamionEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCamionEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCamionNuevo))
                .addGap(20, 20, 20)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCamionEliminar)
                    .addComponent(btnCamionEditar))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCamionGuardar)
                    .addComponent(btnCamionCancelar))
                .addGap(64, 64, 64))
        );

        panelBuscar.setBackground(new java.awt.Color(240, 240, 225));
        panelBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBuscar.setPreferredSize(new java.awt.Dimension(750, 620));

        label15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label15.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label15.setForeground(new java.awt.Color(51, 0, 102));
        label15.setText("Buscar Camión");

        panelDatos.setBackground(new java.awt.Color(240, 240, 225));
        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Registro"));
        panelDatos.setPreferredSize(new java.awt.Dimension(340, 140));

        jLabel71.setText("Hasta:");

        jLabel72.setText("Desde:");

        datCamionBuscarDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datCamionBuscarDesdePropertyChange(evt);
            }
        });

        datCamionBuscarHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datCamionBuscarHastaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datCamionBuscarDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(datCamionBuscarHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel72)
                    .addComponent(datCamionBuscarDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71)
                    .addComponent(datCamionBuscarHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelManten.setBackground(new java.awt.Color(240, 240, 225));
        panelManten.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Camión"));
        panelManten.setPreferredSize(new java.awt.Dimension(340, 125));

        jLabel73.setText("Estado");

        jLabel70.setText("Tipo de Camión:");

        jLabel80.setText("Placa:");

        cmbCamionBuscarTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        cmbCamionBuscarEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Disponible", "En curso", "En mantenimiento", "De regreso" }));

        javax.swing.GroupLayout panelMantenLayout = new javax.swing.GroupLayout(panelManten);
        panelManten.setLayout(panelMantenLayout);
        panelMantenLayout.setHorizontalGroup(
            panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMantenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70)
                    .addComponent(jLabel80)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbCamionBuscarTipo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCamionBuscarPlaca, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbCamionBuscarEstado, 0, 180, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        panelMantenLayout.setVerticalGroup(
            panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMantenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCamionBuscarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addGap(18, 18, 18)
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txtCamionBuscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMantenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(cmbCamionBuscarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCamionBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCamionBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_buscar.png"))); // NOI18N
        btnCamionBuscar.setText("Buscar");
        btnCamionBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionBuscarActionPerformed(evt);
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
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCamionBuscar))
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBuscarLayout.createSequentialGroup()
                                .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelBuscarLayout.createSequentialGroup()
                                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 26, Short.MAX_VALUE)
                                .addComponent(panelManten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane8))))
                .addGap(20, 20, 20))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(panelManten, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCamionBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCamionNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionNuevoActionPerformed
        LimpiarDatos();
        txtCamionPlaca.setEnabled(true);
        cmbCamionTipo.setEnabled(true);
        BotonesEditables(true); 
    }//GEN-LAST:event_btnCamionNuevoActionPerformed

    private void btnCamionEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionEliminarActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar el registro");
        if (confirmacion== 0){           
            int codigo = Integer.parseInt( txtCamionCodigo.getText());
            String mensaje = camionControlador.EliminarCamion(codigo);
            RefrescarTablaCamion();
            LimpiarDatos();  
            BotonesEditables(true);
            txtCamionPlaca.setEnabled(true);
            cmbCamionTipo.setEnabled(true);
        }
    }//GEN-LAST:event_btnCamionEliminarActionPerformed

    private void btnCamionEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionEditarActionPerformed
        txtCamionPlaca.setEnabled(true);
        cmbCamionTipo.setEnabled(false);
        BotonesEditables(true);
    }//GEN-LAST:event_btnCamionEditarActionPerformed

    private void btnCamionCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionCancelarActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que no desea guardar los cambios");
        if (confirmacion== 0){ 
            VerDatos(txtCamionCodigo.getText());
            txtCamionPlaca.setEnabled(false);
            cmbCamionTipo.setEnabled(false);
            BotonesEditables(false);
        }         
    }//GEN-LAST:event_btnCamionCancelarActionPerformed

    private void btnCamionGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionGuardarActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que desea guardar los cambios");
        if (confirmacion== 0){ 
         
            String strCodigo = txtCamionCodigo.getText();
            String placa = txtCamionPlaca.getText();
            
            // Nuevo Registro de Camion
            if (strCodigo.equals("")){
                Camion camion = new Camion();
                camion.setPlaca(placa);
                Camion tmp = camionControlador.BuscarCamionPorPlaca(placa);
                if(tmp==null){
                    String strTipo = cmbCamionTipo.getSelectedItem().toString();
                    TipoCamion tipo = tpCamionControlador.BuscarTipoCamionPorNombre(strTipo);
                    camion.setTipoCamion(tipo);
                    int verifica = camionControlador.GuardarCamion(camion);
                    if (verifica==1){
                        txtCamionPlaca.setEnabled(false);
                        cmbCamionTipo.setEnabled(false);
                        BotonesEditables(false);
                        RefrescarTablaCamion();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Este número de placa ya esta registrado");
            }
            // Actualizacion de Registro de Tipo Camion
            else {
                int codigo = Integer.parseInt(strCodigo);
                int verifica = camionControlador.ActualizarCamion(codigo, placa);
                if (verifica==1){
                    txtCamionPlaca.setEnabled(false);
                    cmbCamionTipo.setEnabled(false);
                    BotonesEditables(false);
                    RefrescarTablaCamion();
                }                
            } 
        } 
    }//GEN-LAST:event_btnCamionGuardarActionPerformed

    private void btnCamionBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionBuscarActionPerformed
        RefrescarTablaCamion();
        /*String placa = txtCamionBuscarPlaca.getText();
        String estado = cmbCamionBuscarEstado.getSelectedItem().toString();
        String strTipo = cmbCamionBuscarTipo.getSelectedItem().toString();
        Date desde = datCamionBuscarDesde.getDate();
        Date hasta = datCamionBuscarHasta.getDate();
        
        int codTipo = 0;       
        if (estado.compareTo("Todos") == 0) estado = "";
        if (strTipo.compareTo("Todos") != 0){
            codTipo = tpCamionControlador.BuscarTipoCamionPorNombre(strTipo).getIdTipoCamion();
        }
        
        List<Camion> listaCamion= camionControlador.BuscarCamion(placa, estado, codTipo, desde, hasta);     */  
    }//GEN-LAST:event_btnCamionBuscarActionPerformed

    private void cmbCamionTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCamionTipoItemStateChanged
        String strTipo = cmbCamionTipo.getSelectedItem().toString();
        TipoCamion tipo = tpCamionControlador.BuscarTipoCamionPorNombre(strTipo);
        txtCamionGLP.setText("" + tipo.getCapacidadGlp());
        txtCamionDiesel.setText("" + tipo.getCapacidadDiesel());
        txtCamionTara.setText("" + tipo.getTara());
    }//GEN-LAST:event_cmbCamionTipoItemStateChanged

    private void datCamionBuscarDesdePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datCamionBuscarDesdePropertyChange
        if (evt.getSource() == datCamionBuscarDesde){
            if (datCamionBuscarDesde.getDate()!= null)
                datCamionBuscarHasta.setMinSelectableDate(datCamionBuscarDesde.getDate());
        }
    }//GEN-LAST:event_datCamionBuscarDesdePropertyChange

    private void datCamionBuscarHastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datCamionBuscarHastaPropertyChange
        if (evt.getSource() == datCamionBuscarHasta){
            if (datCamionBuscarHasta.getDate()!= null)
                datCamionBuscarDesde.setMaxSelectableDate(datCamionBuscarHasta.getDate());
        }
    }//GEN-LAST:event_datCamionBuscarHastaPropertyChange

    private void tblCamionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCamionMouseClicked
        if (evt.getSource() == tblCamion){
            int fila = tblCamion.getSelectedRow();
            VerDatos(tblCamion.getValueAt(fila, 0).toString());
        }
    }//GEN-LAST:event_tblCamionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCamionBuscar;
    private javax.swing.JButton btnCamionCancelar;
    private javax.swing.JButton btnCamionEditar;
    private javax.swing.JButton btnCamionEliminar;
    private javax.swing.JButton btnCamionGuardar;
    private javax.swing.JButton btnCamionNuevo;
    private javax.swing.JComboBox cmbCamionBuscarEstado;
    private javax.swing.JComboBox cmbCamionBuscarTipo;
    private javax.swing.JComboBox cmbCamionTipo;
    private com.toedter.calendar.JDateChooser datCamionBuscarDesde;
    private com.toedter.calendar.JDateChooser datCamionBuscarHasta;
    private com.toedter.calendar.JDateChooser datCamionFecha;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
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
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelCamion;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelManten;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JTable tblCamion;
    private javax.swing.JTextField txtCamionBuscarPlaca;
    private javax.swing.JTextField txtCamionCodigo;
    private javax.swing.JTextField txtCamionDiesel;
    private javax.swing.JTextField txtCamionEstado;
    private javax.swing.JTextField txtCamionGLP;
    private javax.swing.JTextField txtCamionPlaca;
    private javax.swing.JTextField txtCamionTara;
    // End of variables declaration//GEN-END:variables
}
