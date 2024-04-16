/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import dto.dtoFormula;
import utils.constantes.RespuestaGeneral;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Cuenta;
import modelo.Formula;
import modelo.TipoCatalogo;

/**
 *
 * @author vacev
 */
public class daoFormula {
    
    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoCuenta _daoCuenta;
    daoTipoCatalogo _daoTipoCatalogo;

    public daoFormula(Conexion cx) {
        this.cx = cx;
        this._daoCuenta = new daoCuenta(cx);
        this._daoTipoCatalogo = new daoTipoCatalogo(cx);
    }
    
    public RespuestaGeneral Listar(int idTipoCatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<dtoFormula> lista = new ArrayList<>();
        ResultSet rs = null;
        String sql = """
                    select f.* 
                    from formula f 
                    where f.id_tipo_catalogo = ? and f.eliminado = 0
                     order by cast(f.posicion as NUMERIC(16,2))
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idTipoCatalogo);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoFormula formula = new dtoFormula();
                Formula formulaAux = new Formula();
                formulaAux.setId(rs.getInt("id"));
                formulaAux.setId_tipo_catalogo(rs.getInt("id_tipo_catalogo"));
                formulaAux.setId_cuenta(rs.getInt("id_cuenta"));
                formulaAux.setSigno(rs.getString("signo"));
                formulaAux.setNombre(rs.getString("nombre"));
                formulaAux.setTipo_cuenta_especial(rs.getInt("tipo_cuenta_especial"));
                formulaAux.setPosicion(rs.getString("posicion"));
                formulaAux.setId_formula(rs.getInt("id_formula"));
                formulaAux.setTipo_formula(rs.getString("tipo_formula"));
                formulaAux.setEliminado(rs.getInt("eliminado"));
                
                // obtenemos la cuenta para que vaya tipado
                formulaAux.setCuenta(new Cuenta());
                if (formulaAux.getId_cuenta() > 0) {
                    RespuestaGeneral rgc = _daoCuenta.ObtenerPorId(formulaAux.getId_cuenta(), idTipoCatalogo);
                    if (rgc.esExitosa()) {
                        ArrayList<Cuenta> listaCuenta = (ArrayList<Cuenta>)rgc.getDatos();
                        if (!listaCuenta.isEmpty()) {
                            formulaAux.setCuenta(listaCuenta.get(0));
                        }   
                    }
                }
                
                // obtenemos la formula papa para que vaya tipado
                formula.setFormulaPadre(new Formula());
                if (formulaAux.getId_formula() > 0) {
                    RespuestaGeneral rgf = ObtenerPorId(formulaAux.getId_formula(), idTipoCatalogo);
                    if (rgf.esExitosa()) {
                        ArrayList<dtoFormula> listaFormula = (ArrayList<dtoFormula>)rgf.getDatos();
                        if (!listaFormula.isEmpty()) {
                            formula.setFormulaPadre(listaFormula.get(0).getFormula());
                        }   
                    }
                }
                
                // obtenemos el tipo de catalogo papa para que vaya tipado
                formulaAux.setTipoCatalogo(new TipoCatalogo());
                RespuestaGeneral rgt = _daoTipoCatalogo.ObtenerPorId(idTipoCatalogo);
                if (rgt.esExitosa()) {
                    ArrayList<TipoCatalogo> listaTipoCatalogo = (ArrayList<TipoCatalogo>)rgt.getDatos();
                    if (!listaTipoCatalogo.isEmpty()) {
                        formulaAux.setTipoCatalogo(listaTipoCatalogo.get(0));
                    }   
                }
                formula.setFormula(formulaAux);
                
                lista.add(formula);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral ObtenerPorId(int id, int idTipoCatalogo) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<dtoFormula> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select f.* 
                  from formula f 
                  where f.id_tipo_catalogo = ? and f.id = ? and f.eliminado = 0
                  order by cast(f.posicion as NUMERIC(16,2))
                  """;
        
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idTipoCatalogo);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                dtoFormula formula = new dtoFormula();
                Formula formulaAux = new Formula();
                formulaAux.setId(rs.getInt("id"));
                formulaAux.setId_tipo_catalogo(rs.getInt("id_tipo_catalogo"));
                formulaAux.setId_cuenta(rs.getInt("id_cuenta"));
                formulaAux.setSigno(rs.getString("signo"));
                formulaAux.setNombre(rs.getString("nombre"));
                formulaAux.setTipo_cuenta_especial(rs.getInt("tipo_cuenta_especial"));
                formulaAux.setPosicion(rs.getString("posicion"));
                formulaAux.setId_formula(rs.getInt("id_formula"));
                formulaAux.setTipo_formula(rs.getString("tipo_formula"));
                formulaAux.setEliminado(rs.getInt("eliminado"));
                formula.setFormula(formulaAux);
                formula.setFormulaPadre(new Formula());
                lista.add(formula);
            }
            
            return rg.asOk("", lista);
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral insertar(Formula formula) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO formula     
                  VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, formula.getId_tipo_catalogo());
            ps.setInt(2, formula.getId_cuenta());
            ps.setString(3, formula.getSigno());
            ps.setString(4, formula.getNombre());
            ps.setInt(5, formula.getTipo_cuenta_especial());
            ps.setString(6, formula.getPosicion());
            ps.setInt(7, formula.getId_formula());
            ps.setString(8, formula.getTipo_formula());
            ps.setInt(9, formula.getEliminado());
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
    
    public RespuestaGeneral editar(Formula formula) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE formula SET 
                        id_tipo_catalogo=?,
                        id_cuenta=?,
                        signo=?,
                        nombre=?,
                        tipo_cuenta_especial=?,
                        posicion=?,
                        id_formula=?,
                        tipo_formula=?
                    WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, formula.getId_tipo_catalogo());
            ps.setInt(2, formula.getId_cuenta());
            ps.setString(3, formula.getSigno());
            ps.setString(4, formula.getNombre());
            ps.setInt(5, formula.getTipo_cuenta_especial());
            ps.setString(6, formula.getPosicion());
            ps.setInt(7, formula.getId_formula());
            ps.setString(8, formula.getTipo_formula());
            ps.setInt(9, formula.getId());
            ps.executeUpdate();
            
            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, formula.getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    UPDATE formula SET eliminado=? WHERE id=?
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
