/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import modelo.Usuario;
import utils.constantes.RespuestaGeneral;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author vacev
 */
public class daoUsuario {
    
    Conexion cx;
    
    public daoUsuario() {
        this.cx = new Conexion();
    }
    
    public RespuestaGeneral insertarUsuario(Usuario usuario) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO usuario 
                  VALUES(null, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.conectar().prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getCorreo());
            ps.executeUpdate();
            cx.desconectar();
            return rg.asCreated("", ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString().contains("is not unique") ? "Ya existe un usuario llamado " + usuario.getUsuario() : e.getMessage();
            return rg.asBadRequest(mensaje);
        }
    }
    
    public RespuestaGeneral editarUsuario(Usuario usuario) {
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
            ps.executeUpdate();
            cx.desconectar();
            return rg.asCreated("", ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString().contains("is not unique") ? "Ya existe un usuario llamado " + usuario.getUsuario() : e.getMessage();
            return rg.asBadRequest(mensaje);
        }
    }
    
}
