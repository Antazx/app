/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelos;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Dani
 */
public class Lote {

    private int id;
    private int cantidad;
    private LocalDate fechaCreacion;
    private int estado;
    
    public Lote(JSONObject loteJ) {
        if(loteJ != null){
            try {

                this.id = loteJ.getInt("id");
                this.cantidad = loteJ.getInt("cantidad");
                this.fechaCreacion = ((java.sql.Date) loteJ.get("fechaCreacion")).toLocalDate();
                this.estado = loteJ.getInt("estado");

            
            } catch (JSONException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    public int getId() {
        return id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public int getEstado() {
        return estado;
    }

}