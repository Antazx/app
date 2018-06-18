/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.gestor.GestorInterfaces;
import interfaz.vistas.VistaIdentificarse;
import negocio.controladoresCU.CtrlCUIdentificarse;


/**
 *
 * @author GUillermo
 */
public class CtrlVistaIdentificarse {
    
    private VistaIdentificarse vista;
    private CtrlCUIdentificarse controladorCU;
    
    public CtrlVistaIdentificarse(VistaIdentificarse vista){
        this.vista = vista;
        controladorCU = new CtrlCUIdentificarse();
    }
    
    public void procesaEventoIntroduceDatos(){
        
        String dni = vista.getDni();
        String pass = vista.getPassword();
        String respuesta = "";               
        
        if(compruebaDatos(dni)){
            respuesta = controladorCU.identificarEmpleado(dni,pass);
            switch(respuesta){
                case "No existe el usuario":
                    vista.mostrarError(respuesta);
                    break;
                case "La contraseña no es correcta":
                    vista.mostrarError(respuesta);
                    break;
                case "Usuario Inactivo":
                    vista.mostrarError(respuesta);
                    break;
                default:
                    GestorInterfaces.getInstancia().empleadoIdentificado(respuesta);
                    break;
            }
        } else {
            vista.mostrarError("El DNI no es valido");
        }
    }
    
    public boolean compruebaDatos(String dni){
        boolean ok = false;
        
        if (dni.length() == 9){
            if(Character.isLetter(dni.charAt(8))){
                String letra = dni.substring(8).toUpperCase();
                if(onlyNums(dni)){
                    ok = true;
                }
            }
        }
        return ok;
    }

    private boolean onlyNums(String dni) {
        int i, j = 0;
        String num = "";
        String let = "";
        String dniOk = "";
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        
        for (i = 0; i < dni.length() - 1; i++){
        
            num = dni.substring(i, i+1);
            for (j = 0; j < nums.length; j++){
                if(num.equals(nums[j])){
                    dniOk += nums[j];
                }
            }
        }
        if(dniOk.length() == 8){
            return true;
        }else{
            return false;
        }
    }
}
