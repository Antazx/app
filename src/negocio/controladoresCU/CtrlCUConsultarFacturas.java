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
    

    public String getProveedores() {
        
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

    public String getFacturas(int select, LocalDate fechaInicio, LocalDate fechaFin) {
        
        Proveedor selProv = proveedores.get(select);
        System.out.println("PROVEEDOR SELECCIONADO: " +selProv.getNombre());
        
        JSONArray pedidosPendientes = fachada.getPedidosPendientes(selProv.getCIF());
        
            try {
                
                for (int i = 0; i < pedidosPendientes.length(); i++){
                    
                    PedidoAProveedor ped = new PedidoAProveedor(pedidosPendientes.getJSONObject(i));
                    System.out.println("PEDIDO ENCONTRADO: " +ped.getNumero());
                    
                    JSONObject factura = fachada.getFacturas(ped.getNumero(), fechaInicio, fechaFin);
                    
                    if (factura != null){
                        System.out.println("FACTURA ENCONTRADA: " +factura.toString());
                        Factura fact = new Factura(factura);
                        ped.setFactura(fact);
                    }
                    selProv.setPedidoPendiente(ped);
                }
                
            } catch (JSONException ex) {
                Logger.getLogger(CtrlCUConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
            }
        String informe = "INFORME FACTURAS DEL PROVEEDOR: " +selProv.getNombre() +" (" +fechaInicio.toString()
                +" -- " +fechaFin.toString() +"):\n";
        
        ArrayList<PedidoAProveedor> pedidosP = selProv.getPedidos();
        for (int i = 0; i < pedidosP.size(); i++){
            
            PedidoAProveedor pedI= pedidosP.get(i);
            Factura factI = pedI.getFactura();
            
            if(factI != null && fechasFactura(factI.getFecha(), fechaInicio, fechaFin)){
                System.out.println("HAY FACTURAS EN ESA FECHA !!!!");
                String infI = "--NUMERO DE PEDIDO: " +pedI.getNumero() +" CON IMPORTE: " +factI.getImporte() +"€";
                String fechI = "-----FECHA DE REALIZACION: " +pedI.getFecha() +" FECHA DE EMISION FACTURA: " +factI.getFecha();
                informe =informe +infI +fechI +"/n";
            }
        }
        
        return informe;
    }

    private boolean fechasFactura(LocalDate fechaFactura, LocalDate fechaInicio, LocalDate fechaFin) {
        if((fechaInicio.isBefore(fechaFactura) || fechaInicio.isEqual(fechaFactura)) 
                && (fechaFin.isAfter(fechaFactura)|| fechaFin.isEqual(fechaFactura))){
            return true;
        }else{
            return false;
        }
    }
}
    

