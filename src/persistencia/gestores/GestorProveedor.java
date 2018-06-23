/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class GestorProveedor {
    
    private static GestorProveedor gestorProovedor;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorProveedor(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static GestorProveedor getInstance(){
        if(gestorProovedor == null){
            return new GestorProveedor();
        } else {
            return gestorProovedor;
        }
    }

    public JSONObject readProveedor(String proveedorS) {
        JSONObject json = null;
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * from PROVEEDOR where (CIF = ?)");
            stmt.setString(1, proveedorS);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                json = new JSONObject();
                json.put("cif" , rs.getString("CIF"));
                json.put("nombre", rs.getString("NOMBRE"));
                json.put("telefono", rs.getString("TELEFONO"));
                json.put("email", rs.getString("EMAIL"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return json;
    }

    public JSONArray readAllProveedores() {
        JSONArray proveedores = new JSONArray(new ArrayList());
        JSONObject json;
        
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * from PROVEEDOR");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){ 
                
                json = new JSONObject();
                json.put("cif" , rs.getString("CIF"));
                json.put("nombre", rs.getString("NOMBRE"));
                json.put("telefono", rs.getString("TELEFONO"));
                json.put("email", rs.getString("EMAIL"));
                
                proveedores.put(json);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return proveedores;
    }
}
