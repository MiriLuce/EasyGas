/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Empleado;
import Modelo.Hibernate.Ruta;
import Modelo.Hibernate.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;

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
            fila[2] = new SimpleDateFormat("HH:mm:ss").format(lista.get(i).getHoraSalida());
            fila[3] = new SimpleDateFormat("HH:mm:ss").format(lista.get(i).getHoraLlegada());
            fila[4] = lista.get(i).getCamion().getPlaca();
            modelo.addRow(fila);
        }
    }
     
     
     public List<Ruta> listarRutas(){
        List<Ruta> lista=null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        
        try {
            tx=EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Ruta");
            //falta jalar informacion de empleado          
            lista=query.list();
            for(Ruta u : lista){
                Hibernate.initialize(u.getEmpleadoByIdConductor());
                Hibernate.initialize(u.getCamion());
            }
            tx.commit();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }
        return lista;
    
    }
     
      public Ruta obtenerRutaPorCodigo(int codigo){
       
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        Ruta ruta = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            ruta = (Ruta) EasyGas.sesion.get(Ruta.class, codigo);
            Hibernate.initialize(ruta.getCamion());
            Hibernate.initialize(ruta.getEmpleadoByIdConductor());
            tx.commit();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            if (tx != null) {
                tx.rollback();
            }
        }   
        finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        return ruta;
    }
     
}
