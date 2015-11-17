/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.RutaControlador;
import Controlador.TipoCamionControlador;
import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Empleado;
import Modelo.Hibernate.Ruta;
import Modelo.Hibernate.TipoCamion;
import java.awt.Cursor;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

/**
 *
 * @author alulab14
 */
public class Pantalla_Ruta extends javax.swing.JInternalFrame {

    /**
     * Creates new form InternalFrameRuta
     */
    RutaControlador rutaControlador = new RutaControlador();
    TipoCamionControlador tipoCamionControlador = new TipoCamionControlador();  
    public Pantalla_Ruta() {
        initComponents();
        List<Ruta> lista = rutaControlador.listarRutas();
        rutaControlador.actualizaTablaRutas(lista,this.tblRuta);
        this.btnVisualizar.setEnabled(false);
        RefrescarCmbCamion(); 
    }
     private void RefrescarCmbCamion(){
        /*cmbCamionTipo.setEnabled(true);
        cmbCamionTipo.removeAllItems();
        cmbCamionBuscarTipo.removeAllItems();
        cmbCamionBuscarTipo.addItem("Todos");*/
        List<TipoCamion> listaTipoCamion = tipoCamionControlador.ListarTipoCamion();
        for (TipoCamion tp : listaTipoCamion) {
            cmbTipoCamion.addItem(tp);
            
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

        panelRuta1 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        label23 = new java.awt.Label();
        jPanel57 = new javax.swing.JPanel();
        txtHoraLlegada = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        txtHoraSalida = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        txtDiesel = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        txtCantGLP = new javax.swing.JTextField();
        txtFechaSalida = new javax.swing.JTextField();
        jPanel58 = new javax.swing.JPanel();
        txtConductor = new javax.swing.JTextField();
        txtPlaca = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        btnVisualizar = new javax.swing.JButton();
        jPanel59 = new javax.swing.JPanel();
        label24 = new java.awt.Label();
        jPanel60 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        datFechaDesde = new com.toedter.calendar.JDateChooser();
        datFechaHasta = new com.toedter.calendar.JDateChooser();
        jPanel61 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        cmbTipoCamion = new javax.swing.JComboBox();
        txtPlacaBuscar = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblRuta = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        panelRuta1.setBackground(new java.awt.Color(240, 240, 225));

        jPanel56.setBackground(new java.awt.Color(240, 240, 225));
        jPanel56.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel56.setPreferredSize(new java.awt.Dimension(400, 620));
        jPanel56.setRequestFocusEnabled(false);

        label23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label23.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label23.setForeground(new java.awt.Color(51, 0, 102));
        label23.setText("Ruta");

        jPanel57.setBackground(new java.awt.Color(240, 240, 225));
        jPanel57.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Salida"));
        jPanel57.setPreferredSize(new java.awt.Dimension(360, 180));

        txtHoraLlegada.setEnabled(false);

        jLabel110.setText("Hora de Llegada:");

        jLabel112.setText("Hora de Salida:");

        jLabel125.setText("Fecha de Salida:");

        txtHoraSalida.setEnabled(false);

        jLabel126.setText("Carga de GLP:");

        jLabel127.setText("Carga de Diesel:");

        txtDiesel.setEnabled(false);

        txtCodigo.setEnabled(false);

        jLabel128.setText("Código:");

        txtCantGLP.setEnabled(false);

        txtFechaSalida.setEnabled(false);

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel128)
                    .addComponent(jLabel125)
                    .addComponent(jLabel112)
                    .addComponent(jLabel110)
                    .addComponent(jLabel126)
                    .addComponent(jLabel127))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFechaSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtHoraSalida, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHoraLlegada, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantGLP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDiesel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel125)
                    .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(txtHoraLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel126)
                    .addComponent(txtCantGLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(txtDiesel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel58.setBackground(new java.awt.Color(240, 240, 225));
        jPanel58.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignaciones"));

        txtConductor.setEnabled(false);

        txtPlaca.setEnabled(false);

        jLabel129.setText("Placa:");

        jLabel130.setText("Conductor:");

        txtDNI.setEnabled(false);

        jLabel131.setText("DNI:");

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel130)
                    .addComponent(jLabel129)
                    .addComponent(jLabel131))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDNI)
                    .addComponent(txtConductor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtPlaca))
                .addGap(20, 20, 20))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130)
                    .addComponent(txtConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel131)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnVisualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_generar.png"))); // NOI18N
        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVisualizar))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel56Layout.createSequentialGroup()
                                .addComponent(label23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVisualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel59.setBackground(new java.awt.Color(240, 240, 225));
        jPanel59.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel59.setPreferredSize(new java.awt.Dimension(750, 620));
        jPanel59.setRequestFocusEnabled(false);

        label24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label24.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label24.setForeground(new java.awt.Color(51, 0, 102));
        label24.setText("Buscar Ruta");

        jPanel60.setBackground(new java.awt.Color(240, 240, 225));
        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Programada"));
        jPanel60.setPreferredSize(new java.awt.Dimension(340, 120));
        jPanel60.setVerifyInputWhenFocusTarget(false);

        jLabel132.setText("Hasta:");

        jLabel133.setText("Desde:");

        datFechaDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datFechaDesdePropertyChange(evt);
            }
        });

        datFechaHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datFechaHastaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel133, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel132, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(42, 42, 42)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel133)
                    .addComponent(datFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel132)
                    .addComponent(datFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel61.setBackground(new java.awt.Color(240, 240, 225));
        jPanel61.setBorder(javax.swing.BorderFactory.createTitledBorder("Camión"));
        jPanel61.setPreferredSize(new java.awt.Dimension(340, 120));

        jLabel134.setText("Placa:");

        jLabel135.setText("Tipo de Camión:");

        cmbTipoCamion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel135)
                    .addComponent(jLabel134))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipoCamion, 0, 200, Short.MAX_VALUE)
                    .addComponent(txtPlacaBuscar))
                .addGap(20, 20, 20))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel135))
                .addGap(18, 18, 18)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134)
                    .addComponent(txtPlacaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblRuta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(32), "12/09/15", "07:45", "09:30", "B14490"},
                { new Integer(254), "12/09/15", "15:20", "16:30", "G40M12"},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Fecha de Salida", "Hora Salida", "Hora Llegada", "Camión"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRutaMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblRuta);

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_borrar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane12)
                            .addGroup(jPanel59Layout.createSequentialGroup()
                                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(label24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnBuscar))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRuta1Layout = new javax.swing.GroupLayout(panelRuta1);
        panelRuta1.setLayout(panelRuta1Layout);
        panelRuta1Layout.setHorizontalGroup(
            panelRuta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRuta1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        panelRuta1Layout.setVerticalGroup(
            panelRuta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRuta1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelRuta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRuta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRuta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void VerDatos(String strCodigo){    
        int idRutaCodigo = Integer.parseInt(strCodigo);
        Ruta ruta = rutaControlador.obtenerRutaPorCodigo(idRutaCodigo);
        txtCodigo.setText(ruta.getIdRuta().toString());
        this.txtDNI.setText(ruta.getCamion().getPlaca());
        this.txtFechaSalida.setText(new SimpleDateFormat("dd/MM/yyyy").format(ruta.getFechaEntrega()));
        this.txtHoraSalida.setText(new SimpleDateFormat("HH:mm:ss").format(ruta.getHoraSalida()));
        this.txtHoraLlegada.setText(new SimpleDateFormat("HH:mm:ss").format(ruta.getHoraLlegada()));
        this.txtPlaca.setText(ruta.getCamion().getPlaca());
        this.txtConductor.setText(ruta.getEmpleadoByIdConductor().getNombres() + " " +ruta.getEmpleadoByIdConductor().getApellidoPat() + " " + ruta.getEmpleadoByIdConductor().getApellidoMat());
        this.txtDNI.setText(ruta.getEmpleadoByIdConductor().getDni());
        this.btnVisualizar.setEnabled(true);
        
        
        
    }  
    private void txtCantDieselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantDieselActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantDieselActionPerformed

    private void txtPlacaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        List<Ruta> lista = rutaControlador.buscaRutaFiltro(datFechaDesde.getDate() ,datFechaHasta.getDate(),txtPlacaBuscar.getText(),cmbTipoCamion.getSelectedItem().toString());
        rutaControlador.actualizaTablaRutas(lista,this.tblRuta);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRutaMouseClicked
        // TODO add your handling code here:
         if (evt.getSource() == tblRuta){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            int fila = tblRuta.getSelectedRow();
            VerDatos(tblRuta.getValueAt(fila, 0).toString());
            List<Ruta> lista = rutaControlador.listarRutas();
            rutaControlador.actualizaTablaRutas(lista,this.tblRuta);
            this.setCursor(Cursor.getDefaultCursor());
         
         }
    }//GEN-LAST:event_tblRutaMouseClicked

    private void datFechaDesdePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datFechaDesdePropertyChange
        // TODO add your handling code here:
         if (evt.getSource() == datFechaDesde) {
            if (datFechaHasta.getDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(datFechaDesde.getDate());
                cal.add(Calendar.MINUTE, 60*24);
                datFechaHasta.setMinSelectableDate(cal.getTime());
            }
        }
    }//GEN-LAST:event_datFechaDesdePropertyChange

    private void datFechaHastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datFechaHastaPropertyChange
        // TODO add your handling code here:
        if (evt.getSource() == datFechaHasta) {
            if (datFechaDesde.getDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(datFechaHasta.getDate());
                cal.add(Calendar.MINUTE, 60*24);
                datFechaDesde.setMaxSelectableDate(cal.getTime());
            }
        }
    }//GEN-LAST:event_datFechaHastaPropertyChange

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        datFechaDesde.setDate(null);
        datFechaHasta.setDate(null);
        cmbTipoCamion.setSelectedIndex(0);
        txtPlacaBuscar.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        // TODO add your handling code here:
         String reportSource = new File("").getAbsolutePath()+ "/src/Vista/Itinerario.jrxml";
     
        
        Map<String, Object> params = new HashMap<String, Object>();
       
        try
            {   
                if (!EasyGas.sesion.isOpen()) {
                    EasyGas.sesion = EasyGas.sesFact.openSession();
                }
                Connection conn = null;
                SessionFactoryImplementor sfi = (SessionFactoryImplementor) EasyGas.sesion.getSessionFactory();
                    ConnectionProvider cp = sfi.getConnectionProvider();
                    conn = cp.getConnection();
                SimpleDateFormat formato = 
                    new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
                String fecha = formato.format(new Date());
                //System.out.println(fecha);
                // cambio el parametro por fechas y se acabo,  yupii
                params.put("idRuta",this.txtCodigo.getText() );
                String nombre =EasyGas.usuarioActual==null? "Administrador":EasyGas.usuarioActual.getEmpleado().getNombres() +  " " + EasyGas.usuarioActual.getEmpleado().getApellidoPat();
                params.put("reportTitle", "Itinerario de la Ruta N°" + txtCodigo.getText() ); params.put("author", nombre ); params.put("startDate", fecha);
                params.put("reportSubTitle", "Camión: " + this.txtPlaca.getText() + "- Conductor: " + this.txtConductor.getText() );
                JasperReport jasperReport =
                    JasperCompileManager.compileReport(reportSource);

                JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                        jasperReport, params,conn);
 
                JasperViewer.viewReport(jasperPrint);
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally{
                if (EasyGas.sesion.isOpen()){
                    EasyGas.sesion.close();
                }
            }
    }//GEN-LAST:event_btnVisualizarActionPerformed

    
         
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox cmbTipoCamion;
    private com.toedter.calendar.JDateChooser datFechaDesde;
    private com.toedter.calendar.JDateChooser datFechaHasta;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JScrollPane jScrollPane12;
    private java.awt.Label label23;
    private java.awt.Label label24;
    private javax.swing.JPanel panelRuta1;
    private javax.swing.JTable tblRuta;
    private javax.swing.JTextField txtCantGLP;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtConductor;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDiesel;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtHoraLlegada;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPlacaBuscar;
    // End of variables declaration//GEN-END:variables
}
