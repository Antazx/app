/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.gestores;

import org.json.JSONArray;

/**
 *
 * @author GUillermo
 */
public class GestorDisponibilidad {
    
    private static GestorDisponibilidad gestor;
    
    public GestorDisponibilidad(){
        
    }
    public static GestorDisponibilidad getInstance() {
        if(gestor == null){gestor = new GestorDisponibilidad();}
            
        return gestor;
    }

    public JSONArray readDisponibilidades(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
