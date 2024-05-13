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
import modelo.CicloContableFolio;

/**
 *
 * @author vacev
 */
public class daoCicloContableFolio {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoCuenta _daoCuenta;

    public daoCicloContableFolio(Conexion cx) {
        this.cx = cx;
        this._daoCuenta = new daoCuenta(cx);
    }
    
    public RespuestaGeneral Listar(int idCicloContable, int idTipoCatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CicloContableFolio> lista = new ArrayList<>();
        ResultSet rs = null;
        String sql = """
                    select cb.*
                    from ciclo_contable_folios cb 
                    where cb.id_ciclo_contable = ? 
                    order by case 
                            when cb.folio_mayor = null then 0
                            else cb.folio_mayor
                            end ASC
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idCicloContable);
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloContableFolio cicloFolio = new CicloContableFolio();
                cicloFolio.setId(rs.getInt("id"));
                cicloFolio.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                cicloFolio.setId_cuenta(rs.getInt("id_cuenta"));
                //cicloFolio.setSaldo(rs.getDouble("saldo"));
                cicloFolio.setFolio_mayor(rs.getInt("folio_mayor"));
                
                if (cicloFolio.getId_cuenta() > 0) {
                    RespuestaGeneral rgc = _daoCuenta.ObtenerPorId(cicloFolio.getId_cuenta(), idTipoCatalogo, cicloFolio.getId_ciclo_contable());
                    if (rgc.esExitosa()) {
                        ArrayList<Cuenta> listaCuenta = (ArrayList<Cuenta>)rgc.getDatos();
                        if (!listaCuenta.isEmpty()) {
                            cicloFolio.setCuenta(listaCuenta.get(0));
                        }   
                    }
                }
                
                lista.add(cicloFolio);
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
        ArrayList<CicloContableFolio> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                    select cb.* 
                    from ciclo_contable_folios cb 
                    where cb.id_ciclo_contable = ? and cb.id = ?
                  """;
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idTipoCatalogo);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloContableFolio cicloFolio = new CicloContableFolio();
                cicloFolio.setId(rs.getInt("id"));
                cicloFolio.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                cicloFolio.setId_cuenta(rs.getInt("id_cuenta"));
                //cicloFolio.setSaldo(rs.getDouble("saldo"));
                cicloFolio.setFolio_mayor(rs.getInt("folio_mayor"));
                
                if (cicloFolio.getId_cuenta() > 0) {
                    RespuestaGeneral rgc = _daoCuenta.ObtenerPorId(cicloFolio.getId_cuenta(), idTipoCatalogo, cicloFolio.getId_ciclo_contable());
                    if (rgc.esExitosa()) {
                        ArrayList<Cuenta> listaCuenta = (ArrayList<Cuenta>)rgc.getDatos();
                        if (!listaCuenta.isEmpty()) {
                            cicloFolio.setCuenta(listaCuenta.get(0));
                        }   
                    }
                }
                
                lista.add(cicloFolio);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral buscarIdCuentaPorCicloContable(int id, int idCuenta, int idCicloContable, int folio) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CicloContableFolio> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                    select cb.* 
                    from ciclo_contable_folios cb 
                    where cb.id_ciclo_contable = ? and (cb.id_cuenta = ? or cb.folio_mayor = ?) and cb.id != ?
                  """;
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idCicloContable);
            ps.setInt(2, idCuenta);
            ps.setInt(3, folio);
            ps.setInt(4, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloContableFolio cicloFolio = new CicloContableFolio();
                cicloFolio.setId(rs.getInt("id"));
                cicloFolio.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                cicloFolio.setId_cuenta(rs.getInt("id_cuenta"));
                //cicloFolio.setSaldo(rs.getDouble("saldo"));
                cicloFolio.setFolio_mayor(rs.getInt("folio_mayor"));
                lista.add(cicloFolio);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral buscarIdCuentaPorCicloContableSinId(int id, int idCuenta, int idCicloContable, int folio) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<CicloContableFolio> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                    select cb.* 
                    from ciclo_contable_folios cb 
                    where cb.id_ciclo_contable = ? and (cb.id_cuenta = ? or cb.folio_mayor = ?) and cb.id != ?
                  """;
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idCicloContable);
            ps.setInt(2, idCuenta);
            ps.setInt(3, folio);
            ps.setInt(4, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloContableFolio cicloFolio = new CicloContableFolio();
                cicloFolio.setId(rs.getInt("id"));
                cicloFolio.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                cicloFolio.setId_cuenta(rs.getInt("id_cuenta"));
                //cicloFolio.setSaldo(rs.getDouble("saldo"));
                cicloFolio.setFolio_mayor(rs.getInt("folio_mayor"));
                lista.add(cicloFolio);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertar(CicloContableFolio cicloFolio) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO ciclo_contable_folios     
                  VALUES(null, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, cicloFolio.getId_ciclo_contable());
            ps.setInt(2, cicloFolio.getId_cuenta());
            ps.setInt(3, cicloFolio.getFolio_mayor());
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
    
    public RespuestaGeneral editar(CicloContableFolio cicloFolio) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE ciclo_contable_folios SET 
                        id_ciclo_contable=?,
                        id_cuenta=?,
                        folio_mayor=?
                    WHERE id=?
                  
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, cicloFolio.getId_ciclo_contable());
            ps.setInt(2, cicloFolio.getId_cuenta());
            ps.setInt(3, cicloFolio.getFolio_mayor());
            ps.setInt(4, cicloFolio.getId());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, cicloFolio.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
//    public RespuestaGeneral editarDetallesPartidas(CicloContableFolio cicloFolio) {
//        RespuestaGeneral rg = new RespuestaGeneral();
//        ResultSet rs = null;
//        var sql = """
//                    UPDATE partida_detalle SET
//                        folio_mayor=? 
//                    WHERE id in (
//                        select pd.id from partida p
//                        left join partida_detalle pd on p.id = pd.id_partida
//                        where p.id_ciclo = ? and pd.id_cuenta = ?
//                    )
//                        
//                  """;
//        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
//            ps.setInt(1, cicloFolio.getFolio_mayor());
//            ps.setInt(2, cicloFolio.getId_ciclo_contable());
//            ps.setInt(3, cicloFolio.getId_cuenta());
//            ps.executeUpdate();
//            
//            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, cicloFolio.getId());
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//            String mensaje = e.getMessage().toString();
//            return rg.asServerError(mensaje);
//        }
//    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    DELETE FROM ciclo_contable_folios WHERE id=?
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
