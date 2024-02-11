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
import modelo.dtoCicloContable;
import vista.vCicloContable;
/**
 *
 * @author vacev
 */
public class daoCicloContable {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;

    public daoCicloContable() {
        this.cx = new Conexion();
    }
    
    public RespuestaGeneral ListarCiclosContables() {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<dtoCicloContable> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select cc.*, tc.tipo as catalogo from ciclo_contable cc
                  left join tipo_catalogo tc on cc.id_catalogo = tc.id
                  """;
        try (PreparedStatement ps = cx.conectar().prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoCicloContable cicloContable = new dtoCicloContable();
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
                    Logger.getLogger(vCicloContable.class.getName()).log(Level.SEVERE, null, ex);
                }
                cicloContable.setDesde(desde);
                cicloContable.setHasta(hasta);
                cicloContable.setCatalogo(rs.getString("catalogo"));
                lista.add(cicloContable);
            }
            ps.close();
            cx.desconectar();
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asBadRequest(mensaje);
        }
    }
    
    public RespuestaGeneral insertarCicloContable(CicloContable ccontable) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                  INSERT INTO ciclo_contable     
                  VALUES(null, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.conectar().prepareStatement(sql)) {
            ps.setInt(1, ccontable.getId_catalogo());
            ps.setString(2, ccontable.getTitulo());
            ps.setString(3, sdfString.format(ccontable.getDesde()));
            ps.setString(4, sdfString.format(ccontable.getHasta()));
            ps.setInt(5, 0);
            
            ps.executeUpdate();
            ps.close();
            cx.desconectar();
            return rg.asCreated(RespuestaGeneral.GUARDADO_CORRECTAMENTE, ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asBadRequest(mensaje);
        }
    }
    
    public RespuestaGeneral editarCicloContable(CicloContable ccontable) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE ciclo_contable SET id_catalogo=?,titulo=?,desde=?,hasta=?,eliminado=? WHERE id=?
                  """;
        try (PreparedStatement ps = cx.conectar().prepareStatement(sql)) {
            ps.setInt(1, ccontable.getId_catalogo());
            ps.setString(2, ccontable.getTitulo());
            ps.setString(3, sdfString.format(ccontable.getDesde()));
            ps.setString(4, sdfString.format(ccontable.getHasta()));
            ps.setInt(5, ccontable.isEliminado() ? 1 : 0);
            ps.setInt(6, ccontable.getId());
            ps.executeUpdate();
            ps.close();
            cx.desconectar();
            return rg.asCreated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asBadRequest(mensaje);
        }
    }
    
    public RespuestaGeneral eliminarCicloContable(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE ciclo_contable SET eliminado=? WHERE id=?
                  """;
        try (PreparedStatement ps = cx.conectar().prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();
            cx.desconectar();
            ps.close();
            return rg.asOk(RespuestaGeneral.ELIMINADO_CORRECTAMENTE, ps);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asBadRequest(mensaje);
        }
    }
}
