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
import modelo.CicloContable;
import modelo.TipoCatalogo;

/**
 *
 * @author vacev
 */
public class daoCicloContable {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoTipoCatalogo _tipoCatalogo = null;

    public daoCicloContable(Conexion cx) {
        this.cx = cx;
        _tipoCatalogo = new daoTipoCatalogo(this.cx);
    }
    
    public RespuestaGeneral ListarCiclosContables(String busqueda) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CicloContable> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select 
                    cc.* 
                  from ciclo_contable cc
                  where cc.eliminado = 0 and cc.titulo like '%paramBusqueda%'
                  """;
        String newSql = sql.replaceAll("paramBusqueda", busqueda);
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloContable cicloContable = new CicloContable();
                cicloContable.setId(rs.getInt("id"));
                cicloContable.setId_catalogo(rs.getInt("id_catalogo"));
                cicloContable.setTitulo(rs.getString("titulo"));
                String sDesde = rs.getString("desde");
                String sHasta = rs.getString("hasta");
                Date desde = new Date();
                Date hasta = new Date();
                try {
                    desde = new SimpleDateFormat("yyyy-MM-dd").parse(sDesde);
                    hasta = new SimpleDateFormat("yyyy-MM-dd").parse(sHasta);
                } catch (ParseException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                cicloContable.setDesde(desde);
                cicloContable.setHasta(hasta);
                cicloContable.setTipo_sociedad(rs.getInt("tipo_sociedad"));
                cicloContable.setPorcentaje_reserva_legal(rs.getDouble("porcentaje_reserva_legal"));
                cicloContable.setMonto_maximo_ventas(rs.getDouble("monto_maximo_ventas"));
                cicloContable.setPorcentaje_min(rs.getDouble("porcentaje_min"));
                cicloContable.setPorcentaje_max(rs.getDouble("porcentaje_max"));
                cicloContable.setSin_libro_diario(rs.getInt("sin_libro_diario"));
                cicloContable.setTipoCatalogo(new TipoCatalogo());
                if (cicloContable.getId_catalogo() > 0) {
                    RespuestaGeneral rg1 = _tipoCatalogo.ObtenerPorId(cicloContable.getId_catalogo());
                    if (rg1.esExitosa()) {
                        ArrayList<TipoCatalogo> listaTipoCatalogo = (ArrayList<TipoCatalogo>)rg1.getDatos();
                        cicloContable.setTipoCatalogo(listaTipoCatalogo.get(0));
                    }
                }
                lista.add(cicloContable);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ObtenerPorIdTipoCatalogo(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CicloContable> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select * from ciclo_contable cc where cc.id_catalogo = ? and cc.eliminado = 0
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloContable cicloContable = new CicloContable();
                cicloContable.setId(rs.getInt("id"));
                cicloContable.setId_catalogo(rs.getInt("id_catalogo"));
                cicloContable.setTitulo(rs.getString("titulo"));
                String sDesde = rs.getString("desde");
                String sHasta = rs.getString("hasta");
                Date desde = new Date();
                Date hasta = new Date();
                try {
                    desde = new SimpleDateFormat("yyyy-MM-dd").parse(sDesde);
                    hasta = new SimpleDateFormat("yyyy-MM-dd").parse(sHasta);
                } catch (ParseException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                cicloContable.setDesde(desde);
                cicloContable.setHasta(hasta);
                cicloContable.setTipo_sociedad(rs.getInt("tipo_sociedad"));
                cicloContable.setPorcentaje_reserva_legal(rs.getDouble("porcentaje_reserva_legal"));
                cicloContable.setMonto_maximo_ventas(rs.getDouble("monto_maximo_ventas"));
                cicloContable.setPorcentaje_min(rs.getDouble("porcentaje_min"));
                cicloContable.setPorcentaje_max(rs.getDouble("porcentaje_max"));
                cicloContable.setSin_libro_diario(rs.getInt("sin_libro_diario"));
                lista.add(cicloContable);
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
        ArrayList<CicloContable> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select * from ciclo_contable cc where cc.id = ? and cc.eliminado = 0
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloContable cicloContable = new CicloContable();
                cicloContable.setId(rs.getInt("id"));
                cicloContable.setId_catalogo(rs.getInt("id_catalogo"));
                cicloContable.setTitulo(rs.getString("titulo"));
                String sDesde = rs.getString("desde");
                String sHasta = rs.getString("hasta");
                Date desde = new Date();
                Date hasta = new Date();
                try {
                    desde = new SimpleDateFormat("yyyy-MM-dd").parse(sDesde);
                    hasta = new SimpleDateFormat("yyyy-MM-dd").parse(sHasta);
                } catch (ParseException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                cicloContable.setDesde(desde);
                cicloContable.setHasta(hasta);
                cicloContable.setTipo_sociedad(rs.getInt("tipo_sociedad"));
                cicloContable.setPorcentaje_reserva_legal(rs.getDouble("porcentaje_reserva_legal"));
                cicloContable.setMonto_maximo_ventas(rs.getDouble("monto_maximo_ventas"));
                cicloContable.setPorcentaje_min(rs.getDouble("porcentaje_min"));
                cicloContable.setPorcentaje_max(rs.getDouble("porcentaje_max"));
                cicloContable.setSin_libro_diario(rs.getInt("sin_libro_diario"));
                cicloContable.setTipoCatalogo(new TipoCatalogo());
                if (cicloContable.getId_catalogo() > 0) {
                    RespuestaGeneral rg1 = _tipoCatalogo.ObtenerPorId(cicloContable.getId_catalogo());
                    if (rg1.esExitosa()) {
                        ArrayList<TipoCatalogo> listaTipoCatalogo = (ArrayList<TipoCatalogo>)rg1.getDatos();
                        cicloContable.setTipoCatalogo(listaTipoCatalogo.get(0));
                    }
                }
                lista.add(cicloContable);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertarCicloContable(CicloContable ccontable) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO ciclo_contable     
                  VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, ccontable.getId_catalogo());
            ps.setString(2, ccontable.getTitulo());
            ps.setString(3, sdfString.format(ccontable.getDesde()));
            ps.setString(4, sdfString.format(ccontable.getHasta()));
            ps.setInt(5, ccontable.getTipo_sociedad());
            ps.setDouble(6, ccontable.getPorcentaje_reserva_legal());
            ps.setDouble(7, ccontable.getMonto_maximo_ventas());
            ps.setDouble(8, ccontable.getPorcentaje_min());
            ps.setDouble(9, ccontable.getPorcentaje_max());
            ps.setInt(10, 0);
            ps.setDouble(11, ccontable.getSin_libro_diario());
            
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
    
    public RespuestaGeneral editarCicloContable(CicloContable ccontable) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE ciclo_contable SET 
                        id_catalogo=?
                        ,titulo=?
                        ,desde=?
                        ,hasta=?
                        ,tipo_sociedad=?
                        ,porcentaje_reserva_legal=?
                        ,monto_maximo_ventas=?
                        ,porcentaje_min=?
                        ,porcentaje_max=?
                        ,eliminado=?
                        ,sin_libro_diario=?
                  WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, ccontable.getId_catalogo());
            ps.setString(2, ccontable.getTitulo());
            ps.setString(3, sdfString.format(ccontable.getDesde()));
            ps.setString(4, sdfString.format(ccontable.getHasta()));
            ps.setInt(5, ccontable.getTipo_sociedad());
            ps.setDouble(6, ccontable.getPorcentaje_reserva_legal());
            ps.setDouble(7, ccontable.getMonto_maximo_ventas());
            ps.setDouble(8, ccontable.getPorcentaje_min());
            ps.setDouble(9, ccontable.getPorcentaje_max());
            ps.setInt(10, ccontable.isEliminado() ? 1 : 0);
            ps.setDouble(11, ccontable.getSin_libro_diario());
            ps.setInt(12, ccontable.getId());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, ccontable.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral eliminarCicloContable(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE ciclo_contable SET eliminado=? WHERE id=?
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
