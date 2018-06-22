/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import org.json.JSONObject;
import persistencia.gestores.GestorLote;
import persistencia.gestores.GestorProducto;

/**
 *
 * @author Dani
 */
public class FachadaCUActualizarFlores {
    
    private static FachadaCUActualizarFlores fachada;
    private final GestorProducto gestorProducto;
    private final GestorLote gestorLote;
    
    public FachadaCUActualizarFlores() {
        
        gestorProducto = GestorProducto.getInstance();
        gestorLote = GestorLote.getInstance();
        
    }
    
    public static FachadaCUActualizarFlores getInstance() {
        if(fachada == null){
            fachada = new FachadaCUActualizarFlores();
        } 
        return fachada;
    }

    public JSONObject comprobarFlores(String codigoPlanta) {
        return gestorProducto.readProductoCodigo(codigoPlanta);
    }
    
}
