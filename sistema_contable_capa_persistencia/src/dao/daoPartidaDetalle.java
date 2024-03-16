/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import dto.dtoPartida;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.constantes.RespuestaGeneral;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelo.PartidaDetalle;

/**
 *
 * @author vacev
 */
public class daoPartidaDetalle {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;

    public daoPartidaDetalle(Conexion cx) {
        this.cx = cx;
    }
    
    public RespuestaGeneral ObtenerPorId(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<dtoPartida> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT * FROM partida p where p.id = ?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoPartida partida = new dtoPartida();
                partida.setId(rs.getInt("id"));
                partida.setId_ciclo(rs.getInt("id_ciclo"));
                partida.setId_tipo_partida(rs.getInt("id_tipo_partida"));
                partida.setNum_partida(rs.getInt("num_partida"));
                partida.setComentario(rs.getString("comentario"));
                String sFechaPartida = rs.getString("fecha");
                Date fechaPartida = new Date();
                try {
                    fechaPartida = new SimpleDateFormat("yyyy-MM-dd").parse(sFechaPartida);
                } catch (ParseException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                partida.setFecha(fechaPartida);
                partida.setEliminado(rs.getInt("eliminado") == 0 ? false : true);
                lista.add(partida);
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
