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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class GestorFactura {
    
    private static GestorFactura gestorFactura;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorFactura () {
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static GestorFactura getInstance(){
        if(gestorFactura == null){
            return new GestorFactura();
        }else {
            return gestorFactura;
        }
    }
    public JSONArray readFacturas(LocalDate fechaInicio, LocalDate fechaFin) {
        
        JSONArray list = new JSONArray();
        JSONObject json;
        
        try {
            
            PreparedStatement stmt = conn.prepareStatement("select * from FACTURA where (FECHADEEMISION >= ?) and (FECHADEEMISION <= ?)");
            stmt.setString(1, fechaInicio.toString());
            stmt.setString(2, fechaFin.toString());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                json = new JSONObject();
                json.put("id", rs.getInt("ID"));
                json.put("fechaDeEmision", rs.getDate("FECHADEEMISION"));
                json.put("importe", rs.getDouble("IMPORTE"));
                json.put("cuentaBancaria", rs.getString("CUENTABANCARIA"));
                json.put("pedido", rs.getInt("PEDIDO"));
                json.put("enTransferencia", rs.getInt("ENTRANSFERENCIA"));
                
                list.put(json);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GestorFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list; 
    }

    public JSONObject readFacturas(int select, LocalDate fechaInicio, LocalDate fechaFin) {
        
        JSONObject json = null;
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * from FACTURA where PEDIDO = 1");
            //stmt.setString(1, Integer.toString(select));
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                System.out.println("ENTRA GESTOR FACTURA");
                json = new JSONObject();
                json.put("id", rs.getInt("ID"));
                json.put("fechaDeEmision", rs.getDate("FECHADEEMISION"));
                json.put("importe", rs.getDouble("IMPORTE"));
                json.put("cuentaBancaria", rs.getString("CUENTABANCARIA"));
                json.put("pedido", rs.getInt("PEDIDO"));
                json.put("enTransferencia", rs.getInt("ENTRANSFERENCIA"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(json.toString());
        return json;
    }
    
}
