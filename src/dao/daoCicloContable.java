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
import java.util.ArrayList;
import modelo.dtoCicloContable;
/**
 *
 * @author vacev
 */
public class daoCicloContable {
    Conexion cx;

    public daoCicloContable() {
        this.cx = new Conexion();
    }
    
    public ArrayList<dtoCicloContable> ListarCiclosContables() {
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
                cicloContable.setDesde(rs.getString("desde"));
                cicloContable.setHasta(rs.getString("hasta"));
                cicloContable.setCatalogo(rs.getString("catalogo"));
                lista.add(cicloContable);
            }
            cx.desconectar();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
