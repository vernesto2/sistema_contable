/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCicloContable;
import dao.daoConfigUsuario;
import java.util.ArrayList;
import modelo.ConfiguracionUsuario;
import modelo.TipoCatalogo;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioConfigUsuario {
    daoConfigUsuario daoConfigUsuario;
    Conexion cx;

    public ServicioConfigUsuario(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoConfigUsuario = new daoConfigUsuario(this.cx);
    }
    
    public RespuestaGeneral obtenerPorIdUsuario(int id) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoConfigUsuario.ObtenerPorIdUsuario(id);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(ConfiguracionUsuario usuario) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        // si todo esta correcto procedemos a guardar
        this.cx.conectar();
        rs = this.daoConfigUsuario.insertar(usuario);
        this.cx.desconectar();
        return rs;
    }
    
    public RespuestaGeneral editar(ConfiguracionUsuario usuario) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        // si todo esta correcto procedemos a guardar
        this.cx.conectar();
        rs = this.daoConfigUsuario.editar(usuario);
        this.cx.desconectar();
        return rs;
    }
    
}
