/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import org.json.JSONObject;
import persistencia.gestores.GestorProducto;

/**
 *
 * @author Dani
 */
public class FachadaCUModificarLote {

    private static FachadaCUModificarLote fachada;
    private final GestorProducto gestorProducto;
    
    public FachadaCUModificarLote() {
        
        gestorProducto = GestorProducto.getInstance();
        
    }
    
    public static FachadaCUModificarLote getInstance() {
        if(fachada == null){
            fachada = new FachadaCUModificarLote();
        } 
        return fachada;
    }

    public JSONObject obtenerPlanta(String nombre) {
         return gestorProducto.readProducto(nombre);
    }
    
}
