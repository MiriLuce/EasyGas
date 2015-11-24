/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author inf250
 */
public class RelojAlgoritmo {
    public static Calendar horaActual;
    DateFormat formato;
    int factorTiempo = 1; //1000 para un reloj normal =24*60/(30*1000)=0,048
    public RelojAlgoritmo() {

            formato = new SimpleDateFormat("HH:mm:ss");
            horaActual = Calendar.getInstance();
            
            horaActual.set(Calendar.HOUR_OF_DAY,0);
            horaActual.set(Calendar.MINUTE, 0);
            horaActual.set(Calendar.SECOND, 0);
            System.out.println("Empezo el dia");
            /*
            Timer t1 = new Timer(10, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    horaActual.add(Calendar.SECOND, 1);
                    EasyGas.horaActual=horaActual.getTime();
                }
            });
            t1.start(); */
            
            
             TimerTask task = new TimerTask() {
                @Override
                public void run()
                {
                    
                    horaActual.add(Calendar.SECOND, 1);
                    
                    //EasyGas.horaActual=horaActual.getTime();
                    
                }
                };
            Timer t = new Timer();
            t.schedule(task,10, factorTiempo);
           
   }
}
