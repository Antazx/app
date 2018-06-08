/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.gestores;

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
}
