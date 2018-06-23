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
 * @author GUillermo
 */
public class GestorLineaVenta {
    
    private static GestorLineaVenta gestor;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorLineaVenta(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
        }
    }
    
    public static GestorLineaVenta getInstance(){
        if(gestor == null)
            gestor = new GestorLineaVenta();
        return gestor;
    }

    public void registrarLineaVenta(JSONObject lVentaJ) {
        System.out.println("EN GESTOR: " +lVentaJ.toString());
        PreparedStatement stmt;
        try {
            
            stmt = conn.prepareStatement("insert into LINEADEVENTA (CANTIDAD, VENTA, PRODUCTO) values (?, ?, ?)");
            stmt.setInt(1, lVentaJ.getInt("cantidad"));
            stmt.setInt(2, lVentaJ.getInt("venta"));
            stmt.setString(3, lVentaJ.getString("producto"));
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorLineaVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GestorLineaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteLineaVenta(int vent) {
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from LINEADEVENTA where VENTA = ?");
            stmt.setInt(1, vent);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestorLineaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
