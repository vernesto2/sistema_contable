/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoFormula;
import modelo.Formula;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioFormula {
    daoFormula daoFormula;
    Conexion cx;

    public ServicioFormula(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoFormula = new daoFormula(this.cx);
    }
    
    public RespuestaGeneral obtenerListaPorIdTipoCatalogo(int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoFormula.Listar(idTipoCatalogo);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id, int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoFormula.ObtenerPorId(id, idTipoCatalogo);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(Formula formula) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (formula.getNombre().isEmpty()) {
            rs.setMensaje("No se ha ingresado el nombre");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoFormula.insertar(formula);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral editar(Formula formula) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (formula.getNombre().isEmpty()) {
            rs.setMensaje("No se ha ingresado el nombre");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoFormula.editar(formula);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rg = this.daoFormula.eliminar(id);
        this.cx.desconectar();
        return rg;
    }
    
}
