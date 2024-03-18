/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCuenta;
import modelo.Cuenta;
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
    
}
