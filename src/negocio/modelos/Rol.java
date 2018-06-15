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
public class Rol {
    
    private String tipoRol;
    private LocalDate comienzoEnRol;
    
    public Rol(JSONObject rol) throws JSONException {
        
        this.tipoRol = rol.getString("tipoRol");
        this.comienzoEnRol = ((java.sql.Date) rol.get("comienzoEnRol")).toLocalDate();
    }
    
    public String getTipoRol(){
        return tipoRol;
    }
    
    public LocalDate getComienzoEnRol(){
        return comienzoEnRol;
    }
    
}
