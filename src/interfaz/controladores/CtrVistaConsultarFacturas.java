/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.vistas.VistaConsultarFacturas;
import java.time.LocalDate;
import negocio.controladoresCU.CtrlCUConsultarFacturas;

/**
 *
 * @author GUillermo
 */
public class CtrVistaConsultarFacturas {
    
    private VistaConsultarFacturas vista;
    private CtrlCUConsultarFacturas controladorCU;
    private String[] proveedoresList;
    
    public CtrVistaConsultarFacturas(VistaConsultarFacturas vista){
        this.vista = vista;
        controladorCU = new CtrlCUConsultarFacturas();
    }
    public void procesaEventoIntroduceDatos() {
        
        LocalDate fechaInicio = vista.getFechaInicio();
        LocalDate fechaFin = vista.getFechaFin();
        
        System.out.println("FechaInicio: " +fechaInicio.toString() +" fechafin: " +fechaFin);
        
        String[] proveedorI;
        
        if(fechasCorrectas(fechaInicio, fechaFin)){
            
            String proveedores = controladorCU.getProveedores(fechaInicio, fechaFin);
            proveedoresList = proveedores.split(";");
            
            for (String proveedorI1 : proveedoresList) {
                vista.mostrarProveedor(proveedorI1);
            }
            
        } else {
            vista.mostrarError("Las fechas introducidas no son validas");
        }
    }

    private boolean fechasCorrectas(LocalDate fechaInicio, LocalDate fechaFin) {
        boolean ok = false;
        if (fechaFin.isBefore(fechaInicio)){
                System.out.println("FEchaOK");
                ok = true;
        }
        return ok;
    }

    public void procesaEventoProveedorSel() {
        int nombre = vista.getNombreProveedor();
        
        String datosFactura = controladorCU.getFacturas(nombre);
        
    }
    
}
