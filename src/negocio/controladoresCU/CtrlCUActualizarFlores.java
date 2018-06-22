/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import negocio.modelos.Producto;
import org.json.JSONObject;
import persistencia.Fachadas.FachadaCUActualizarFlores;

/**
 *
 * @author Dani
 */
public class CtrlCUActualizarFlores {

    FachadaCUActualizarFlores fachada = FachadaCUActualizarFlores.getInstance();
    Producto planta;
    JSONObject plantaJ;
    
    public boolean comprobarFlores(String codigoPlanta) {
        plantaJ = fachada.comprobarFlores(codigoPlanta);
        planta = new Producto(plantaJ);
        return true;
    }
    
}
