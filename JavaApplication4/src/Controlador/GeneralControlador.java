/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Hibernate.Nodo;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        //selector.setFileFilter( new FileNameExtensionFilter("*.txt", "txt"));
        
        File archivo;
        
        if(JFileChooser.APPROVE_OPTION == selector.showOpenDialog(null)){
            archivo = selector.getSelectedFile();
        }
        else archivo = null;
        
        return archivo;
    }
}
