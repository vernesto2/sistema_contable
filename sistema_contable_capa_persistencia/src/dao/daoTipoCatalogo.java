/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import utils.constantes.RespuestaGeneral;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.TipoCatalogo;
import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class daoTipoCatalogo {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;

    public daoTipoCatalogo(Conexion cx) {
        this.cx = cx;
    }
    
    public RespuestaGeneral Listar() {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<TipoCatalogo> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT * FROM tipo_catalogo tc where tc.eliminado = 0
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoCatalogo tcatalogo = new TipoCatalogo();
                tcatalogo.setId(rs.getInt("id"));
                tcatalogo.setTipo(rs.getString("tipo"));
                lista.add(tcatalogo);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertar(TipoCatalogo tcatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO tipo_catalogo     
                  VALUES(null, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setString(1, tcatalogo.getTipo());
            ps.setString(2, tcatalogo.getRef());
            ps.setInt(3, 0);
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = -1;
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            return rg.asCreated(RespuestaGeneral.GUARDADO_CORRECTAMENTE, id);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral editar(TipoCatalogo tcatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE tipo_catalogo SET tipo=?,ref=?,eliminado=? WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setString(1, tcatalogo.getTipo());
            ps.setString(2, tcatalogo.getRef());
            ps.setInt(3, tcatalogo.isEliminado() ? 1 : 0);
            ps.setInt(4, tcatalogo.getId());
            ps.executeUpdate();
            
            return rg.asCreated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, tcatalogo.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE tipo_catalogo SET eliminado=? WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            return rg.asOk(RespuestaGeneral.ELIMINADO_CORRECTAMENTE, ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
}
