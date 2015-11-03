/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.MantenimientoControlador;
import Controlador.TipoCamionControlador;
import Controlador.CamionControlador;
import Modelo.Hibernate.Mantenimiento;
import Modelo.Hibernate.TipoCamion;
import Modelo.Hibernate.Camion;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alulab14
 */
public class Pantalla_Mantenimiento extends javax.swing.JInternalFrame {

    /**
     * Creates new form InternalFrameMantenimiento
     */
    
    private MantenimientoControlador mantenControlador = new MantenimientoControlador();
    private TipoCamionControlador tpCamionControlador = new TipoCamionControlador(); 
    private CamionControlador camControlador = new CamionControlador();
    private MantenimientoModelo mantenModelo = new MantenimientoModelo();
    private EventoTabla eventoTabla = new EventoTabla();
    private EventoCombo eventoCombo = new EventoCombo();
    private EventoComboBuscar eventoComboBuscar = new EventoComboBuscar();
    private SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
    
    public Pantalla_Mantenimiento() {        
        initComponents();
        tablaMantenimiento.setModel(mantenModelo);       
        tablaMantenimiento.addMouseListener(eventoTabla);
        cmbTipoCamion.addItemListener(eventoCombo);
        cmbTipoCamionBuscar.addItemListener(eventoComboBuscar);
        RefrescarTabla(null);        
        llenaCmbTipoCamion();
        llenaCmbPlaca();
        dateDesde.setDate(new Date());
        dateHasta.setDate(new Date());
    }
    
    //Llenado de cmb Tipo Camion
    private void llenaCmbTipoCamion(){
        List<TipoCamion> lista = tpCamionControlador.ListarTipoCamion();
        for (TipoCamion tp : lista) {
            cmbTipoCamion.addItem(tp);
            cmbTipoCamionBuscar.addItem(tp);
        }
       
    }
    
    //Llenado de cmb Placas
    
    private void llenaCmbPlaca(){        
        TipoCamion tpc = (TipoCamion)cmbTipoCamion.getSelectedItem();
        int id = tpc.getIdTipoCamion();
        List<Camion> listaCamion = mantenControlador.BuscarCamionPorTipo(id);
        //List<Camion> listaCamion = camionControlador.ListarCamion();
        cmbPlaca.removeAllItems();
        for (int i = 0; i < listaCamion.size(); i++) {
            cmbPlaca.addItem(listaCamion.get(i).getPlaca());                
        }
    }
    
    private void llenaCmbPlacaBuscar(){
        if(cmbTipoCamionBuscar.getSelectedIndex()!=0){
            TipoCamion tpc = (TipoCamion)cmbTipoCamionBuscar.getSelectedItem();
            int id = tpc.getIdTipoCamion();
            List<Camion> listaCamion = mantenControlador.BuscarCamionPorTipo(id);
            cmbPlacaBuscar.removeAllItems();
            cmbPlacaBuscar.addItem("Todos");
            for (int i = 0; i < listaCamion.size(); i++) {
                cmbPlacaBuscar.addItem(listaCamion.get(i).getPlaca());                
            }
        }
        
    }
    //Refrescar tabla
    
    private void RefrescarTabla(List<Mantenimiento> lista){
        if(lista == null){
            mantenModelo.lista = mantenControlador.ListarMantenimiento();
        }else{
            mantenModelo.lista = lista;
        }
        
        mantenModelo.fireTableChanged(null);
    }
    //Modelo de tabla
    
    private class MantenimientoModelo extends AbstractTableModel{
        
        String [] titles = {"Código","Camión","Cant Turnos","Fecha de Registro","Hora","Estado"};
        List<Mantenimiento> lista = new ArrayList<Mantenimiento>();
        
        @Override
        public int getRowCount() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return titles.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            Object value = null;            
            switch(columnIndex){
                case 0: value = lista.get(rowIndex).getIdMantenimiento(); break;
                case 1: value = lista.get(rowIndex).getCamion().getPlaca(); break;
                case 2: value = lista.get(rowIndex).getCantTurnos(); break;
                case 3: value = lista.get(rowIndex).getFecha().toString(); break;
                case 4: value = formatoHora.format(lista.get(rowIndex).getHora()); break;
                case 5: value = lista.get(rowIndex).getEstado(); break;
            }
            return value;
        }
        
        @Override
        public String getColumnName(int col){
            return titles[col];
        }
        
    }
    
    private class EventoTabla implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == tablaMantenimiento){
                int fila = tablaMantenimiento.getSelectedRow();
                VerDatos(tablaMantenimiento.getValueAt(fila,0).toString());
                DatosEditables(false);
                BotonesEditables(false);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private class EventoCombo implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getStateChange() == ItemEvent.SELECTED){
                llenaCmbPlaca();
            }
        }
        
    }
    
    private class EventoComboBuscar implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getStateChange() == ItemEvent.SELECTED){
                llenaCmbPlacaBuscar();
            }
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMantenimiento = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        label11 = new java.awt.Label();
        btnGuardar = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        cmbTipoCamion = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        spinTurnos = new javax.swing.JSpinner();
        cmbPlaca = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        txtHora = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        label12 = new java.awt.Label();
        jPanel25 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        dateDesde = new com.toedter.calendar.JDateChooser();
        dateHasta = new com.toedter.calendar.JDateChooser();
        jPanel26 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        cmbTipoCamionBuscar = new javax.swing.JComboBox();
        cmbPlacaBuscar = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaMantenimiento = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();

        panelMantenimiento.setBackground(new java.awt.Color(240, 240, 225));

        jPanel18.setBackground(new java.awt.Color(240, 240, 225));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setPreferredSize(new java.awt.Dimension(400, 620));

        label11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label11.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        label11.setForeground(new java.awt.Color(51, 0, 102));
        label11.setText("Mantenimiento de Camiones");

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(240, 240, 225));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel23.setPreferredSize(new java.awt.Dimension(360, 180));

        jLabel55.setText("Hora:");

        jLabel47.setText("Fecha:");

        jLabel58.setText("Placa:");

        jLabel57.setText("Turnos Bloqueados:");

        spinTurnos.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));

        cmbPlaca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));

        jLabel59.setText("Tipo de Camión:");

        txtCodigo.setEnabled(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        jLabel90.setText("Código:");

        txtHora.setToolTipText("");

        jLabel1.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "En mantenimiento", "Listo" }));
        cmbEstado.setEnabled(false);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addComponent(jLabel58)
                    .addComponent(jLabel47)
                    .addComponent(jLabel55)
                    .addComponent(jLabel57)
                    .addComponent(jLabel90)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(spinTurnos)
                    .addComponent(cmbPlaca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTipoCamion, 0, 200, Short.MAX_VALUE)
                    .addComponent(dateFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHora)
                    .addComponent(cmbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setPreferredSize(new java.awt.Dimension(140, 41));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(240, 240, 225));
        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel24.setPreferredSize(new java.awt.Dimension(750, 620));
        jPanel24.setRequestFocusEnabled(false);

        label12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label12.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label12.setForeground(new java.awt.Color(51, 0, 102));
        label12.setText("Buscar Mantenimiento de Camiones");

        jPanel25.setBackground(new java.awt.Color(240, 240, 225));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Programada"));
        jPanel25.setPreferredSize(new java.awt.Dimension(340, 120));

        jLabel60.setText("Hasta:");

        jLabel61.setText("Desde:");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(dateHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel61)
                    .addComponent(dateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addComponent(dateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(240, 240, 225));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Camión"));
        jPanel26.setPreferredSize(new java.awt.Dimension(340, 120));

        jLabel62.setText("Placa:");

        jLabel64.setText("Tipo de Camión:");

        cmbTipoCamionBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        cmbPlacaBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipoCamionBuscar, 0, 200, Short.MAX_VALUE)
                    .addComponent(cmbPlacaBuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoCamionBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(cmbPlacaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        tablaMantenimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tablaMantenimiento);

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addComponent(label12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(321, 321, 321)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(20, 20, 20)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMantenimientoLayout = new javax.swing.GroupLayout(panelMantenimiento);
        panelMantenimiento.setLayout(panelMantenimientoLayout);
        panelMantenimientoLayout.setHorizontalGroup(
            panelMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMantenimientoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelMantenimientoLayout.setVerticalGroup(
            panelMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMantenimientoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VerDatos(String stringId){
        int id = Integer.parseInt(stringId);
        Mantenimiento man = mantenControlador.BuscarMantenimientoPorCodigo(id);
        txtCodigo.setText("" + man.getIdMantenimiento());
        cmbTipoCamion.setSelectedItem(man.getCamion().getTipoCamion());
        cmbPlaca.setSelectedItem(man.getCamion());
        dateFecha.setDate(man.getFecha());
        txtHora.setText(formatoHora.format(man.getHora()));
        spinTurnos.setValue(man.getCantTurnos());
    }
    
    private void DatosEditables(boolean valor){
        cmbTipoCamion.setEnabled(valor);
        cmbPlaca.setEnabled(valor);
        dateFecha.setEnabled(valor);
        txtHora.setEnabled(valor);
        spinTurnos.setEnabled(valor);
    }
    
    private void BotonesEditables(boolean valor){
        btnNuevo.setEnabled(!valor);
        btnEliminar.setEnabled(!valor);
        btnEditar.setEnabled(!valor);
        btnCancelar.setEnabled(valor);
        btnGuardar.setEnabled(valor);
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que desea guardar los cambios");
        if(confirmacion==0){
            DatosEditables(false);
            BotonesEditables(false);
            cmbEstado.setEnabled(false);

            Date hora = new Date();
            Mantenimiento man = new Mantenimiento();
            String strCodigo = txtCodigo.getText();
            if(cmbPlaca.getItemCount()==0){
                JOptionPane.showMessageDialog(null,"Seleccione una placa");
                return;
            }else{
                String strCam = (String)cmbPlaca.getSelectedItem();
                Camion cam = camControlador.BuscarCamionPorPlaca(strCam);
                man.setCamion(cam);
            }
            try {
                hora = formatoHora.parse(txtHora.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,"La hora no esta en el formato adecuado");
                return;
            }
            man.setFecha(new Date());
            man.setHora(hora);
            String estado = (String) cmbEstado.getSelectedItem();
            man.setEstado(estado);
            man.setIdTipoMantenimiento("");
            man.setCantTurnos(Integer.parseInt(spinTurnos.getValue().toString()));

            //Si es Editar
            if(!strCodigo.equals("")){
                man.setIdMantenimiento(Integer.parseInt(strCodigo));
            }
            String mensaje = mantenControlador.GuardarMantenimiento(man);
            mantenControlador.GuardarDisponibilidadEnMantenimiento(man, man.getCantTurnos());
            JOptionPane.showMessageDialog(null, mensaje);
            RefrescarTabla(null);
        }
        
        //String mensaje = mantenControlador.GuardarMantenimiento(turnos, camion);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        llenaCmbTipoCamion();
        llenaCmbPlaca();
        txtCodigo.setText("");
        dateFecha.setDate(new Date());
        txtHora.setText(formatoHora.format(new Date()));
        spinTurnos.setValue(1);
        cmbEstado.setSelectedIndex(0);
        DatosEditables(true);
        BotonesEditables(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que no desea guardar los cambios");
        if(confirmacion==0){
            VerDatos(txtCodigo.getText());
            DatosEditables(false);
            BotonesEditables(false);
        }
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        DatosEditables(true);
        BotonesEditables(true);
        cmbEstado.setEnabled(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int confirma = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el registro?");
        if(confirma==0){
            int id = Integer.parseInt(txtCodigo.getText());                       
            String mensaje = mantenControlador.EliminarMantenimiento(id);
            JOptionPane.showMessageDialog(null, mensaje);
            txtCodigo.setText("");
            dateFecha.setDate(new Date());
            txtHora.setText(formatoHora.format(new Date()));
            spinTurnos.setValue(1);
            cmbEstado.setSelectedIndex(0);
            RefrescarTabla(null); 
            DatosEditables(true);
            BotonesEditables(true);
        }
        
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        DatosEditables(false);
        BotonesEditables(false);
        if(dateDesde.getDate().getTime()>dateHasta.getDate().getTime()){
            JOptionPane.showMessageDialog(null, "Las fechas dadas no conforman un rango (desde - hasta)");
            return;
        }
        String stringId = null;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(cmbTipoCamionBuscar.getSelectedIndex()==0){
            stringId = (String) cmbTipoCamionBuscar.getSelectedItem();
        }else{
            TipoCamion tp = (TipoCamion)cmbTipoCamionBuscar.getSelectedItem();
            stringId = tp.getIdTipoCamion().toString();        
        }        
        List<Mantenimiento> aux = mantenControlador.BuscarMantenimiento(stringId, cmbPlacaBuscar.getSelectedItem().toString());
        List<Mantenimiento> lista = new ArrayList<Mantenimiento>();
        for(Mantenimiento man : aux){
            if(man.getFecha().after(dateDesde.getDate()) && man.getFecha().before(dateHasta.getDate())){
                lista.add(man);
            }
        }
        RefrescarTabla(lista);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbPlaca;
    private javax.swing.JComboBox cmbPlacaBuscar;
    private javax.swing.JComboBox cmbTipoCamion;
    private javax.swing.JComboBox cmbTipoCamionBuscar;
    private com.toedter.calendar.JDateChooser dateDesde;
    private com.toedter.calendar.JDateChooser dateFecha;
    private com.toedter.calendar.JDateChooser dateHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JScrollPane jScrollPane7;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private javax.swing.JPanel panelMantenimiento;
    private javax.swing.JSpinner spinTurnos;
    private javax.swing.JTable tablaMantenimiento;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtHora;
    // End of variables declaration//GEN-END:variables
}
