/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoFormulaParametro;
import modelo.FormulaParametro;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioFormulaParametro {
    daoFormulaParametro _daoFormulaParametro;
    Conexion cx;

    public ServicioFormulaParametro(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this._daoFormulaParametro = new daoFormulaParametro(this.cx);
    }
    
    public RespuestaGeneral obtenerLista(int idCicloContable, int tipoCuentaEspecial) {
        this.cx.conectar();
        RespuestaGeneral rs = this._daoFormulaParametro.Listar(idCicloContable, tipoCuentaEspecial);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id) {
        this.cx.conectar();
        RespuestaGeneral rs = this._daoFormulaParametro.ObtenerPorId(id);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(FormulaParametro formulaP) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        this.cx.conectar();
        rs = this._daoFormulaParametro.insertar(formulaP);
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral editar(FormulaParametro formulaP) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        this.cx.conectar();
        rs = this._daoFormulaParametro.editar(formulaP);
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rg = this._daoFormulaParametro.eliminar(id);
        this.cx.desconectar();
        return rg;
    }
}
