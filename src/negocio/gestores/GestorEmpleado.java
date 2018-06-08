/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.gestores;

import java.util.ArrayList;
import negocio.modelos.Disponibilidad;
import negocio.modelos.Empleado;
import negocio.modelos.Rol;
import negocio.modelos.VinculacionConLaEmpresa;
import org.json.JSONObject;
import persistencia.FachadaPersistencia;

/**
 *
 * @author GUillermo
 */
public class GestorEmpleado {
    
    private static GestorEmpleado gestorEmpleado;
    private final FachadaPersistencia fachada;
    
    public GestorEmpleado(){
        fachada = FachadaPersistencia.getInstance();
    }
    
    public static GestorEmpleado getInstance(){
        if(gestorEmpleado == null){
            return new GestorEmpleado();
        }else {
            return gestorEmpleado;
        }
    }
    
    public Empleado getEmpleado(JSONObject js){
        return new Empleado (js);
    }
    public Empleado getEmpleado(String dni){
        
        Empleado empleado = null;
        JSONObject consulta;
        
        ArrayList<Rol> roles;
        ArrayList<Disponibilidad> disponibilidades;
        ArrayList<VinculacionConLaEmpresa> vinculaciones;
        
        return empleado;
    }
}
