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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Cuenta;

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
        String sql = """
                  select c.*, nc.nivel as nivel, tc.tipo as catalogo
                  from cuenta c
                  left join tipo_catalogo tc on c.id_tipo_catalogo = tc.id
                  inner join ( select length(ci.codigo) as length_codigo, row_number() over (order by length(ci.codigo)) as nivel
                    from cuenta ci where ci.id_tipo_catalogo = paramIdCatalogo and ci.eliminado = 0 group by length(ci.codigo)
                  ) as nc on nc.length_codigo = length(c.codigo) where c.id_tipo_catalogo = paramIdCatalogo and c.eliminado = 0 and (c.nombre like '%paramBusqueda%' or c.codigo like '%paramBusqueda%')
                  order by cast(c.codigo as text)
                  """;
        String newSql = sql.replaceAll("paramBusqueda", busqueda);
        String newSql1 = newSql.replaceAll("paramIdCatalogo", String.valueOf(idTipoCatalogo));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql1)) {
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
                  	,nc.nivel as nivel 
                        ,IIF(IIF(tc.nivel_mayorizar <= nc.nivel and nc.nivel, 1, 0) = 1 and 
                            (nc.nivel < (LEAD(nc.nivel, 1) OVER(ORDER BY cast(c.codigo as text)))) = 0, 1, 0) disponible
                  	
                  from cuenta c
                  left join tipo_catalogo tc on c.id_tipo_catalogo = tc.id
                  inner join (
                    select 
                  	length(ci.codigo) as length_codigo
                  	,row_number() over (order by length(ci.codigo)) as nivel
                    from cuenta ci
                    where ci.id_tipo_catalogo = paramIdCatalogo and ci.eliminado = 0
                    group by length(ci.codigo)
                  ) as nc 
                  on nc.length_codigo = length(c.codigo)
                  where c.id_tipo_catalogo = paramIdCatalogo and c.eliminado = 0 and (c.nombre like '%paramBusqueda%' or c.codigo like '%paramBusqueda%')
                  order by cast(c.codigo as text)
                  """;
        String newSql = sql.replaceAll("paramBusqueda", busqueda);
        String newSql1 = newSql.replaceAll("paramIdCatalogo", String.valueOf(idTipoCatalogo));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql1)) {
            //ps.setInt(1, idTipoCatalogo);
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
                cuenta.setDisponible(rs.getInt("disponible"));
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
                  select 
                  	c.*
                  	,nc.nivel as nivel 
                  	
                  from cuenta c
                  inner join (
                    select 
                  	length(ci.codigo) as length_codigo
                  	,row_number() over (order by length(ci.codigo)) as nivel
                    from cuenta ci
                    where ci.id = paramId and ci.eliminado = 0
                    group by length(ci.codigo)
                  ) as nc 
                  on nc.length_codigo = length(c.codigo)
                  where c.id = paramId and c.eliminado = 0
                  order by cast(c.codigo as text)
                  """;
        String newSql = sql.replaceAll("paramId", String.valueOf(id));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql)) {
            //ps.setInt(1, id);
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
                  VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, cuenta.getId_tipo_catalogo());
            ps.setString(2, cuenta.getCodigo());
            ps.setString(3, cuenta.getRef());
            ps.setString(4, cuenta.getNombre());
            ps.setInt(5, cuenta.getNivel());
            //ps.setString(6, cuenta.getTipo_saldo());
            ps.setString(6, cuenta.getIngresos());
            ps.setString(7, cuenta.getEgresos());
            ps.setInt(8, cuenta.isEliminado() ? 1 : 0);
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
            //ps.setInt(5, cuenta.getNivel());
            ps.setString(5, cuenta.getTipo_saldo());
            ps.setString(6, cuenta.getIngresos());
            ps.setString(7, cuenta.getEgresos());
            ps.setInt(8, cuenta.getId());
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
