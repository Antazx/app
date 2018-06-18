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
public class Factura {
    
    private int idFactura;
    private LocalDate fechaDeEmision;
    private double importe;
    private String cuentaBancaria;
    private PedidoAProveedor pedido;
    
    private int entransFerencia;
    
    public Factura(JSONObject facturasJ) {
      
        if(facturasJ != null){
            try {
                this.idFactura = facturasJ.getInt("id");
                this.fechaDeEmision = ((java.sql.Date) facturasJ.get("fechaDeEmision")).toLocalDate();
                this.cuentaBancaria = facturasJ.getString("cuentaBancaria");
                this.importe = facturasJ.getDouble("importe");
                
            
            } catch (JSONException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    public void setPedido(PedidoAProveedor pedido) {
        this.pedido = pedido;
    }

    public double getImporte() {
        return importe;
    }

    public LocalDate getFecha() {
        return fechaDeEmision;
    }
}
