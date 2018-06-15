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
public class GestorRol {
    
    private static GestorRol gestor;
    private static String dbURL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorRol(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL); 
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static GestorRol getInstance() {
        if(gestor == null)
            gestor = new GestorRol();
        return gestor;
    }

    public JSONArray readRol(String dni) {
        
        String[] tipoRol = new String[20];
        JSONArray list = null;
        JSONObject obj;
        
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select * from TIPODEROL");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
            tipoRol[rs.getInt("IDTIPO")]=rs.getString("NOMBRETIPO");
            }

            list = new JSONArray(new ArrayList());
            stmt = conn.prepareStatement("select * from ROLESENEMPRESA where EMPLEADO = ?");
            stmt.setString(1, dni);
            rs = stmt.executeQuery();

            while(rs.next()){

                obj = new JSONObject();
                obj.put("tipoRol", tipoRol[rs.getInt("ROL")]);
                obj.put("comienzoEnRol", rs.getDate("COMIENZOENROL"));
                list.put(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorRol.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GestorRol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
