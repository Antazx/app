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
public class GestorVinculacion {
    private static GestorVinculacion gestor;
    public GestorVinculacion(){}
    public static GestorVinculacion getInstance() {
        if(gestor == null)
            gestor = new GestorVinculacion();
        return gestor;
    }

    public JSONArray readVinculaciones(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
