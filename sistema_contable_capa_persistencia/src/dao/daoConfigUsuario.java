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
import modelo.CicloContable;
import modelo.ConfiguracionUsuario;
import modelo.TipoCatalogo;

/**
 *
 * @author vacev
 */
public class daoConfigUsuario {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoCicloContable _daoCicloContable = null;

    public daoConfigUsuario(Conexion cx) {
        this.cx = cx;
        _daoCicloContable = new daoCicloContable(this.cx);
    }
    
    public RespuestaGeneral ObtenerPorIdUsuario(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<ConfiguracionUsuario> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  SELECT 
                    cu.*
                    ,ifnull(cc.titulo, '') as nombre_ciclo_contable
                    ,ifnull(tc.tipo, '') as nombre_catalogo 
                  
                  FROM configuracion_usuario cu 
                  left join ciclo_contable cc on cu.id_ciclo_contable = cc.id
                  left join tipo_catalogo tc on cc.id_catalogo = tc.id
                  where cu.id_usuario = ? 
                  limit 1
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ConfiguracionUsuario usuario = new ConfiguracionUsuario();
                usuario.setId(rs.getInt("id"));
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                usuario.setAvatar(rs.getString("avatar"));
                usuario.setNombre_ciclo_contable(rs.getString("nombre_ciclo_contable"));
                usuario.setNombre_catalogo(rs.getString("nombre_catalogo"));
                // obtenemos el cicloContable segun el idCicloContable
                usuario.setCicloContable(new CicloContable());
                if (usuario.getId_ciclo_contable() > 0) {
                    RespuestaGeneral rg1 = _daoCicloContable.ObtenerPorId(usuario.getId_ciclo_contable());
                    if (rg1.esExitosa()) {
                        ArrayList<CicloContable> listaCicloContable = (ArrayList<CicloContable>)rg1.getDatos();
                        usuario.setCicloContable(listaCicloContable.get(0));
                    }
                }
                lista.add(usuario);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertar(ConfiguracionUsuario usuario) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO configuracion_usuario     
                  VALUES(null, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, usuario.getId_usuario());
            ps.setInt(2, usuario.getId_ciclo_contable());
            ps.setString(3, usuario.getAvatar());
            
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
    
    public RespuestaGeneral editar(ConfiguracionUsuario usuario) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE configuracion_usuario SET 
                        id_ciclo_contable=?
                        ,avatar=? 
                    WHERE id_usuario=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, usuario.getId_ciclo_contable());
            ps.setString(2, usuario.getAvatar());
            ps.setInt(3, usuario.getId_usuario());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, usuario.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
}
