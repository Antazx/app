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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author GUillermo
 */
public class GestorVinculacion {
    
    private static GestorVinculacion gestor;
    private static String dbURL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorVinculacion(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL);
            
        }catch (Exception e){
            
            e.printStackTrace();
        }
    }
    public static GestorVinculacion getInstance() {
        if(gestor == null)
            gestor = new GestorVinculacion();
        return gestor;
    }

    public JSONArray readVinculaciones(String dni) {
        String[] tipoVinc = new String[20];
        JSONArray list = null;
        JSONObject obj;
        
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select * from TIPODEVINCULACION");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tipoVinc[rs.getInt("IDTIPO")]=rs.getString("NOMBRETIPO");
            }

            list = new JSONArray(new ArrayList());
            stmt = conn.prepareStatement("select * from VINCULACIONCONLAEMPRESA where EMPLEADO = ?");
            stmt.setString(1, dni);
            rs = stmt.executeQuery();

            while(rs.next()){
                obj = new JSONObject();
                obj.put("vinculo", tipoVinc[rs.getInt("VINCULO")]);
                obj.put("inicio", rs.getDate("INICIO"));
                list.put(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorVinculacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GestorVinculacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
