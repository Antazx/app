/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladoresCU;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelos.Venta;
import negocio.modelos.LineaVenta;
import negocio.modelos.Producto;
import org.json.JSONException;
import org.json.JSONObject;
import persistencia.Fachadas.FachadaCURegistrarVenta;

/**
 *
 * @author GUillermo
 */
public class CtrlCURegistrarVista {
    
    private final FachadaCURegistrarVenta fachada = FachadaCURegistrarVenta.getInstance();
    private Producto producto;
    private Venta venta;
    private LineaVenta lVenta;
    private float total;
    
    public boolean comprobarCodigo(String codigo) {
        
        JSONObject productoJ = fachada.getProducto(codigo);
        
        if(productoJ != null){
            producto = new Producto(productoJ);
            return true;
        } else {
            return false;
        }
    }

    public String registrarLineaVenta(int cantidad) {
        
        String respuesta  = "";
        //String dni = empleado.getDNI();
        LocalDate fecha = LocalDate.now();
        
        if (venta == null){
            int idVenta = fachada.creaNuevaVenta(fecha.toString(), "11111111A");
            venta = new Venta(idVenta);
        }
        
        
        lVenta = new LineaVenta(cantidad, venta, producto);
        JSONObject lVentaJ = new JSONObject();
        try {
            
            Producto p = lVenta.getProducto();
            int existencias = p.getExistencias();
            
            if (existencias > 0 && existencias >= cantidad){
                
                float precio = p.getPrecioVenta();
                float subtotal = precio*cantidad;
                total += subtotal;
                respuesta += p.getNombre() +" " +precio +" " +subtotal +" " +total;
            
                lVentaJ.put("cantidad", lVenta.getCantidad());
                lVentaJ.put("producto", p.getCodigo());
                lVentaJ.put("venta", venta.getID());
            
                venta.setLineaVenta(lVenta);
                fachada.registrarVenta(lVentaJ);
                fachada.actualizarProducto(p.getCodigo(), existencias - cantidad);
                return respuesta;
                
            } else {
                return "";
            }
            
            
        } catch (JSONException ex) {
            Logger.getLogger(CtrlCURegistrarVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void cancelarLineasVenta() {
        total = 0;
        int vent = venta.getID();
        fachada.borrarLineasVenta(vent);
        
    }
}
