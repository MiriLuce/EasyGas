/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Accidente;
import Modelo.Hibernate.Nodo;
import Util.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Luis
 */
public class AccidenteControlador {
    public List<Accidente> ListarAccidente(){
        List<Accidente> lista = null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        try{
           tx = EasyGas.sesion.beginTransaction();
            lista = EasyGas.sesion.createCriteria(Accidente.class).list();
            for(Accidente acc : lista){
                Hibernate.initialize(acc.getNodo());
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
    
    public Accidente BuscarAccidentePorId(int id){
        Accidente accidente = null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            accidente = (Accidente) EasyGas.sesion.get(Accidente.class, id);
            Hibernate.initialize(accidente.getNodo());
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
        return accidente;
    }
    
    public String GuardarAccidente (Accidente acc){
        String mensaje = "Se guardaron los cambios exitosamente";
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;          

        try{
            
            tx = EasyGas.sesion.beginTransaction();         
  
            EasyGas.sesion.saveOrUpdate(acc);
            tx.commit();
        }
        catch(Exception e){
            mensaje = "Hubo un error en la conexion" + e.toString();
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
    
    public String EliminarAccidente(int id){
        String mensaje = "Se eliminado el registro exitosamente";
        SessionFactory sesFact = null;
        Session sesion = null;          
        
        try{
            sesFact = HibernateUtil.getSessionFactory();
            sesion = sesFact.openSession();           
            Accidente accidente = (Accidente) sesion.get(Accidente.class, id);
            sesion.delete(accidente);
            sesion.beginTransaction().commit();
        }
        catch(Exception e){
            mensaje = "Hubo un error en la coneccion";
        } 
        finally{
            sesion.close();
            sesFact.close();
        }
        return mensaje;
    }
}
