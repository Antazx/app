/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelos;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Dani
 */
public class FloresEnLote {
    private int cantidad;
    private Producto flor;
    private Lote lote;

    public FloresEnLote(JSONObject floresEnLoteJ) {
        if(floresEnLoteJ != null){
            try {
                this.cantidad = floresEnLoteJ.getInt("cantidad");
            
            } catch (JSONException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    public void setFlor(Producto flor) {
        this.flor = flor;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    
}