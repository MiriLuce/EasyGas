/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Algoritmo.Genetico.AlgoritmoGenetico;
import static Algoritmo.Genetico.AlgoritmoGenetico.mapa;
import Algoritmo.Genetico.Cromosoma;
import Controlador.*;
import Mapa.Mapa;
import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.*;
import Util.RelojAlgoritmo;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRPrintPage;
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
public class Pantalla_Simulacion extends javax.swing.JInternalFrame {

    //private CamionControlador camionControlador;
    private ArrayList<Cromosoma> soluciones;
    private ArrayList<Ruta> listaRuta;
    private List<Pedido> lstPedidos;
    private List<Pedido> lstPedidosSinPrioridad=null;
    private List<Pedido> lstPedidosConPrioridad=null;

    public Pantalla_Simulacion() {
        initComponents();

        this.btnIniciar.setEnabled(false);
        this.btnExportar.setEnabled(false);
        this.btnGrabar.setEnabled(false);
        this.btnCalcular.setEnabled(false);
        this.duracionSpinner.setEnabled(false);
        this.tblResultados.setEnabled(false);

        listaRuta = new ArrayList<Ruta>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSimulacion = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        label23 = new java.awt.Label();
        jPanel57 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        duracionSpinner = new javax.swing.JSpinner();
        btnCalcular = new javax.swing.JButton();
        jPanel59 = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();
        nPedCargaBoton = new javax.swing.JButton();
        panelMapa = new javax.swing.JPanel();

        panelSimulacion.setBackground(new java.awt.Color(240, 240, 225));

        jPanel56.setBackground(new java.awt.Color(240, 240, 225));
        jPanel56.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel56.setPreferredSize(new java.awt.Dimension(400, 620));

        label23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label23.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label23.setForeground(new java.awt.Color(51, 0, 102));
        label23.setText("Simulación");

        jPanel57.setBackground(new java.awt.Color(240, 240, 225));
        jPanel57.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel57.setPreferredSize(new java.awt.Dimension(360, 120));

        jLabel127.setText("Duración (min):");

        duracionSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel127)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(duracionSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(duracionSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnCalcular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_calcular.png"))); // NOI18N
        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        jPanel59.setBackground(new java.awt.Color(240, 240, 225));
        jPanel59.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel59.setPreferredSize(new java.awt.Dimension(360, 120));

        btnIniciar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_iniciar.png"))); // NOI18N
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Camiones", "Diesel", "Duración", "Costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultadosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblResultados);

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnIniciar)))
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addComponent(btnIniciar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExportar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_descargar.png"))); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        btnGrabar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_guardar.png"))); // NOI18N
        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        nPedCargaBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nPedCargaBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/img_editar.png"))); // NOI18N
        nPedCargaBoton.setText("Cargar Pedidos");
        nPedCargaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nPedCargaBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(label23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(nPedCargaBoton))
                    .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel56Layout.createSequentialGroup()
                            .addComponent(btnExportar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGrabar))))
                .addContainerGap())
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nPedCargaBoton))
                .addGap(18, 18, 18)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCalcular)
                .addGap(41, 41, 41)
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportar)
                    .addComponent(btnGrabar))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        panelMapa.setBackground(new java.awt.Color(240, 240, 225));
        panelMapa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelMapa.setPreferredSize(new java.awt.Dimension(750, 620));
        panelMapa.setRequestFocusEnabled(false);

        javax.swing.GroupLayout panelMapaLayout = new javax.swing.GroupLayout(panelMapa);
        panelMapa.setLayout(panelMapaLayout);
        panelMapaLayout.setHorizontalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        panelMapaLayout.setVerticalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelSimulacionLayout = new javax.swing.GroupLayout(panelSimulacion);
        panelSimulacion.setLayout(panelSimulacionLayout);
        panelSimulacionLayout.setHorizontalGroup(
            panelSimulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSimulacionLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(panelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        panelSimulacionLayout.setVerticalGroup(
            panelSimulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSimulacionLayout.createSequentialGroup()
                .addGroup(panelSimulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSimulacionLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSimulacionLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(panelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelSimulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelSimulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHoraInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraInicioActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        this.btnIniciar.setEnabled(false);

        int fila = tblResultados.getSelectedRow();
        Cromosoma solucion = null;

        if (fila != -1) { //si se ha seleccionado una fila
            if (fila == 0) {
                solucion = soluciones.get(0);
            }
            if (fila == 1) {
                solucion = soluciones.get(1);
            }
            if (fila == 2) {
                solucion = soluciones.get(2);
            }

            // obtener el camino de la solucion
            listaRuta.clear();
            int cantRutas = solucion.getCadena().size();
            for (int i = 0; i < cantRutas; i++) {
                Ruta ruta = solucion.getCadena().get(i).guardarEnMapa();
                listaRuta.add(ruta);
            }

            ////////////////////// MUESTRA SIMULACION //////////////////////////
            ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(); //esta lista debe ser la lista de todos los clientes obtenida con la carga masiva
            Mapa mapa = new Mapa("Ciudad XYZ", 800, 800, listaRuta, listaClientes);
            mapa.Empieza();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una solución para empezar la simulación");
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void tblResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultadosMouseClicked
        if (evt.getSource() == tblResultados) {
            this.btnIniciar.setEnabled(true);
            this.btnExportar.setEnabled(true);
        }
    }//GEN-LAST:event_tblResultadosMouseClicked

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        // TODO add your handling code here:
        //envio mensaje de confirmacion
        // guardo la ruta seleccionada por el algoritmo a bd
        // mensaje de que se guardaron los datos exitosamente
        this.btnCalcular.setEnabled(true);
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed

        int fila = tblResultados.getSelectedRow();

        if (fila != -1) {
            List<Ruta> ruta = null;// lo que acaba de guardar en la bd
            JasperPrint jMain = null;
            for (int i = 0; i < ruta.size(); i++) {
                String reportSource = new File("").getAbsolutePath() + "/src/Vista/Itinerario.jrxml";
                Map<String, Object> params = new HashMap<String, Object>();
                try {
                    if (!EasyGas.sesion.isOpen()) {
                        EasyGas.sesion = EasyGas.sesFact.openSession();
                    }
                    Connection conn = null;
                    SessionFactoryImplementor sfi = (SessionFactoryImplementor) EasyGas.sesion.getSessionFactory();
                    ConnectionProvider cp = sfi.getConnectionProvider();
                    conn = cp.getConnection();
                    SimpleDateFormat formato
                            = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
                    String fecha = formato.format(new Date());
                    //System.out.println(fecha);
                    // cambio el parametro por fechas y se acabo,  yupii
                    params.put("idRuta", ruta.get(i).getIdRuta());
                    String nombre = EasyGas.usuarioActual == null ? "Administrador" : EasyGas.usuarioActual.getEmpleado().getNombres() + " " + EasyGas.usuarioActual.getEmpleado().getApellidoPat();
                    params.put("reportTitle", "Itinerario de la Ruta N°" + ruta.get(i).getIdRuta().toString());
                    params.put("author", nombre);
                    params.put("startDate", fecha);
                    params.put("reportSubTitle", "Camión: " + ruta.get(i).getCamion().getPlaca() + "- Conductor: " + ruta.get(i).getEmpleadoByIdConductor().getNombres() + " " + ruta.get(i).getEmpleadoByIdConductor().getApellidoPat());
                    JasperReport jasperReport
                            = JasperCompileManager.compileReport(reportSource);

                    JasperPrint jasperPrint
                            = JasperFillManager.fillReport(
                                    jasperReport, params, conn);

                    jasperPrint.setName("Ruta " + ruta.get(i).getIdRuta());
                    if (i == 0) {
                        jMain = jasperPrint; // el primero es el main
                    } else { // sino anhado las paginas al main
                        List pages = jasperPrint.getPages();
                        for (int j = 0; j < pages.size(); j++) {
                            JRPrintPage object = (JRPrintPage) pages.get(j);
                            jMain.addPage(object);

                        }

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (EasyGas.sesion.isOpen()) {
                        EasyGas.sesion.close();
                    }
                }

            }
            JasperViewer.viewReport(jMain);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una solución para exportar los datos");
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        try {
            List<Camion> lstCamiones = CamionControlador.ListarCamion(); // por base de datos
            
            RelojAlgoritmo r = new RelojAlgoritmo();
            Turno t = obtenerTurnoActual();
            ArrayList<Cromosoma> solucionParcial = new ArrayList<Cromosoma>();
            
            AlgoritmoGenetico algoritmo = new AlgoritmoGenetico((ArrayList) lstCamiones, (ArrayList) lstPedidos, 1,mapa);
            
            while (true) {
                Turno t2 = obtenerTurnoActual();
                int cantListos = 0;
                if (t2 != null) {
                    if (!t.equals(t2)) {
                        System.out.println("Cambio de turno");
                        t = t2;
                    }
                    cantListos = obtenerPedidosListos(t2); // (lstPedidos actualiza tiene y no prioridad y lstConPrioridad y lstSinPrioridad tmabien los llena)
                }
                if (cantListos != 0) {
                    //recalcula
                    Algoritmo.Genetico.AlgoritmoGenetico.pedidos=(ArrayList)lstPedidos;
                    // algoritmo.setPedidosConPrioridad((ArrayList) lstPedidosConPrioridad); // solo se va a cambiar siempre su nueva lista de pedidos listos con prioridad
                    // algoritmo.setPedidosSinPrioridad((ArrayList) lstPedidosSinPrioridad); // solo se va a cambiar siempre su nueva lista de pedidos listos sin prioridad
                    soluciones = algoritmo.empieza();
                    System.out.println("Ya recalculu");
                    solucionParcial.add(soluciones.get(0));
                    solucionParcial.add(soluciones.get(1));
                    solucionParcial.add(soluciones.get(2));
                    quitarListos();
                    break;
                    
                }
                if (obtenerPedidosNoAtendidos(t) == 0) {
                    System.out.println("Todos los pedidos atendidos");
                    break;
                }
            }
            
            
            GeneralControlador.ActualizaTablaResultadosAlgoritmo(solucionParcial, tblResultados); //se actualiza la tabla
            
            this.tblResultados.setEnabled(true);
        } catch (IOException ex) {
            Logger.getLogger(Pantalla_Simulacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void nPedCargaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nPedCargaBotonActionPerformed
        File archivo = GeneralControlador.obtenerArchivo();

        if (archivo != null) {
            nPedCargaBoton.setEnabled(false);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                lstPedidos = PedidoControlador.CargaPedidosSimulacion(archivo.getAbsolutePath()); //funcion de carga masiva de pedidos con el formato del profe
            } catch (IOException ex) {
                Logger.getLogger(Pantalla_Simulacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Pantalla_Simulacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(null, "Se cargaron los pedidos correctamente");

            this.duracionSpinner.setEnabled(true);
            this.btnCalcular.setEnabled(true);
            this.btnGrabar.setEnabled(true);
        }
    }//GEN-LAST:event_nPedCargaBotonActionPerformed
    public Turno obtenerTurnoActual() {
        //String originalString = "2010-07-14 09:00:02";
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
        //String newString = new SimpleDateFormat("H:mm").format(date); // 9:00
        Date horaActual = RelojAlgoritmo.horaActual.getTime();
        ArrayList<Turno> lturnos = Algoritmo.Constantes.Constantes.lTurnos;
        //System.out.println(horaActual);
        for (int i = 0; i < 3; i++) {

            if (perteneceATurno(horaActual, lturnos.get(i))) {
                return lturnos.get(i);
            }

        }
        return null;

    }
    public void quitarListos(){
        int cantPedidos=lstPedidos.size();
        ArrayList<Pedido> pedidosFinales = new ArrayList<Pedido>();
        for(int i=0;i<cantPedidos;i++){
            if(lstPedidos.get(i).getEstado().compareTo("listo")!=0) 
                pedidosFinales.add(new Pedido(lstPedidos.get(i)));
        }
        lstPedidos.clear();
        lstPedidos=pedidosFinales;
    }
    public boolean perteneceATurno(Date hora, Turno turno) {
        return hora.after(turno.getHoraInicio()) && hora.before(turno.getHoraFin());

    }



    public int obtenerPedidosNoAtendidos(Turno t) {
        List<Pedido> pedidosListo;
        int cantPedidos = lstPedidos.size();
        int cantNoAtendidos = 0;

        for (int i = 0; i < cantPedidos; i++) {
            cantNoAtendidos++;
          

        }
        return cantNoAtendidos;

    }

    public int obtenerPedidosListos(Turno t) {
        List<Pedido> pedidosListo = new ArrayList<Pedido>();
        int cantPedidos = lstPedidos.size();
        int cantListo = 0;

        for (int i = 0; i < cantPedidos; i++) {
            Pedido p = lstPedidos.get(i);
            if (perteneceATurno(p.getHoraSolicitada(), t)) {
                    if (Algoritmo.Constantes.Constantes.lTurnos.get(1).equals(t)) {
                        p.setPrioridad("no tiene");
                    }
                    if (Algoritmo.Constantes.Constantes.lTurnos.get(0).equals(t) && p.getCliente().getTipoDocumento().compareTo("DNI") == 0) {
                        p.setPrioridad("tiene");
                    }
                    if (Algoritmo.Constantes.Constantes.lTurnos.get(2).equals(t) && !(p.getCliente().getTipoDocumento().compareTo("DNI") == 0)) {
                        p.setPrioridad("tiene");
                    }
                    lstPedidos.get(i).setEstado("listo");
                   /* if (p.getPrioridad().compareTo("tiene") == 0) {
                        lstPedidosConPrioridad.add(p);
                    } else {
                        lstPedidosSinPrioridad.add(p);
                    }
                           */

                    cantListo++;
                
            }

        }
        return cantListo;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JSpinner duracionSpinner;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JScrollPane jScrollPane8;
    private java.awt.Label label23;
    private javax.swing.JButton nPedCargaBoton;
    private javax.swing.JPanel panelMapa;
    private javax.swing.JPanel panelSimulacion;
    private javax.swing.JTable tblResultados;
    // End of variables declaration//GEN-END:variables
}
