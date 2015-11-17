   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.*;
import Controlador.NodoControlador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.hibernate.*;

/**
 *
 * @author Eduardo
 */
public class ClienteControlador {

    public static ArrayList<Cliente> ListarClientes() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            String sql = "SELECT * FROM CLIENTE";
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Cliente.class);
            List aux = query.list();

            tx.commit();

            for (Iterator i = aux.iterator(); i.hasNext();) {
                Cliente c = (Cliente) i.next();
               // Integer ix = c.getNodo().getIdNodo();
               // Nodo n = NodoControlador.BuscaNodoId(ix);
                Hibernate.initialize(c.getNodo());
                lista.add(c);
            }

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

        return lista;
    }

    public static Cliente BuscaClienteId(Integer id) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            String sql = "SELECT * FROM CLIENTE WHERE idCliente = :idcliente";
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Cliente.class);
            query.setParameter("idcliente", id);
            List aux = query.list();

            tx.commit();

            for (Iterator i = aux.iterator(); i.hasNext();) {
                Cliente c = (Cliente) i.next();
                /*Integer ix = c.getNodo().getIdNodo();
                Nodo n = NodoControlador.BuscaNodoId(ix);*/
                Hibernate.initialize(c.getNodo());
                lista.add(c);
            }

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

        return lista.get(0);
    }
    
    public static ArrayList<Cliente> BuscaClienteNroDoc(String nrodoc) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            String sql = "SELECT * FROM CLIENTE WHERE NroDocumento = :nrodoc";
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Cliente.class);
            query.setParameter("nrodoc", nrodoc);
            List aux = query.list();

            tx.commit();

            for (Iterator i = aux.iterator(); i.hasNext();) {
                Cliente c = (Cliente) i.next();
                /*Integer ix = c.getNodo().getIdNodo();
                Nodo n = NodoControlador.BuscaNodoId(ix);*/
                Hibernate.initialize(c.getNodo());
                lista.add(c);
            }

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

        return lista;
    }

    public static Cliente BuscaClienteDireccion(ArrayList<Cliente> lista, ArrayList<Integer> aux){
        if (lista.size() == 1) {
            return lista.get(0);
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getNodo().getCoordX() == aux.get(0) && lista.get(i).getNodo().getCoordY() == aux.get(1)) {
                    return lista.get(i);
                }
            }
        }
        return null;
    }
    
    public static ArrayList<Nodo> ListarDireccionesCliente(String nrodoc) {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();

        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            String sql = "SELECT * FROM CLIENTE WHERE NroDocumento = :nrodoc";
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Cliente.class);
            query.setParameter("nrodoc", nrodoc);
            List aux = query.list();

            tx.commit();

            for (Iterator i = aux.iterator(); i.hasNext();) {
                Cliente c = (Cliente) i.next();
                Integer ix = c.getNodo().getIdNodo();
                Nodo n = NodoControlador.BuscaNodoId(ix);
                lista.add(n);
            }

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

        return lista;
    }
    
    public String GuardarCliente(Cliente cli){
    
        String mensaje = "Se guardaron los cambios exitosamente";
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;          

        try{            
            tx = EasyGas.sesion.beginTransaction();   
            EasyGas.sesion.saveOrUpdate(cli);
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
    
    public String EliminarCliente(int codigo){
        
        String mensaje = "Se ha eliminado el registro exitosamente";
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;   
        
        try{
            tx = EasyGas.sesion.beginTransaction();         
            Cliente cliente = (Cliente) EasyGas.sesion.get(Cliente.class, codigo);
            EasyGas.sesion.delete(cliente);
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
    
    public List<Cliente> BuscarCliente(Date dtFechaDesde,Date dtFechaHasta,String nroDoc,String nombre){
        List<Cliente> lista = null;
        
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
        String fechaDesde=dtFechaDesde==null?"":myFormat.format(dtFechaDesde);
        String fechaHasta=dtFechaHasta==null?"":myFormat.format(dtFechaHasta);
        
        String consulta = "select CLIENTE.* FROM CLIENTE where 1";
                
        if((fechaDesde.compareTo("")==0) && (fechaHasta.compareTo("")==0) && (nroDoc.compareTo("")==0) && (nombre.compareTo("")==0)){
            lista = this.ListarClientes();
            return lista;
        }else{
            if (!(fechaDesde.compareTo("")==0) ) {                
                consulta += " and  CLIENTE.FechaRegistro >= :fechaDesde";
            }
            if(!(fechaHasta.compareTo("")==0)) {
                consulta += " and  CLIENTE.FechaRegistro <= :fechaHasta";
            }          
            if(!(nroDoc.compareTo("")==0)){
                consulta += " and CLIENTE.NroDocumento = :nroDoc";
            }
            if(!(nombre.compareTo("")==0)){
                consulta += " and EMPLEADO.Nombres like %:nombre%";
            }
        }
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        
        try {
            tx = EasyGas.sesion.beginTransaction(); 
            
            SQLQuery query = EasyGas.sesion.createSQLQuery(consulta);
            query.addEntity(Cliente.class);
            if (!(fechaDesde.compareTo("")==0) ) {                
                query.setParameter("fechaDesde",fechaDesde);
            }
            if(!(fechaHasta.compareTo("")==0)) {                
                query.setParameter("fechaHasta",fechaHasta);           
            }          
            if(!(nroDoc.compareTo("")==0)){
                query.setParameter("nroDoc",nroDoc);
            }
            if(!(nombre.compareTo("")==0)){
                query.setParameter("nombre",nombre);
            }
            lista = query.list();
            for(Cliente cli: lista){
                Hibernate.initialize(cli.getNodo());
            }
            tx.commit();            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay un cliente con estos datos");
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
    
    private int ValidarNumeroDoc(String nroDoc, String tipoDoc){
        int verifica = 1;
        int longitud = nroDoc.length();
        if(tipoDoc.compareTo("DNI")==0 && longitud!=8){
            verifica=0;            
        }else if(tipoDoc.compareTo("RUC")==0 && longitud!=11){
            verifica=0;
        }else{
            verifica = 1;
        }
        return verifica;
    }
    
    private int ValidarNodo(int posX, int posY){
        int verifica =1;
        if(posX>300 && posY>200){
            verifica = 0;
        }
        return verifica;
    }
    
    public void CargaMasiva(String nombreArchivo, int[] datos, List<Cliente> listaCliente) throws FileNotFoundException{       
       
        int verifica = 0;
        datos[0]= 0 ;
        datos[1] = 0;
        String cadena = null;
        
        FileReader f = new FileReader(nombreArchivo);
        BufferedReader b = new BufferedReader(f);
        try {            
            while((cadena = b.readLine())!=null) {
                 
                verifica = 0;
                String[] cadenaArr = cadena.split(" ");
                ArrayList<Cliente> arrCli = BuscaClienteNroDoc(cadenaArr[0]);                
                Cliente c = new Cliente();
                
                if (arrCli.size()==0){
                    int verificaPlaca = ValidarNumeroDoc(cadenaArr[0],cadenaArr[1]);
                    if (verificaPlaca==1){
                        int posX = Integer.parseInt(cadenaArr[2]);
                        int posY = Integer.parseInt(cadenaArr[3]);
                        int verificaNodo = ValidarNodo(posX,posY);
                        if (verificaNodo==1){
                            Nodo nod = new Nodo();
                            nod.setCoordX(posX);
                            nod.setCoordY(posY);
                            nod.setHabilitado("SI");
                            
                            NodoControlador nodControlador = new NodoControlador();
                            nodControlador.GuardarNodo(nod);
                            
                            c.setNodo(nod);
                            c.setFechaRegistro(new Date());
                            c.setNroDocumento(cadenaArr[0]);
                            c.setTipoDocumento(cadenaArr[1]);
                            String nombre = "";
                            for(int i = 4; i<cadenaArr.length;i++){
                                if(i!=4){
                                    nombre += " ";
                                }
                                nombre += cadenaArr[i];
                            }
                            c.setNombres(nombre);
                            c.setEstado("Registrado");
                            GuardarCliente(c);
                            verifica = 1;
                        }
                    }  
                }
                if (verifica == 1){ 
                    datos[1]++;
                    listaCliente.add(c);
                }
                else datos[0]++;
            }
            b.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            Logger.getLogger(CamionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
}
