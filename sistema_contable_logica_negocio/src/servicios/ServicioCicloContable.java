/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCicloContable;
import modelo.CicloContable;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioCicloContable {
    daoCicloContable daoCicloContable;
    Conexion cx;
    
    public ServicioCicloContable(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoCicloContable = new daoCicloContable(this.cx);
    }
    
    public RespuestaGeneral obtenerLista(String busqueda) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCicloContable.ListarCiclosContables(busqueda);
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCicloContable.ObtenerPorIdTipoCatalogo(id);
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral insertar(CicloContable cicloContable) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (cicloContable.getTitulo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el titulo");
        } else if (cicloContable.getId_catalogo() == -1) {
            rs.setMensaje("No se ha seleccionado ningun tipo de catalogo");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoCicloContable.insertarCicloContable(cicloContable);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral editar(CicloContable cicloContable) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (cicloContable.getTitulo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el titulo");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoCicloContable.editarCicloContable(cicloContable);
            this.cx.desconectar();        }
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCicloContable.eliminarCicloContable(id);
        this.cx.desconectar(); 
        return rs;
    }
    
}
