/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Fachadas;

import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;
import persistencia.gestores.GestorFactura;
import persistencia.gestores.GestorPedido;
import persistencia.gestores.GestorProveedor;

/**
 *
 * @author GUillermo
 */
public class FachadaCUConsultarFacturas {
    
    private static FachadaCUConsultarFacturas fachada;
    private final GestorFactura gestorFacturas;
    private final GestorPedido gestorPedidos;
    private final GestorProveedor gestorProveedor;
            
    
    public FachadaCUConsultarFacturas() {
        
        gestorFacturas = GestorFactura.getInstance();
        gestorPedidos = GestorPedido.getInstance();
        gestorProveedor = GestorProveedor.getInstance();
        
    }
    public static FachadaCUConsultarFacturas getInstance() {
        if(fachada == null){
            fachada = new FachadaCUConsultarFacturas();
        } 
        return fachada;
    }

    public JSONArray getFacturas(LocalDate fechaInicio, LocalDate fechaFin) {
        return gestorFacturas.readFacturas(fechaInicio, fechaFin);
    }
    
    public JSONObject getPedido(int idPedido) {
        return gestorPedidos.readPedido(idPedido);
    }

    public JSONObject getProveedor(String proveedorS) {
        return gestorProveedor.readProveedor(proveedorS);
    }

    public JSONArray getAllProveedor() {
        return gestorProveedor.readAllProveedores();
    }

    public JSONArray getPedidosPendientes(String cif) {
        return gestorPedidos.getPedidosPendientes(cif);
    }
    
}
