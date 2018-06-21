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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Dani
 */
public class GestorProducto {
    
    private static GestorProducto gestorProducto;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;

    public GestorProducto() {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
        }
    }
    public static GestorProducto getInstance() {
        
        if(gestorProducto == null){
            return new GestorProducto();
        }else{
            return gestorProducto;
        }
    } 

    public JSONObject readProducto(String nombre) {
        JSONObject json = null;
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * from PRODUCTO where (NOMBRE = ?)");
            stmt.setString(1,nombre);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                json = new JSONObject();
                json.put("codigo", rs.getString("CODIGO"));
                json.put("nombre", rs.getString("NOMBRE"));
                json.put("descripcion", rs.getString("DESCRIPCION"));
                json.put("existencias", rs.getInt("EXISTENCIAS"));
                json.put("cantidad", rs.getInt("CANTIDADNECESARIA"));
                json.put("precioVenta", rs.getFloat("PRECIODEVENTA"));
                json.put("precioCompra", rs.getFloat("PRECIOCOMPRA"));
                json.put("diasEntrega", rs.getInt("DIASPARAENTREGADELPROVEEDOR"));
                if(rs.getString("TIPODEPRODUCTOAUXILIAR")!=null){
                    json.put("tipoAuxiliar", rs.getString("TIPODEPRODUCTOAUXILIAR"));
                }else{
                    json.put("tipoAuxiliar", "");
                }
                json.put("subtipo", rs.getString("SUBTIPO"));
                //json.put("plantaDeFlor", rs.getString("PLANTADELAFLOR"));
                
            }
            
        }catch(SQLException | JSONException e){
        }
        
        return json;
    }
    
}
