/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import dto.dtoCuenta;
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
import modelo.Cuenta;
import modelo.TipoCatalogo;
import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class daoCuenta {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;

    public daoCuenta(Conexion cx) {
        this.cx = cx;
    }
    
    public RespuestaGeneral Listar(int idTipoCatalogo, String busqueda) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<dtoCuenta> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select 
                  	c.*
                  	,tc.tipo as catalogo
                  from cuenta c
                  left join tipo_catalogo tc on c.id_tipo_catalogo = tc.id
                  where c.id_tipo_catalogo = ? and c.eliminado = 0 and (c.nombre like '%busqueda%' or c.codigo like '%busqueda%')
                  order by cast(c.codigo as text)
                  """;
        String newSql = sql.replaceAll("busqueda", busqueda);
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql)) {
            ps.setInt(1, idTipoCatalogo);
            //ps.setString(2, busqueda);
            //ps.setString(3, busqueda);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoCuenta cuenta = new dtoCuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setId_tipo_catalogo(rs.getInt("id_tipo_catalogo"));
                cuenta.setCodigo(rs.getString("codigo"));
                cuenta.setCatalogo(rs.getString("catalogo"));
                cuenta.setRef(rs.getString("ref"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setNivel(rs.getInt("nivel"));
                cuenta.setTipo_saldo(rs.getString("tipo_saldo"));
                cuenta.setIngresos(rs.getString("ingresos"));
                cuenta.setEgresos(rs.getString("egresos"));
                cuenta.setEliminado(rs.getInt("eliminado") == 0 ? false : true);
                lista.add(cuenta);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ListarCatalogo(int idTipoCatalogo, String busqueda) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<Cuenta> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select 
                  	c.*
                  from cuenta c
                  where c.id_tipo_catalogo = ? and c.eliminado = 0 and (c.nombre like '%busqueda%' or c.codigo like '%busqueda%')
                  order by cast(c.codigo as text)
                  """;
        String newSql = sql.replaceAll("busqueda", busqueda);
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql)) {
            ps.setInt(1, idTipoCatalogo);
            //ps.setString(2, busqueda);
            //ps.setString(3, busqueda);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setId_tipo_catalogo(rs.getInt("id_tipo_catalogo"));
                cuenta.setCodigo(rs.getString("codigo"));
                cuenta.setRef(rs.getString("ref"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setNivel(rs.getInt("nivel"));
                cuenta.setTipo_saldo(rs.getString("tipo_saldo"));
                cuenta.setIngresos(rs.getString("ingresos"));
                cuenta.setEgresos(rs.getString("egresos"));
                cuenta.setEliminado(rs.getInt("eliminado") == 0 ? false : true);
                lista.add(cuenta);
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
        ArrayList<Cuenta> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT * FROM cuenta c where c.id = ?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setId_tipo_catalogo(rs.getInt("id_tipo_catalogo"));
                cuenta.setCodigo(rs.getString("catalogo"));
                cuenta.setRef(rs.getString("ref"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setNivel(rs.getInt("nivel"));
                cuenta.setTipo_saldo(rs.getString("tipo_caldo"));
                cuenta.setIngresos(rs.getString("ingresos"));
                cuenta.setEgresos(rs.getString("egresos"));
                cuenta.setEliminado(rs.getInt("eliminado") == 0 ? false : true);
                lista.add(cuenta);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertar(Cuenta cuenta) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO cuenta     
                  VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, cuenta.getId_tipo_catalogo());
            ps.setString(2, cuenta.getCodigo());
            ps.setString(3, cuenta.getRef());
            ps.setString(4, cuenta.getNombre());
            ps.setInt(5, cuenta.getNivel());
            ps.setString(6, cuenta.getTipo_saldo());
            ps.setString(7, cuenta.getIngresos());
            ps.setString(8, cuenta.getEgresos());
            ps.setInt(9, cuenta.isEliminado() ? 1 : 0);
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
    
    public RespuestaGeneral editar(Cuenta cuenta) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE cuenta SET 
                        id_tipo_catalogo=?,
                        codigo=?,
                        ref=?,
                        nombre=?,
                        nivel=?,
                        tipo_saldo=?,
                        ingresos=?,
                        egresos=?
                    WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, cuenta.getId_tipo_catalogo());
            ps.setString(2, cuenta.getCodigo());
            ps.setString(3, cuenta.getRef());
            ps.setString(4, cuenta.getNombre());
            ps.setInt(5, cuenta.getNivel());
            ps.setString(6, cuenta.getTipo_saldo());
            ps.setString(7, cuenta.getIngresos());
            ps.setString(8, cuenta.getEgresos());
            ps.setInt(9, cuenta.getId());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, cuenta.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE cuenta SET eliminado=? WHERE id=?
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
