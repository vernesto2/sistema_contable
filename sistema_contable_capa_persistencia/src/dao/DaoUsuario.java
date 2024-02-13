/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Persona;
import modelo.Usuario;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class DaoUsuario {
    Conexion cx;
    public DaoUsuario(Conexion cx) {
        this.cx = cx;
    }
    
    public RespuestaGeneral insertar(Usuario usuario) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sqlPersona = """
                  INSERT INTO persona ( nombres, apellidos, tipo, carnet )
                  values (?, ?, ?, ?)
                  """;
        var sqlUsuario = """
                  INSERT INTO usuario ( 
                         id_persona, nombre, correo, salt, clave, resetear_clave, 
                         pregunta1, respuesta1,  pregunta2, respuesta2, pregunta3, respuesta3 
                  )
                  VALUES ( 
                         ?, ?, ?, ?; ?, ?, 
                         ?, ?, ?, ?, ?, ?
                  )    
        """;
        try (
                PreparedStatement psPersona = cx.getCx().prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS); 
                PreparedStatement psUsuario = cx.getCx().prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS); 
                ) {
            Persona persona = usuario.getPersona();
            
            psPersona.setString(1, persona.getNombres());
            psPersona.setString(2, persona.getApellidos());
            psPersona.setInt(3, persona.getTipo());
            psPersona.setString(4, persona.getCarnet());
            
            psPersona.executeUpdate();
            
            ResultSet rsKeyPersona = psPersona.getGeneratedKeys();
            while(rsKeyPersona.next()) {
                Integer id = rsKeyPersona.getInt(1);
                persona.setId(id);
            }
            
            psUsuario.setInt(1, persona.getId());
            psUsuario.setString(2, usuario.getNombre());
            psUsuario.setString(3, usuario.getCorreo());
            psUsuario.setString(4, usuario.getSalt());
            psUsuario.setString(5, usuario.getClave());
            psUsuario.setInt(6, usuario.getResetear_clave());
            psUsuario.setInt(7, usuario.getPregunta1());
            psUsuario.setString(8, usuario.getRespuesta1());
            psUsuario.setInt(9, usuario.getPregunta2());
            psUsuario.setString(10, usuario.getRespuesta2());
            psUsuario.setInt(11, usuario.getPregunta3());
            psUsuario.setString(12, usuario.getRespuesta3());
            
            psUsuario.executeUpdate();
            
            ResultSet rsKeyUsuario = psPersona.getGeneratedKeys();
            while(rsKeyUsuario.next()) {
                Integer id = rsKeyUsuario.getInt(1);
                usuario.setId(id);
            }
            return rg.asCreated("", psPersona);
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage();
            return rg.asBadRequest(mensaje);
        }
    }
    
    public RespuestaGeneral actualizar(Usuario usuario) {
        return null;
/*
        
                RespuestaGeneral rg = new RespuestaGeneral();
        PreparedStatement ps = null;
        try {
            var sql = """
                      INSERT INTO usuario 
                      values (?, ?, ?, ?)
                      """;
            ps = cx.conectar().prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.setInt(2, usuario.getId_persona());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getCorreo());
            ps.setString(5, usuario.getCorreo());
            ps.executeUpdate();
            cx.desconectar();
            return rg.asCreated("", ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString().contains("is not unique") ? "Ya existe un usuario llamado " + usuario.getUsuario() : e.getMessage();
            return rg.asBadRequest(mensaje);
        }
        *
*/
    }
    
}


