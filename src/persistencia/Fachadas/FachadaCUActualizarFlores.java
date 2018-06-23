/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import org.json.JSONObject;
import persistencia.gestores.GestorFloresEnLote;
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
    private final GestorFloresEnLote gestorFloresEnLote;
    
    public FachadaCUActualizarFlores() {
        
        gestorProducto = GestorProducto.getInstance();
        gestorLote = GestorLote.getInstance();
        gestorFloresEnLote = GestorFloresEnLote.getInstance();
        
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
    
    public JSONObject obtenerPlantaCodigo(String plantaDeFlor) {
        return gestorProducto.readProductoCodigo(plantaDeFlor);
    }

    public JSONObject obtenerEstimacion(String codigoPlanta, int idLote) {
        return gestorFloresEnLote.obtenerEstimacion(codigoPlanta, idLote);
    }

    public JSONObject obtenerLoteId(int idLote) {
        return gestorLote.obtenerLoteId(idLote);
    }

    public void actualizarEstimacion(String codigoPlanta, int idLote, int estimacionNueva) {
        gestorFloresEnLote.actualizarEstimacion(codigoPlanta, idLote, estimacionNueva);
    }
}