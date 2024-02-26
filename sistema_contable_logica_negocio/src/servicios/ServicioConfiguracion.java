/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public ServicioConfiguracion(String rutaConexion) {
        _usuario = new ServicioUsuario(rutaConexion);
    }

    public ServicioConfiguracion(ServicioUsuario _usuario) {
        this._usuario = _usuario;
    }

    public RespuestaGeneral crear(Usuario usuario, char[] claveSinCifrar)  {
        try {
            RespuestaGeneral respValidarUsuario = _usuario.validarUsuario(usuario, claveSinCifrar);
            if (respValidarUsuario.esFallida()) {
                return respValidarUsuario;
            }
            //ejecutar el código SQL inicial
            String fileName = crearConfiguracionInicial();
            
            Conexion conexion = new Conexion(fileName);
            //crear el usuario en el nuevo archivo
            conexion.conectar();
            _usuario.setConexion(conexion);
            //crear el nuevo usuario
            RespuestaGeneral rgUsuario = _usuario.crear(usuario, claveSinCifrar);
            _usuario.cerrarConexion();
            return rgUsuario;
        } catch (NoSuchAlgorithmException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        } catch (InvalidKeySpecException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        } catch (IOException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        } catch (SQLException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        }
    }

    private String crearConfiguracionInicial() throws IOException, SQLException {
        String sql = leerConfiguracionInicial();
        String strFechaHoraActual = fechaAFormatoPreferido(LocalDateTime.now());
        String fileName = "database/db-" + strFechaHoraActual + ".sqlite";
        try (Connection connection = conectar(fileName)) {
            ejecutarSQL(connection, sql);
            return fileName;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private String fechaAFormatoPreferido(LocalDateTime fechaHora) {
        //2024-02-16-1657
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm");
        return fechaHora.format(formatter); // "1986-04-08 12:30"
    }

    private void ejecutarSQL(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        }
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

    private String leerConfiguracionInicial() throws FileNotFoundException, IOException {
        // File path is passed as parameter
        File file = new File("database/config_inicial.sql");
        try (
                // Note:  Double backquote is to avoid compiler
                // jecu words
                // like \test as \t (ie. as a escape sequence)
                // Creating an object of BufferedReader class
                BufferedReader br = new BufferedReader(new FileReader(file));) {
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
