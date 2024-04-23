/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCuenta;
import dto.dtoFormula;
import java.util.ArrayList;
import java.util.List;
import modelo.Cuenta;
import reportes.CuentaBalanza;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioCuenta {
    daoCuenta daoCuenta;
    Conexion cx;

    public ServicioCuenta(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoCuenta = new daoCuenta(this.cx);
    }
    
    public RespuestaGeneral obtenerListaPorIdTipoCatalogo(int idTipoCatalogo, String busqueda) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuenta.Listar(idTipoCatalogo, busqueda);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerListaPorIdTipoCatalogoGeneral(int idTipoCatalogo, String busqueda) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuenta.ListarCatalogo(idTipoCatalogo, busqueda);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id, int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuenta.ObtenerPorId(id, idTipoCatalogo);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(Cuenta cuenta) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (cuenta.getCodigo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el codigo");
        } else if (cuenta.getNombre().isEmpty()) {
            rs.setMensaje("No se ha ingresado el concepto de la cuenta");
        } else if (cuenta.getId_tipo_catalogo() == -1) {
            rs.setMensaje("No se ha seleccionado ningun catálogo para guardar la cuenta");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoCuenta.insertar(cuenta);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral editar(Cuenta cuenta) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (cuenta.getCodigo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el codigo");
        } else if (cuenta.getNombre().isEmpty()) {
            rs.setMensaje("No se ha ingresado el concepto de la cuenta");
        } else if (cuenta.getId_tipo_catalogo() == -1) {
            rs.setMensaje("No se ha seleccionado ningun catálogo para actualizar la cuenta");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoCuenta.editar(cuenta);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rg = this.daoCuenta.eliminar(id);
        this.cx.desconectar();
        return rg;
    }
    public RespuestaGeneral tamanoCodigoAMayorizar(Integer idTipoCatalogo) {
        try {
            this.cx.conectar();
            Integer tamanoCodigoAMayorizar = this.daoCuenta.tamanoCodigoAMayorizar(idTipoCatalogo);
            this.cx.desconectar();
            if(tamanoCodigoAMayorizar == null) {
                return RespuestaGeneral.asBadRequest("Error: No se encontró el tamaño del código de cuenta a mayorizar");
            }
            return RespuestaGeneral.asOk(null, tamanoCodigoAMayorizar);
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        }
        
    }
    public RespuestaGeneral listarCuentaBalanzaComprobacion(Integer idTipoCatalogo, Integer idCiclo, Integer tipoPartida) {
        try {
            this.cx.conectar();
            List<CuentaBalanza> listBeans = daoCuenta
                    .listarCuentaBalanzaComprobacion(
                            idTipoCatalogo, idCiclo, tipoPartida, null
                    );
            this.cx.desconectar();
            return RespuestaGeneral.asOk(null, listBeans);
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        }
    }
    public RespuestaGeneral verCuentaBalanzaComprobacion(Integer idTipoCatalogo, Integer idCiclo, Integer tipoPartida, Integer idCuenta) {
        try {
            this.cx.conectar();
            List<CuentaBalanza> listBeans = daoCuenta
                    .listarCuentaBalanzaComprobacion(
                            idTipoCatalogo, idCiclo, tipoPartida, idCuenta
                    );
            this.cx.desconectar();
            if(listBeans.isEmpty()) {
                return RespuestaGeneral.asNotFound(null, null);
            } else {
                return RespuestaGeneral.asOk(null, listBeans.get(0));
            }
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        }
    }
}
