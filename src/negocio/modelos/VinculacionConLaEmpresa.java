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
public class VinculacionConLaEmpresa {
    private String vinculo;
    private LocalDate inicio;
    
    VinculacionConLaEmpresa(JSONObject jsonObject) {
       try{
           this.vinculo = jsonObject.getString("vinculo");
           this.inicio = ((java.sql.Date)jsonObject.get("inicio")).toLocalDate();
       } catch (JSONException ex) {
            Logger.getLogger(VinculacionConLaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
