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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.TipoCatalogo;

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
    
    public RespuestaGeneral Listar(String busqueda) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<TipoCatalogo> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT * FROM tipo_catalogo tc where tc.eliminado = 0 and tc.tipo like '%paramBusqueda%'
                  """;
        String newSql = sql.replaceAll("paramBusqueda", busqueda);
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoCatalogo tcatalogo = new TipoCatalogo();
                tcatalogo.setId(rs.getInt("id"));
                tcatalogo.setTipo(rs.getString("tipo"));
                tcatalogo.setRef(rs.getString("ref"));
                tcatalogo.setColor(rs.getInt("color"));
                tcatalogo.setLibro_diario(rs.getInt("libro_diario"));
                tcatalogo.setLibro_mayor(rs.getInt("libro_mayor"));
                tcatalogo.setBalanza_comprobacion(rs.getInt("balanza_comprobacion"));
                tcatalogo.setEstado_resultado(rs.getInt("estado_resultado"));
                tcatalogo.setBalance_general(rs.getInt("balance_general"));
                tcatalogo.setFlujo_efectivo(rs.getInt("flujo_efectivo"));
                tcatalogo.setCambios_patrimonio(rs.getInt("cambios_patrimonio"));
                tcatalogo.setNivel_mayorizar(rs.getInt("nivel_mayorizar"));
                lista.add(tcatalogo);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ObtenerPorId(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<TipoCatalogo> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT * FROM tipo_catalogo tc where tc.id = ?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoCatalogo tcatalogo = new TipoCatalogo();
                tcatalogo.setId(rs.getInt("id"));
                tcatalogo.setTipo(rs.getString("tipo"));
                tcatalogo.setRef(rs.getString("ref"));
                tcatalogo.setColor(rs.getInt("color"));
                tcatalogo.setLibro_diario(rs.getInt("libro_diario"));
                tcatalogo.setLibro_mayor(rs.getInt("libro_mayor"));
                tcatalogo.setBalanza_comprobacion(rs.getInt("balanza_comprobacion"));
                tcatalogo.setEstado_resultado(rs.getInt("estado_resultado"));
                tcatalogo.setBalance_general(rs.getInt("balance_general"));
                tcatalogo.setFlujo_efectivo(rs.getInt("flujo_efectivo"));
                tcatalogo.setCambios_patrimonio(rs.getInt("cambios_patrimonio"));
                tcatalogo.setNivel_mayorizar(rs.getInt("nivel_mayorizar"));
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
                  VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setString(1, tcatalogo.getTipo());
            ps.setString(2, tcatalogo.getRef());
            ps.setInt(3, tcatalogo.getColor());
            ps.setInt(4, tcatalogo.getLibro_diario());
            ps.setInt(5, tcatalogo.getLibro_mayor());
            ps.setInt(6, tcatalogo.getBalanza_comprobacion());
            ps.setInt(7, tcatalogo.getEstado_resultado());
            ps.setInt(8, tcatalogo.getBalance_general());
            ps.setInt(9, tcatalogo.getFlujo_efectivo());
            ps.setInt(10, tcatalogo.getCambios_patrimonio());
            ps.setInt(11, tcatalogo.getNivel_mayorizar());
            ps.setInt(12, 0);
            
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
                    UPDATE tipo_catalogo SET 
                        tipo=?
                        ,ref=?
                        ,color=?
                        ,libro_diario=?
                        ,libro_mayor=?
                        ,balanza_comprobacion=?
                        ,estado_resultado=?
                        ,balance_general=?
                        ,flujo_efectivo=?
                        ,cambios_patrimonio=?
                        ,nivel_mayorizar=?
                    WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setString(1, tcatalogo.getTipo());
            ps.setString(2, tcatalogo.getRef());
            ps.setInt(3, tcatalogo.getColor());
            ps.setInt(4, tcatalogo.getLibro_diario());
            ps.setInt(5, tcatalogo.getLibro_mayor());
            ps.setInt(6, tcatalogo.getBalanza_comprobacion());
            ps.setInt(7, tcatalogo.getEstado_resultado());
            ps.setInt(8, tcatalogo.getBalance_general());
            ps.setInt(9, tcatalogo.getFlujo_efectivo());
            ps.setInt(10, tcatalogo.getCambios_patrimonio());
            ps.setInt(11, tcatalogo.getNivel_mayorizar());
            ps.setInt(12, tcatalogo.getId());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, tcatalogo.getId());
            
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
