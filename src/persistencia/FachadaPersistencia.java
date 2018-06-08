/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author GUillermo
 */
public class FachadaPersistencia {

    private static String dbURL = "jdbc:derby://localhost:1527/DisBD";
    private static Connection conn = null;
    
    public FachadaPersistencia() {
    
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static FachadaPersistencia getInstance() {
        return FachadaPersistenciaHolder.INSTANCE;
    }
    
    public JSONObject readEmpleado(String dni){
        JSONObject json = new JSONObject();
        
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * "
                    + "from EMPLEADO where (NIF = ?)");
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
            
                JSONObject obj;
                JSONObject list;
                
                json.put("nombre" , rs.getString("NOMBRE"));
                json.
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return json;
    }
    
    private static class FachadaPersistenciaHolder {

        private static final FachadaPersistencia INSTANCE = new FachadaPersistencia();
    }
}
