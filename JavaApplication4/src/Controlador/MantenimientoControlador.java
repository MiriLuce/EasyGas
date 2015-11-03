/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Mantenimiento;
import Modelo.Hibernate.Camion;
import Modelo.Hibernate.Disponibilidad;
import Util.HibernateUtil;
import java.util.*;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Luis
 */
public class MantenimientoControlador {
    
    public List<Mantenimiento> ListarMantenimiento(){
        List<Mantenimiento> lista = null;
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        
        try{
            tx = EasyGas.sesion.beginTransaction();             
            lista = EasyGas.sesion.createCriteria(Mantenimiento.class).list();
            for(Mantenimiento man : lista){
                Hibernate.initialize(man.getCamion());
                Hibernate.initialize(man.getCamion().getTipoCamion());
            }
            tx.commit();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        }finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        
        return lista;
    }
    
    public List<Mantenimiento> BuscarMantenimiento(String idTipoCamion, String placa){
        List<Mantenimiento> lista = null;
        
        String consulta = "select MANTENIMIENTO.* from MANTENIMIENTO inner join CAMION on MANTENIMIENTO.idCamion = CAMION.idCamion where 1";
        if((idTipoCamion.compareTo("Todos")==0) && (placa.compareTo("Todos")==0)){
            lista = this.ListarMantenimiento();
            return lista;
        }else{
            if(!(idTipoCamion.compareTo("Todos")==0)){
                consulta += " and CAMION.idTipoCamion = :idTipoCamion";
            }
            if(!(placa.compareTo("Todos")==0)){
                consulta += " and CAMION.Placa = :placa";
            }
        }
            
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        try {
            tx = EasyGas.sesion.beginTransaction(); 
            
            SQLQuery query = EasyGas.sesion.createSQLQuery(consulta);
            query.addEntity(Mantenimiento.class);
            if(!(idTipoCamion.compareTo("Todos")==0)){
                query.setParameter("idTipoCamion", Integer.parseInt(idTipoCamion));
            }
            if(!(placa.compareTo("Todos")==0)){
                query.setParameter("placa", placa);
            }
            lista = query.list();
            for(Mantenimiento man : lista){
                Hibernate.initialize(man.getCamion());
                Hibernate.initialize(man.getCamion().getTipoCamion());
            }
            
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        } finally{
           if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }        
        return lista;
    }
    
    public Mantenimiento BuscarMantenimientoPorCodigo(int codigo){
       
        Mantenimiento mantenimiento = null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();   
            mantenimiento = (Mantenimiento) EasyGas.sesion.get(Mantenimiento.class, codigo);
            Hibernate.initialize(mantenimiento.getCamion());
            Hibernate.initialize(mantenimiento.getCamion().getTipoCamion());
            tx.commit();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        }                
        finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        return mantenimiento;
    }
    
    public String GuardarMantenimiento(Mantenimiento man){
    
        String mensaje = "Se guardaron los cambios exitosamente";
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;          

        try{            
            tx = EasyGas.sesion.beginTransaction();   
            EasyGas.sesion.saveOrUpdate(man);
            tx.commit();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        }       
        finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }      
                
        return mensaje;
    }
        
    
    public String EliminarMantenimiento(int codigo){
        
        String mensaje = "Se ha eliminado el registro exitosamente";
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;   
        
        try{
            tx = EasyGas.sesion.beginTransaction();         
            Mantenimiento mantenimiento = (Mantenimiento) EasyGas.sesion.get(Mantenimiento.class, codigo);
            EasyGas.sesion.delete(mantenimiento);
            tx.commit();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        } 
        finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        return mensaje;
    }
    
    //Esta funcion ira temporalmente aqui para evitar merges
    //Debe ir a CamionControlador
    
    public List<Camion> BuscarCamionPorTipo(int idTipoCamion){
        List<Camion> listaCamion = null;
       if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        try {
            tx = EasyGas.sesion.beginTransaction();
            String consulta = "select * from CAMION where";
            consulta += " idTipoCamion = :idTipoCamion";             
             SQLQuery query = EasyGas.sesion.createSQLQuery(consulta);            
            query.setParameter("idTipoCamion", idTipoCamion);
            query.addEntity(Camion.class);
            listaCamion = query.list();
            for(Camion cam : listaCamion){
                Hibernate.initialize(cam.getTipoCamion());                
            }
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        }finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        
        return listaCamion;
    }
    
    //Para llenar la tabla disponibilidad
    
    public String GuardarDisponibilidadEnMantenimiento(Mantenimiento man, int turnos){
        Disponibilidad disp = new Disponibilidad();
        disp.setFecha(man.getFecha());
        disp.setHoraInicio(man.getHora());
        Calendar cal = Calendar.getInstance();
        cal.setTime(man.getHora());
        cal.add(Calendar.HOUR_OF_DAY, 8*turnos);
        disp.setHoraFin(cal.getTime());
        disp.setCamion(man.getCamion());
        
        String mensaje = "Se guardaron los cambios exitosamente";
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null; 
        
        try {
            tx = EasyGas.sesion.beginTransaction();   
            EasyGas.sesion.saveOrUpdate(disp);
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            if (tx != null) {
                tx.rollback();
            }
        } finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        return mensaje;
    }
}
