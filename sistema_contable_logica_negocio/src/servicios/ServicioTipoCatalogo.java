/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoTipoCatalogo;
import modelo.TipoCatalogo;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioTipoCatalogo {
    daoTipoCatalogo daoTipoCatalogo;
    Conexion cx = new Conexion();

    public ServicioTipoCatalogo() {
        this.daoTipoCatalogo = new daoTipoCatalogo(this.cx);
    }
    
    public RespuestaGeneral obtenerLista() {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoTipoCatalogo.Listar();
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral insertar(TipoCatalogo tipoCatalogo) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (tipoCatalogo.getTipo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el tipo");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoTipoCatalogo.insertar(tipoCatalogo);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral editar(TipoCatalogo tipoCatalogo) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (tipoCatalogo.getTipo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el tipo");
        } else {
            // si todo esta correcto procedemos a guardar
            this.cx.conectar();
            rs = this.daoTipoCatalogo.editar(tipoCatalogo);
            this.cx.desconectar();
        }
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoTipoCatalogo.eliminar(id);
        this.cx.desconectar();
        return rs;
    }
    
}
