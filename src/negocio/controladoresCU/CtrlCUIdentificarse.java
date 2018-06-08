/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import negocio.gestores.GestorEmpleado;
import negocio.modelos.Empleado;
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class CtrlCUIdentificarse {
    
    private Empleado empleado;
    
    public Empleado getEmpleado(String dni){
        GestorEmpleado gestor = GestorEmpleado.getInstance();
        empleado = gestor.getEmpleado(dni);
        System.out.println(empleado.toString());
        return empleado;
    }
}
