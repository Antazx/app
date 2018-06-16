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
                roles = new ArrayList<>();
                disponibilidades = new ArrayList<>();
                vinculacionesConLaEmpresa = new ArrayList<>();
        
            }catch (Exception e){
                e.printStackTrace();
            }
        } 
    }
    public String getRolActual(){
        String tipoRol = "";
        LocalDate fechaProxima;
        
        Rol rol;
        ArrayList<Rol> rolesComp = this.getRoles();
 
        rol = rolesComp.get(0);
        fechaProxima = rol.getComienzoEnRol();
        tipoRol = rol.getTipoRol();
       
        for (int i = 0; i < rolesComp.size(); i++){
            rol = rolesComp.get(i);
            if(rol.getComienzoEnRol().isAfter(fechaProxima)){
                    tipoRol = rol.getTipoRol();
                    fechaProxima = rol.getComienzoEnRol();         
            }
        }
        
        return tipoRol;
    }
    
    public String getVinculacionActual(){
        
       String tipoVin = "";
       LocalDate fechaProxima;
       
       VinculacionConLaEmpresa vin;
       ArrayList<VinculacionConLaEmpresa> vinComp= this.getVinculaciones();
       
       vin = vinComp.get(0);
       fechaProxima = vin.getFechaInicio();
       tipoVin = vin.getVinculacion();
       

       for(int i = 0; i < vinComp.size(); i++){
           vin = vinComp.get(i);
           if(vin.getFechaInicio().isAfter(fechaProxima)){
               tipoVin = vin.getVinculacion();
               fechaProxima = vin.getFechaInicio();
           }
       }
 
       return tipoVin;
    }
    public String getDisponibilidadActual(){
        String tipoDisp = "";
        LocalDate fechaProxima;
        Disponibilidad disp;
        ArrayList<Disponibilidad> dispComp = this.getDisponibilidades();
        
        disp = dispComp.get(0);
        fechaProxima = disp.getComienzo();
        tipoDisp = disp.getDisponibilidad();
        for (int i = 0; i < dispComp.size(); i++){
            disp = dispComp.get(i);
            if(disp.getComienzo().isAfter(fechaProxima)){
               tipoDisp= disp.getDisponibilidad();
               fechaProxima = disp.getComienzo();
           }
        }
        return tipoDisp;
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
