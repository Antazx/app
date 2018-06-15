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
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class GestorEmpleado {
    
    private static GestorEmpleado gestorEmpleado;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorEmpleado(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static GestorEmpleado getInstance(){
        if(gestorEmpleado == null){
            return new GestorEmpleado();
        }else {
            return gestorEmpleado;
        }
    }

    public JSONObject readEmpleado(String dni) {
        
        JSONObject json = null;
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * from EMPLEADO where (NIF = ?)");
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                json = new JSONObject();
                json.put("nombre" , rs.getString("NOMBRE"));
                json.put("fechaIni", rs.getDate("FECHAINICIOENEMPRESA"));
                json.put("password", rs.getString("PASSWORD"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return json;
    }
}
