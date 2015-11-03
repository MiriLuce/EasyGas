/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Accidente;
import Modelo.Hibernate.Disponibilidad;
import Modelo.Hibernate.Nodo;
import Modelo.Hibernate.Ruta;
import Util.HibernateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Luis
 */
public class AccidenteControlador {
    
    //Listar todos los accidentes
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
                Hibernate.initialize(acc.getRuta());
                Hibernate.initialize(acc.getRuta().getCamion());
                Hibernate.initialize(acc.getRuta().getEmpleadoByIdConductor());
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
    
    public List<Accidente> BuscarAccidente(String placa, String conductor){
        List<Accidente> lista = null;
        
        String consulta = "SELECT ACCIDENTE.* FROM ACCIDENTE " +
"INNER JOIN RUTA  on ACCIDENTE.idRuta=RUTA.idRuta " +
"INNER JOIN CAMION  on CAMION.idCamion=RUTA.idCamion" +
"INNER JOIN EMPLEADO  on EMPLEADO.idEmpleado=RUTA.idConductor" +
"WHERE 1";
        if((placa.compareTo("   -   ")==0) && (conductor.compareTo("")==0)){
            lista = this.ListarAccidente();
            return lista;
        }else{
            if(!(placa.compareTo("   -   ")==0)){
                consulta += " and CAMION.Placa = :placa";
            }
            if(!(conductor.compareTo("")==0)){
                consulta += " and Empleado.Nombres= :conductor";
            }
        }
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        
        try {
            tx = EasyGas.sesion.beginTransaction(); 
            
            SQLQuery query = EasyGas.sesion.createSQLQuery(consulta);
            query.addEntity(Accidente.class);
            if(!(placa.compareTo("   -   ")==0)){
                query.setParameter("placa", placa);
            }
            if(!(conductor.compareTo("")==0)){
                query.setParameter("conductor", conductor);
            }
            lista = query.list();
            for(Accidente acc : lista){
                Hibernate.initialize(acc.getNodo());                
                Hibernate.initialize(acc.getRuta());
                Hibernate.initialize(acc.getRuta().getCamion());
                Hibernate.initialize(acc.getRuta().getEmpleadoByIdConductor());
            }
            tx.commit();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay un accidente con estos datos");
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
    
    //Busca accidente por id
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
            Hibernate.initialize(accidente.getRuta());
            Hibernate.initialize(accidente.getRuta().getCamion());
            Hibernate.initialize(accidente.getRuta().getEmpleadoByIdConductor());
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
    
    //Guarda un accidente
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
    
    //Elimina un accidente
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
    
    //Busca ruta por placa
    //Estara temporalmente aqui mientras RutaControlador no se cree
    
   public Ruta BuscarRutaPorPlaca(String placa){
       Ruta ruta = null;
       
       String consulta ="select RUTA.* from RUTA inner join CAMION on RUTA.idCamion where";
       consulta += "CAMION.placa = :placa";
       
       if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        
        try {
           tx = EasyGas.sesion.beginTransaction(); 
            
            SQLQuery query = EasyGas.sesion.createSQLQuery(consulta);
            query.addEntity(Ruta.class);
            query.setParameter("placa", placa);
            ruta = (Ruta) query.list().get(0);
            Hibernate.initialize(ruta.getCamion());
            Hibernate.initialize(ruta.getEmpleadoByIdConductor());
            Hibernate.initialize(ruta.getEmpleadoByIdCopiloto());
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
       return ruta;
   }
   
   //Guardar la disponibilidad
   
   public String GuardarDisponibilidadEnMantenimiento(Accidente acc){
        Disponibilidad disp = new Disponibilidad();
        disp.setFecha(acc.getFecha());
        disp.setHoraInicio(acc.getHora());
        Calendar cal = Calendar.getInstance();
        cal.setTime(acc.getHora());
        cal.add(Calendar.HOUR_OF_DAY, 8);
        disp.setHoraFin(cal.getTime());
        disp.setCamion(acc.getRuta().getCamion());
        
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
