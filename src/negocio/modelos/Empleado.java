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
        
        if(js != null){
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
    public String getRolActual(){
        /*String tipoRol = "";
        LocalDate fechaProxima;
        
        Rol rol;
        ArrayList<Rol> rolesComp = this.getRoles();
        
        rol = rolesComp.get(0);
        fechaProxima = rol.getComienzoEnRol();
        for (int i = 0; i < rolesComp.size(); i++){
            rol = rolesComp.get(i);
            if(rol.getComienzoEnRol().isAfter(fechaProxima)){
                tipoRol = rol.getTipoRol();
                fechaProxima = rol.getComienzoEnRol();
            }
        }*/
        
        return "Supervisor";
    }
    
    public String getVinculacionActual(){
        
       String tipoVin = "";
       LocalDate fechaProxima;
       
       VinculacionConLaEmpresa vin;
       ArrayList<VinculacionConLaEmpresa> vinComp= this.getVinculaciones();
       
       vin = vinComp.get(0);
       fechaProxima = vin.getFechaInicio();
       
       for(int i = 0; i < vinComp.size(); i++){
           vin = vinComp.get(i);
           if(vin.getFechaInicio().isAfter(fechaProxima)){
               tipoVin = vin.getVinculacion();
               fechaProxima = vin.getFechaInicio();
           }
       }
        System.out.println("VINCULACION ACTUAL ----->" +tipoVin);
       return tipoVin;
    }
    public String getDisponibilidadActual(){
        /*String tipoDisp = "";
        LocalDate fechaProxima;
        Disponibilidad disp;
        ArrayList<Disponibilidad> dispComp = this.getDisponibilidades();
        
        disp = dispComp.get(0);
        fechaProxima = disp.get
        for (int i = 0; i < disponibilidades.size(); i++){
            disp = 
        }*/
        return "Trabajando";
    }
   
    public String getPassword() {
        return password;
    }
    
    public ArrayList<VinculacionConLaEmpresa> getVinculaciones(){
        return vinculacionesConLaEmpresa;
    }
    public ArrayList<Disponibilidad> getDisponibilidades(){
        return disponibilidades;
    }
    public ArrayList<Rol> getRoles(){
        return roles;
    }

    public void addRol(Rol rolJ) {
        roles.add(rolJ);
    }

    public void addDisponibilidad(Disponibilidad disponibilidadJ) {
        disponibilidades.add(disponibilidadJ);
    }

    public void addVinculacion(VinculacionConLaEmpresa vinculacionJ) {
        vinculacionesConLaEmpresa.add(vinculacionJ);
    }
    
    
}
