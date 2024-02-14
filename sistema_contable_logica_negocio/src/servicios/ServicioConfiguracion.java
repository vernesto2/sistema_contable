/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class ServicioConfiguracion {

    ServicioUsuario _usuario;

    public ServicioConfiguracion() {
        _usuario = new ServicioUsuario();
    }

    public ServicioConfiguracion(ServicioUsuario _usuario) {
        this._usuario = _usuario;
    }

    public RespuestaGeneral crear(Usuario usuario, char[] claveSinCifrar) throws IOException, SQLException {
        try {
            //ejecutar el código SQL inicial
            crearConfiguracionInicial();
            //crear el nuevo usuario
            return _usuario.crear(usuario, claveSinCifrar);
        } catch (NoSuchAlgorithmException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        } catch (InvalidKeySpecException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        }
    }

    public void crearConfiguracionInicial() throws IOException, SQLException {
        String sql = leerArchivoConfiguracionInicial();
        ejecutarSQL(sql);
    }

    private void ejecutarSQL(String sql) throws SQLException {
        String fileName = "database/db-copia.sqlite";
        Connection connection = conectar(fileName);
        try (
                Statement statement = connection.createStatement();
        ) {
            
            statement.executeUpdate(sql);
        }catch( SQLException ex ) {
            throw ex;
        }
        desconectar(connection);
    }
    
    private Connection conectar(String fileName) {
        Connection cx = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + fileName;
            cx = DriverManager.getConnection(url);
            System.out.println("CONECTADO!");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return cx;
    }
    
    private void desconectar(Connection connection) {
        try {
            connection.close();
            System.out.println("Desconectado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String leerArchivoConfiguracionInicial() throws FileNotFoundException, IOException {
        // File path is passed as parameter
        File file = new File("database/config_inicial.sql");
        try (
            // Note:  Double backquote is to avoid compiler
            // jecu words
            // like \test as \t (ie. as a escape sequence)
            // Creating an object of BufferedReader class
            BufferedReader br = new BufferedReader(new FileReader(file));
         ) {
            // Declaring a string variable
            StringBuilder sb = new StringBuilder();
            // Condition holds true till
            // there is character in a string
            String st;
            while ((st = br.readLine()) != null) {
                // Print the string
                System.out.println(st);
                sb.append(st);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
