/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.gestores;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author GUillermo
 */
public class GestorPedido {
    
    private static GestorPedido gestorPedido;
    private static final String URL = "jdbc:derby://localhost:1527/BDDis";
    private static Connection conn = null;
    
    public GestorPedido() {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(URL); 
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static GestorPedido getInstance() {
        
        if(gestorPedido == null){
            return new GestorPedido();
        }else{
            return gestorPedido;
        }
    } 
}
