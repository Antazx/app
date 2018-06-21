/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.controladores;

import interfaz.vistas.VistaModificarLote;
import java.util.ArrayList;
import negocio.controladoresCU.CtrlCUModificarLote;

/**
 *
 * @author Dani
 */
public class CtrlVistaModificarLote {

    private VistaModificarLote vista;
    private CtrlCUModificarLote controladorCU;
    private ArrayList<String> lotes = new ArrayList<>();
    
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
            lotes = controladorCU.getLotes();
            if(!lotes.isEmpty()){
                mostrarLotes(lotes);
            }else{
                vista.mostrarError("No existe al menos un lote con estado distinto de 'Eliminado' asociado con la planta indicada");
            }
        }
    }

    private void mostrarLotes(ArrayList<String> lotes) {
        for (int i = 0; i < lotes.size()/3; i++) {
            vista.añadirLote(lotes.get(i*3));
        }
        vista.mostrarDatosLote(lotes.get(1),lotes.get(2));
        vista.mostrarLotes();
    }

    public void procesaEventoNuevoLoteSeleccionado() {
        int selectedIndex = vista.getIndexLoteSeleccionado();
        vista.mostrarDatosLote(lotes.get((selectedIndex*3)+1),lotes.get((selectedIndex*3)+2));
    }

    public void procesaEventoNuevoEstadoSeleccionado() {
        int selectedLote = vista.getLoteSeleccionado();
        int selectedEstado = vista.getEstadoSeleccionado();
        
        controladorCU.registrarCambioEstado(selectedLote, selectedEstado);
    }
}
