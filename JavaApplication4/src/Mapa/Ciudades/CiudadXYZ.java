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

    public CiudadXYZ(Mapa m) throws IOException {
        mapa = m;
        cargaMapa();
    }

    public void Actualiza() {

    }

    public void Dibuja(Graphics g) {

        int xi = (int) Math.max(0, mapa.ObtenCamara().getxOffSet() / Teja.ANCHOTEJA);
        int xf = ancho;
        int yi = (int) Math.max(0, mapa.ObtenCamara().getyOffSet() / Teja.ALTOTEJA);
        int yf = alto;

        //aqui si importa el orden del x,y
        for (int i = yi; i < yf; i++) {
            for (int j = xi; j < xf; j++) {
                ObtenTeja(j, i).Dibuja(g, (int) (j * Teja.ANCHOTEJA - mapa.ObtenCamara().getxOffSet()), (int) (i * Teja.ALTOTEJA - mapa.ObtenCamara().getyOffSet()), null);
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
        int j = 0;

        try {

            br = new BufferedReader(new FileReader("mapa.csv"));
            while ((linea = br.readLine()) != null) {

                //se usa punto y coma de separador
                String[] datosMapa = linea.split(cvsDiv);

                for (int i = 0; i < ancho; i++) {
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

    //se carga el mapa en "m" desde el archivo mapa.txt
    private void cargaMapa() throws IOException { //el throw es es formalidad xD en caso no exista el txt
        ancho = EasyGas.anchoMapa;
        alto = EasyGas.altoMapa;
        tejasXYZ = new int[alto][ancho];

        //inicia todo sin obstaculos
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                tejasXYZ[i][j] = 1;
            }
        }

        FileInputStream fistream = new FileInputStream("callesBloqueadas.txt");
        DataInputStream in = new DataInputStream(fistream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        while ((line = br.readLine()) != null) {
            String obstaculos[] = line.split(";");
            int[][] obstaculosEnteros = new int[obstaculos.length + 1][2];
            for (int i = 0; i < obstaculos.length; i++) {
                int x = Integer.parseInt((obstaculos[i].split(","))[0]);
                int y = Integer.parseInt((obstaculos[i].split(","))[1]);
                obstaculosEnteros[i][0] = x;
                obstaculosEnteros[i][1] = y;
            }
            obstaculosEnteros[obstaculos.length][0] = obstaculosEnteros[0][0];
            obstaculosEnteros[obstaculos.length][1] = obstaculosEnteros[0][1];
            for (int i = 0; i < obstaculos.length; i++) {
                tejasXYZ[obstaculosEnteros[i][1]][obstaculosEnteros[i][0]] = 0;

                int xi = obstaculosEnteros[i][0];
                int yi = obstaculosEnteros[i][1];
                int xf = obstaculosEnteros[i + 1][0];
                int yf = obstaculosEnteros[i + 1][1];
                if (xf == xi) {
                    for (int j = (yi < yf ? yi : yf); j < (yi > yf ? yi : yf); j++) {
                        tejasXYZ[j][xi] = 0;
                    }
                }
                if (yf == yi) {
                    for (int j = (xi < xf ? xi : xf); j < (xi > xf ? xi : xf); j++) {
                        tejasXYZ[yf][j] = 0;
                    }
                }
            }
        }
    }
}
