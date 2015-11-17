/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Eduardo
 */
public class Mapa {

    int alto, ancho;
    int[][] mapa;
    boolean barrera1;
    boolean barrera2;

    //se carga el mapa en "m" desde el archivo mapa.txt
    private void cargaMapa() throws FileNotFoundException { //el throw es es formalidad xD en caso no exista el txt
        Scanner s = new Scanner(new File("mapa.txt"));

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                mapa[i][j] = s.nextInt();
            }
        }
    }

    //constructor
    public Mapa(int nuevoAlto, int nuevoAncho) throws FileNotFoundException {
        alto = nuevoAlto;
        ancho = nuevoAncho;

        mapa = new int[alto][ancho];

        cargaMapa();
    }

    private void hayBarreras(int xi, int yi, int xf, int yf) {
        boolean hayBarreraX1 = false;
        boolean hayBarreraX2 = false;
        boolean hayBarreraY1 = false;
        boolean hayBarreraY2 = false;

        int i;

        if (xf > xi) {
            for (i = xi + 1; i < xf; i++) {
                if (mapa[yi][i] == 0) {
                    hayBarreraX1 = true;
                }
                if (mapa[yf][i] == 0) {
                    hayBarreraX2 = true;
                }
            }
        }

        if (xf < xi) {
            for (i = xf + 1; i < xi; i++) {
                if (mapa[yi][i] == 0) {
                    hayBarreraX1 = true;
                }
                if (mapa[yf][i] == 0) {
                    hayBarreraX2 = true;
                }
            }
        }

        if (yf > yi) {
            for (i = yi + 1; i < yf; i++) {
                if (mapa[i][xi] == 0) {
                    hayBarreraY1 = true;
                }
                if (mapa[i][xf] == 0) {
                    hayBarreraY2 = true;
                }
            }
        }

        if (yf < yi) {
            for (i = yf + 1; i < yi; i++) {
                if (mapa[i][xi] == 0) {
                    hayBarreraY1 = true;
                }
                if (mapa[i][xf] == 0) {
                    hayBarreraY2 = true;
                }
            }
        }

        barrera1 = hayBarreraX1 && hayBarreraY2;
        barrera2 = hayBarreraX2 || hayBarreraY1;

    }

    public boolean hayBarrerasY(int x, int yi, int yf) {
        int i;
        if (yf > yi) {
            for (i = yi + 1; i < yf; i++) {
                if (mapa[i][x] == 0) {
                    return true;
                }
            }
        } else if (yi > yf) {
            for (i = yi + 1; i < yf; i++) {
                if (mapa[i][x] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hayBarrerasX(int y, int xi, int xf) {
        int i;
        if (xf > xi) {
            for (i = xi + 1; i < xf; i++) {
                if (mapa[y][i] == 0) {
                    return true;
                }
            }
        } else if (xi > xf) {
            for (i = xi + 1; i < xf; i++) {
                if (mapa[y][i] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int calculaMenorY(int x, int yi, int yf) {
        int ind;
        int indDer;
        int indIzq;
        int menorDer = 0;
        int menorIzq = 0;

        int aux, aux2;

        if (yf > yi) {
            aux = yi;
            aux2 = yf;
        } else {
            aux = yf;
            aux2 = yi;
        }

        ind = aux + 1;

        while (mapa[ind][x] != 0) {
            ind++;
        }

        indDer = x + 1;
        indIzq = x - 1;

        //cuento cuanto bloque hay a la derecha
        while (mapa[ind][indDer] == 0 && indDer <= (ancho - 1)) {
            menorDer++;
            indDer++;
        }

        //cuento cuanto bloque hay a la izquierda
        while (mapa[ind][indIzq] == 0 && indIzq >= 0) {
            menorIzq++;
            indIzq--;
        }

        if (menorDer < menorIzq) {
            return menorDer;
        } else {
            return menorIzq;
        }
    }

    private int calculaMayorY(int x, int yi, int yf) {
        int ind;
        int indDer;
        int indIzq;
        int menorDer = 0;
        int menorIzq = 0;

        int aux, aux2;

        if (yf > yi) {
            aux = yi;
            aux2 = yf;
        } else {
            aux = yf;
            aux2 = yi;
        }

        ind = aux + 1;

        while (mapa[ind][x] != 0) {
            ind++;
        }

        indDer = x + 1;
        indIzq = x - 1;

        //cuento cuanto bloque hay a la derecha
        while (mapa[ind][indDer] == 0 && indDer <= (ancho - 1)) {
            menorDer++;
            indDer++;
        }

        //cuento cuanto bloque hay a la izquierda
        while (mapa[ind][indIzq] == 0 && indIzq >= 0) {
            menorIzq++;
            indIzq--;
        }

        if (menorDer < menorIzq) {
            return menorIzq;
        } else {
            return menorDer;
        }
    }

    private int calculaMayorX(int y, int xi, int xf) {
        int ind;
        int indArriba;
        int indAbajo;
        int menorArriba = 0;
        int menorAbajo = 0;

        int aux, aux2;

        if (xf > xi) {
            aux = xi;
            aux2 = xf;
        } else {
            aux = xf;
            aux2 = xi;
        }

        ind = aux + 1;

        while (mapa[y][ind] != 0) {
            ind++;
        }

        indArriba = y - 1;
        indAbajo = y + 1;

        //cuento cuanto bloque hay a la derecha
        while (mapa[indArriba][ind] == 0 && indArriba >= 0) {
            menorArriba++;
            indArriba--;
        }

        //cuento cuanto bloque hay a la izquierda
        while (mapa[indAbajo][ind] == 0 && indAbajo <= (alto - 1)) {
            menorAbajo++;
            indAbajo++;
        }

        if (menorArriba < menorAbajo) {
            return menorAbajo;
        } else {
            return menorArriba;
        }
    }

    private int calculaMenorX(int y, int xi, int xf) {
        int ind;
        int indArriba;
        int indAbajo;
        int menorArriba = 0;
        int menorAbajo = 0;

        int aux, aux2;

        if (xf > xi) {
            aux = xi;
            aux2 = xf;
        } else {
            aux = xf;
            aux2 = xi;
        }

        ind = aux + 1;

        while (mapa[y][ind] != 0) {
            ind++;
        }

        indArriba = y - 1;
        indAbajo = y + 1;

        //cuento cuanto bloque hay a la derecha
        while (mapa[indArriba][ind] == 0 && indArriba >= 0) {
            menorArriba++;
            indArriba--;
        }

        //cuento cuanto bloque hay a la izquierda
        while (mapa[indAbajo][ind] == 0 && indAbajo <= (alto - 1)) {
            menorAbajo++;
            indAbajo++;
        }

        if (menorArriba < menorAbajo) {
            return menorArriba;
        } else {
            return menorAbajo;
        }
    }

    public int distanciaMinima(int xi, int yi, int xf, int yf) { //coordenadas del punto inicial (xi,yi), coordenadas del punto final (xf,yf)
        int dx = Math.abs(xf - xi); //diferencia de puntos X
        int dy = Math.abs(yf - yi); //diferencia de puntos Y

        if (xi == xf && yi != yf) { //caso 1
            if (!hayBarrerasY(xi, yi, yf)) {
                return dy;
            } else {
                int menorBloque = calculaMenorY(xi, yi, yf);
                return (menorBloque * 2) + dy;
            }
        } else if (yi == yf && xi != xf) { //caso 2
            if (!hayBarrerasX(yi, xi, xf)) {
                return dx;
            } else {
                int menorBloque = calculaMenorX(yi, xi, xf);
                return (menorBloque * 2) + dx;
            }
        } else if (xi != xf && yi != yf) { //caso 3
            if (!(barrera1 || barrera2)) {
                return Math.abs(yf - yi) + Math.abs(xf - xi);
            } else {
                if (barrera1 && barrera2) { // escojo la distancia minima, es decir entre barrera 1 y barrera2
                    if (xf > xi && yf > yi) {
                        //calcular la distancia 2
                        int menorX = 0, menorY = 0, mayorX = 0, mayorY = 0;
                        mayorX = calculaMayorX(yi, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMenorX(yi, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xf, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xf, yi, yf);
                            mayorY = 0;
                        }
                        int distancia2 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        //calcular la distancia 1 

                        mayorX = calculaMayorX(yf, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMenorX(yf, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xi, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xi, yi, yf);
                            mayorY = 0;
                        }
                        int distancia1 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        if (distancia1 > distancia2) {
                            return distancia2;
                        } else {
                            return distancia1;
                        }
                    }
                    if (xf > xi && yf < yi) {
                        //calcular la distancia 2
                        int menorX = 0, menorY = 0, mayorX = 0, mayorY = 0;
                        mayorX = calculaMayorX(yf, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMenorX(yf, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xi, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xi, yi, yf);
                            mayorY = 0;
                        }
                        int distancia2 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        //calcular la distancia 1 

                        mayorX = calculaMayorX(yf, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMenorX(yf, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xi, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xi, yi, yf);
                            mayorY = 0;
                        }
                        int distancia1 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        if (distancia1 > distancia2) {
                            return distancia2;
                        } else {
                            return distancia1;
                        }
                    }

                    if (xf < xi && yf > yi) {
                        //calcular la distancia 2
                        int menorX = 0, menorY = 0, mayorX = 0, mayorY = 0;
                        mayorX = calculaMayorX(yi, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMenorX(yi, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xf, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xf, yi, yf);
                            mayorY = 0;
                        }
                        int distancia2 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        //calcular la distancia 1 

                        mayorX = calculaMayorX(yf, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMenorX(yf, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xi, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xi, yi, yf);
                            mayorY = 0;
                        }
                        int distancia1 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        if (distancia1 > distancia2) {
                            return distancia2;
                        } else {
                            return distancia1;
                        }
                    }

                    if (xf < xi && yf < yi) {
                        //calcular la distancia 2
                        int menorX = 0, menorY = 0, mayorX = 0, mayorY = 0;
                        mayorX = calculaMayorX(yf, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMenorX(yf, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xi, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xi, yi, yf);
                            mayorY = 0;
                        }
                        int distancia2 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        //calcular la distancia 1 

                        mayorX = calculaMayorX(yi, xi, xf);
                        if (mayorX < xi) {
                            menorX = calculaMayorX(yi, xi, xf);
                            mayorX = 0;
                        }
                        mayorY = calculaMayorY(xf, yi, yf);
                        if (mayorY > yf) {
                            menorY = calculaMenorY(xf, yi, yf);
                            mayorY = 0;
                        }
                        int distancia1 = Math.abs(yf - yi) + Math.abs(xf - xi) + ((menorY + mayorY) * 2) + ((menorX + mayorX) * 2);
                        if (distancia1 > distancia2) {
                            return distancia2;
                        } else {
                            return distancia1;
                        }
                    }
                } else {
                    if (barrera1) { // tengo que ir por barrera2
                        return Math.abs(yf - yi) + Math.abs(xf - xi);
                    }
                    if (barrera2) { //tengo que ir por barrera1
                        return Math.abs(yf - yi) + Math.abs(xf - xi);
                    }
                }

            }
        } else { //si ambos puntos son iguales tons no hay distancia duuuuuuuuuh :v
            return 0;
        }

        return 0;
    }
}
