/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.vistas.VistaActualizarFlores;
import negocio.controladoresCU.CtrlCUActualizarFlores;

/**
 *
 * @author Dani
 */
public class CtrlVistaActualizarFlores {

    private final VistaActualizarFlores vista;
    private final CtrlCUActualizarFlores controladorCU;
    
    public CtrlVistaActualizarFlores(VistaActualizarFlores vista) {
        this.vista = vista;
        controladorCU = new CtrlCUActualizarFlores();
    }

    public void procesaEventoComprobarFlores() {
        String codigoPlanta = vista.getCodigoPlanta();
        boolean ok = controladorCU.comprobarFlores(codigoPlanta);
    }
    
}
