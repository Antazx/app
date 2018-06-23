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
    private String codigoPlanta;
    private int idLote;
    private int estimacionNueva;
    
    public CtrlVistaActualizarFlores(VistaActualizarFlores vista) {
        this.vista = vista;
        controladorCU = new CtrlCUActualizarFlores();
    }

    public void procesaEventoComprobarFlores() {
        codigoPlanta = vista.getCodigoPlanta();
        boolean ok = controladorCU.comprobarFlores(codigoPlanta);
    }

    public void procesaEventoMostrarEstimacion() {
        idLote = vista.getIdLote();
        String estimacion = controladorCU.getEstimacionFlores(codigoPlanta, idLote);
        vista.mostrarEstimacion(estimacion);
    }

    public void procesaEventoActualizarEstimacion() {
        estimacionNueva = Integer.parseInt(vista.getEstimacion());
        if(comprobarEstimacion(estimacionNueva)){
            controladorCU.actualizarEstimacion(codigoPlanta, idLote, estimacionNueva);
        }else{
            vista.mostrarError("Las estimacion introducida no es valida");
        }
    }

    private boolean comprobarEstimacion(int estimacionNueva) {
        return estimacionNueva>0;
    }
    
}
