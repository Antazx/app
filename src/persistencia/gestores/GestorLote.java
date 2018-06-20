/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Dani
 */
public class GestorLote {

    private static GestorLote gestorLote;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public static GestorLote getInstance() {
        if(gestorLote == null){
            return new GestorLote();
        }else{
            return gestorLote;
        }
    }

    public JSONArray readLotes(String codigo) {
        
        JSONArray lotesJ = new JSONArray();
        JSONObject loteJ;
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from LOTE where (PLANTA = ?) and (ESTADO <> ?)");
            stmt.setString(1, codigo);
            stmt.setInt(2, 5);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                loteJ = new JSONObject();
                loteJ.put("id", rs.getInt("ID"));
                loteJ.put("cantidad", rs.getInt("CANTIDAD"));
                loteJ.put("fechaCreacion", rs.getDate("FECHADECREACION"));
                loteJ.put("estado", rs.getInt("ESTADO"));
                
                lotesJ.put(loteJ);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GestorFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lotesJ;
    }
    
}
