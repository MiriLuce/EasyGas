/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    int [] caminoX;
    int [] caminoY;
    int caso;
    int ultimoIndice=-1;
    boolean fueArriba;
    boolean fueAbajo;
    boolean fueIzq;
    boolean fueDer;
    
    //se carga el mapa en "m" desde el archivo mapa.txt
    private void cargaMapa() throws IOException { //el throw es es formalidad xD en caso no exista el txt
        //Scanner s = new Scanner(new File("mapa.txt"));
        
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                mapa[i][j] = 1;
                
            }
        }
        /*
        FileInputStream fistream = new FileInputStream("mapa.txt");
        DataInputStream in = new DataInputStream(fistream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String fullLine = br.readLine();
        String[] lineRead = fullLine.split("\\s+");
        // lee cordenada y y coordenada x
        int cantObstaculos = Integer.parseInt(lineRead[0]);
        for(int i=0;i<cantObstaculos;i++){
               fullLine = br.readLine();
               lineRead = fullLine.split(",");
               int x=Integer.parseInt(lineRead[0]);
               int y=Integer.parseInt(lineRead[1]);
               mapa[y][x]=0;
            
        }
        */
    }

    //constructor
    public Mapa(int nuevoAlto, int nuevoAncho) throws IOException {
        alto = nuevoAlto;
        ancho = nuevoAncho;

        mapa = new int[alto][ancho];

        cargaMapa();
    }

    public void agregaBarrera(ArrayList<modeloCompleto.Nodo> barrera){
        
        int cantNodo = barrera.size();
        for(int i = 0; i< cantNodo; i++){
            
        }       
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

        barrera1 = hayBarreraX1 || hayBarreraY2;
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
            for (i = yf; i < yi; i++) {
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
            for (i = xf; i < xi; i++) {
                if (mapa[y][i] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int calculaMenorY(int x, int yi, int yf) {
        int ind,indDer,indIzq,menorDer = 0,menorIzq = 0,aux, aux2;
        int izqLimite=0;
        int derLimite=0;
        
        for(int i=yi<yf?yi:yf; i<(yi<yf?yf:yi); i++){
            if(mapa[i][x]==0){
                menorDer=0;
                menorIzq=0;
                indDer = x + 1;
                indIzq = x - 1;
                //cuento cuanto bloque hay a la derecha
                while (indDer < ancho && mapa[i][indDer] == 0  ) {
                    menorDer++;
                    indDer++;
                }
                //cuento cuanto bloque hay a la izquierda
                while ( indIzq >= 0 && mapa[i][indIzq] == 0 ) {
                    menorIzq++;
                    indIzq--;
                }
                if(derLimite < menorDer) derLimite=menorDer;
                if(izqLimite < menorIzq) izqLimite=menorIzq;
            }
        }
        if (menorDer!=0 && menorDer < menorIzq) {
            if(caso!=3){
                ultimoIndice++;
                caminoY[ultimoIndice]=yi;
                caminoX[ultimoIndice]=x-menorDer-1;
                ultimoIndice++;
                caminoY[ultimoIndice]=yf;
                caminoX[ultimoIndice]=x-menorDer-1;
            }
            return menorDer;
        } else {
            if(caso!=3){
                ultimoIndice++;
                caminoY[ultimoIndice]=yi;
                caminoX[ultimoIndice]=x+menorIzq-1;
                ultimoIndice++;
                caminoY[ultimoIndice]=yf;
                caminoX[ultimoIndice]=x+menorIzq-1;
            }
            return menorIzq * -1;
        }
    }
    
    private int calculaMayorY(int x, int yi, int yf) {
        int ind,indDer,indIzq,mayorDer = 0,mayorIzq = 0,aux, aux2;
        int izqLimite=0;
        int derLimite=0;
        fueIzq=false;
        fueDer=false;
        for(int i=yi<yf?yi:yf;i<(yi<yf?yf:yi);i++){
            if(mapa[i][x]==0){
                mayorDer=0;
                mayorIzq=0;
                indDer = x + 1;
                indIzq = x - 1;
                //cuento cuanto bloque hay a la derecha
                while (indDer < ancho && mapa[i][indDer] == 0  ) {
                    mayorDer++;
                    indDer++;
                }
                //cuento cuanto bloque hay a la izquierda
                while ( indIzq >= 0 && mapa[i][indIzq] == 0 ) {
                    mayorIzq++;
                    indIzq--;
                }
                if(derLimite < mayorDer) derLimite=mayorDer;
                if(izqLimite < mayorIzq) izqLimite=mayorIzq;
            }
        }
        if (izqLimite>0 && derLimite < izqLimite) {
            fueIzq=true;
            return izqLimite;
        } else {
            fueDer=true;
            return derLimite;
        }
    }
    private int calculaMayorX(int y, int xi, int xf) {
        int ind,indArriba,indAbajo,mayorArriba = 0, mayorAbajo = 0,aux, aux2,abajoLimite=0,arribaLimite=0;
        fueArriba=false;
        fueAbajo=false;
        for(int i=xi<xf?xi:xf;i<(xi<xf?xf:xi);i++){
            if(mapa[y][i]==0){
                indArriba=y-1;
                mayorArriba=0;
                mayorAbajo=0;
                //cuento cuanto bloque hay a la derecha
                while (indArriba >=0  && mapa[indArriba][i] == 0 ) {
                    mayorArriba++;
                    indArriba--;
                }
                indAbajo=y+1;
                //cuento cuanto bloque hay a la izquierda
                while (indAbajo < alto  &&  indAbajo>=0 && mapa[indAbajo][i] == 0 ) {
                    mayorAbajo++;
                    indAbajo++;
                }

                if(abajoLimite < mayorAbajo) abajoLimite=mayorAbajo;
                if(arribaLimite < mayorArriba) arribaLimite=mayorArriba;
            }
        }

        
        
        if (arribaLimite>0 &&arribaLimite > abajoLimite) {
            fueArriba=true;
            return arribaLimite;
        } else {
            fueAbajo=true;
            return abajoLimite;
        }
    }
    public int calculaMenorX(int y, int xi, int xf) {
        int ind,indArriba,indAbajo,menorArriba = 0, menorAbajo = 0,aux, aux2,abajoLimite=0,arribaLimite=0;
         // se escogen los mayores entre toda la linea para arriba y para abajo
        // al final se escogen entre arriba y abajo
        for(int i=xi<xf?xi:xf;i<(xi<xf?xf:xi);i++){
            if(mapa[y][i]==0){
                indArriba=y-1;
                menorArriba=0;
                menorAbajo=0;
                //cuento cuanto bloque hay a la derecha
                while (  indArriba >=0 &&mapa[indArriba][i] == 0) {
                    menorArriba++;
                    indArriba--;
                }
                indAbajo=y+1;
                //cuento cuanto bloque hay a la izquierda
                while (indAbajo < alto && mapa[indAbajo][i] == 0  ) {
                    menorAbajo++;
                    indAbajo++;
                }
                if(abajoLimite < menorAbajo) abajoLimite=menorAbajo;
                if(arribaLimite < menorArriba) arribaLimite=menorArriba;
            }
        }
        if (arribaLimite!=0 && arribaLimite < abajoLimite) {
            if(caso!=3){
                ultimoIndice++;
                caminoY[ultimoIndice]=y-arribaLimite+1;
                caminoX[ultimoIndice]=xi;
                ultimoIndice++;
                caminoY[ultimoIndice]=y-arribaLimite+1;
                caminoX[ultimoIndice]=xf;
            }
            return arribaLimite;
        } else {
            if(caso!=3){
                ultimoIndice++;
                caminoY[ultimoIndice]=y+abajoLimite+1;
                caminoX[ultimoIndice]=xi;
                ultimoIndice++;
                caminoY[ultimoIndice]=y+abajoLimite+1;
                caminoX[ultimoIndice]=xf;
            }
            return abajoLimite * -1;
        }
    }
    public void inicializa(){
        ultimoIndice=-1;
        caminoX= new int[3];
        caminoY= new int[3];
        for(int i=0;i<3;i++){
            caminoX[i]=-1;
            caminoY[i]=-1;
        }
        caso=0;
        fueArriba=false;
        fueAbajo=false;
        fueIzq=false;
        fueDer=false;
    
    }
    public int distanciaMinima(int xi, int yi, int xf, int yf) { //coordenadas del punto inicial (xi,yi), coordenadas del punto final (xf,yf)
        inicializa();
        int dx = Math.abs(xf - xi)+1; //diferencia de puntos X
        int dy = Math.abs(yf - yi)+1; //diferencia de puntos Y
        
        if (xi == xf && yi != yf) { //caso 1
            caso=1;
            if (!hayBarrerasY(xi, yi, yf)) {  // no hay caminoX ni caminoY
                return dy;
            } else {
                int menorBloque =  Math.abs( calculaMenorY(xi, yi, yf));
                return (menorBloque * 2) + dy;
            }
        } else if (yi == yf && xi != xf) { //caso 2
            caso=2;
            if (!hayBarrerasX(yi, xi, xf)) { // no hay caminoX ni caminoY
                return dx;
            } else {
                int menorBloque =  Math.abs(calculaMenorX(yi, xi, xf));
                return (menorBloque * 2) + dx;
            }
        } else if (xi != xf && yi != yf) { //caso 3
            caso=3;
            hayBarreras(xi,yi,xf,yf);
            if(!(barrera1 && barrera2)){
                if(!barrera1 && !barrera2) {
                    // cualquier camino
                    ultimoIndice++;
                    caminoX[ultimoIndice]=xf;
                    caminoY[ultimoIndice]=yi;
                } else {
                    if(!barrera1){ //camino2
                        ultimoIndice++;
                        caminoX[ultimoIndice]=xf;
                        caminoY[ultimoIndice]=yi;
                    }
                    if(!barrera2){ // camino1
                        ultimoIndice++;
                        caminoX[ultimoIndice]=xi;
                        caminoY[ultimoIndice]=yf;
                    }
                }
                return Math.abs(yf - yi) + Math.abs(xf - xi);
            } else {
                 // escojo la distancia minima, es decir entre barrera 1 y barrera2
                   int mayorLimiteY=0,mayorActualY=0;
                   boolean fueIzqFinal=false;
                   boolean fueDerFinal=false;
                   for(int i=xi<xf?xi:xf;i<(xi<xf?xf:xi);i++){
                        mayorActualY= calculaMayorY(i, yi, yf);
                        if(mayorActualY>mayorLimiteY){
                            fueIzqFinal=fueIzq;
                            fueDerFinal=fueDer;
                            mayorLimiteY=mayorActualY;
                        }
                   }
                   
                   
                   boolean fueAbajoFinal=false;
                   boolean fueArribaFinal=false;
                   int mayorLimiteX=0,mayorActualX=0;
                   for(int i=yi<yf?yi:yf;i<(yi<yf?yf:yi);i++){
                        mayorActualX= calculaMayorX(i, xi, xf);
                        if(mayorActualX>mayorLimiteX){
                            fueAbajoFinal=fueAbajo;
                            fueArribaFinal=fueArriba;
                            mayorLimiteX=mayorActualX;
                        }
                   }
                   
                   
                   ultimoIndice++;
                   caminoX[ultimoIndice]=xi<xf?xf:xi;
                   caminoY[ultimoIndice]=fueArribaFinal?(xi<xf?xi:xf)-mayorLimiteX-1:(xi<xf?xi:xf)+mayorLimiteX+1;
                   ultimoIndice++;
                   caminoX[ultimoIndice]=xi<xf?xf:xi ;
                   caminoY[ultimoIndice]=fueArribaFinal?(xi<xf?xi:xf)-mayorLimiteX-1:(xi<xf?xi:xf)+mayorLimiteX+1;
                   
                   return Math.abs(xf-xi)+caminoY[ultimoIndice];
            
            }
        } else { //si ambos puntos son iguales tons no hay distancia duuuuuuuuuh :v
            return 0;
        }
        
      
    }
}
