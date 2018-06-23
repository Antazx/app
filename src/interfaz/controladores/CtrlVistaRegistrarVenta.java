/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.vistas.VistaRegistrarVenta;
import negocio.controladoresCU.CtrlCURegistrarVista;

/**
 *
 * @author GUillermo
 */
public class CtrlVistaRegistrarVenta {
    
    private final VistaRegistrarVenta vista;
    private final CtrlCURegistrarVista controladorCU;
    
    public CtrlVistaRegistrarVenta(VistaRegistrarVenta aThis) {
        this.vista = aThis;
        this.controladorCU = new CtrlCURegistrarVista();
    }

    public void procesaEventoRegistrar() {
        
        String codigo = vista.getCodigo();
        int cantidad = vista.getCantidad();
        
        System.out.println("CANTIDAD " +cantidad);
        System.out.println("CODIGO " +codigo);
        
        boolean okCantidad = comprobarCantidad(cantidad);
        boolean okCodigo = controladorCU.comprobarCodigo(codigo);
        
        if(okCantidad){
            if(okCodigo){
                String respuesta = controladorCU.registrarLineaVenta(cantidad);
                if(respuesta.equals("")){
                    vista.mostrarError("No hay suficientes existencias");
                }else{
                    vista.mostrarRespuesta(respuesta);
                }
                
            }else{
                vista.mostrarError("El codigo de producto no existe");
            }
        }else{
            vista.mostrarError("La cantidad introcucida no es valida");
        }
    } 

    private boolean comprobarCantidad(int cantidad) {
        if(cantidad < 1)
            return false;
        return true;
    }

    public void cancelarLineasVenta() {
        controladorCU.cancelarLineasVenta();
    }
}
