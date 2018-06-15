/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import persistencia.gestores.GestorDisponibilidad;
import persistencia.gestores.GestorEmpleado;
import persistencia.gestores.GestorRol;
import persistencia.gestores.GestorVinculacion;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class FachadaCUIdentificarse {

    
    private static GestorEmpleado gestorEmpleado;
    private static GestorDisponibilidad gestorDisponibilidad;
    private static GestorRol gestorRol;
    private static GestorVinculacion gestorVinculacion;
    
    private static FachadaCUIdentificarse fachada;
  
    public FachadaCUIdentificarse(){
        
        gestorEmpleado = GestorEmpleado.getInstance();
        gestorRol = GestorRol.getInstance();
        gestorVinculacion = GestorVinculacion.getInstance();
        gestorDisponibilidad = GestorDisponibilidad.getInstance();
    }

    public static FachadaCUIdentificarse getInstance() {
        if(fachada == null){ 
            fachada = new FachadaCUIdentificarse();}
           
        return fachada;
    }
    
    public JSONArray getRoles(String dni) {
        return gestorRol.readRol(dni);
    }

    public JSONArray getDisponibilidad(String dni) {
        return gestorDisponibilidad.readDisponibilidades(dni);
    }

    public JSONArray getVinculacion(String dni) {
        return gestorVinculacion.readVinculaciones(dni);
    }

    public JSONObject getEmpleado(String dni) {
        return gestorEmpleado.readEmpleado(dni);
    }
}
