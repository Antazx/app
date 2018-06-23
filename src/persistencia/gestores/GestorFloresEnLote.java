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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Dani
 */
public class GestorFloresEnLote {

    private static GestorFloresEnLote gestorFloresEnLote;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorFloresEnLote() {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
        }
    }
    
    public static GestorFloresEnLote getInstance() {
        if(gestorFloresEnLote == null){
            return new GestorFloresEnLote();
        }else{
            return gestorFloresEnLote;
        }
    }

    public JSONObject obtenerEstimacion(String codigoPlanta, int idLote) {
        JSONObject json = null;
        System.out.println(codigoPlanta+"   " +idLote+"");
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * from FLORESENLOTE where (FLOR = ?) and (LOTE = ?)");
            stmt.setString(1,codigoPlanta);
            stmt.setInt(1,idLote);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                json = montarLote(rs);
            }
            
        }catch(SQLException e){
        }
        
        return json;
    }

    private JSONObject montarLote(ResultSet rs) {
        JSONObject json = new JSONObject();
        
        try {
        
            json.put("cantidad", rs.getString("CANTIDAD"));
            json.put("flor", rs.getInt("FLOR"));
            json.put("lote", rs.getString("LOTE"));
            
            return json;
        } catch (SQLException | JSONException ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    
}
