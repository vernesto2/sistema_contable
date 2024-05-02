/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCuenta;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelo.CicloContable;
import modelo.Cuenta;
import modelo.CuentaBalance;
import reportes.CuentaBalanza;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class ServicioCuenta {
    daoCuenta daoCuenta;
    Conexion cx;
    ServicioCuentaBalance _cuentaBalance;

    public ServicioCuenta(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoCuenta = new daoCuenta(this.cx);
    }
    public void setServicioCuentaBalanza (ServicioCuentaBalance _cuentaBalance) {
        this._cuentaBalance = _cuentaBalance;
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
    
    public RespuestaGeneral obtenerListaPorIdTipoCatalogoGeneralNivelAMayorizar(int idTipoCatalogo, String busqueda, int nivelMayorizar) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuenta.ListarCatalogoNivelMayorizar(idTipoCatalogo, busqueda, nivelMayorizar);
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
    public RespuestaGeneral listarCuentaBalanzaComprobacion(CicloContable cicloContable, Integer tipoPartida) {
        try {
            this.cx.conectar();
            List<CuentaBalanza> listBeans = null;
            //si los saldos fueron ingresados directamente, se lee desde otra tabla
            
            if(cicloContable.getSin_libro_diario() == 1) {
                 ArrayList<CuentaBalance> listaCuentaBalanza = (ArrayList<CuentaBalance>) _cuentaBalance
                .obtenerListaPorIdCicloContable(
                     cicloContable.getId(), 
                     cicloContable.getTipoCatalogo().getId()
                ).getDatos();
                
                listBeans = listaCuentaBalanza.stream().map(item -> {
                    CuentaBalanza cuentaBalanza = new CuentaBalanza();
                    cuentaBalanza.setCodigo(item.getCuenta().getCodigo());
                    cuentaBalanza.setFolioMayor(null);
                    cuentaBalanza.setId(item.getCuenta().getId());
                    cuentaBalanza.setNombre(item.getCuenta().getNombre());
                    cuentaBalanza.setTipoSaldo(item.getCuenta().getTipo_saldo());
                    
                    if(cuentaBalanza.getTipoSaldo().equals(Constantes.TIPO_SALDO_ACREEDOR.getValue())  ) {
                        cuentaBalanza.setSaldoAcreedor(item.getSaldo_final());
                    } else if(cuentaBalanza.getTipoSaldo().equals(Constantes.TIPO_SALDO_DEUDOR.getValue()) ) {
                        cuentaBalanza.setSaldoDeudor(item.getSaldo_final());
                    }
                    cuentaBalanza.setSaldoInicial(item.getSaldo_inicial());
                    return cuentaBalanza;
                }).collect(Collectors.toList());
            } 
            //si tiene partidas sacar los saldos de la balanza de comprobacion
            else {
                listBeans = daoCuenta
                 .listarCuentaBalanzaComprobacion(
                            cicloContable.getTipoCatalogo().getId(), cicloContable.getId(), tipoPartida, null
                 );
            }
            
            
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
