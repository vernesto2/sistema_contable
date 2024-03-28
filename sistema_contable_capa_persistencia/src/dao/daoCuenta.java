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
                cuenta.setEs_restado(rs.getInt("es_restado"));
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
                  with catalogo as (
                  select 
                  	c.*
                  	,nc.nivel as nivel 
                  	,(LEAD(nc.nivel, 1) OVER(ORDER BY cast(c.codigo as text))) as nivel_sig
                  	,(IIF(tc.nivel_mayorizar <= nc.nivel, 1, 0)) as disponible_1
                  	,((tc.nivel_mayorizar = nc.nivel) and (nc.nivel < (LEAD(nc.nivel, 1) OVER(ORDER BY cast(c.codigo as text))))) as disponible_2
                  	,IIF(IIF(tc.nivel_mayorizar <= nc.nivel, 1, 0) = 1 and 
                  		(nc.nivel < (LEAD(nc.nivel, 1) OVER(ORDER BY cast(c.codigo as text)))) = 0, 1, 0) disponible_old
                  	
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
                    where c.id_tipo_catalogo = paramIdCatalogo and c.eliminado = 0 
                    order by cast(c.codigo as text)
                  )
                  select 
                  	ct.*
                  	,case 
                            when ct.disponible_1 = 0 and ct.disponible_2 = 0 then 0
                            when ct.disponible_1 = 1 and ct.disponible_2 = 1 then 0
                            when ct.disponible_1 = 1 and ct.disponible_2 = 0 then 1
                  	end as disponible
                  from catalogo ct
                  WHERE (ct.nombre like '%paramBusqueda%' or ct.codigo like '%paramBusqueda%')
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
                cuenta.setEs_restado(rs.getInt("es_restado"));
                lista.add(cuenta);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ObtenerPorId(int id, int idTipoCatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<Cuenta> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  with catalogo as (
                    select 
                      c.*
                      ,nc.nivel as nivel 
                      ,(LEAD(nc.nivel, 1) OVER(ORDER BY cast(c.codigo as text))) as nivel_sig
                      ,(IIF(tc.nivel_mayorizar <= nc.nivel, 1, 0)) as disponible_1
                      ,((tc.nivel_mayorizar = nc.nivel) and (nc.nivel < (LEAD(nc.nivel, 1) OVER(ORDER BY cast(c.codigo as text))))) as disponible_2
                      ,IIF(IIF(tc.nivel_mayorizar <= nc.nivel, 1, 0) = 1 and 
                              (nc.nivel < (LEAD(nc.nivel, 1) OVER(ORDER BY cast(c.codigo as text)))) = 0, 1, 0) disponible_old

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
                      where c.id_tipo_catalogo = paramIdCatalogo and c.eliminado = 0 
                      order by cast(c.codigo as text)
                    )
                    select 
                      ct.*
                      ,case 
                        when ct.disponible_1 = 0 and ct.disponible_2 = 0 then 0
                        when ct.disponible_1 = 1 and ct.disponible_2 = 1 then 0
                        when ct.disponible_1 = 1 and ct.disponible_2 = 0 then 1
                      end as disponible
                    from catalogo ct
                    WHERE ct.id = paramId
                  """;
        String newSql = sql.replaceAll("paramIdCatalogo", String.valueOf(idTipoCatalogo));
        String newSql1 = newSql.replaceAll("paramId", String.valueOf(id));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql1)) {
            //ps.setInt(1, id);
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
                cuenta.setEs_restado(rs.getInt("es_restado"));
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
            //ps.setString(6, cuenta.getTipo_saldo());
            ps.setString(6, cuenta.getIngresos());
            ps.setString(7, cuenta.getEgresos());
            ps.setInt(8, cuenta.isEliminado() ? 1 : 0);
            ps.setInt(9, cuenta.getEs_restado());
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
                        egresos=?,
                        es_restado=?
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
            ps.setInt(8, cuenta.getEs_restado());
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
    
    public Integer tamanoCodigoAMayorizar(Integer idTipoCaalogo) {
        
        ArrayList<Cuenta> lista = new ArrayList<>();
        ResultSet rs = null;
        Integer tamanoCodigoAMayorizar = null;
        var sql = """
                select 
                    length(ci.codigo) as length_codigo
                    ,row_number() over (order by length(ci.codigo)) as nivel
                from cuenta ci
                where ci.id_tipo_catalogo = ?
                and ci.eliminado = false
                group by length(ci.codigo) 
                  """;
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idTipoCaalogo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                 tamanoCodigoAMayorizar = rs.getInt("length_codigo");
            }
            return tamanoCodigoAMayorizar;
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            throw new IllegalStateException(mensaje);
        }
    }
}
