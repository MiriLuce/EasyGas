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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
      
    public  List<Ruta> buscaRutaFiltro(Date dtfechaDesde, Date dtfechaHasta,String placa,String nombreTipoCamion) {
       
        List aux=null;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
        String fechaDesde=dtfechaDesde==null?"":myFormat.format(dtfechaDesde);
        String fechaHasta=dtfechaHasta==null?"":myFormat.format(dtfechaHasta);
        String sql = "SELECT RUTA.* FROM RUTA INNER JOIN CAMION  on CAMION.idCamion=RUTA.idCamion INNER JOIN TIPO_CAMION ON TIPO_CAMION.idTipoCamion=CAMION.idCamion INNER JOIN EMPLEADO ON EMPLEADO.idEmpleado=RUTA.idConductor WHERE 1";
        if ((fechaDesde.compareTo("")==0) && (fechaHasta.compareTo("")==0) && (placa.compareTo("")==0) && (nombreTipoCamion.compareTo("Todos")==0) ) {
            aux=listarRutas();
            return aux;
           
        }
        else{ 
            if (!(fechaDesde.compareTo("")==0) ) {
                
                sql = sql + " and  RUTA.FechaEntrega >= :fechaDesde";
               

            }
             if(!(fechaHasta.compareTo("")==0)) {
                    sql = sql + " and  RUTA.FechaEntrega <= :fechaHasta";
             }
             
             if(!(placa.compareTo("")==0)){
                    sql = sql + " and  CAMION.Placa = :placa";
             }
             
             if(!(nombreTipoCamion.compareTo("Todos")==0)){
                    sql = sql + " and  TIPO_CAMION.Nombre = :nombreTipoCamion";
             }
        }
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Ruta.class);
           

            if (!(fechaHasta.compareTo("")==0)) {
               
              
                query.setParameter("fechaHasta",fechaHasta);
           
            }
            if (!(fechaDesde.compareTo("")==0)) {
                
                
                 query.setParameter("fechaDesde",fechaDesde);
            }
            
            if(!(placa.compareTo("")==0)){
                    query.setParameter("placa",placa);
             }
             
             if(!(nombreTipoCamion.compareTo("Todos")==0)){
                     query.setParameter("nombreTipoCamion",nombreTipoCamion);
             }
            aux = query.list();
            
            for(Ruta u : (ArrayList<Ruta>)aux){
                Hibernate.initialize(u.getCamion());
                Hibernate.initialize(u.getEmpleadoByIdConductor());
            }
            
            tx.commit();

            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }

        return aux;
    }
    
    public static void GuardarRutas(ArrayList<Ruta> lista){
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            for (int i = 0; i < lista.size(); i++) {
                for(int j=0;j<lista.get(i).getAristas().size();i++){
                    EasyGas.sesion.saveOrUpdate(lista.get(i).getAristas().get(j));
                }
                EasyGas.sesion.saveOrUpdate(lista.get(i));
            }

            tx.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }
    }
     
}
