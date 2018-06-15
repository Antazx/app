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
 * @author GUillermo
 */
public class Proveedor {
    
    private String cif;
    private String nombre;
    private String telefono;
    private String email;
    
    public Proveedor(JSONObject js){
        if(js != null){
            
            try {
                this.cif = js.getString("cif");
                this.email = js.getString("email");
                this.telefono = js.getString("telefono");
                this.nombre = js.getString("nombre");
            } catch (JSONException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public String getNombre() {
        return nombre;
    }
    
}
