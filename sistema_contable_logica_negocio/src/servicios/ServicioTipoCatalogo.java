/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCicloContable;
import dao.daoTipoCatalogo;
import java.util.ArrayList;
import modelo.TipoCatalogo;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioTipoCatalogo {
    daoTipoCatalogo daoTipoCatalogo;
    daoCicloContable daoCicloContable;
    Conexion cx = new Conexion();

    public ServicioTipoCatalogo() {
        this.daoTipoCatalogo = new daoTipoCatalogo(this.cx);
        this.daoCicloContable = new daoCicloContable(this.cx);
    }
    
    public RespuestaGeneral obtenerLista(String busqueda) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoTipoCatalogo.Listar(busqueda);
        this.cx.desconectar(); 
        return rs;
    }
    
    public RespuestaGeneral obtenerPorId(int id) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoTipoCatalogo.ObtenerPorId(id);
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
        RespuestaGeneral rg = RespuestaGeneral.asBadRequest("");
        rg = daoCicloContable.ObtenerPorIdTipoCatalogo(id);
        if (rg.esExitosa()) {
            ArrayList<daoCicloContable> listaCicloContable = (ArrayList<daoCicloContable>) rg.getDatos();
            if (listaCicloContable.isEmpty()) {
                rg = this.daoTipoCatalogo.eliminar(id);
            } else {
                rg = RespuestaGeneral.asBadRequest("No se puede eliminar, \nEsta siendo usado en al menos un ciclo contable");
            }
        } else {
            rg = RespuestaGeneral.asBadRequest("No se pudo validar para eliminar");
        }
        this.cx.desconectar();
        return rg;
    }
    
}
