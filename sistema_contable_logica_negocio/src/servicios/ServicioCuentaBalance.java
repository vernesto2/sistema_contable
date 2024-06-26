/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCicloContableFolio;
import dao.daoCuentaBalance;
import java.util.ArrayList;
import modelo.CicloContableFolio;
import modelo.CuentaBalance;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioCuentaBalance {
    daoCuentaBalance daoCuentaBalance;
    daoCicloContableFolio daoCicloContableFolio;
    Conexion cx;

    public ServicioCuentaBalance(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoCuentaBalance = new daoCuentaBalance(this.cx);
        this.daoCicloContableFolio = new daoCicloContableFolio(this.cx);
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
        // verificamos que si existe el ciclo_contable_folios y sino existe creamos
        RespuestaGeneral rs1 = this.daoCicloContableFolio.buscarIdCuentaPorCicloContableSinId(cBalance.getId_ciclo_folio(), cBalance.getId_cuenta(), cBalance.getId_ciclo_contable(), cBalance.getFolio());
        if (rs1.esExitosa()) {
            ArrayList<CicloContableFolio> listaFolios = (ArrayList<CicloContableFolio>) rs1.getDatos();
            // si la lista viene vacia procedemos a guardar en cicloFolios
            if (listaFolios.isEmpty()) {
                CicloContableFolio cFolio = new CicloContableFolio();
                cFolio.setId(cBalance.getId_ciclo_folio());
                cFolio.setId_ciclo_contable(cBalance.getId_ciclo_contable());
                cFolio.setId_cuenta(cBalance.getId_cuenta());
                cFolio.setFolio_mayor(cBalance.getFolio());
                RespuestaGeneral rs2;
                if (cFolio.getId() > 0) {
                    rs2 = this.daoCicloContableFolio.editar(cFolio);
                } else {
                    rs2 = this.daoCicloContableFolio.insertar(cFolio);
                }
                
                // validamos si se guardo existosamente y procedemos a crear el detalle en cuanta_balance
                if (rs2.esExitosa()) {
                    rs = this.daoCuentaBalance.insertar(cBalance);
                }
                
            } else {
                rs.setMensaje("La Cuenta o Folio ya esta registrada con saldo");
            }
            
        }
        
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral editar(CuentaBalance cBalance) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        this.cx.conectar();
        // verificamos que si existe el ciclo_contable_folios y sino existe creamos
        RespuestaGeneral rs1 = this.daoCicloContableFolio.buscarIdCuentaPorCicloContableSinId(cBalance.getId_ciclo_folio(), cBalance.getId_cuenta(), cBalance.getId_ciclo_contable(), cBalance.getFolio());
        if (rs1.esExitosa()) {
            ArrayList<CicloContableFolio> listaFolios = (ArrayList<CicloContableFolio>) rs1.getDatos();
            // si la lista viene vacia procedemos a guardar en cicloFolios
            if (listaFolios.isEmpty()) {
                CicloContableFolio cFolio = new CicloContableFolio();
                cFolio.setId(cBalance.getId_ciclo_folio());
                cFolio.setId_ciclo_contable(cBalance.getId_ciclo_contable());
                cFolio.setId_cuenta(cBalance.getId_cuenta());
                cFolio.setFolio_mayor(cBalance.getFolio());
                RespuestaGeneral rs2;
                if (cFolio.getId() > 0) {
                    rs2 = this.daoCicloContableFolio.editar(cFolio);
                } else {
                    rs2 = this.daoCicloContableFolio.insertar(cFolio);
                }
                // validamos si se guardo existosamente y procedemos a crear el detalle en cuanta_balance
                if (rs2.esExitosa()) {
                    rs = this.daoCuentaBalance.editar(cBalance);
                }
                
            } else {
                rs.setMensaje("La Cuenta o Folio ya esta registrada con saldo");
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
