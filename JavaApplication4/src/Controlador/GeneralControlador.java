/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Algoritmo.Genetico.Cromosoma;
import Modelo.Hibernate.Nodo;
import Modelo.Hibernate.Pedido;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo
 */
public class GeneralControlador {
    
    public static int SacaSiguienteNumeroEnString(String cad) {
        int i = 0;

        while (!Character.isDigit(cad.charAt(i))) {
            i++;
        }
        int j = i;
        while (Character.isDigit(cad.charAt(j))) {
            j++;
        }
        return Integer.parseInt(cad.substring(i, j));
    }

    public static ArrayList<Integer> SacaCoordinadas(String cadena) {
        int i = 0;
        while (cadena.charAt(i) != ',') {
            i++;
        }
        String aux = cadena.substring(i);

        int x = SacaSiguienteNumeroEnString(cadena);
        int y = SacaSiguienteNumeroEnString(aux);

        ArrayList<Integer> nuevo = new ArrayList<Integer>();
        nuevo.add(x);
        nuevo.add(y);
        return nuevo;
    }
    
    public static boolean VerificaNroDoc(String nrodoc) {
        char[] chars = nrodoc.toCharArray();

        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<String> ObtenListaDirecciones(JTextField nPedNroDocText) {
        ArrayList<Nodo> nds = ClienteControlador.ListarDireccionesCliente(nPedNroDocText.getText());
        ArrayList<String> aux = new ArrayList<String>();

        for (int j = 0; j < nds.size(); j++) {
            String s = "(" + nds.get(j).getCoordX() + "," + nds.get(j).getCoordY() + ")";
            aux.add(s);
        }

        return aux;
    }
    
    public static String DevuelveFormatoDireccion(int x, int y){
        return "(" + x + "," + y + ")";
    }
    
    public static File obtenerArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter( new FileNameExtensionFilter("*.csv", "csv"));
        selector.setFileFilter( new FileNameExtensionFilter("*.txt", "txt"));
        
        File archivo;
        
        if(JFileChooser.APPROVE_OPTION == selector.showOpenDialog(null)){
            archivo = selector.getSelectedFile();
        }
        else archivo = null;
        
        return archivo;
    }
    
    public static void ActualizaTablaResultadosAlgoritmo(ArrayList<Cromosoma> lista, JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (int i = 0; i < lista.size(); i++) {
            Object[] fila = new Object[4];
            fila[0] = lista.get(i).getCadena().size(); //la cantidad de camiones es igual a la cantidad de rutas
            fila[1] = lista.get(i).getCantDieselTotal();
            fila[2] = (lista.get(i).getCantTiempoTotal()/60); //en horas
            fila[3] = lista.get(i).getCosto();
            modelo.addRow(fila);
        }
    }
}
