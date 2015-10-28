/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Empleado;
import Modelo.Hibernate.Turno;
import Modelo.Hibernate.Usuario;
import Util.HibernateUtil;
import java.awt.Cursor;
import java.text.DateFormat;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author Luis
 */
public class EmpleadoControlador {

    
    public Empleado BuscarAccidentePorId(int id){
        Empleado empleado = null;
        SessionFactory sesFact = null;
        Session sesion = null;
        try{
            sesFact = HibernateUtil.getSessionFactory();
            sesion = sesFact.openSession();
            empleado = (Empleado) sesion.get(Empleado.class, id);
        }
        catch(Exception e){
        }                
        finally{
                sesion.close();
                sesFact.close();
            }
        return empleado;
    }
    
    public boolean existeDNI(String dni){
       
        Empleado empleado=null;
        
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Empleado where DNI = :dni ");
            query.setParameter("dni", dni);
           
            empleado = (Empleado) query.list().get(0);     
            tx.commit();
        }
        catch(Exception e){
           
            if (tx != null) {
                tx.rollback();
            }
        }           
        finally{
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
           
        }
        
        return !(empleado==null);
    }
    //
    //empleadoControlador.guardarEmpleado(txtNombre.getText(),txtApellidoPat.getText(),txtApellidoMat.getText()
    //    ,txtDNI.getText(),cmbPuesto.getSelectedItem().toString());
    //
    public void actualizaTablaEmpleados(List<Empleado> lista, JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            Object[] fila = new Object[6];
            fila[0] = lista.get(i).getIdEmpleado();
            fila[1] = lista.get(i).getDni();
            fila[2] = lista.get(i).getNombres()+" "+lista.get(i).getApellidoPat();
            fila[3] = new SimpleDateFormat("HH:mm:ss").format(lista.get(i).getTurno().getHoraInicio()) + " - " + new SimpleDateFormat("HH:mm:ss").format(lista.get(i).getTurno().getHoraFin());
            fila[4] = lista.get(i).getPuesto();
            modelo.addRow(fila);
        }
    }
    public  List<Empleado> buscaEmpleadoFiltro(String dni, String nombre,int idTurno,String puesto) {
       
        List aux=null;
        

        String sql = "SELECT * FROM EMPLEADO WHERE 1";
        if ((dni.compareTo("")==0) && (nombre.compareTo("")==0) && idTurno==0 && (puesto.compareTo("Todos")==0 )) {
            aux=this.listarEmpleado();
            return aux;
           
        }
        else{ 
            if (!(dni.compareTo("")==0) ) {
                sql = sql + " and  DNI = :dni";
               

            }
            if(!(nombre.compareTo("")==0)) {
                    sql = sql + " and  Nombres = :nombre";
            }
            
            if(!(puesto.compareTo("Todos")==0)) {
                    sql = sql + " and  Puesto = :puesto";
            }
            if(!(idTurno==0)) {
                    sql = sql + " and  idTurno = :idTurno";
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
            query.addEntity(Empleado.class);
           

            if (!(dni.compareTo("")==0)) {
                query.setParameter("dni",dni);
           
            }
            if (!(nombre.compareTo("")==0)) {
                 query.setParameter("nombre",nombre);
            }
            
             if(!(puesto.compareTo("Todos")==0)) {
                 query.setParameter("puesto",puesto);
            }
            if(!(idTurno==0)) {
                 query.setParameter("idTurno",idTurno);
            }
            
            aux = query.list();
            
            for(Empleado e : (ArrayList<Empleado>)aux){
                Hibernate.initialize(e.getTurno());
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
    public List<Empleado> listarEmpleado(){
        List<Empleado> lista=null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        
        try {
            tx=EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Empleado");
            //falta jalar informacion de empleado          
            lista=query.list();
            for(Empleado e : lista){
                Hibernate.initialize(e.getTurno());
               
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
    public String guardarEmpleado(String nombre,String apellidoPat,
            String apellidoMat,String DNI,String puesto, int idTurno,String codigo){
        
        
        String mensaje = "Se guardaron los cambios exitosamente";
        if (!(codigo.compareTo("")==0)){
            actualizarEmpleado( nombre, apellidoPat,
             apellidoMat, DNI, puesto,  idTurno, codigo);
            return mensaje;
        }
        Turno t= obtenerTurnoPorId(idTurno);       
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                Empleado e = new Empleado();
                e.setNombres(nombre);
                e.setApellidoMat(apellidoMat);
                e.setApellidoPat(apellidoPat);
                e.setDni(DNI);
                e.setPuesto(puesto);
                e.setTurno(t);
                e.setEstado("En planilla");
                e.setFechaRegistro(new Date());
                EasyGas.sesion.saveOrUpdate(e);
                
                
                tx.commit();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Hubo un error en la conexion");
                if (tx != null) {
                    tx.rollback();
                }
            }       
            finally{
                if (EasyGas.sesion.isOpen()) {
                    EasyGas.sesion.close();
                }
               
            }
              
                
        return mensaje;
    }
    
     public String actualizarEmpleado(  String nombre,String apellidoPat,
            String apellidoMat,String DNI,String puesto, int idTurno,String idEmpleado
     ){
       // valido para cambiar contrasenha
        int codigo=Integer.parseInt(idEmpleado);
        Turno t= obtenerTurnoPorId(idTurno);     
        String mensaje = "Se guardaron los cambios exitosamente";
       // String contrasenhaEncriptada=encriptar(contrasenha); 
             if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }

            Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                Empleado empleado = (Empleado) EasyGas.sesion.get(Empleado.class, codigo);
                Hibernate.initialize(empleado.getTurno());
                
                empleado.setNombres(nombre);
                empleado.setApellidoMat(apellidoMat);
                empleado.setApellidoPat(apellidoPat);
                empleado.setDni(DNI);
                empleado.setPuesto(puesto);
                empleado.setTurno(t);
                EasyGas.sesion.saveOrUpdate(empleado);
                //verificar que el username no exista en la bd
                
                
                tx.commit();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Hubo un error en la conexion");
                if (tx != null) {
                    tx.rollback();
                }
            }       
            finally{
                if (EasyGas.sesion.isOpen()) {
                    EasyGas.sesion.close();
                }
                
            }
              
                
        return mensaje;
    }
    public Empleado obtenerEmpleadoPorCodigo(int id){
       
        Empleado empleado=null;
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Empleado where idEmpleado = :id");
            query.setParameter("id", id);
            empleado = (Empleado) query.list().get(0);     
            Hibernate.initialize(empleado.getTurno());
            tx.commit();
        }
        catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }           
        finally{
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
           
        }
       
        return empleado; 
    }
    
     public Turno obtenerTurnoPorId(int id){
       
        Turno turno=null;
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Turno where idTurno = :id");
            query.setParameter("id", id);
            turno = (Turno) query.list().get(0);     
            tx.commit();
        }
        catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }           
        finally{
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
           
        }
       
        return turno; 
    }
    
    public Empleado obtenerEmpleadoPorDNI(String DNI){
       
        Empleado empleado=null;
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Empleado where DNI = :dni");
            query.setParameter("dni", DNI);
            empleado = (Empleado) query.list().get(0);     
            Hibernate.initialize(empleado.getTurno());
            tx.commit();
        }
        catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }           
        finally{
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
           
        }
       
        return empleado; 
    }
    public String ActualizarEmpleado(int id, String estado){
    
        SessionFactory sesFact = null;
        Session sesion = null;          
        
            try{
                sesFact = HibernateUtil.getSessionFactory();
                sesion = sesFact.openSession();
                Transaction tx = sesion.beginTransaction();

                Empleado empleado = (Empleado) sesion.get(Empleado.class, id);
                empleado.setEstado(estado);
                sesion.saveOrUpdate(empleado);
                tx.commit();
            }
            catch(Exception e){
                
            }        
            finally{
                sesion.close();
                sesFact.close();
            }
                
        return null;
    }
    
   public String EliminarEmpleado(int codigo){
       String mensaje = "Se ha eliminado el registro exitosamente";
       if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null; 
        try {
           tx = EasyGas.sesion.beginTransaction(); 
           Empleado empleado = (Empleado)EasyGas.sesion.get(Empleado.class, codigo);
           EasyGas.sesion.delete(empleado);
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
       return mensaje;
   }
}
