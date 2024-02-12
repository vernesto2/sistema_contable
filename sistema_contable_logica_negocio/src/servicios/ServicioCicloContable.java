/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCicloContable;
import java.util.ArrayList;
import modelo.CicloContable;
import modelo.dtoCicloContable;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioCicloContable {
    daoCicloContable daoCicloContable;
    Conexion cx = new Conexion();

    public ServicioCicloContable() {
        this.daoCicloContable = new daoCicloContable(this.cx);
    }
    
    public RespuestaGeneral obtenerLista() {
        RespuestaGeneral rs = this.daoCicloContable.ListarCiclosContables();
        return rs;
    }
    
    public RespuestaGeneral insertar(CicloContable cicloContable) {
        RespuestaGeneral rs = RespuestaGeneral.asBadRequest("");
        // validaciones
        if (cicloContable.getTitulo().isEmpty()) {
            rs.setMensaje("No se ha ingresado el titulo");
        } else {
            // si todo esta correcto procedemos a guardar
            rs = this.daoCicloContable.insertarCicloContable(cicloContable);
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
            rs = this.daoCicloContable.editarCicloContable(cicloContable);
        }
        return rs;
    }
    
    public RespuestaGeneral eliminar(int id) {
        RespuestaGeneral rs = this.daoCicloContable.eliminarCicloContable(id);
        return rs;
    }
    
}
