/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelos;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class PedidoAProveedor {
    
    private int numeroPedido;
    private LocalDate fechaDeRealizacion;
    private boolean estaPendiente;
    private Proveedor proveedor;
    
    public PedidoAProveedor(JSONObject pedidoI) {
        if (pedidoI != null){
            try {
                this.numeroPedido = pedidoI.getInt("numeroDePedido");
                this.fechaDeRealizacion = ((java.sql.Date) pedidoI.get("fechaDeRealizacion")).toLocalDate();
                
                if(pedidoI.getBoolean("estaPendiente")){
                    this.estaPendiente = true;
                }else{
                    this.estaPendiente = false;
                }       
            } catch (JSONException ex) {
                Logger.getLogger(PedidoAProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void setProveedor(Proveedor proveedor){
        this.proveedor = proveedor;
    }
}
