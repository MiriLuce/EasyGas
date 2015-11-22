/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Constantes;

import Mapa.Utilidades.Imagen;
import Modelo.Hibernate.Arista;
import Modelo.Hibernate.Nodo;
import Modelo.Hibernate.Ruta;
import Modelo.Hibernate.Usuario;
import Util.HibernateUtil;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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
    
    //variables para el mapa
    public static int anchoMapa = 300;
    public static int altoMapa = 200;
    public static int tamCuadMapa = 10;
    
    public static double factorTiempo=0.5;
    
    public static BufferedImage camionNormal;
    public static BufferedImage camionAccidente;
    public static BufferedImage mapaCentral;
    public static BufferedImage mapaClienteNat;
    public static BufferedImage mapaClienteJur;
    public static BufferedImage obstaculo;
    public static BufferedImage pista;
    public static BufferedImage rutaAzul;
    public static BufferedImage rutaRojo;
    public static BufferedImage rutaAmarillo;
    public static BufferedImage rutaVerde;
    public static BufferedImage imagenPausa; 
    
    public static Ruta rutaPrueba;
    
    public static void Inicializa(){
        camionNormal = Imagen.CargaImagen("/mapa_camion_16.png");
//        camionAccidente = Imagen.CargaImagen("/mapa_camion_accidente.png");
        mapaCentral = Imagen.CargaImagen("/mapa_central.png");
        mapaClienteNat = Imagen.CargaImagen("/mapa_cliente_nat.png");
        mapaClienteJur = Imagen.CargaImagen("/mapa_cliente_jur.png");
        obstaculo = Imagen.CargaImagen("/mapa_obstaculo.png");
        pista = Imagen.CargaImagen("/mapa_pista.png");
        rutaAzul = Imagen.CargaImagen("/mapa_ruta_azul.png");
        rutaRojo = Imagen.CargaImagen("/mapa_ruta_rojo.png");
        rutaAmarillo = Imagen.CargaImagen("/mapa_ruta_amarillo.png");
        rutaVerde = Imagen.CargaImagen("/mapa_ruta_verde.png");
        imagenPausa = Imagen.CargaImagen("/mapa_pausa_200.png");
        
        rutaPrueba = CreaRutaPrueba();
    }
    
    private static Ruta CreaRutaPrueba(){
        Ruta nRuta = new Ruta();
        List aristas = new ArrayList();
        
        Arista arista1 = new Arista();
        arista1.setNodoByIdOrigen(new Nodo(20,10));
        arista1.setNodoByIdDestino(new Nodo(20,52));
        arista1.CalculaDireccion();
        
        aristas.add(arista1);
        
        Arista arista2 = new Arista();
        arista2.setNodoByIdOrigen(new Nodo(20,52));
        arista2.setNodoByIdDestino(new Nodo(60,52));
        arista2.CalculaDireccion();
        
        aristas.add(arista2);
        
        Arista arista3 = new Arista();
        arista3.setNodoByIdOrigen(new Nodo(60,52));
        arista3.setNodoByIdDestino(new Nodo(60,10));
        arista3.CalculaDireccion();
        
        aristas.add(arista3);
        
        Arista arista4 = new Arista();
        arista4.setNodoByIdOrigen(new Nodo(60,10));
        arista4.setNodoByIdDestino(new Nodo(20,10));
        arista4.CalculaDireccion();
        
        aristas.add(arista4);
        
        nRuta.setAristas(aristas);
        
        return nRuta;
    }
}
