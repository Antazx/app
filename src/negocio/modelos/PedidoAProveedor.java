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
    private Factura factura;
    
    public PedidoAProveedor(JSONObject pedidoI) {
        if (pedidoI != null){
            try {
                this.numeroPedido = pedidoI.getInt("numeroDePedido");
                System.out.println(pedidoI.toString());
                this.fechaDeRealizacion = LocalDate.parse((CharSequence) pedidoI.get("fechaDeRealizacion"));
                
                if(pedidoI.getInt("estaPendiente") == 1){
                    this.estaPendiente = true;
                }else{
                    this.estaPendiente = false;
                }       
            } catch (JSONException ex) {
                Logger.getLogger(PedidoAProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public void setFactura(Factura fact) {
        factura = fact;
    }

    public int getNumero() {
        return numeroPedido;
    }

    public Factura getFactura() {
        return factura;
    }

    public LocalDate getFecha() {
        return fechaDeRealizacion;
    }
}
