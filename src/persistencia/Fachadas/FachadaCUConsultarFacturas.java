/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import persistencia.gestores.GestorFactura;
import persistencia.gestores.GestorPedido;
import persistencia.gestores.GestorProveedor;

/**
 *
 * @author GUillermo
 */
public class FachadaCUConsultarFacturas {
    
    private static FachadaCUConsultarFacturas fachada;
    private GestorFactura gestorFacturas;
    private GestorPedido gestorPedidos;
    private GestorProveedor gestorProveedor;
            
    
    public FachadaCUConsultarFacturas() {
        
    }
    public static FachadaCUConsultarFacturas getInstance() {
        if(fachada == null) new FachadaCUConsultarFacturas();
        return fachada;
    }
    
}
