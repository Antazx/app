/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.vistas.VistaConsultarFacturas;
import java.time.LocalDate;
import java.time.Month;
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
        
        if(fechasCorrectas(fechaInicio, fechaFin)){
            
            String proveedores = controladorCU.getProveedores();
            proveedoresList = proveedores.split(";");
            
            for (String proveedorI1 : proveedoresList) {
                vista.mostrarProveedor(proveedorI1);
            }
            
        } else {
            vista.mostrarError("Las fechas introducidas no son validas");
        }
    }

    private boolean fechasCorrectas(LocalDate fechaInicio, LocalDate fechaFin) {
     
        if (fechaFin.isAfter(fechaInicio) || fechaInicio.isEqual(fechaFin)){
            return true;
        }else{
            return false;
        }
    }

    public void procesaEventoProveedorSel() {
        
        int nombre = vista.getNombreProveedor();
        
        LocalDate fechaInicio = vista.getFechaInicio();
        LocalDate fechaFin = vista.getFechaFin();
        
        if(fechasCorrectas(fechaInicio, fechaFin)){
            
            String datosFactura = controladorCU.getFacturas(nombre, fechaInicio, fechaFin);
            vista.mostraFacturas(datosFactura);
        } else {
            
            vista.mostrarError("Las fechas introducidas no son validas");
        } 
    }

    public void todasLasFacturas() {
        
        LocalDate actual = LocalDate.now();
        LocalDate añoActual = LocalDate.of(actual.getYear(), Month.JANUARY, 1);
        LocalDate añoFinal = LocalDate.of(actual.getYear(), Month.DECEMBER, 31);
        
        String datosFactuas = controladorCU.getAllFacturas(añoActual, añoFinal);
        vista.mostraFacturas(datosFactuas);
    }

    public void todosLosProveedores() {
        
        LocalDate añoInicio = vista.getFechaInicio();
        LocalDate añoFinal = vista.getFechaFin();
        
        String datosFacturas = controladorCU.getAllFacturas(añoInicio, añoFinal);
        vista.mostraFacturas(datosFacturas);
        
    }
}
