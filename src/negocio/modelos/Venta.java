/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelos;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author GUillermo
 */
public class Venta {
    
    private final int idVenta;
    private LocalDate fechaVenta;
    private Empleado empleado;
    private ArrayList<LineaVenta> lineaDeVenta = new ArrayList<>();
    
    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta){
        this.fechaVenta = fechaVenta;
    }
    public void setEmpleado(Empleado e){
        this.empleado = e;
    }

    public int getID() {
        return idVenta;
    }

    public void setLineaVenta(LineaVenta lVenta) {
        lineaDeVenta.add(lVenta);
    }
    
}
