/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCuentaBalance;
import java.util.ArrayList;
import modelo.CuentaBalance;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioCuentaBalance {
    daoCuentaBalance daoCuentaBalance;
    Conexion cx;

    public ServicioCuentaBalance(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoCuentaBalance = new daoCuentaBalance(this.cx);
    }
    
    public RespuestaGeneral obtenerListaPorIdCicloContable(int idCicloContable, int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuentaBalance.Listar(idCicloContable, idTipoCatalogo);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id, int idCicloContable, int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuentaBalance.ObtenerPorId(id, idCicloContable, idTipoCatalogo);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(CuentaBalance cBalance) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        this.cx.conectar();
        RespuestaGeneral rs1 = this.daoCuentaBalance.buscarIdCuentaPorCicloContable(cBalance.getId(), cBalance.getId_cuenta(), cBalance.getId_ciclo_contable());
        if (rs1.esExitosa()) {
            ArrayList<CuentaBalance> lista = (ArrayList<CuentaBalance>) rs1.getDatos();
            if (lista.isEmpty()) {
                rs = this.daoCuentaBalance.insertar(cBalance);
            } else {
                rs.setMensaje("La cuenta ya esta registrada con saldo");
            }
        }
        
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral editar(CuentaBalance cBalance) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        this.cx.conectar();
        RespuestaGeneral rs1 = this.daoCuentaBalance.buscarIdCuentaPorCicloContable(cBalance.getId(), cBalance.getId_cuenta(), cBalance.getId_ciclo_contable());
        if (rs1.esExitosa()) {
            ArrayList<CuentaBalance> lista = (ArrayList<CuentaBalance>) rs1.getDatos();
            if (lista.isEmpty()) {
                rs = this.daoCuentaBalance.editar(cBalance);
            } else {
                rs.setMensaje("La cuenta ya esta registrada con saldo");
            }
        }
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rg = this.daoCuentaBalance.eliminar(id);
        this.cx.desconectar();
        return rg;
    }
    
}
