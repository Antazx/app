/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.gestores;

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
public class GestorDisponibilidad {
    
    private static GestorDisponibilidad gestor;
    private static String dbURL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorDisponibilidad(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL);
            
        }catch (Exception e){
            
            e.printStackTrace();
        }
    }
    public static GestorDisponibilidad getInstance() {
        if(gestor == null){gestor = new GestorDisponibilidad();}
            
        return gestor;
    }

    public JSONArray readDisponibilidades(String dni){
        String[] tipoDisp = new String[20];
        JSONArray list = null;
        JSONObject obj;
        
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select * from TIPODEDISPONIBILIDAD");
            ResultSet rs = stmt.executeQuery();
        
            while(rs.next()){
                tipoDisp[rs.getInt("IDTIPO")] = rs.getString("NOMBRETIPO");
            }

            list = new JSONArray(new ArrayList());
            stmt = conn.prepareStatement("select * from DISPONIBILIDADEMPLEADO where EMPLEADO = ?");
            stmt.setString(1, dni);
            rs = stmt.executeQuery();

            while(rs.next()){
                obj = new JSONObject();
                obj.put("disponibilidad", tipoDisp[rs.getInt("DISPONIBILIDAD")]);
                obj.put("comienzo", rs.getDate("COMIENZO"));
                obj.put("finalPrevisto", rs.getDate("FINALPREVISTO"));
                list.put(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GestorDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("GESTOR DISPONIBILIDAD: " +list.toString());
        return list;
    }
    
}
