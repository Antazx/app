/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import negocio.modelos.Proveedor;
import org.json.JSONArray;
import persistencia.FachadaPersistencia;

/**
 *
 * @author GUillermo
 */
public class GestorProovedor {
    
    private static GestorProovedor gestorProovedor;
    private final FachadaPersistencia fachada;
    
    public GestorProovedor(){
        fachada = FachadaPersistencia.getInstance();
    }
    
    public static GestorProovedor getInstance(){
        if(gestorProovedor == null){
            return new GestorProovedor();
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
