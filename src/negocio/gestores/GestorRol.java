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
public class GestorRol {
    
    private static GestorRol gestor;
    
    public GestorRol(){}
    
    public static GestorRol getInstance() {
        if(gestor == null)
            gestor = new GestorRol();
        return gestor;
    }

    public JSONArray readEmpleado(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
