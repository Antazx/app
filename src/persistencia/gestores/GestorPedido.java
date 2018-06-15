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
public class GestorPedido {
    
    private static GestorPedido gestorPedido;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorPedido() {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static GestorPedido getInstance() {
        
        if(gestorPedido == null){
            return new GestorPedido();
        }else{
            return gestorPedido;
        }
    } 

    public JSONObject readPedido(int idPedido) {
        JSONObject json = null;
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * from PEDIDOAPROVEEDOR where (NUMERODEPEDIDO = ?) and (ESTAPENDIENTE = '1')");
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                json = new JSONObject();
                json.put("numeroDePedido", rs.getInt("NUMERODEPEDIDO"));
                json.put("fechaDeRealizacion" , rs.getDate("FECHADEREALIZACION"));
                json.put("estaPendiente", rs.getInt("ESTAPENDIENTE"));
                json.put("proveedor", rs.getString("PROVEEDOR"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return json;
    }
}
