/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Camion;
import Modelo.Hibernate.TipoCamion;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 * Version: 1.1
 * Ultima Modificacion: 20/10/2015
 * Descripcion del Cambio: 
 * Autor: Miriam Encarnacion
 */

public class TipoCamionControlador {
    
    public List<TipoCamion> ListarTipoCamion(){
       
        List<TipoCamion> lista = null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            lista = EasyGas.sesion.createCriteria(TipoCamion.class).list();
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
        
        return lista;
    }
    
     public TipoCamion BuscarTipoCamionPorCodigo(int codigo){
       
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        TipoCamion tipoCamion = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            tipoCamion = (TipoCamion) EasyGas.sesion.get(TipoCamion.class, codigo);
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
        return tipoCamion;
    }
     
    public TipoCamion BuscarTipoCamionPorNombre(String nombre){
       
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        TipoCamion tipoCamion = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from TipoCamion where Nombre = :nombre ");
            query.setParameter("nombre", nombre);
            List<TipoCamion> lista = query.list();
            if (lista.size()!=0)
                tipoCamion = (TipoCamion) query.list().get(0);
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
        return tipoCamion;
    }
    
    public int GuardarTipoCamion(TipoCamion tp){
    
        String mensaje = "Se guardaron los cambios exitosamente";
        int verificar = 0;
        
        if ( tp.getNombre().compareTo("")== 0){
            mensaje = "El nombre esta vacio";
        }
        else if (tp.getCapacidadGlp()== 0){
            mensaje = "La cantidad de GLP debe ser mayor a cero";
        }            
        else if(tp.getCapacidadDiesel() == 0){
            mensaje = "La cantidad de Diesel debe ser mayor a cero";
        }
        else if (tp.getTara()==0){
            mensaje = "La cantidad de GLP debe ser mayor a cero";
        }
        else {              
            if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }
            Transaction tx = null;
            TipoCamion tipoCamion = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                EasyGas.sesion.saveOrUpdate(tp);
                tx.commit();
                verificar = 1;
            }
            catch(Exception e){
                mensaje = "Hubo un error en la conexión";
                //JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
                if (tx != null) {
                    tx.rollback();
                }
            }   
            finally{
                if (EasyGas.sesion.isOpen()){
                    EasyGas.sesion.close();
                }
            }
        }                      
        JOptionPane.showMessageDialog(null, mensaje);
        return verificar;
    }
    
    public int ActualizarTipoCamion(TipoCamion tp){
        // Falta verificar que los camiones tenga capacidad para las rutas
        String mensaje = "Se ha actualizado los cambios exitosamente";
        int verificar = 0;
        
        if ( tp.getNombre().compareTo("")== 0){
            mensaje = "El nombre esta vacio";
        }
        else if (tp.getCapacidadGlp()== 0){
            mensaje = "La cantidad de GLP debe ser mayor a cero";
        }            
        else if(tp.getCapacidadDiesel() == 0){
            mensaje = "La cantidad de Diesel debe ser mayor a cero";
        }
        else if (tp.getTara()==0){
            mensaje = "La cantidad de GLP debe ser mayor a cero";
        }
        else {   
            if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }
            Transaction tx = null;
            TipoCamion tipoCamion = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                EasyGas.sesion.saveOrUpdate(tp);
                tx.commit();
                verificar = 1;
            }
            catch(Exception e){
                mensaje = "Hubo un error en la conexión";
                //JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
                if (tx != null) {
                    tx.rollback();
                }
            }   
            finally{
                if (EasyGas.sesion.isOpen()){
                    EasyGas.sesion.close();
                }
            }
        }                      
        JOptionPane.showMessageDialog(null, mensaje);
        return verificar;
    }
    
    public String EliminarTipoCamion(int codigo){
        
        String mensaje = "Se eliminado el registro exitosamente";
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            TipoCamion tipoCamion = (TipoCamion) EasyGas.sesion.get(TipoCamion.class, codigo);
            Query query = EasyGas.sesion.createQuery("from Camion where idTipoCamion = :tipo ");
            query.setParameter("tipo", tipoCamion.getIdTipoCamion());
            List<Camion> lista = query.list();
            if (lista.size()==0){
                EasyGas.sesion.delete(tipoCamion);
                tx.commit();
            }
            else{
                mensaje = "No se puede eliminar este registro, ya que existen camiones asignados";  
            }
        }
        catch(Exception e){
            mensaje = "Hubo un error en la conexión";
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
}