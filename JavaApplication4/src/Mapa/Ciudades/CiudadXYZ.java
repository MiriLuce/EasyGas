/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa.Ciudades;


import Mapa.Mapa;
import Mapa.Tejas.Teja;
import Modelo.Constantes.EasyGas;
import java.awt.Graphics;
import java.io.*;

/**
 *
 * @author Eduardo
 */
public class CiudadXYZ {

    private int ancho, alto;
    private int[][] tejasXYZ;
    private Mapa mapa;

    public CiudadXYZ(Mapa m) {
        mapa = m;
        CargaCiudad();
    }

    public void Actualiza() {

    }

    public void Dibuja(Graphics g) {

        int xi = (int) Math.max(0, mapa.ObtenCamara().getxOffSet()/Teja.ANCHOTEJA);
        int xf = ancho;
        int yi = (int) Math.max(0, mapa.ObtenCamara().getyOffSet()/Teja.ALTOTEJA);
        int yf = alto;

        //aqui si importa el orden del x,y
        for (int i = yi; i < yf; i++) {
            for (int j = xi; j < xf; j++) {
                ObtenTeja(j, i).Dibuja(g, (int) (j * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet()), (int) (i * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet()),null);
            }
        }
    }

    public Teja ObtenTeja(int x, int y) {
        Teja t = Teja.tejas[tejasXYZ[y][x]];
        if (t == null) {
            return Teja.tejaPista;
        }
        return t;
    }

    private void CargaCiudad() {
        ancho = EasyGas.anchoMapa;
        alto = EasyGas.altoMapa;
        tejasXYZ = new int[alto][ancho];

        BufferedReader br = null;
        String linea = "";
        String cvsDiv = ";";
        int j=0;

        try {

            br = new BufferedReader(new FileReader("mapa.csv"));
            while ((linea = br.readLine()) != null) {

                //se usa punto y coma de separador
                String[] datosMapa = linea.split(cvsDiv);

                for(int i=0;i<ancho;i++){
                    tejasXYZ[j][i] = Integer.valueOf(datosMapa[i]);
                }
                
                j++;
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
