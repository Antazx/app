/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import java.time.LocalDate;
import java.util.ArrayList;
import negocio.gestores.GestorProovedor;
import negocio.modelos.Proveedor;

/**
 *
 * @author GUillermo
 */
public class CtrlCUConsultarFacturas {
    
    GestorProovedor gestor = GestorProovedor.getInstance();
    ArrayList<Proveedor> proovedores;
    

    public void getProovedores(LocalDate fechaInicio, LocalDate fechaFin) {
        
        //proovedores = gestor.getProovedores(fechaInicio, fechaFin);
    }
    
}
