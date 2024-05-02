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
import modelo.Cuenta;
import modelo.CuentaBalance;

/**
 *
 * @author vacev
 */
public class daoCuentaBalance {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoCuenta _daoCuenta;

    public daoCuentaBalance(Conexion cx) {
        this.cx = cx;
        this._daoCuenta = new daoCuenta(cx);
    }
    
    public RespuestaGeneral Listar(int idCicloContable, int idTipoCatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CuentaBalance> lista = new ArrayList<>();
        ResultSet rs = null;
        String sql = """
                    select cb.* 
                    from cuenta_balance cb 
                    where cb.id_ciclo_contable = ? 
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idCicloContable);
            rs = ps.executeQuery();
            while (rs.next()) {
                CuentaBalance cBalance = new CuentaBalance();
                cBalance.setId(rs.getInt("id"));
                cBalance.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                cBalance.setId_cuenta(rs.getInt("id_cuenta"));
                cBalance.setSaldo_inicial(rs.getDouble("saldo_inicial"));
                cBalance.setSaldo_final(rs.getDouble("saldo_final"));
                cBalance.setFolio_mayor(rs.getInt("folio_mayor"));
                
                if (cBalance.getId_cuenta() > 0) {
                    RespuestaGeneral rgc = _daoCuenta.ObtenerPorId(cBalance.getId_cuenta(), idTipoCatalogo);
                    if (rgc.esExitosa()) {
                        ArrayList<Cuenta> listaCuenta = (ArrayList<Cuenta>)rgc.getDatos();
                        if (!listaCuenta.isEmpty()) {
                            cBalance.setCuenta(listaCuenta.get(0));
                        }   
                    }
                }
                
                lista.add(cBalance);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ObtenerPorId(int id, int idCicloContable, int idTipoCatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CuentaBalance> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                    select cb.* 
                    from cuenta_balance cb 
                    where cb.id_ciclo_contable = ? and cb.id = ?
                  """;
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idTipoCatalogo);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CuentaBalance cBalance = new CuentaBalance();
                cBalance.setId(rs.getInt("id"));
                cBalance.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                cBalance.setId_cuenta(rs.getInt("id_cuenta"));
                cBalance.setSaldo_inicial(rs.getDouble("saldo_inicial"));
                cBalance.setSaldo_final(rs.getDouble("saldo_final"));
                cBalance.setFolio_mayor(rs.getInt("folio_mayor"));
                
                if (cBalance.getId_cuenta() > 0) {
                    RespuestaGeneral rgc = _daoCuenta.ObtenerPorId(cBalance.getId_cuenta(), idTipoCatalogo);
                    if (rgc.esExitosa()) {
                        ArrayList<Cuenta> listaCuenta = (ArrayList<Cuenta>)rgc.getDatos();
                        if (!listaCuenta.isEmpty()) {
                            cBalance.setCuenta(listaCuenta.get(0));
                        }   
                    }
                }
                
                lista.add(cBalance);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral buscarIdCuentaPorCicloContable(int id, int idCuenta, int idCicloContable) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CuentaBalance> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                    select cb.* 
                    from cuenta_balance cb 
                    where cb.id_ciclo_contable = ? and cb.id_cuenta = ? and cb.id != ?
                  """;
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idCicloContable);
            ps.setInt(2, idCuenta);
            ps.setInt(3, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CuentaBalance cBalance = new CuentaBalance();
                cBalance.setId(rs.getInt("id"));
                cBalance.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                cBalance.setId_cuenta(rs.getInt("id_cuenta"));
                cBalance.setSaldo_inicial(rs.getDouble("saldo_inicial"));
                cBalance.setSaldo_final(rs.getDouble("saldo_final"));
                cBalance.setFolio_mayor(rs.getInt("folio_mayor"));
                lista.add(cBalance);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertar(CuentaBalance cBalance) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO cuenta_balance     
                  VALUES(null, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, cBalance.getId_ciclo_contable());
            ps.setInt(2, cBalance.getId_cuenta());
            ps.setDouble(3, cBalance.getSaldo_inicial());
            ps.setDouble(4, cBalance.getSaldo_final());
            ps.setInt(5, cBalance.getFolio_mayor());
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
    
    public RespuestaGeneral editar(CuentaBalance cBalance) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE cuenta_balance SET 
                        id_ciclo_contable=?,
                        id_cuenta=?,
                        saldo_inicial=?,
                        saldo_final=?,
                        folio_mayor=?
                    WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, cBalance.getId_ciclo_contable());
            ps.setInt(2, cBalance.getId_cuenta());
            ps.setDouble(3, cBalance.getSaldo_inicial());
            ps.setDouble(4, cBalance.getSaldo_final());
            ps.setInt(5, cBalance.getFolio_mayor());
            ps.setInt(6, cBalance.getId());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, cBalance.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    DELETE FROM cuenta_balance WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            
            return rg.asOk(RespuestaGeneral.ELIMINADO_CORRECTAMENTE, ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
}
