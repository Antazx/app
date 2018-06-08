/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class Empleado {
    
    private LocalDate fechaIni;
    private String nombre;
    private String dni;
    private String password;
    
    ArrayList<Rol> roles;
    ArrayList<Disponibilidad> disponibilidades;
    ArrayList<VinculacionConLaEmpresa> vinculacionesConLaEmpresa;

    public Empleado(JSONObject js) {
        try{
        
                this.fechaIni = ((java.sql.Date) js.get("fechaIni")).toLocalDate();
                this.nombre = js.getString("nombre");
                this.password = js.getString("password");
                
                JSONArray rolesJSON = js.getJSONArray("roles");
                JSONArray dispJSON = js.getJSONArray("disponibilidades");
                JSONArray vincJSON = js.getJSONArray("vinculaciones");
                
                roles = new ArrayList<Rol>();
                disponibilidades = new ArrayList<Disponibilidad>();
                vinculacionesConLaEmpresa = new ArrayList<VinculacionConLaEmpresa>();
                
                for (int i = 0; i < rolesJSON.length(); i++){
                    
                    Rol rol = new Rol(rolesJSON.getJSONObject(i));
                    roles.add(rol);   
                }
                
                for (int i = 0; i < dispJSON.length(); i++){
                
                    Disponibilidad dis = new Disponibilidad(dispJSON.getJSONObject(i));
                    disponibilidades.add(dis);
                }
                
                for (int i = 0; i < vincJSON.length(); i++){
                
                    VinculacionConLaEmpresa vin = new VinculacionConLaEmpresa(vincJSON.getJSONObject(i));
                    vinculacionesConLaEmpresa.add(vin);
                }
        
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    
}
