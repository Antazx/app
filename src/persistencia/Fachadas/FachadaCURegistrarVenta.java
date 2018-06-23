/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import org.json.JSONObject;
import persistencia.gestores.GestorLineaVenta;
import persistencia.gestores.GestorProducto;
import persistencia.gestores.GestorVenta;

/**
 *
 * @author GUillermo
 */
public class FachadaCURegistrarVenta {
    
    private static FachadaCURegistrarVenta fachada;
    private final GestorProducto gestorProducto;
    private final GestorLineaVenta gestorLineaVenta;
    private final GestorVenta gestorVenta;
    
    public static FachadaCURegistrarVenta getInstance() {
        if (fachada == null) 
            fachada = new FachadaCURegistrarVenta();
        return fachada;
    }
    
    public FachadaCURegistrarVenta(){
        this.gestorProducto = GestorProducto.getInstance();
        this.gestorLineaVenta = GestorLineaVenta.getInstance();
        this.gestorVenta = GestorVenta.getInstance();
    }

    public JSONObject getProducto(String codigo) {
        return gestorProducto.readProductoCodigo(codigo);
    }

    public void registrarVenta(JSONObject lVentaJ) {
        gestorLineaVenta.registrarLineaVenta(lVentaJ);
    }

    public int creaNuevaVenta(String toString, String dni) {
        return gestorVenta.createVenta(toString, dni);
    }

    public void actualizarProducto(String codigo, int i) {
        gestorProducto.updateProducto(codigo, i);
    }

    public void borrarLineasVenta(int vent) {
        gestorLineaVenta.deleteLineaVenta(vent);
    }
    
    
    
}
