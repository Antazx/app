/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import negocio.gestores.GestorEmpleado;
import negocio.modelos.Empleado;


/**
 *
 * @author GUillermo
 */
public class CtrlCUIdentificarse {
    
    private Empleado empleado;


    public String identificarEmpleado(String dni, String pass) {
        
       GestorEmpleado gestor = GestorEmpleado.getInstance();
       empleado = gestor.getEmpleado(dni);
       
       System.out.println(empleado.toString());
       
       String pasw = empleado.getPassword();
       String vinculacion = empleado.getVinculacionActual();
       String disponibilidad = empleado.getDisponibilidadActual();
       String rol = empleado.getRolActual();
       
       
       if (empleado != null){
           if(empleado.getPassword().equals(pass)){
                if(empleado.getVinculacionActual().equals("Contratado")
                        && empleado.getDisponibilidadActual().equals("Trabajando")){
                        return empleado.getRolActual();
                }else{
                    System.out.println("NoActivo" +empleado.getVinculacionActual() +" " +empleado.getDisponibilidadActual());
                    return "NoActivo";
                }
           }else{
               System.out.println("Contraseña Incorrecta");
               return "PassIncorrecta";
           }
       }else{
           System.out.println("NoExiste");
           return "NoExiste";
       }
    }
}
