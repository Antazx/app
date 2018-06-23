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

/**
 *
 * @author GUillermo
 */
public class GestorVenta {
    
    private static GestorVenta gestor;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    public static GestorVenta getInstance() {
        if(gestor == null)
            gestor = new GestorVenta();
        return gestor;
    }
    public GestorVenta(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
        }
    }

    public int createVenta(String fecha, String dni) {
        int count = 0;
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("select COUNT(*) from LINEADEVENTA");
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                count = rs.getInt(1)+1;
                System.out.println("COUNT:" +count);
            }
            
            stmt = conn.prepareStatement("insert into VENTA (IDDEVENTA, FECHADEVENTA, DEPENDIENTE) values (?, ?, ?)");
            stmt.setInt(1, count);
            stmt.setString(2, fecha);
            stmt.setString(3, dni);
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorLineaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
}
