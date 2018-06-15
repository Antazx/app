/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import negocio.modelos.Disponibilidad;
import negocio.modelos.Empleado;
import negocio.modelos.Rol;
import negocio.modelos.VinculacionConLaEmpresa;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import persistencia.FachadaCUIdentificarse;


/**
 *
 * @author GUillermo
 */
public class CtrlCUIdentificarse {
    
    private Empleado empleadoJ;
    private Rol rolJ;
    private Disponibilidad disponibilidadJ;
    private VinculacionConLaEmpresa vinculacionJ;
    private final FachadaCUIdentificarse fachada = FachadaCUIdentificarse.getInstance();

    public String identificarEmpleado(String dni, String pass){
        
        int i;
        String respuesta ="";
        JSONObject empleado = fachada.getEmpleado(dni);
        
        try {
            if(empleado != null){
            
            empleadoJ = new Empleado(empleado);
            JSONArray rol = fachada.getRoles(dni);
                
            if(rol != null){
                for (i = 0; i < rol.length(); i++) {
                    
                    rolJ = new Rol(rol.getJSONObject(i));
                    empleadoJ.addRol(rolJ);
                }
            }
         
            JSONArray disponibilidad = fachada.getDisponibilidad(dni);
            
            if(disponibilidad != null){
                for (i = 0; i < disponibilidad.length(); i++){
                    disponibilidadJ = new Disponibilidad(disponibilidad.getJSONObject(i));
                    empleadoJ.addDisponibilidad(disponibilidadJ);
                }
            }
            
            JSONArray vinculacion = fachada.getVinculacion(dni);
            
            if(vinculacion != null){
                for (i = 0; i < vinculacion.length(); i++){
                    vinculacionJ = new VinculacionConLaEmpresa(vinculacion.getJSONObject(i));
                    empleadoJ.addVinculacion(vinculacionJ);
                }
            }
            
              
            }else{
            
                return (respuesta = "No existe el usuario");
             }
        } catch (Exception e){
            
            e.printStackTrace();
        }
 
        String paswS = empleadoJ.getPassword();
        String vinculacionS = empleadoJ.getVinculacionActual();
        String disponibilidadS = empleadoJ.getDisponibilidadActual();
        String rolS = empleadoJ.getRolActual();
        
        System.out.println(empleado.toString() +vinculacionS +disponibilidadS +rolS);
        
        if(paswS.equals(pass)){
                if(vinculacionS.equals("Contratado")&& disponibilidadS.equals("Trabajando")){
                        return (respuesta = rolS);
                }else{
                    System.out.println("NoActivo" +vinculacionS +" " +disponibilidadS);
                    return (respuesta = "Usuario Inactivo");
                }
            }else{
               return (respuesta = "La contraseña no es correcta");
            }
    }
}
