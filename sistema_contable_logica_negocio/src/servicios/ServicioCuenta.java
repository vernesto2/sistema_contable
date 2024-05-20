/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.daoCuenta;
import dto.dtoFormula;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import modelo.CicloContable;
import modelo.Cuenta;
import modelo.CuentaBalance;
import reportes.CuentaBalanceGeneral;
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

    public void setServicioCuentaBalanza(ServicioCuentaBalance _cuentaBalance) {
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

    public RespuestaGeneral obtenerListaPorIdTipoCatalogoGeneralCicloContable(int idTipoCatalogo, String busqueda, int cicloContable) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuenta.ListarCatalogoCicloContable(idTipoCatalogo, busqueda, cicloContable);
        this.cx.desconectar();
        return rs;
    }

    public RespuestaGeneral obtenerListaPorIdTipoCatalogoGeneralNivelAMayorizar(int idTipoCatalogo, String busqueda, int nivelMayorizar, int cicloContable) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuenta.ListarCatalogoNivelMayorizar(idTipoCatalogo, busqueda, nivelMayorizar, cicloContable);
        this.cx.desconectar();
        return rs;
    }

    public RespuestaGeneral obtenerPorId(int id, int idTipoCatalogo) {
        this.cx.conectar();
        RespuestaGeneral rs = this.daoCuenta.ObtenerPorId(id, idTipoCatalogo, -1);
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

    public RespuestaGeneral tamanoCodigoAMayorizar(Integer idTipoCatalogo, int nivelAMayorizar) {
        try {
            this.cx.conectar();
            Integer tamanoCodigoAMayorizar = this.daoCuenta.tamanoCodigoAMayorizar(idTipoCatalogo, nivelAMayorizar);
            this.cx.desconectar();
            if (tamanoCodigoAMayorizar == null) {
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
            List<CuentaBalanza> listBeans = daoCuenta
                    .listarCuentaBalanzaComprobacion(
                            cicloContable.getTipoCatalogo().getId(), cicloContable.getId(), tipoPartida, null
                    );

            this.cx.desconectar();
            return RespuestaGeneral.asOk(null, listBeans);
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        }
    }

    public RespuestaGeneral listarCuentaBalanzaComprobacion(Integer idTipoCatalogo, Integer idCiclo, Integer tipoPartida, Integer idCuenta) {
        try {
            this.cx.conectar();
            List<CuentaBalanza> listBeans = daoCuenta
                    .listarCuentaBalanzaComprobacion(
                            idTipoCatalogo, idCiclo, tipoPartida, idCuenta
                    );
            this.cx.desconectar();
            if (listBeans.isEmpty()) {
                return RespuestaGeneral.asNotFound(null, null);
            } else {
                return RespuestaGeneral.asOk(null, listBeans.get(0));
            }
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        }
    }

    public RespuestaGeneral listarBalanceGeneral(CicloContable cicloContable, Integer tamanoCodigoMayorizar) {
        Integer idTipoCatalogo = cicloContable.getId_catalogo();
        Integer idCicloContable = cicloContable.getId();
        RespuestaGeneral rgListaCuenta = listarCuentaBalanzaComprobacion(
                cicloContable,
                Integer.parseInt(Constantes.TIPO_PARTIDA_CIERRE.getValue())
        );

        if (rgListaCuenta.esFallida()) {
            return rgListaCuenta;
        }

        final int TAMANO_CODIGO_NIVEL_RAIZ = 1;

        List<CuentaBalanza> listaCuentas = (List<CuentaBalanza>) rgListaCuenta.getDatos();

        List<CuentaBalanza> listaActivo = listaCuentas.stream().filter(cuenta
                -> cuenta.getCodigo().trim().startsWith(Constantes.CODIGO_ACTIVO)
        ).collect(Collectors.toList());
        List<CuentaBalanceGeneral> listaActivoBalance = agregarPadres(listaActivo, TAMANO_CODIGO_NIVEL_RAIZ);

        List<CuentaBalanza> listaPasivo = listaCuentas.stream().filter(cuenta
                -> cuenta.getCodigo().trim().startsWith(Constantes.CODIGO_PASIVO)
        ).collect(Collectors.toList());
        List<CuentaBalanceGeneral> listaPasivoBalance = agregarPadres(listaPasivo, TAMANO_CODIGO_NIVEL_RAIZ);

        List<CuentaBalanza> listaPatrimonio = listaCuentas.stream().filter(cuenta
                -> cuenta.getCodigo().trim().startsWith(Constantes.CODIGO_PATRIMONIO)
        ).collect(Collectors.toList());
        List<CuentaBalanceGeneral> listaPatrimonioBalance = agregarPadres(listaPatrimonio, TAMANO_CODIGO_NIVEL_RAIZ);

        //obtener la cuenta con el nivel que se usara en el reporte
        List<Map<String, Object>> listaCuentaNivel = null;
        try {
            listaCuentaNivel = daoCuenta
                    .listarCuentaNivelParaBalanceGeneral(
                            cicloContable.getTipoCatalogo().getId(),
                            tamanoCodigoMayorizar
                    );
        } catch (SQLException ex) {
            Logger.getLogger(ServicioCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        //asignar nivel que se usara en el reporte
        for (CuentaBalanceGeneral item : listaActivoBalance) {
            asignarNivel(item, listaCuentaNivel);
        }

        for (CuentaBalanceGeneral item : listaPasivoBalance) {
            asignarNivel(item, listaCuentaNivel);
        }

        for (CuentaBalanceGeneral item : listaPatrimonioBalance) {
            asignarNivel(item, listaCuentaNivel);
        }

        return null;
    }

    public CuentaBalanceGeneral convertirACuentaBalanceGeneral(CuentaBalanza item) {
        CuentaBalanceGeneral hija = new CuentaBalanceGeneral();
        hija.setCodigo(item.getCodigo());
        hija.setFolioMayor(item.getFolioMayor());
        hija.setId(item.getId());
        hija.setNombre(item.getNombre());
        hija.setSaldoAcreedor(item.getSaldoAcreedor());
        hija.setSaldoDeudor(item.getSaldoDeudor());
        hija.setSaldoInicial(item.getSaldoInicial());
        hija.setTipoSaldo(item.getTipoSaldo());
        return hija;
    }

    private void asignarNivel(CuentaBalanceGeneral cuenta, List<Map<String, Object>> listaCuentaNivel) {
        Map<String, Object> encontrada = buscarCuentaNivelPorId(cuenta.getId(), listaCuentaNivel);
        if (encontrada != null) {
            cuenta.setNivel((Integer) encontrada.get("nivel"));
        }
    }

    private Map<String, Object> buscarCuentaNivelPorId(int id, List<Map<String, Object>> listaCuentaNivel) {
        for (Map<String, Object> item : listaCuentaNivel) {
            if (item.get("id").equals(id)) {

                return item;
            }
        }
        return null;
    }

    private List<CuentaBalanceGeneral> agregarPadres(List<CuentaBalanza> lista, int tamanoCodigoNivelRaiz) {
        List<CuentaBalanceGeneral> listaAux = new ArrayList<CuentaBalanceGeneral>();
        for (CuentaBalanza item : lista) {
            //identificar nodos raices
            if (item.getCodigo().length() == tamanoCodigoNivelRaiz) {
                CuentaBalanceGeneral hija = convertirACuentaBalanceGeneral(item);
                hija.setSubCuentas(agregarHijos(lista, item.getId()));
                listaAux.add(hija);
            }
        }
        return listaAux;
    }

    public List<CuentaBalanceGeneral> agregarHijos(List<CuentaBalanza> listaHijos, Integer idPadre) {
        List<CuentaBalanceGeneral> arbolHijos = new ArrayList<CuentaBalanceGeneral>();
        for (CuentaBalanza item : listaHijos) {
            if (item.getId() == idPadre && item.getId() > 0) {
                CuentaBalanceGeneral hija = convertirACuentaBalanceGeneral(item);
                hija.setSubCuentas(agregarHijos(listaHijos, item.getId()));
                arbolHijos.add(hija);
            }
        }
        return arbolHijos;
    }
}
