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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Persona;
import modelo.Usuario;
import modelo.dtoCicloContable;
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

    public void insertar(Usuario usuario) {
        RespuestaGeneral rg = new RespuestaGeneral();
        String sqlBuscarPorCarnet = """
select count(id) as encontrados from usuario where usuario.nombre = ?                     
""";
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
                         ?, ?, ?, ?, ?, ?, 
                         ?, ?, ?, ?, ?, ?
                  )    
        """;
        try (
                PreparedStatement psPersona = cx.getCx().prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS); 
                PreparedStatement psUsuario = cx.getCx().prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psBuscarUsuario = cx.getCx().prepareStatement(sqlBuscarPorCarnet);
            ) {
            psBuscarUsuario.setString(1, usuario.getNombre());
            try (ResultSet rs = psBuscarUsuario.executeQuery();) {
                Integer encontrados = rs.getInt("encontrados");
                if(encontrados > 0) {
                    throw new IllegalStateException("Ya existe usuario con el carnet proporcionado");
                }
            }
            Persona persona = usuario.getPersona();

            psPersona.setString(1, persona.getNombres());
            psPersona.setString(2, persona.getApellidos());
            psPersona.setInt(3, persona.getTipo());
            psPersona.setString(4, persona.getCarnet());

            psPersona.executeUpdate();

            try (ResultSet rsKeyPersona = psPersona.getGeneratedKeys();) {
                while (rsKeyPersona.next()) {
                    Integer id = rsKeyPersona.getInt(1);
                    persona.setId(id);
                }
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

            try (ResultSet rsKeyUsuario = psPersona.getGeneratedKeys();) {
                while (rsKeyUsuario.next()) {
                    Integer id = rsKeyUsuario.getInt(1);
                    usuario.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage();
        }
    }

    public Usuario obtenerPorCarnet(String carnet) {
        Usuario usuario = null;
        var sqlUsuario = """
                        select 
                            id_persona, nombres, apellidos, tipo, carnet, 
                            usuario.id as id_usuario, nombre, correo, salt, clave, resetear_clave, 
                            pregunta1, respuesta1,  pregunta2, respuesta2, pregunta3, respuesta3 
                         	
                         from usuario inner join persona on usuario.id_persona = persona.id
                         where usuario.nombre = ?
        """;
        //este try es para que se cierre el PreparedStatement
        try (
                PreparedStatement psUsuario = cx.getCx().prepareStatement(sqlUsuario);) {

            psUsuario.setString(1, carnet);
            //este try aniddado es para que se cierre el ResultSet
            try (ResultSet rs = psUsuario.executeQuery();) {
                Persona persona = null;
                if (rs.getFetchSize() > 1) {
                    throw new IllegalStateException("Error: se esperaba un resultado pero se obtuvieron más");
                }
                while (rs.next()) {
                    persona = new Persona();
                    persona.setId(rs.getInt("id_persona"));
                    persona.setNombres(rs.getString("nombres"));
                    persona.setApellidos(rs.getString("apellidos"));
                    persona.setTipo(rs.getInt("tipo"));
                    persona.setCarnet(rs.getString("carnet"));

                    usuario = new Usuario();
                    usuario.setPersona(persona);

                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setSalt(rs.getString("salt"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setResetear_clave(rs.getInt("resetear_clave"));
                    usuario.setPregunta1(rs.getInt("pregunta1"));
                    usuario.setRespuesta1(rs.getString("respuesta1"));
                    usuario.setPregunta2(rs.getInt("pregunta2"));
                    usuario.setRespuesta2(rs.getString("respuesta2"));
                    usuario.setPregunta3(rs.getInt("pregunta3"));
                    usuario.setRespuesta3(rs.getString("respuesta3"));
                }
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage();
            return null;
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
