/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelos;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Dani
 */
public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int existencias;
    private int cantidadNecesaria;
    private float precioDeVenta;
    private float precioCompra;
    private int diasParaEntregaDelProveedor;
    private String tipoDeProductoAuxiliar;
    private String subtipo;
    private Producto plantaDeLaFlor;
    
    public Producto(JSONObject plantaJ) {
      
        if(plantaJ != null){
            try {

                this.codigo = plantaJ.getString("codigo");
                this.nombre = plantaJ.getString("nombre");
                this.descripcion = plantaJ.getString("descripcion");
                this.existencias = plantaJ.getInt("existencias");
                this.cantidadNecesaria = plantaJ.getInt("cantidad");
                this.precioDeVenta = (float) plantaJ.getDouble("precioVenta");
                this.precioCompra = (float) plantaJ.getDouble("precioCompra");
                this.diasParaEntregaDelProveedor = plantaJ.getInt("diasEntrega");
                this.tipoDeProductoAuxiliar = plantaJ.getString("tipoAuxiliar");
                this.subtipo = plantaJ.getString("subtipo");
                
            
            } catch (JSONException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    public void setPlantaDeLaFlor(Producto plantaDeLaFlor) {
        this.plantaDeLaFlor = plantaDeLaFlor;
    }
    
    public String getCodigo() {
        return codigo;
    }
    public String getSubtipo() {
        return subtipo;
    }
    public String getNombre() {
        return nombre;
    }

    public float getPrecioVenta() {
        return precioDeVenta;
    }

    public int getExistencias() {
        return existencias;
    }
}