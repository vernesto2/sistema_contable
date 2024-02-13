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
        RespuestaGeneral rs = this.daoTipoCatalogo.Listar();
        return rs;
    }
    
    public RespuestaGeneral insertar(TipoCatalogo tipoCatalogo) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (tipoCatalogo.getTipo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el tipo");
        } else {
            // si todo esta correcto procedemos a guardar
            rs = this.daoTipoCatalogo.insertar(tipoCatalogo);
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
            rs = this.daoTipoCatalogo.editar(tipoCatalogo);
        }
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rs = this.daoTipoCatalogo.eliminar(id);
        return rs;
    }
    
}
