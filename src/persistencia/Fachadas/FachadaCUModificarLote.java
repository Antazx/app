/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import org.json.JSONArray;
import org.json.JSONObject;
import persistencia.gestores.GestorLote;
import persistencia.gestores.GestorProducto;

/**
 *
 * @author Dani
 */
public class FachadaCUModificarLote {

    private static FachadaCUModificarLote fachada;
    private final GestorProducto gestorProducto;
    private final GestorLote gestorLote;
    
    public FachadaCUModificarLote() {
        
        gestorProducto = GestorProducto.getInstance();
        gestorLote = GestorLote.getInstance();
        
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

    public JSONArray getLotes(String codigo) {
         return gestorLote.readLotes(codigo);
    }

    public void actualizarEstado(int selectedLote, int selectedEstado) {
        gestorLote.updateLotes(selectedLote, selectedEstado);
    }

    public JSONObject obtenerPlantaCodigo(String plantaDeFlor) {
        return gestorProducto.readProductoCodigo(plantaDeFlor);
    }
    
}