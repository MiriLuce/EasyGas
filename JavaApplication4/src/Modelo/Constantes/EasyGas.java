/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Constantes;

import Modelo.Hibernate.Usuario;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/* 
* Ultima modificacion: 12/09/15
* Descripcion del Cambio: Creación de la clase
* Autor: Chuky
*/
public class EasyGas {
    //constantes de correo electronico
    public static String host = "smtp.gmail.com";
    public static String correoEasyGas = "easygas2015.2@gmail.com";
    public static String contrasenhaEasyGas = "Equipo01EasyGas";
    public static String asuntoRecuperarContrasenha = "EasyGas - Recuperación de contraseña";
    public static String asuntoNuevoUsuario = "EasyGas - Bienvenida";
    public static String errorEnviarCorreo = "Ocurrió un error tratando de enviar el correo!\n\n"
                                            +"*Verifique su conexión a internet\n\n"
                                            +"**Si el problema persiste envíenos un correo indicando su nombre de usuario";
    
    //sesion factory para las transacciones de la bd
    public final static SessionFactory sesFact = HibernateUtil.getSessionFactory();
    public static Session sesion;
    public static Usuario usuarioActual;
}
