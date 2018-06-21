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
    ArrayList<Factura> facturas = new ArrayList<>();
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

    public String getFacturas(int select, LocalDate fechaInicio, LocalDate fechaFin) {
        
        Proveedor selProv = proveedores.get(select);
        
        JSONArray pedidosPendientes = fachada.getPedidosPendientes(selProv.getCIF());
        
            try {
                
                for (int i = 0; i < pedidosPendientes.length(); i++){
                    
                    PedidoAProveedor ped = new PedidoAProveedor(pedidosPendientes.getJSONObject(i));
                    JSONObject factura = fachada.getFacturas(ped.getNumero(), fechaInicio, fechaFin);
                    
                    if (factura != null){
                        
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
                
                String infI = "--Nº DE PEDIDO: " +pedI.getNumero() +" IMPORTE: " +factI.getImporte() +"€";
                String fechI = " F.REALIZACION: " +pedI.getFecha() +" F.EMISION FACTURA: " +factI.getFecha();
                informe =informe +infI +fechI +"\n";
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

    public String getAllFacturas(LocalDate añoActual, LocalDate añoFinal) {
        String informe = "";
        getProveedores();
        
        for (int i = 0; i < proveedores.size(); i++){
            informe = informe+getFacturas(i, añoActual, añoFinal);
        }
        return informe;
    }
}
    

