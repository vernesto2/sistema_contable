/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class Conexion {
        
    Connection cx = null;
    
    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + Constantes.rutaConexion;
            cx = DriverManager.getConnection(url);
            System.out.println("CONECTADO!");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return cx;
    }

    public Connection getCx() {
        return cx;
    }

    public void setCx(Connection cx) {
        this.cx = cx;
    }
    
    public void desconectar() {
        try {
            cx.close();
            System.out.println("NO CONECTADO!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
