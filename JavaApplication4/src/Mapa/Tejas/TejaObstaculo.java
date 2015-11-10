/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa.Tejas;

import Modelo.Constantes.EasyGas;


/**
 *
 * @author Eduardo
 */
public class TejaObstaculo extends Teja{

    public TejaObstaculo(int i) {
        super(EasyGas.obstaculo, i);
    }
    
    @Override
    public boolean esSolido(){
        return true;
    }
}
