/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import negocio.modelos.Proveedor;
import org.json.JSONArray;
import persistencia.FachadaPersistencia;

/**
 *
 * @author GUillermo
 */
public class GestorProveedor {
    
    private static GestorProveedor gestorProovedor;
    private final FachadaPersistencia fachada;
    
    public GestorProveedor(){
        fachada = FachadaPersistencia.getInstance();
    }
    
    public static GestorProveedor getInstance(){
        if(gestorProovedor == null){
            return new GestorProveedor();
        } else {
            return gestorProovedor;
        }
    }

    /*public ArrayList<Proveedor> getProovedores(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<Proveedor> provs = new ArrayList();
        
        //JSONArray consulta = fachada.readProovedores(fechaInicio, fechaFin);
        
        try {
            for(int i = 0; i < consulta.length(); i++){
        
            Proveedor pi = new Proveedor(consulta.getJSONObject(i));
            provs.add(pi);
            
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return provs;
    }*/
}
