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
    ArrayList<Proveedor> provedores;
    

    public ArrayList<String> getProveedores(LocalDate fechaInicio, LocalDate fechaFin) {
        
        ArrayList<String> proveedores = new ArrayList();
        int i;
        JSONArray facturasJ = fachada.getFacturas(fechaInicio, fechaFin);
        
        
        try {
                for (i = 0; i < facturasJ.length(); i++){
                    
                    System.out.println(facturasJ.get(i).toString());
                    
                    JSONObject factI = facturasJ.getJSONObject(i);
                    Factura factura = new Factura(factI);
                    int idPedido = factI.getInt("pedido");
                  
                    
                    JSONObject pedidoI = fachada.getPedido(idPedido);
                    if(pedidoI!=null){
                        
                        PedidoAProveedor pedido = new PedidoAProveedor(pedidoI);
                        System.out.println(pedidoI.toString());

                        String proveedorS = pedidoI.getString("proveedor");

                        JSONObject proveedorI = fachada.getProveedor(proveedorS);
                        
                        Proveedor proveedor = new Proveedor(proveedorI);

                        System.out.println(proveedorI.toString());

                        pedido.setProveedor(proveedor);
                        factura.setPedido(pedido);
                        
                        if(!proveedores.contains(proveedor.getNombre()))
                            proveedores.add(proveedor.getNombre());
                        
                    }else{
                        System.out.println("pedido nulo porque no esta pendiente "+ idPedido);
                    }
           
                }
        } catch (JSONException ex) {
                Logger.getLogger(CtrlCUConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return proveedores;
    }
        //JSONArray pedidosJ = fachada.getPedidos();
        //JSONArray proveedoresJ = fachada.getProveedores();
}
    

