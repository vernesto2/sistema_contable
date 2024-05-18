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
import modelo.Formula;
import modelo.FormulaParametro;

/**
 *
 * @author vacev
 */
public class daoFormulaParametro {

    SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd");
    Conexion cx;
    daoCicloContable _daoCicloContable;
    daoFormula _daoFormula;

    public daoFormulaParametro(Conexion cx) {
        this.cx = cx;
        this._daoCicloContable = new daoCicloContable(cx);
        this._daoFormula = new daoFormula(cx);
    }

    public RespuestaGeneral Listar(int idCicloContable, int tipoCuentaEspecial) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ArrayList<FormulaParametro> lista = new ArrayList<>();
        ResultSet rs = null;
        String sql = """
                    select * from (
                    	select 
                            case 
                                when fp.id is null then -1
                                else fp.id
                            end as id
                            ,f.id as id_formula
                            ,cc.id as id_ciclo_contable
                            ,case 
                                when fp.valor is null then 0
                                else fp.valor
                            end as valor
                            ,f.eliminado
                    		
                    	from formula f
                    	left join ciclo_contable cc on cc.id_catalogo = f.id_tipo_catalogo and cc.eliminado = 0
                    	left join formula_parametro fp on cc.id = fp.id_ciclo_contable and fp.id_formula = f.id
                    	where cc.id = ? and f.tipo_cuenta_especial = ?
                    )tb where tb.id > 0 or tb.eliminado = 0
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, idCicloContable);
            ps.setInt(2, tipoCuentaEspecial);
            rs = ps.executeQuery();
            while (rs.next()) {
                FormulaParametro formulaAux = new FormulaParametro();
                formulaAux.setId(rs.getInt("id"));
                formulaAux.setId_formula(rs.getInt("id_formula"));
                formulaAux.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                formulaAux.setValor(rs.getDouble("valor"));
                formulaAux.setEliminado(rs.getInt("eliminado"));

                // obtenemos la cuenta para que vaya tipado
                formulaAux.setCicloContable(new CicloContable());
                if (formulaAux.getId_ciclo_contable() > 0) {
                    RespuestaGeneral rgc = _daoCicloContable.ObtenerPorId(formulaAux.getId_ciclo_contable());
                    if (rgc.esExitosa()) {
                        ArrayList<CicloContable> listaCicloContable = (ArrayList<CicloContable>) rgc.getDatos();
                        if (!listaCicloContable.isEmpty()) {
                            formulaAux.setCicloContable(listaCicloContable.get(0));
                        }
                    }
                }

                // obtenemos la formula papa para que vaya tipado
                formulaAux.setFormula(new Formula());
                if (formulaAux.getId_formula() > 0) {
                    RespuestaGeneral rgf = _daoFormula.ObtenerPorIdUnico(formulaAux.getId_formula());
                    if (rgf.esExitosa()) {
                        ArrayList<Formula> listaFormula = (ArrayList<Formula>) rgf.getDatos();
                        if (!listaFormula.isEmpty()) {
                            formulaAux.setFormula(listaFormula.get(0));
                        }
                    }
                }

                lista.add(formulaAux);
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
        ArrayList<FormulaParametro> lista = new ArrayList<>();
        ResultSet rs = null;
        var sql = """
                  select * from formula_parametro p where p.id=?
                  """;

        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                FormulaParametro formulaAux = new FormulaParametro();
                formulaAux.setId(rs.getInt("id"));
                formulaAux.setId_formula(rs.getInt("id_formula"));
                formulaAux.setId_ciclo_contable(rs.getInt("id_ciclo_contable"));
                formulaAux.setValor(rs.getDouble("valor"));

                // obtenemos la cuenta para que vaya tipado
                formulaAux.setCicloContable(new CicloContable());
                if (formulaAux.getId_ciclo_contable() > 0) {
                    RespuestaGeneral rgc = _daoCicloContable.ObtenerPorId(formulaAux.getId_ciclo_contable());
                    if (rgc.esExitosa()) {
                        ArrayList<CicloContable> listaCicloContable = (ArrayList<CicloContable>) rgc.getDatos();
                        if (!listaCicloContable.isEmpty()) {
                            formulaAux.setCicloContable(listaCicloContable.get(0));
                        }
                    }
                }

                // obtenemos la formula papa para que vaya tipado
                formulaAux.setFormula(new Formula());
                if (formulaAux.getId_formula() > 0) {
                    RespuestaGeneral rgf = _daoFormula.ObtenerPorIdUnico(formulaAux.getId_formula());
                    if (rgf.esExitosa()) {
                        ArrayList<Formula> listaFormula = (ArrayList<Formula>) rgf.getDatos();
                        if (!listaFormula.isEmpty()) {
                            formulaAux.setFormula(listaFormula.get(0));
                        }
                    }
                }

                lista.add(formulaAux);
            }

            return rg.asOk("", lista);

        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }

    public RespuestaGeneral insertar(FormulaParametro formulaP) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                  INSERT INTO formula_parametro     
                  VALUES(null, ?, ?, ?)
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, formulaP.getId_formula());
            ps.setInt(2, formulaP.getId_ciclo_contable());
            ps.setDouble(3, formulaP.getValor());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            return rg.asCreated(RespuestaGeneral.GUARDADO_CORRECTAMENTE, id);

        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }

    public RespuestaGeneral editar(FormulaParametro formulaP) {
        RespuestaGeneral rg = new RespuestaGeneral();
        ResultSet rs = null;
        var sql = """
                    UPDATE formula_parametro SET 
                        id_formula=?,
                        id_ciclo_contable=?,
                        valor=?
                    WHERE id=?
                  """;
        try (PreparedStatement ps = cx.getCx().prepareStatement(sql)) {
            ps.setInt(1, formulaP.getId_formula());
            ps.setInt(2, formulaP.getId_ciclo_contable());
            ps.setDouble(3, formulaP.getValor());
            ps.setInt(4, formulaP.getId());
            ps.executeUpdate();

            return rg.asUpdated(RespuestaGeneral.ACTUALIZADO_CORRECTAMENTE, formulaP.getId());

        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toString();
            return rg.asServerError(mensaje);
        }
    }

    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rg = new RespuestaGeneral();
        var sql = """
                    DELETE FROM formula_parametro WHERE id=?
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
