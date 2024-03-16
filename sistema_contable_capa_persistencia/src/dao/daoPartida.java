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
import modelo.Partida;

/**
 *
 * @author vacev
 */
public class daoPartida {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoPartidaDetalle _pDetalle;
    int id = -1;

    public daoPartida(Conexion cx) {
        this.cx = cx;
        _pDetalle = new daoPartidaDetalle(cx);
    }
    
    public RespuestaGeneral Listar(int idCicloContable, String busqueda) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<dtoPartida> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select 
                  	p.*
                  	,pr.totalDebe as monto 
                  from partida p
                  inner join(
                  	select 
                            sum(pd.debe) totalDebe
                            ,pd.id_partida 
                        from partida_detalle pd 
                        where pd.eliminado = 0 
                        GROUP by pd.id_partida
                  )pr on pr.id_partida = p.id 
                  where p.eliminado = 0 and p.id_ciclo = ?
                  order by p.num_partida
                  """;
        String newSql = sql.replaceAll("busqueda", busqueda);
        try (PreparedStatement ps = cx.getCx().prepareStatement(newSql)) {
            ps.setInt(1, idCicloContable);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoPartida partida = new dtoPartida();
                partida.setId(rs.getInt("id"));
                partida.setId_ciclo(rs.getInt("id_ciclo"));
                partida.setId_tipo_partida(rs.getInt("id_tipo_partida"));
                partida.setNum_partida(rs.getInt("num_partida"));
                partida.setMonto(rs.getDouble("monto"));
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
    
    public RespuestaGeneral ObtenerUltimoNumPartida() {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<Partida> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT p.* FROM partida p where p.eliminado = 0 order by p.id desc limit 1
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(-1);
                partida.setId_ciclo(-1);
                partida.setId_tipo_partida(-1);
                partida.setNum_partida((rs.getInt("num_partida")) + 1);
                partida.setComentario("");
                partida.setFecha(new Date());
                partida.setEliminado(false);
                lista.add(partida);
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
    
    public RespuestaGeneral insertar(Partida partida) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO partida     
                  VALUES(null, ?, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, partida.getId_ciclo());
            ps.setInt(2, partida.getId_tipo_partida());
            ps.setInt(3, partida.getNum_partida());
            ps.setString(4, partida.getComentario());
            ps.setString(5, sdfString.format(partida.getFecha()));
            ps.setInt(6, 0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            id = -1;
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            // procedemos a guardar los nuevos detalles
            partida.getListaPartidaDetalles().forEach((t) -> {
                t.setId_partida(id);
                RespuestaGeneral rgh = _pDetalle.insertar(t);
                if (!rgh.esExitosa()) {
                    rg.setMensaje("No se guardaron todos los detalles");
                }
            });
            
            return rg.asCreated(RespuestaGeneral.GUARDADO_CORRECTAMENTE, id);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral editar(Partida partida) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE partida SET 
                        id_ciclo=?,
                        id_tipo_partida=?,
                        num_partida=?,
                        comentario=?,
                        fecha=?
                    WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, partida.getId_ciclo());
            ps.setInt(2, partida.getId_tipo_partida());
            ps.setInt(3, partida.getNum_partida());
            ps.setString(4, partida.getComentario());
            ps.setString(5, sdfString.format(partida.getFecha()));
            ps.setInt(6, partida.getId());
            ps.executeUpdate();
            
            // procedemos a guardar o actualizar los detalles de partida
            partida.getListaPartidaDetalles().forEach((t) -> {
                t.setId_partida(id);
                // verificamos si es un nuevo detalle
                if (t.getId() > 0) {
                    RespuestaGeneral rgh = _pDetalle.editar(t);
                    if (!rgh.esExitosa()) {
                        rg.setMensaje("No se guardaron todos los detalles");
                    }
                } else {
                    RespuestaGeneral rgh = _pDetalle.insertar(t);
                    if (!rgh.esExitosa()) {
                        rg.setMensaje("No se guardaron todos los detalles");
                    }
                }
                
            });
            
            // eliminamos los detalles que se hayan eliminado
            partida.getListaPartidaDetallesEliminados().forEach((t) -> {
                RespuestaGeneral rgh = _pDetalle.eliminar(t.getId());
                if (!rgh.esExitosa()) {
                    rg.setMensaje("No se eliminaron los detalles");
                }
            });
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, partida.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE partida SET eliminado=? WHERE id=?
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
