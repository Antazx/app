/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.vistas.VistaConsultarFacturas;
import java.time.LocalDate;
import java.util.ArrayList;
import negocio.controladoresCU.CtrlCUConsultarFacturas;

/**
 *
 * @author GUillermo
 */
public class CtrVistaConsultarFacturas {
    
    private VistaConsultarFacturas vista;
    private CtrlCUConsultarFacturas controladorCU;
    
    public CtrVistaConsultarFacturas(VistaConsultarFacturas vista){
        this.vista = vista;
        controladorCU = new CtrlCUConsultarFacturas();
    }
    public void procesaEventoIntroduceDatos() {
        
        LocalDate fechaInicio = vista.getFechaInicio();
        LocalDate fechaFin = vista.getFechaFin();
         
        
        if(fechasCorrectas(fechaInicio, fechaFin)){
           ArrayList<String> proveedores = controladorCU.getProveedores(fechaInicio, fechaFin);
           mostrarProveedores(proveedores);
            
        } else {
            vista.mostrarError("Las fechas introducidas no son validas");
        }
        
    
    }

    private boolean fechasCorrectas(LocalDate fechaInicio, LocalDate fechaFin) {
        boolean ok = false;
        if (fechaFin.isAfter(fechaInicio))
            ok = true;
        return ok;
    }

    public void procesaEventoTodasLasFacturas() {
        LocalDate fechaInicio = LocalDate.of(1602,3,20);
        LocalDate fechaFin = LocalDate.of(9999, 12, 31);
        
        ArrayList<String> proveedores = controladorCU.getProveedores(fechaInicio, fechaFin);
        mostrarProveedores(proveedores);
    }
    public void mostrarProveedores(ArrayList<String> proveedores){
        if(proveedores.size()>0){
            for(int i=0;i<proveedores.size();i++){
            vista.añadirProveedor(proveedores.get(i));
            }
            vista.mostrarProveedores();
        }
        
    }
    
}
