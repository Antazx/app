/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelos.Factura;
import negocio.modelos.Proveedor;
import negocio.modelos.PedidoAProveedor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import persistencia.Fachadas.FachadaCUConsultarFacturas;

/**
 *
 * @author GUillermo
 */
public class CtrlCUConsultarFacturas {
    
    FachadaCUConsultarFacturas fachada = FachadaCUConsultarFacturas.getInstance();
    ArrayList<Factura> facturas;
    ArrayList<Proveedor> proveedores = new ArrayList<>();
    

    public String getProveedores(LocalDate fechaInicio, LocalDate fechaFin) {
        
        String proveedoreS = "";
        JSONArray proveedoresJ = fachada.getAllProveedor();
       
            
            try {
                
                for (int i = 0; i < proveedoresJ.length(); i++){
            
                    Proveedor prov = new Proveedor(proveedoresJ.getJSONObject(i));
                    proveedores.add(prov);
                    proveedoreS += prov.getNombre()+";";
                }
                
            } catch (JSONException ex) {
                Logger.getLogger(CtrlCUConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
            }
            return proveedoreS;
        }
        
        
        /*String noHay = "No hay facturas pendientes para el periodo indicado";
        int i;
        JSONArray facturasJ = fachada.getFacturas(fechaInicio, fechaFin);
        System.out.println(facturasJ.toString());
        
        try {
                for (i = 0; i < facturasJ.length(); i++){
                    
                    JSONObject factI = facturasJ.getJSONObject(i);
                    Factura factura = new Factura(factI);
                    int idPedido = factI.getInt("pedido");
                    
                    JSONObject pedidoI = fachada.getPedido(idPedido);
                    if(pedidoI != null){
                        
                        PedidoAProveedor pedido = new PedidoAProveedor(pedidoI);
                        String proveedorS = pedidoI.getString("proveedor");

                        JSONObject proveedorI = fachada.getProveedor(proveedorS);
                        Proveedor proveedor = new Proveedor(proveedorI);

                        pedido.setProveedor(proveedor);
                        factura.setPedido(pedido);
                        facturas.add(factura);
                        
                        proveedores += proveedor.getNombre() +";";
                        
                    } else {
                        
                        return noHay;
                    }
                    
           
                }
        } catch (JSONException ex) {
                Logger.getLogger(CtrlCUConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return proveedores;
    }
        //JSONArray pedidosJ = fachada.getPedidos();
        //JSONArray proveedoresJ = fachada.getProveedores();*/

    public String getFacturas(int select) {
        
        Proveedor selProv = proveedores.get(select);
        System.out.println("CUCUCU: "+selProv.getNombre());
        JSONArray pedidosPendientes = fachada.getPedidosPendientes(selProv.getCIF());
        for (int i = 0; i < pedidosPendientes.length(); i++){
            
        }
        return "";
    }
}
    

