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
import modelo.PartidaDetalle;

/**
 *
 * @author vacev
 */
public class daoPartidaDetalle {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoCuenta daoCuenta;
    
    public daoPartidaDetalle(Conexion cx) {
        this.cx = cx;
        this.daoCuenta = new daoCuenta(this.cx);
    }
    
    public RespuestaGeneral ObtenerPorIdPartida(int idPartida, int idTipoCatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<PartidaDetalle> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT * FROM partida_detalle p where p.id_partida = ? and p.eliminado = 0
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idPartida);
            rs = ps.executeQuery();
            while (rs.next()) {
                PartidaDetalle pDetalle = new PartidaDetalle();
                pDetalle.setId(rs.getInt("id"));
                pDetalle.setId_partida(rs.getInt("id_partida"));
                pDetalle.setId_cuenta(rs.getInt("id_cuenta"));
                pDetalle.setParcial(rs.getDouble("parcial"));
                pDetalle.setDebe(rs.getDouble("debe"));
                pDetalle.setHaber(rs.getDouble("haber"));
                pDetalle.setTipo_cargo_abono(rs.getInt("tipo_cargo_abono"));
                pDetalle.setFolio_mayor(rs.getInt("folio_mayor"));
                
                // obtenemos la cuenta para que vaya tipado
                pDetalle.setCuenta(new Cuenta());
                RespuestaGeneral rgc = daoCuenta.ObtenerPorId(pDetalle.getId_cuenta(), idTipoCatalogo);
                if (rgc.esExitosa()) {
                    ArrayList<Cuenta> listaCuenta = (ArrayList<Cuenta>)rgc.getDatos();
                    if (!listaCuenta.isEmpty()) {
                        pDetalle.setCuenta(listaCuenta.get(0));
                    }   
                }
                
                lista.add(pDetalle);
            }
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertar(PartidaDetalle pDetalle) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO partida_detalle     
                  VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, pDetalle.getId_partida());
            ps.setInt(2, pDetalle.getId_cuenta());
            ps.setDouble(3, pDetalle.getParcial());
            ps.setDouble(4, pDetalle.getDebe());
            ps.setDouble(5, pDetalle.getHaber());
            ps.setInt(6, pDetalle.getTipo_cargo_abono());
            ps.setInt(7, 0);
            ps.setInt(8, pDetalle.getFolio_mayor());
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
    
    public RespuestaGeneral editar(PartidaDetalle pDetalle) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE partida_detalle SET 
                        id_partida=?,
                        id_cuenta=?,
                        parcial=?,
                        debe=?,
                        haber=?,
                        tipo_cargo_abono=?,
                        folio_mayor=?
                    WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, pDetalle.getId_partida());
            ps.setInt(2, pDetalle.getId_cuenta());
            ps.setDouble(3, pDetalle.getParcial());
            ps.setDouble(4, pDetalle.getDebe());
            ps.setDouble(5, pDetalle.getHaber());
            ps.setInt(6, pDetalle.getTipo_cargo_abono());
            ps.setInt(7, pDetalle.getFolio_mayor());
            ps.setInt(8, pDetalle.getId());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, pDetalle.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE partida_detalle SET eliminado=? WHERE id=?
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
