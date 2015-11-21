/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Camion;
import Modelo.Hibernate.TipoCamion;
import Modelo.Hibernate.Usuario;
import Util.HibernateUtil;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author MiriamLucero
 */

public class CamionControlador{
    
    public static List<Camion> ListarCamion(){
       
        List<Camion> lista = null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        
        try{
            tx = EasyGas.sesion.beginTransaction();
            lista = EasyGas.sesion.createCriteria(Camion.class).list();
            for(Camion cam : lista){
                Hibernate.initialize(cam.getTipoCamion());                
            }
            tx.commit();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error de conexión");
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
    
    public List<Camion> BuscarCamion(String placa, String estado, int idTipoCamion, Date desde, Date hasta){
       
        if (placa.compareTo("")==0 && estado.compareTo("")==0 && idTipoCamion==0 && (desde== null || hasta == null)) {
            return ListarCamion();           
        }
        
        int verifica = 1;
        List<Camion> listaCamion = null;
        String consulta = "SELECT CAMION.* FROM CAMION WHERE 1";
        
        if (placa.compareTo("") != 0){
            verifica = ValidarPlaca(placa);
            consulta = consulta + " AND CAMION.Placa = :placa";
        }
        if (estado.compareTo("") != 0)
            consulta = consulta + " AND CAMION.Estado = :estado";
        if (idTipoCamion!= 0)
            consulta = consulta + " AND CAMION.idTipoCamion = :idTipoCamion";
        if (desde!=null || hasta!=null)
            consulta = consulta + " AND CAMION.FechaRegistro BETWEEN :desde AND :hasta";
        
        if(verifica == 1){
            if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }
            Transaction tx = null;
            Camion camion = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                SQLQuery query = EasyGas.sesion.createSQLQuery(consulta);
                query.addEntity(Camion.class);
                if (placa.compareTo("") != 0)
                    query.setParameter("placa", placa);
                if (estado.compareTo("") != 0)
                    query.setParameter("estado", estado);
                if (idTipoCamion!= 0)
                    query.setParameter("idTipoCamion", idTipoCamion);
                if (desde!=null || hasta!=null){
                    query.setParameter("desde", desde);
                    query.setParameter("hasta", hasta);
                }
                listaCamion = query.list();
                for(Camion c : (ArrayList<Camion>)listaCamion){
                    Hibernate.initialize(c.getTipoCamion());
                }
                tx.commit();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Hubo un error de conexión");
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
        return listaCamion;
    }
    
    public Camion BuscarCamionPorCodigo(int codigo){
       
        List<TipoCamion> lista = null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        Camion camion = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            camion = (Camion) EasyGas.sesion.get(Camion.class, codigo);
            Hibernate.initialize(camion.getTipoCamion());
            tx.commit();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error de conexión");
            if (tx != null) {
                tx.rollback();
            }
        }   
        finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        return camion;
    }
    
    public Camion BuscarCamionPorPlaca(String placa){
       
        Camion camion = null;
         if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }        
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Camion where Placa = :placa ");
            query.setParameter("placa", placa);
            List<Camion> lista = query.list();
            if (lista.size()!=0){
                camion = (Camion) query.list().get(0);
                Hibernate.initialize(camion.getTipoCamion());
            }
            tx.commit();
        }
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error de conexión");
            if (tx != null) {
                tx.rollback();
            }
        }                
        finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        return camion;
    }
    
    public Camion BuscarCamionPorPlacaSinMensaje(String placa){
       
        Camion camion = null;
         if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }        
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Camion where Placa = :placa ");
            query.setParameter("placa", placa);
            List<Camion> lista = query.list();
            if (lista.size()!=0){
                camion = (Camion) query.list().get(0);
                Hibernate.initialize(camion.getTipoCamion());
            }
            tx.commit();
        }
         catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }                
        finally{
            if (EasyGas.sesion.isOpen()){
                EasyGas.sesion.close();
            }
        }
        return camion;
    }
    
    private int ValidarPlaca(String placa){
        String mensaje = "";
        String tmp = "";
        int verifica = 1;
        int cant = placa.length();
        for(int i=0; i<cant; i++){
            if(placa.charAt(i)!= ' ') tmp = tmp + placa.charAt(i);
        }
        placa = tmp;
        if (tmp.length() == 7 ){
            for(int i=0; i<cant; i++){
                char caracter = placa.charAt(i);
                if(!Character.isLetter(caracter) && !Character.isDigit(caracter) &&
                        !(caracter=='-' && i== 3)){
                    mensaje = "La placa solo debe contener letras mayúsculas, digitos y un '-'";
                    verifica = 0;
                    break; 
                }
                else if (caracter=='-' && i!= 3){
                    mensaje = "La placa debe tener tener el siguiente formato: XXX-XXX";
                    verifica = 0;
                    break;                    
                }
                else if (Character.isLetter(caracter) && !Character.isUpperCase(caracter)){
                    mensaje = "La placa debe contener todas las letras en mayúsculas";
                    verifica = 0;
                    break;
                }                              
            }            
        }
        else {
            mensaje = "La placa debe tener tener el siguiente formato: XXX-XXX";
            verifica = 0;
        }
        if (verifica == 0) JOptionPane.showMessageDialog(null, mensaje);
        return verifica;
    }
    
    private int ValidarPlacaSinMensaje(String placa){
        String tmp = "";
        int verifica = 1;
        int cant = placa.length();
        for(int i=0; i<cant; i++){
            if(placa.charAt(i)!= ' ') tmp = tmp + placa.charAt(i);
        }
        placa = tmp;
        if (tmp.length() == 7 ){
            for(int i=0; i<cant; i++){
                char caracter = placa.charAt(i);
                if(!Character.isLetter(caracter) && !Character.isDigit(caracter) &&
                        !(caracter=='-' && i== 3)){
                    verifica = 0;
                    break; 
                }
                else if (caracter=='-' && i!= 3){
                    verifica = 0;
                    break;                    
                }
                else if (Character.isLetter(caracter) && !Character.isUpperCase(caracter)){
                    verifica = 0;
                    break;
                }                              
            }            
        }
        else {
            verifica = 0;
        }
        return verifica;
    }
    
    public int GuardarCamion(Camion camion){

        String mensaje = "Se ha guardado los cambios exitosamente";
        int verifica = 0;
        if ( camion.getPlaca().compareTo("")== 0){
            mensaje = "El campo placa esta vacio";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        else if (ValidarPlaca(camion.getPlaca())== 1){
            if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }
            Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                camion.setFechaRegistro(new Date());
                camion.setEstado("Disponible");                
                EasyGas.sesion.saveOrUpdate(camion);
                tx.commit();
                verifica = 1;
            }
             catch(Exception e){
                mensaje = "Hubo un error de conexión";
                if (tx != null) {
                    tx.rollback();
                }
            }                
            finally{
                if (EasyGas.sesion.isOpen()){
                    EasyGas.sesion.close();
                }
            }
            JOptionPane.showMessageDialog(null, mensaje);
        }
        return verifica;
    }
    
    public int GuardarCamionSinMensaje(Camion camion){

        int verifica = 0;
        if (ValidarPlaca(camion.getPlaca())== 1){
            if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }
            Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                camion.setFechaRegistro(new Date());
                camion.setEstado("Disponible");                
                EasyGas.sesion.saveOrUpdate(camion);
                tx.commit();
                verifica = 1;
            }
             catch(Exception e){
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
        return verifica;
    }
    
    public int ActualizarCamion(int codigo, String placa){
    
        String mensaje = "Se ha guardado los cambios exitosamente";
        int verifica = 0;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
            JOptionPane.showMessageDialog(null, mensaje);
        }
        else if (ValidarPlaca(placa)== 1){
            Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                Camion camion = (Camion) EasyGas.sesion.get(Camion.class, codigo);
                camion.setPlaca(placa);
                EasyGas.sesion.saveOrUpdate(camion);
                tx.commit();
                verifica = 1;
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
            JOptionPane.showMessageDialog(null, mensaje);
        }
        return verifica;
    }
    
    public String EliminarCamion(int codigo){
        
        String mensaje = "Se eliminado el registro exitosamente";
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Camion camion = (Camion) EasyGas.sesion.get(Camion.class, codigo);
            EasyGas.sesion.delete(camion);
            tx.commit();
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
    
    public void CargaMasiva(String nombreArchivo, int[] datos, List<Camion> listaCamion) throws FileNotFoundException{       
       
        int verifica = 0;
        datos[0]= 0 ;
        datos[1] = 0;
        String cadena;
        //List<Camion> listaCamion= new ArrayList<Camion>();
        
        FileReader f = new FileReader(nombreArchivo);
        BufferedReader b = new BufferedReader(f);
        try {
            TipoCamionControlador tpC = new TipoCamionControlador();
            while((cadena = b.readLine())!=null) {
                 
                verifica = 0;
                String[] cadenaArr = cadena.split(" ");
                Camion tmp = BuscarCamionPorPlaca(cadenaArr[0]);
                Camion c = new Camion();
                
                if (tmp==null){
                    int verificaPlaca = ValidarPlacaSinMensaje(cadenaArr[0]);
                    if (verificaPlaca==1){
                        TipoCamion tp = tpC.BuscarTipoCamionPorNombre("Tipo " + cadenaArr[1]);               
                        if (tp!= null){
                            c.setPlaca(cadenaArr[0]);
                            c.setTipoCamion(tp);
                            verifica = GuardarCamionSinMensaje(c);
                        }
                    }  
                }
                if (verifica == 1){ 
                    datos[1]++;
                    listaCamion.add(c);
                }
                else datos[0]++;
            }
            b.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            Logger.getLogger(CamionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }        
        //return <cantProcesados, cantInvalidos, listaCamion>;
    }

}