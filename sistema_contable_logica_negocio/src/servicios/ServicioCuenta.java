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
import reportes.ElementoFormulaReporte;
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

        //obtener la cuenta con el nivel que se usara en el reporte
        List<Map<String, Object>> listaCuentaNivel = null;
        try {
            this.cx.conectar();
            listaCuentaNivel = daoCuenta
                    .listarCuentaNivelParaBalanceGeneral(
                            cicloContable.getTipoCatalogo().getId(),
                            tamanoCodigoMayorizar
                    );
            this.cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        //estas cuentas son parte del balance general, pero no tienen saldo establecido ya que se va a calcular
        List<CuentaBalanza> listaCuentasAAgregar = listaCuentaNivel
                .stream()
                .filter(item -> ((String) item.get("codigo")).length() < tamanoCodigoMayorizar)
                .map(item -> {
                    CuentaBalanza cuenta = new CuentaBalanza();
                    cuenta.setId((Integer) item.get("id"));
                    cuenta.setCodigo((String) item.get("codigo"));
                    cuenta.setNombre((String) item.get("nombre"));
                    cuenta.setTipoSaldo((String) item.get("tipo_saldo"));
                    cuenta.setEsRestado((Boolean) item.get("es_restado"));
                    return cuenta;
                })
                .collect(Collectors.toList());

        final int TAMANO_CODIGO_NIVEL_RAIZ = 1;
        List<CuentaBalanza> listaCuentasTemp = ((List<CuentaBalanza>) rgListaCuenta.getDatos()).stream()
                .filter(item -> item.saldo() != null && item.saldo() != 0)
                .collect(Collectors.toList());
        listaCuentasTemp.addAll(listaCuentasAAgregar);

        List<CuentaBalanceGeneral> listaCuentas = listaCuentasTemp.stream()
                .map(item -> convertirACuentaBalanceGeneral(item)
                )
                .collect(Collectors.toList());

        //asignar nivel que se usara en el reporte
        for (CuentaBalanceGeneral item : listaCuentas) {
            asignarCamposRequeridos(item, listaCuentaNivel);
        }

        List<CuentaBalanceGeneral> listaActivo = listaCuentas.stream().filter(cuenta
                -> cuenta.getCodigo().trim().startsWith(Constantes.CODIGO_ACTIVO)
        )
                .collect(Collectors.toList());
        List<CuentaBalanceGeneral> listaActivoBalance = agregarPadres(listaActivo, TAMANO_CODIGO_NIVEL_RAIZ);

        List<CuentaBalanceGeneral> listaPasivo = listaCuentas.stream()
                .filter(cuenta -> cuenta.getCodigo().trim().startsWith(Constantes.CODIGO_PASIVO)
                ).collect(Collectors.toList());
        List<CuentaBalanceGeneral> listaPasivoBalance = agregarPadres(listaPasivo, TAMANO_CODIGO_NIVEL_RAIZ);

        List<CuentaBalanceGeneral> listaPatrimonio = listaCuentas.stream().filter(cuenta
                -> cuenta.getCodigo().trim().startsWith(Constantes.CODIGO_PATRIMONIO)
        )
                .collect(Collectors.toList());
        List<CuentaBalanceGeneral> listaPatrimonioBalance = agregarPadres(listaPatrimonio, TAMANO_CODIGO_NIVEL_RAIZ);

        //calcular los valores para cuentas de nivel 1 y 2, poner en negativo las cuentas que llevan la R (restado)
        CuentaBalanceGeneral cuentaActivo = listaActivoBalance.get(0);
        CuentaBalanceGeneral cuentaPasivo = listaPasivoBalance.get(0);
        CuentaBalanceGeneral cuentaPatrimonio = listaPatrimonioBalance.get(0);
        final int COLUMNA_REPORTE = 3;

        List<ElementoFormulaReporte> listaCuentasBalanceGeneral = new ArrayList<ElementoFormulaReporte>();

        Double totalActivo = calcularSaldos(cuentaActivo, listaCuentasBalanceGeneral, COLUMNA_REPORTE);
        Double totalPasivo = calcularSaldos(cuentaPasivo, listaCuentasBalanceGeneral, COLUMNA_REPORTE);
        Double totalPatrimonio = calcularSaldos(cuentaPatrimonio, listaCuentasBalanceGeneral, COLUMNA_REPORTE);

        return RespuestaGeneral.asOk(null, listaCuentasBalanceGeneral);
    }

    private Double calcularSaldos(CuentaBalanceGeneral cuenta, List<ElementoFormulaReporte> listaElementos, int colmnaReporte) {
        Double valor = Double.valueOf(0);

        ElementoFormulaReporte elemento = cuentaBalanceGeneralAElementoReporteFormula(cuenta, colmnaReporte);
        listaElementos.add(elemento);

        if (cuenta.tieneSubcuentas()) {
            for (CuentaBalanceGeneral subCuenta : cuenta.getSubCuentas()) {
                valor = valor + calcularSaldos(subCuenta, listaElementos, colmnaReporte - 1);
            }
            //establecer el saldo al valor calculado
            cuenta.saldo(valor);
            elemento.setValor(valor, colmnaReporte);
        } else {
            valor = cuenta.saldo() == null ? 0 : cuenta.saldo();
            elemento.setValor(valor, colmnaReporte);
        }
        return valor;
    }

    private ElementoFormulaReporte cuentaBalanceGeneralAElementoReporteFormula(CuentaBalanceGeneral cuenta, int columna) {
        ElementoFormulaReporte elemento = new ElementoFormulaReporte();

        elemento.setCodigo(cuenta.getCodigo());
        elemento.setId(cuenta.getId());
        elemento.setNombre(cuenta.getNombre());
        if (cuenta.saldo() != null) {
            elemento.setValor(cuenta.saldo(), columna);
        }
        return elemento;
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

    private void asignarCamposRequeridos(CuentaBalanceGeneral cuenta, List<Map<String, Object>> listaCuentaNivel) {
        Map<String, Object> encontrada = buscarCuentaNivelPorId(cuenta.getId(), listaCuentaNivel);
        if (encontrada != null) {
            cuenta.setNivel((Integer) encontrada.get("nivel"));
            cuenta.setEsRestado((Boolean) encontrada.get("es_restado"));
        }
    }

    //asignar el nivel a la cuenta especificada
    private Map<String, Object> buscarCuentaNivelPorId(int id, List<Map<String, Object>> listaCuentaNivel) {
        for (Map<String, Object> item : listaCuentaNivel) {
            if (item.get("id").equals(id)) {

                return item;
            }
        }
        return null;
    }

    private List<CuentaBalanceGeneral> agregarPadres(List<CuentaBalanceGeneral> lista, int tamanoCodigoNivelRaiz) {
        List<CuentaBalanceGeneral> listaAux = new ArrayList<CuentaBalanceGeneral>();
        for (CuentaBalanceGeneral item : lista) {
            //identificar nodos raices
            //System.out.print("comparando " + item.getCodigo() + " - " + item.getNombre());
            if (item.getCodigo().length() == tamanoCodigoNivelRaiz) {
                //System.out.println(" OK padres ");
                item.setSubCuentas(agregarHijos(lista, item.getCodigo(), item.getNivel()));
                listaAux.add(item);
            }
            System.out.println("");
        }
        return listaAux;
    }

    public List<CuentaBalanceGeneral> agregarHijos(List<CuentaBalanceGeneral> listaCuentas, String codigoPadre, int nivelPadre) {
        List<CuentaBalanceGeneral> arbolHijos = new ArrayList<CuentaBalanceGeneral>();
        for (CuentaBalanceGeneral item : listaCuentas) {
            //System.out.print("comparando " + "codigoPadre: " + codigoPadre + " codigoHija: " + item.getCodigo() + " - " + item.getNombre());
            //es cuenta hija solo si: 
            //el nivel de la cuenta actual es = nivel de la padre + 1, 
            //y el codigo actual inicia con el codigo padre, 
            //y si el tamano del codigo actual > tamano del codigo del padre
            if (item.getNivel() == (nivelPadre + 1) && item.getCodigo().startsWith(codigoPadre) && item.getCodigo().length() > codigoPadre.length()) {
                //System.out.println(" OK hijas");
                item.setSubCuentas(agregarHijos(listaCuentas, item.getCodigo(), item.getNivel()));
                arbolHijos.add(item);
            }
            System.out.println("");
        }
        return arbolHijos;
    }
}
