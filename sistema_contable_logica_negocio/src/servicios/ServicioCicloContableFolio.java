/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCicloContableFolio;
import java.util.ArrayList;
import modelo.CicloContableFolio;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioCicloContableFolio {
    daoCicloContableFolio daoCicloContableFolio;
    Conexion cx;

    public ServicioCicloContableFolio(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoCicloContableFolio = new daoCicloContableFolio(this.cx);
    }
    
    public RespuestaGeneral obtenerListaPorIdCicloContable(int idCicloContable, int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCicloContableFolio.Listar(idCicloContable, idTipoCatalogo);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id, int idCicloContable, int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCicloContableFolio.ObtenerPorId(id, idCicloContable, idTipoCatalogo);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(CicloContableFolio cBalance) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        this.cx.conectar();
        RespuestaGeneral rs1 = this.daoCicloContableFolio.buscarIdCuentaPorCicloContable(cBalance.getId(), cBalance.getId_cuenta(), cBalance.getId_ciclo_contable());
        if (rs1.esExitosa()) {
            ArrayList<CicloContableFolio> lista = (ArrayList<CicloContableFolio>) rs1.getDatos();
            if (lista.isEmpty()) {
                rs = this.daoCicloContableFolio.insertar(cBalance);
            } else {
                rs.setMensaje("La cuenta ya esta registrada con folio");
            }
        }
        
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral editar(CicloContableFolio cBalance) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        this.cx.conectar();
        RespuestaGeneral rs1 = this.daoCicloContableFolio.buscarIdCuentaPorCicloContable(cBalance.getId(), cBalance.getId_cuenta(), cBalance.getId_ciclo_contable());
        if (rs1.esExitosa()) {
            ArrayList<CicloContableFolio> lista = (ArrayList<CicloContableFolio>) rs1.getDatos();
            if (lista.isEmpty()) {
                rs = this.daoCicloContableFolio.editar(cBalance);
                this.daoCicloContableFolio.editarDetallesPartidas(cBalance);
            } else {
                rs.setMensaje("La cuenta ya esta registrada con saldo");
            }
        }
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rg = this.daoCicloContableFolio.eliminar(id);
        this.cx.desconectar();
        return rg;
    }
    
}
