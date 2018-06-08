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
 * @author GUillermo
 */
public class Disponibilidad {
    
    private String disponibilidad;
    private LocalDate comienzo;
    private LocalDate finPrevisto;
    
    Disponibilidad(JSONObject jsonObject) {
        try{
            this.disponibilidad = jsonObject.getString("disponibilidad");
            this.comienzo = ((java.sql.Date)jsonObject.get("comienzo")).toLocalDate();
            this.finPrevisto = ((java.sql.Date)jsonObject.get("finalPrevisto")).toLocalDate();
            
        }catch(JSONException ex){
            Logger.getLogger(Disponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LocalDate getComienzo(){
        return comienzo;
    }
    public LocalDate getFinPrevisto(){
        return finPrevisto;
    }
    public String getDisponibilidad(){
        return disponibilidad;
    }
}
