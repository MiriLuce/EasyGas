/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Hibernate.Empleado;
import Modelo.Hibernate.Ruta;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author inf250
 */
public class RutaControlador {
    
    
     public void actualizaTablaRutas(List<Ruta> lista, JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            Object[] fila = new Object[5];
            fila[0] = lista.get(i).getIdRuta();
            fila[1] = lista.get(i).getFechaEntrega();
            fila[2] = new SimpleDateFormat("HH:mm:ss").format(lista.get(i).getHoraLlegada());
            fila[2] = new SimpleDateFormat("HH:mm:ss").format(lista.get(i).getHoraLlegada());
            fila[3] = lista.get(i).getCamion().getPlaca();
            modelo.addRow(fila);
        }
    }
}
