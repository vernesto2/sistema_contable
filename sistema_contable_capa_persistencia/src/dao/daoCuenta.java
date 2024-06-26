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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Cuenta;
import reportes.CuentaBalanza;

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
                            when ct.disponible_1 = 1 and ct.nivel_sig is null then 1
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
    
    public RespuestaGeneral ListarCatalogoCicloContable(int idTipoCatalogo, String busqueda, int idCicloContable) {
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
                            when ct.disponible_1 = 1 and ct.nivel_sig is null then 1
                  	end as disponible
                  	,ccf.folio_mayor
                        ,ccf.id as id_ciclo_folio
                        ,cb.id as id_cuenta_balance
                  from catalogo ct
                  left join ciclo_contable_folios ccf on ct.id = ccf.id_cuenta and ccf.id_ciclo_contable = paramIdCicloContable
                  left join cuenta_balance cb on ct.id = cb.id_cuenta and cb.id_ciclo_contable = paramIdCicloContable
                  WHERE (ct.nombre like '%paramBusqueda%' or ct.codigo like '%paramBusqueda%')
                  """;
        String newSql = sql.replaceAll("paramBusqueda", busqueda);
        String newSql1 = newSql.replaceAll("paramIdCatalogo", String.valueOf(idTipoCatalogo));
        String newSql2 = newSql1.replaceAll("paramIdCicloContable", String.valueOf(idCicloContable));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql2)) {
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
                cuenta.setFolio_mayor(rs.getInt("folio_mayor"));
                cuenta.setId_ciclo_folio(rs.getInt("id_ciclo_folio"));
                cuenta.setId_cuenta_balance(rs.getInt("id_cuenta_balance"));
                lista.add(cuenta);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ListarCatalogoNivelMayorizar(int idTipoCatalogo, String busqueda, int nivelMayorizar, int cicloContable) {
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
                            when ct.disponible_1 = 1 and ct.nivel_sig is null then 1
                  	end as disponible
                        ,ccf.folio_mayor
                        ,ccf.id as id_ciclo_folio
                        ,cb.id as id_cuenta_balance
                  from catalogo ct
                  left join ciclo_contable_folios ccf on ct.id = ccf.id_cuenta and ccf.id_ciclo_contable = pCicloContable
                  left join cuenta_balance cb on ct.id = cb.id_cuenta and cb.id_ciclo_contable = pCicloContable
                  WHERE (ct.nombre like '%paramBusqueda%' or ct.codigo like '%paramBusqueda%')
                    AND ct.nivel = paramNivel
                  """;
        String newSql = sql.replaceAll("paramBusqueda", busqueda);
        String newSql1 = newSql.replaceAll("paramIdCatalogo", String.valueOf(idTipoCatalogo));
        String newSql2 = newSql1.replaceAll("paramNivel", String.valueOf(nivelMayorizar));
        String newSql3 = newSql2.replaceAll("pCicloContable", String.valueOf(cicloContable));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql3)) {
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
                cuenta.setFolio_mayor(rs.getInt("folio_mayor"));
                cuenta.setId_ciclo_folio(rs.getInt("id_ciclo_folio"));
                cuenta.setId_cuenta_balance(rs.getInt("id_cuenta_balance"));
                lista.add(cuenta);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ObtenerPorId(int id, int idTipoCatalogo, int idCicloContable) {
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
                          when ct.disponible_1 = 1 and ct.nivel_sig is null then 1
                        end as disponible
                        ,ccf.folio_mayor
                        ,ccf.id as id_ciclo_folio
                  
                    from catalogo ct
                    left join ciclo_contable_folios ccf on ct.id = ccf.id_cuenta and ccf.id_ciclo_contable = pCicloContable
                    WHERE ct.id = paramId
                  """;
        String newSql = sql.replaceAll("paramIdCatalogo", String.valueOf(idTipoCatalogo));
        String newSql1 = newSql.replaceAll("paramId", String.valueOf(id));
        String newSql2 = newSql1.replaceAll("pCicloContable", String.valueOf(idCicloContable));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql2)) {
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
                cuenta.setFolio_mayor(rs.getInt("folio_mayor"));
                cuenta.setId_ciclo_folio(rs.getInt("id_ciclo_folio"));
                lista.add(cuenta);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ObtenerPorIdCicloContable(int id, int idTipoCatalogo, int idCicloContable) {
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
                        when ct.disponible_1 = 1 and ct.nivel_sig is null then 1
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
    
    public RespuestaGeneral ObtenerPorIdYCicloContable(int id, int idTipoCatalogo, int idCicloContable) {
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
                        when ct.disponible_1 = 1 and ct.nivel_sig is null then 1
                      end as disponible
                        ,ccf.folio_mayor
                        ,ccf.id as id_ciclo_folio
                  from catalogo ct
                  left join ciclo_contable_folios ccf on ct.id = ccf.id_cuenta and ccf.id_ciclo_contable = pIdCicloContable
                  WHERE ct.id = paramId
                  """;
        String newSql = sql.replaceAll("paramIdCatalogo", String.valueOf(idTipoCatalogo));
        String newSql1 = newSql.replaceAll("paramId", String.valueOf(id));
        String newSql2 = newSql1.replaceAll("pIdCicloContable", String.valueOf(idCicloContable));
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql2)) {
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
                cuenta.setFolio_mayor(rs.getInt("folio_mayor"));
                cuenta.setId_ciclo_folio(rs.getInt("id_ciclo_folio"));
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
    
    public Integer tamanoCodigoAMayorizar(Integer idTipoCaalogo, int nivelAMayorizar) {
        
        ArrayList<Cuenta> lista = new ArrayList<>();
        ResultSet rs = null;
        Integer tamanoCodigoAMayorizar = null;
        var sql = 
"""
with cte_nivel_cuenta as (
    select length(ci.codigo) as length_codigo
    , row_number() over (order by length(ci.codigo)) as nivel
    from cuenta ci
    where ci.id_tipo_catalogo = ?
    and ci.eliminado = false
    group by length(ci.codigo) 
) 
select * from cte_nivel_cuenta
where nivel = ?
""";
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idTipoCaalogo);
            ps.setInt(2, nivelAMayorizar);
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
    
    public List<CuentaBalanza> listarCuentaBalanzaComprobacion(Integer idTipoCatalogo, Integer idCiclo, Integer tipoPartida, 
            // cuando idCuenta = NULL, la consulta devuelve todas las cuentas de mayor que se han utilizado, caso contrario devuelve la cuenta específica
            Integer idCuenta) 
            throws SQLException {
        
        var sql = 
"""
with cte_balanza_comprobacion as (
  select c.id, ccf.folio_mayor, c.codigo, c.nombre, c.tipo_saldo, 
  case 
    when tipo_saldo = 'D' then debe - haber
    when tipo_saldo = 'A' then haber - debe
    else 0 
  end
  as saldo_inicial, 
  row_number() over (PARTITION by c.id order by p.fecha || ' ' || p.hora asc, p.id asc) as row_number
  from cuenta c 
  inner join vw_cargo_abono pd on pd.id_cuenta = c.id and pd.parcial = 0
  left join partida p on pd.id_partida = p.id and pd.id_ciclo_contable = p.id_ciclo
  left join ciclo_contable_folios ccf on ccf.id_ciclo_contable = pd.id_ciclo_contable and ccf.id_cuenta = c.id
  where c.eliminado = false 
  and ( pd.id_partida is null or p.eliminado = false )
  and pd.eliminado = false
  and pd.id_ciclo_contable = ?
  and c.id_tipo_catalogo = ?
  and (
      ? is null or c.id = ?
  )
)
select cbc.*, saldo_calculado.saldo_deudor, saldo_calculado.saldo_acreedor
from cte_balanza_comprobacion cbc
inner join (
  select c.id, 
  sum(
      case
        when tipo_saldo = 'D' then debe - haber
	else 0
    end
  ) as saldo_deudor, 
  sum(
      case
        when tipo_saldo = 'A' then haber - debe
	else 0
      end
  ) as saldo_acreedor
  from cuenta c 
    inner join vw_cargo_abono pd on pd.id_cuenta = c.id
    left join partida p on pd.id_partida = p.id and pd.id_ciclo_contable = p.id_ciclo and p.id_tipo_partida <= ?
    where c.eliminado = false 
    and ( pd.id_partida is null or p.eliminado = false )
    and pd.eliminado = false
    and pd.id_ciclo_contable = ?
    and c.id_tipo_catalogo = ?
    and (
      ? is null or c.id = ?
    )
  group by c.id, c.codigo, c.nombre, c.tipo_saldo
) as saldo_calculado on saldo_calculado.id = cbc.id
where row_number = 1
order by folio_mayor
""";
        List<CuentaBalanza> lista = new ArrayList<CuentaBalanza>();
        try (
                PreparedStatement ps = cx.getCx().prepareStatement(sql);
            ) 
        {
            ps.setObject(1, idCiclo);
            ps.setObject(2, idTipoCatalogo);
            //idCuenta puede ser NULL, la consulta se encarga de manejar esos casos
            ps.setObject(3, idCuenta);
            //idCuenta puede ser NULL, la consulta se encarga de manejar esos casos
            ps.setObject(4, idCuenta);
            ps.setObject(5, tipoPartida);
            ps.setObject(6, idCiclo);
            ps.setObject(7, idTipoCatalogo);
            //idCuenta puede ser NULL, la consulta se encarga de manejar esos casos
            ps.setObject(8, idCuenta);
            //idCuenta puede ser NULL, la consulta se encarga de manejar esos casos
            ps.setObject(9, idCuenta);
            ResultSet rs = ps.executeQuery();
            //ps.setInt(1, id);
            
            while (rs.next()) {
                CuentaBalanza item = new CuentaBalanza();
                item.setId(rs.getObject("id", Integer.class));;
                item.setFolioMayor( 
                        rs.getInt("folio_mayor")
                );
                if(rs.wasNull()) {
                    item.setFolioMayor(null);
                }
                item.setCodigo(rs.getObject("codigo", String.class));
                item.setNombre(rs.getObject("nombre", String.class));
                item.setTipoSaldo(rs.getObject("tipo_saldo", String.class));
                item.setSaldoInicial(rs.getObject("saldo_inicial", Double.class));
                Double val;
                val = rs.getObject("saldo_deudor", Double.class);
                item.setSaldoDeudor(val);
                val = rs.getObject("saldo_acreedor", Double.class);
                item.setSaldoAcreedor(val);
                
                lista.add(item);
            }
            
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<Map<String, Object>> listarCuentaNivelParaBalanceGeneral(int idTipoCatalogo, int tamanoCodigoMayorizar) throws SQLException {
        String sql = """
 select c.*, nc.nivel as nivel, tc.tipo as catalogo
 from cuenta c
 left join tipo_catalogo tc on c.id_tipo_catalogo = tc.id
 inner join ( 
        select length(ci.codigo) as length_codigo, 
        	row_number() over (order by length(ci.codigo)) as nivel
        from cuenta ci 
        where ci.id_tipo_catalogo = ? and ci.eliminado = 0 
 	group by length(ci.codigo)
 ) as nc on nc.length_codigo = length(c.codigo) 
 where c.id_tipo_catalogo = ? and c.eliminado = 0
       and ( ? = 0 or nc.length_codigo <= ? )
 order by cast(c.codigo as text)
                     """;
        
        List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
        try (
                PreparedStatement ps = cx.getCx().prepareStatement(sql);
            ) 
        {
            ps.setObject(1, idTipoCatalogo);
            ps.setObject(2, idTipoCatalogo);
            
            ps.setObject(3, tamanoCodigoMayorizar);
            ps.setObject(4, tamanoCodigoMayorizar);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> item = new HashMap<String, Object>();
                
                item.put("id", rs.getInt("id"));
                item.put("codigo", rs.getString("codigo"));
                item.put("nivel", rs.getInt("nivel"));
                item.put("nombre", rs.getString("nombre"));
                item.put("tipo_saldo", rs.getString("tipo_saldo"));
                item.put("es_restado", rs.getBoolean("es_restado"));
                lista.add(item);
            }
            
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public static Collection<CuentaBalanza> generarCuentasBalanzaComprobacion() {
        List<CuentaBalanza> coleccion = new ArrayList<CuentaBalanza>();
        
        CuentaBalanza cuenta0 = new CuentaBalanza();
        cuenta0.setId(1);
        cuenta0.setNombre("Efectivo y Equivalente de Efectivo");
        cuenta0.setCodigo("1010");
        cuenta0.setFolioMayor(1);
        cuenta0.setSaldoDeudor(new Double(300));
        cuenta0.setSaldoAcreedor(new Double(150));

        coleccion.add(cuenta0);
        
        CuentaBalanza cuenta1 = new CuentaBalanza();
        cuenta1.setId(2);
        cuenta1.setNombre("Deudas por pagar");
        cuenta1.setCodigo("2010");
        cuenta1.setFolioMayor(2);
        cuenta1.setSaldoDeudor(new Double(300));
        cuenta1.setSaldoAcreedor(new Double(150));
        
        coleccion.add(cuenta1);
        
        CuentaBalanza cuenta2 = new CuentaBalanza();
        cuenta2.setId(3);
        cuenta2.setNombre("Ventas");
        cuenta2.setCodigo("3010");
        cuenta2.setFolioMayor(3);
        cuenta2.setSaldoDeudor(new Double(300));
        cuenta2.setSaldoAcreedor(new Double(150));
        
        coleccion.add(cuenta2);
        
        CuentaBalanza cuenta3 = new CuentaBalanza();
        cuenta3.setId(4);
        cuenta3.setNombre("Otras cuentas por cobrar");
        cuenta3.setCodigo("4010");
        cuenta3.setFolioMayor(4);
        cuenta3.setSaldoDeudor(new Double(300));
        cuenta3.setSaldoAcreedor(new Double(150));
        
        coleccion.add(cuenta3);
        
        return coleccion;
    }
}
