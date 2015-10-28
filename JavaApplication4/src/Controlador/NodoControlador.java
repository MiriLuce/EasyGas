/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.Nodo;
import java.util.*;
import javax.swing.JOptionPane;
import org.hibernate.*;

/**
 *
 * @author Eduardo
 */
public class NodoControlador {

    public static Nodo BuscaNodoId(Integer id) {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();

        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            String sql = "SELECT * FROM NODO WHERE idNodo = :idnodo";
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Nodo.class);
            query.setParameter("idnodo", id);
            List aux = query.list();

            tx.commit();
            
            for (Iterator i = aux.iterator(); i.hasNext();) {
                Nodo n = (Nodo) i.next();
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

        return lista.get(0);
    }
    
    public String GuardarNodo(Nodo nod){
        String mensaje = "Se guardaron los cambios exitosamente";
        
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }
        
        Transaction tx = null;
        
        try {
            tx = EasyGas.sesion.beginTransaction();   
            EasyGas.sesion.saveOrUpdate(nod);
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
