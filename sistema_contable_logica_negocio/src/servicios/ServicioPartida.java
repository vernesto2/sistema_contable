/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCuenta;
import dao.daoPartida;
import modelo.Cuenta;
import modelo.Partida;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioPartida {
    daoPartida daoPartida;
    Conexion cx;

    public ServicioPartida(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoPartida = new daoPartida(this.cx);
    }
    
    public RespuestaGeneral obtenerListaPorIdCicloContable(int idTipoCatalogo, String busqueda) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoPartida.Listar(idTipoCatalogo, busqueda);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoPartida.ObtenerPorId(id);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerUltimoNumPartida() {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoPartida.ObtenerUltimoNumPartida();
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(Partida partida) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (partida.getComentario().isEmpty()) {
            rs.setMensaje("No se ha ingresado el comentario");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoPartida.insertar(partida);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral editar(Partida partida) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (partida.getComentario().isEmpty()) {
            rs.setMensaje("No se ha ingresado el codigo");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoPartida.editar(partida);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rg = this.daoPartida.eliminar(id);
        this.cx.desconectar();
        return rg;
    }
    
}
