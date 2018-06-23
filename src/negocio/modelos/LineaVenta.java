/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelos;

import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class LineaVenta {

    private final Producto producto;
    private final Venta venta;
    private final int cantidad;

    public LineaVenta(int cantidad, Venta venta, Producto producto) {
        this.cantidad = cantidad;
        this.venta = venta;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    
}
