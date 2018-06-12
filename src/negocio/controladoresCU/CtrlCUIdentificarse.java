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
    private GestorEmpleado gestor = GestorEmpleado.getInstance();

    public String identificarEmpleado(String dni, String pass) {
        
       empleado = gestor.getEmpleado(dni);
       
       String pasw = empleado.getPassword();
       String vinculacion = empleado.getVinculacionActual();
       String disponibilidad = empleado.getDisponibilidadActual();
       String rol = empleado.getRolActual();
       String respuesta ="";
       
       if (empleado != null){
           if(pasw.equals(pass)){
                if(vinculacion.equals("Contratado")
                        && disponibilidad.equals("Trabajando")){
                        return (respuesta = empleado.getRolActual());
                }else{
                    System.out.println("NoActivo" +vinculacion +" " +disponibilidad);
                    return (respuesta = "Usuario Inactivo");
                }
           }else{
              
               return (respuesta = "La contraseña no es correcta");
           }
       }else{
           System.out.println("NoExiste");
           return (respuesta = "No existe el usuario");
       }
    }
}
