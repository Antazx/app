/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author GUillermo
 */
public class FachadaPersistencia {
    private static String dbURL = "jdbc:derby://localhost:1527/BDDis";
    // jdbc Connection
    private static Connection conn = null;
    
    public FachadaPersistencia() {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static FachadaPersistencia getInstance() {
        return FachadaPersistenciaHolder.INSTANCE;
    }
    
    public JSONObject readEmpleado(String dni){
        JSONObject json = null;
        
        try{
        
            PreparedStatement stmt = conn.prepareStatement("select * "
                    + "from EMPLEADO where (NIF = ?)");
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                JSONArray list;
                json = new JSONObject();
                json.put("nombre" , rs.getString("NOMBRE"));
                json.put("fechaIni", rs.getDate("FECHAINICIOENEMPRESA"));
                json.put("password", rs.getString("PASSWORD"));
                
                list = getRoles(dni);
                json.put("roles", list);
                
                list = getDisponibilidades(dni);
                json.put("disponibilidades", list);
                
                list = getVinculaciones(dni);
                json.put("vinculaciones", list);
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return json;
    }
    
    private JSONArray getRoles(String dni) throws SQLException, JSONException{
        String[] tipoRol = new String[20];
        JSONArray list;
        JSONObject obj;
        
        PreparedStatement stmt = conn.prepareStatement("select * from TIPODEROL");
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
        
        return list;
    }
    
    private JSONArray getDisponibilidades(String dni) throws SQLException, JSONException {
        String[] tipoDisp = new String[20];
        JSONArray list;
        JSONObject obj;
        
        PreparedStatement stmt = conn.prepareStatement("select * from TIPODEDISPONIBILIDAD");
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
        return list;
    }

    private JSONArray getVinculaciones(String dni) throws SQLException, JSONException {
        String[] tipoVinc = new String[20];
        JSONArray list;
        JSONObject obj;
        
        PreparedStatement stmt = conn.prepareStatement("select * from TIPODEVINCULACION");
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
        return list;
    }
    private static class FachadaPersistenciaHolder {

        private static final FachadaPersistencia INSTANCE = new FachadaPersistencia();
    }
}
