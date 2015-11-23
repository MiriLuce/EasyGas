    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;
import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.*;
import Util.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.crypto.Cipher; 
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Mariella
 * fecha 22/10/2015
 * creacion del usuario
 * observaciones: falta en la tabla el campo correo
 */
public class UsuarioControlador {
    
    
    
    public String encriptar(String contrasenha){
        String llaveSecreta = "easyGas"; //llave para encriptar datos
        String base64EncriptadaString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestDeContrasenha = md.digest(llaveSecreta.getBytes("utf-8"));
            byte[] llaveBytes = Arrays.copyOf(digestDeContrasenha, 24);
 
            SecretKey key = new SecretKeySpec(llaveBytes, "DESede");
            Cipher cifrar = Cipher.getInstance("DESede");
            cifrar.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = contrasenha.getBytes("utf-8");
            byte[] buf = cifrar.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncriptadaString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncriptadaString;
    }
    
    public String descriptar(String contrasenha){
        String llaveSecreta = "easyGas"; //llave para desenciptar datos
        String base64EncriptadaString = "";
 
        try {
            byte[] mensaje = Base64.decodeBase64(contrasenha.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestDeContrasenha = md.digest(llaveSecreta.getBytes("utf-8"));
            byte[] llaveBytes = Arrays.copyOf(digestDeContrasenha, 24);
            SecretKey key = new SecretKeySpec(llaveBytes, "DESede");
 
            Cipher decifrar = Cipher.getInstance("DESede");
            decifrar.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decifrar.doFinal(mensaje);
 
            base64EncriptadaString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64EncriptadaString;
    }
    
    public Usuario obtenerUsuario(String contrasenha, String nombreUsuario){
       
        Usuario usuario=null;
        String contrasenhaEncriptada=encriptar(contrasenha);
        // es la primera vez que se abre la sesion
        EasyGas.sesion = EasyGas.sesFact.openSession();

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Usuario where Contrasenha = :contrasenha AND NombreUsuario = :nombreUsuario");
            query.setParameter("contrasenha", contrasenhaEncriptada);
            query.setParameter("nombreUsuario",nombreUsuario);
            usuario = (Usuario) query.list().get(0);     
            Hibernate.initialize(usuario.getPerfil());
            Hibernate.initialize(usuario.getEmpleado());
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
        // si es nulo es que las contraseña fueran invalidas
        return usuario; 
    }
    
    public Usuario obtenerUsuarioPorCodigo(int id){
       
        Usuario usuario=null;
        
        // es la primera vez que se abre la sesion
        EasyGas.sesion = EasyGas.sesFact.openSession();

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Usuario where idUsuario = :id");
            query.setParameter("id", id);
            usuario = (Usuario) query.list().get(0);     
            Hibernate.initialize(usuario.getPerfil());
            Hibernate.initialize(usuario.getEmpleado());
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
       
        return usuario; 
    }
    
    public Usuario obtenerUsuarioPorIdEmpleado(int idEmpleado){
       
        Usuario usuario=null;
        
        // es la primera vez que se abre la sesion
        EasyGas.sesion = EasyGas.sesFact.openSession();

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Usuario where idEmpleado = :id");
            query.setParameter("id", idEmpleado);
            usuario = (Usuario) query.list().get(0);     
            Hibernate.initialize(usuario.getPerfil());
            Hibernate.initialize(usuario.getEmpleado());
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
       
        return usuario; 
    }
   
    public boolean existeNombreUsuario(String nombreUsuario,String modo){
       
        Usuario usuario=null;
        
        if( modo.compareTo("recuperacion")== 0) {
             EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        else {
            if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }
        }
        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Usuario where NombreUsuario = :nombreUsuario ");
            query.setParameter("nombreUsuario", nombreUsuario);
           
            usuario = (Usuario) query.list().get(0);    
            Hibernate.initialize(usuario.getEmpleado());
            Hibernate.initialize(usuario.getPerfil());
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
        
        return !(usuario==null);
    }
    
    public boolean existeCorreo(String correo){
       
        Usuario usuario=null;
        
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        try{
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Usuario where Correo = :correo ");
            query.setParameter("correo", correo);
           
            usuario = (Usuario) query.list().get(0);     
            Hibernate.initialize(usuario.getEmpleado());
            Hibernate.initialize(usuario.getPerfil());
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
        
        return !(usuario==null);
    }
    public void recuperarContrasenha(String nombreUsuario){
       
        Usuario usuario=null;
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        try{
           
            tx = EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Usuario where nombreUsuario = :nombreUsuario");
            query.setParameter("nombreUsuario",nombreUsuario);
            usuario = (Usuario) query.list().get(0); 
            Hibernate.initialize(usuario.getEmpleado());
            Hibernate.initialize(usuario.getPerfil());
            String contrasenhaDescriptada=this.descriptar(usuario.getContrasenha());
            
            String []para = {usuario.getCorreo()};
                String mensaje = "Buenas tardes "+  usuario.getEmpleado().getNombres()+ ",\n\n"
                               + "Su contraseña es: "+contrasenhaDescriptada+" \n\n"
                               + "*Puede cambiar su contraseña desde la pantalla principal, haciendo click en 'Archivo' y "
                               + "luego en 'Modificar Contraseña'.\n\nEASYGAS 2015";
                
            if(Control_Correo.enviarCorreo(EasyGas.correoEasyGas, EasyGas.contrasenhaEasyGas, EasyGas.asuntoRecuperarContrasenha, mensaje, para)){
                    JOptionPane.showMessageDialog(null, "Se enviaron sus datos a su correo");
                   
            }
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
      
    }
    public String guardarUsuario(String nombreUsuario,String correo, Empleado empleado,String codigo){
    
        String mensaje = "Se guardaron los cambios exitosamente";
        
        if (!(codigo.compareTo("")==0)) 
        {
            this.actualizarUsuario(codigo, nombreUsuario, correo);
            return mensaje;
        }
        String contrasenhaEncriptada=encriptar(empleado.getDni()); 
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                Usuario u = new Usuario();
                u.setEmpleado(empleado);
                u.setNombreUsuario(nombreUsuario);
                u.setContrasenha(contrasenhaEncriptada);
                u.setFechaRegistro(new Date());
                u.setEstado("Registrado");
                u.setCorreo(correo);
                Perfil p = new Perfil();
                p.setIdPerfil(1);
                p.setNombre("Default");
                p.setDescripcion("Default");
                u.setPerfil(p);
                EasyGas.sesion.saveOrUpdate(u);
                String []para = {u.getCorreo()};
                String mensajeCorreo = "Buenas tardes "+  empleado.getNombres()+ ",\n\n"
                               + "Su contraseña es: "+empleado.getDni()+" \n\n"
                               + "*Puede cambiar su contraseña desde la pantalla principal, haciendo click en 'Archivo' y "
                               + "luego en 'Modificar Contraseña'.\n\nEASYGAS 2015";
                
                if(Control_Correo.enviarCorreo(EasyGas.correoEasyGas, EasyGas.contrasenhaEasyGas, EasyGas.asuntoNuevoUsuario, mensajeCorreo, para)){
                        

                }
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
    public List<Usuario> listarUsuarios(){
        List<Usuario> lista=null;
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;
        
        try {
            tx=EasyGas.sesion.beginTransaction();
            Query query = EasyGas.sesion.createQuery("from Usuario");
            //falta jalar informacion de empleado          
            lista=query.list();
            for(Usuario u : lista){
                Hibernate.initialize(u.getEmpleado());
                Hibernate.initialize(u.getPerfil());
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
    public  List<Usuario> buscaUsuarioFiltro(String dni, String nombre,String puesto,String nombreUsuario) {
       
        List aux=null;
        

        String sql = "SELECT USUARIO.* FROM USUARIO INNER JOIN EMPLEADO  on EMPLEADO.idEmpleado=USUARIO.idEmpleado WHERE 1";
        if ((dni.compareTo("")==0) && (nombre.compareTo("")==0) && (puesto.compareTo("Todos")==0) && (nombreUsuario.compareTo("")==0) ) {
            aux=listarUsuarios();
            return aux;
           
        }
        else{ 
            if (!(dni.compareTo("")==0) ) {
                sql = sql + " and  EMPLEADO.DNI = :dni";
               

            }
             if(!(nombre.compareTo("")==0)) {
                    sql = sql + " and  EMPLEADO.Nombres = :nombre";
             }
             
             if(!(puesto.compareTo("Todos")==0)){
                    sql = sql + " and  EMPLEADO.Puesto = :puesto";
             }
             
             if(!(nombreUsuario.compareTo("")==0)){
                    sql = sql + " and  USUARIO.NombreUsuario = :nombreUsuario";
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
            query.addEntity(Usuario.class);
           

            if (!(dni.compareTo("")==0)) {
                query.setParameter("dni",dni);
           
            }
            if (!(nombre.compareTo("")==0)) {
                 query.setParameter("nombre",nombre);
            }
            
            if(!(puesto.compareTo("Todos")==0)){
                    query.setParameter("puesto",puesto);
             }
             
             if(!(nombreUsuario.compareTo("")==0)){
                     query.setParameter("nombreUsuario",nombreUsuario);
             }
            aux = query.list();
            
            for(Usuario u : (ArrayList<Usuario>)aux){
                Hibernate.initialize(u.getEmpleado());
                Hibernate.initialize(u.getPerfil());
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
    public void actualizaTablaUsuarios(List<Usuario> lista, JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            Object[] fila = new Object[6];
            fila[0] = lista.get(i).getIdUsuario();
            fila[1] = lista.get(i).getEmpleado().getNombres()+" "+lista.get(i).getEmpleado().getApellidoPat();
            fila[2] = lista.get(i).getEmpleado().getDni();
            fila[3] = lista.get(i).getNombreUsuario();
            fila[4] = lista.get(i).getEstado();
            modelo.addRow(fila);
        }
    }
    public String actualizarUsuario(String idUsuario,String nombreUsuario,String correo){
       // valido para cambiar contrasenha
        int codigo=Integer.parseInt(idUsuario);
        String mensaje = "Se guardaron los cambios exitosamente";
       // String contrasenhaEncriptada=encriptar(contrasenha); 
             if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }

            Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                Usuario usuario = (Usuario) EasyGas.sesion.get(Usuario.class, codigo);
                Hibernate.initialize(usuario.getPerfil());
                Hibernate.initialize(usuario.getEmpleado());
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setCorreo(correo);
                EasyGas.sesion.saveOrUpdate(usuario);
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
    
    public String actualizarContrasenha(String idUsuario,String contrasenha){
       // valido para cambiar contrasenha
        int codigo=Integer.parseInt(idUsuario);
        String mensaje = "Se guardaron los cambios exitosamente";
       // String contrasenhaEncriptada=encriptar(contrasenha); 
             if (!EasyGas.sesion.isOpen()) {
                EasyGas.sesion = EasyGas.sesFact.openSession();
            }

            Transaction tx = null;
            try{
                tx = EasyGas.sesion.beginTransaction();
                Usuario usuario = (Usuario) EasyGas.sesion.get(Usuario.class, codigo);
                Hibernate.initialize(usuario.getPerfil());
                Hibernate.initialize(usuario.getEmpleado());
                usuario.setContrasenha(contrasenha);
                EasyGas.sesion.saveOrUpdate(usuario);
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
    
    public String EliminarUsuario(int codigo){
        
        String mensaje = "Se ha eliminado el registro exitosamente";
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;   
        
        try{
            tx = EasyGas.sesion.beginTransaction();         
            Usuario usuario = (Usuario) EasyGas.sesion.get(Usuario.class, codigo);
            EasyGas.sesion.delete(usuario);
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
}
