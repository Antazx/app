/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.vistas.VistaModificarLote;
import negocio.controladoresCU.CtrlCUConsultarFacturas;
import negocio.controladoresCU.CtrlCUModificarLote;

/**
 *
 * @author Dani
 */
public class CtrlVistaModificarLote {

    private VistaModificarLote vista;
    private CtrlCUModificarLote controladorCU;
    
    public CtrlVistaModificarLote(VistaModificarLote vista) {
        this.vista = vista;
        controladorCU = new CtrlCUModificarLote();
    }

    public void procesaEventoIntroduceNombrePlanta() {
        String nombre = vista.getNombre();
        boolean ok = controladorCU.comprobarNombre(nombre);
        if(!ok){
            vista.mostrarError("La planta indicada no existe");
        }else{
            vista.mostrarError("La planta indicada SI QUE EXISTE");
        }
    }
}
