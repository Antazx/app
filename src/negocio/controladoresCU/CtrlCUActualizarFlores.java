/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelos.FloresEnLote;
import negocio.modelos.Lote;
import negocio.modelos.Producto;
import org.json.JSONException;
import org.json.JSONObject;
import persistencia.Fachadas.FachadaCUActualizarFlores;

/**
 *
 * @author Dani
 */
public class CtrlCUActualizarFlores {

    FachadaCUActualizarFlores fachada = FachadaCUActualizarFlores.getInstance();
    Producto planta;
    Lote lote;
    FloresEnLote floresEnLote;
    JSONObject plantaJ;
    JSONObject loteJ;
    JSONObject floresEnLoteJ;
    
    public boolean comprobarFlores(String codigoPlanta) {
        plantaJ = fachada.comprobarFlores(codigoPlanta);
        planta = new Producto(plantaJ);
        try {
            String plantaDeFlor = plantaJ.getString("plantaDeFlor");
            if(!plantaDeFlor.equals("")){
                JSONObject florJ = fachada.obtenerPlantaCodigo(plantaDeFlor);
                planta.setPlantaDeLaFlor(new Producto(florJ));
            }
        } catch (JSONException ex) {
            Logger.getLogger(CtrlCUConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String subtipo = planta.getSubtipo();
        return subtipo.equals("Flor");
    }

    public String getEstimacionFlores(String codigoPlanta, int idLote) {
        floresEnLoteJ = fachada.obtenerEstimacion(codigoPlanta, idLote);
        floresEnLote = new FloresEnLote(floresEnLoteJ);
        loteJ = fachada.obtenerLoteId(idLote);
        floresEnLote.setFlor(planta);
        floresEnLote.setLote(lote);
        return floresEnLote.getCantidad()+"";
    }

    public void actualizarEstimacion(String codigoPlanta, int idLote, int estimacionNueva) {
        fachada.actualizarEstimacion(codigoPlanta, idLote, estimacionNueva);
    }
    
}
