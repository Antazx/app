/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import java.time.LocalDate;
import java.util.ArrayList;
import persistencia.gestores.GestorProveedor;
import negocio.modelos.Proveedor;
import org.json.JSONArray;
import persistencia.Fachadas.FachadaCUConsultarFacturas;

/**
 *
 * @author GUillermo
 */
public class CtrlCUConsultarFacturas {
    
    FachadaCUConsultarFacturas fachada = FachadaCUConsultarFacturas.getInstance();
    ArrayList<Proveedor> proovedores;
    

    public void getProovedores(LocalDate fechaInicio, LocalDate fechaFin) {
        
        JSONArray facturasJ = fachada.getFacturas(fechaInicio, fechaFin);
        System.out.println(facturasJ.toString());
        Factura factura = new Factura(facturasJ);
        //JSONArray pedidosJ = fachada.getPedidos();
        //JSONArray proveedoresJ = fachada.getProveedores();
    }
    
}
