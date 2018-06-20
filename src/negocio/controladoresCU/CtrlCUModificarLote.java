/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import negocio.modelos.Producto;
import org.json.JSONObject;
import persistencia.Fachadas.FachadaCUModificarLote;

/**
 *
 * @author Dani
 */
public class CtrlCUModificarLote {

    FachadaCUModificarLote fachada = FachadaCUModificarLote.getInstance();
    Producto planta; 
    JSONObject plantaJ;
    
    public boolean comprobarNombre(String nombre) {
        plantaJ = fachada.obtenerPlanta(nombre);
        return plantaJ!=null;
    }
    
}
