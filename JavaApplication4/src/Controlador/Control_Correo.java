/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

/* 
* Ultima modificacion: 12/09/15
* Descripcion del Cambio: Creación de la clase
* Autor: Chuky
*/
public class Control_Correo {
    public static boolean enviarCorreo(String de, String contrasenha, String asunto,String mensaje, String para[]){
        String host = EasyGas.host;
        Properties propiedades = System.getProperties();
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", host);
        propiedades.put("mail.smtp.user", de);
        propiedades.put("mail.smtp.password", contrasenha);
        propiedades.put("mail.smtp.port", 587);
        propiedades.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(propiedades, null);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(de));
            InternetAddress[] toAddress = new InternetAddress[para.length];
            for(int i=0;i<para.length;i++){
                toAddress[i]=new InternetAddress(para[i]);
            }
            for(int i=0;i<toAddress.length;i++){
                mimeMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            mimeMessage.setSubject(asunto);
            mimeMessage.setText(mensaje);
            Transport transport = session.getTransport("smtp");
            transport.connect(host,de,contrasenha);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null,EasyGas.errorEnviarCorreo);
        }
        return false;
    }
}
