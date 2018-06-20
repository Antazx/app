/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelos.Factura;
import negocio.modelos.Lote;
import negocio.modelos.PedidoAProveedor;
import negocio.modelos.Producto;
import negocio.modelos.Proveedor;
import org.json.JSONArray;
import org.json.JSONException;
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
    String codigo;
    
    public boolean comprobarNombre(String nombre) {
        plantaJ = fachada.obtenerPlanta(nombre);
        return plantaJ!=null;
    }
    
    public ArrayList<String> getLotes() {
        ArrayList<String> lotes = new ArrayList();
        planta = new Producto(plantaJ);
        codigo = planta.getCodigo();
        JSONArray lotesJ = fachada.getLotes(codigo);
        try {
                for (int i = 0; i < lotesJ.length(); i++){
                    
                    JSONObject loteJ = lotesJ.getJSONObject(i);
                    Lote lote = new Lote(loteJ);
                    lotes.add(lote.getId()+"");       
                }
        } catch (JSONException ex) {
                Logger.getLogger(CtrlCUConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return lotes;
    }
    
    
}
