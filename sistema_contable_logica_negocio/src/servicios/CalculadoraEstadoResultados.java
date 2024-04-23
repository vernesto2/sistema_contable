/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dto.dtoFormula;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jdk.jshell.spi.ExecutionControl;
import modelo.CicloContable;
import modelo.Formula;
import reportes.CuentaBalanza;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class CalculadoraEstadoResultados {
    Integer tipoSociedad;
    Double porcentajeReservaLegal;
    List<ImpuestoSobreRenta> listaImpuestoSobreRenta;
    ArrayList<dtoFormula> listaFormula;
    ArrayList<CuentaBalanza> listaCuentaBalanza;
    public CalculadoraEstadoResultados(ArrayList<dtoFormula> listaFormula, ArrayList<CuentaBalanza> listaCuentaBalanza, CicloContable cicloContable) {
        this.listaFormula = listaFormula;
        this.listaCuentaBalanza = listaCuentaBalanza;
        tipoSociedad = cicloContable.getTipo_sociedad();
        porcentajeReservaLegal = cicloContable.getPorcentaje_reserva_legal();
        this.listaImpuestoSobreRenta = new ArrayList<ImpuestoSobreRenta>();
        
        ImpuestoSobreRenta impuestoSobreRenta;
        
        impuestoSobreRenta = new ImpuestoSobreRenta(cicloContable.getMonto_maximo_ventas(), cicloContable.getPorcentaje_min());
        listaImpuestoSobreRenta.add(impuestoSobreRenta);
        
        impuestoSobreRenta = new ImpuestoSobreRenta(null, cicloContable.getPorcentaje_max());
        listaImpuestoSobreRenta.add(impuestoSobreRenta);
        
        //ordenar por la propiedad hasta
        Collections.sort(listaImpuestoSobreRenta, (item1, item2) -> {
            if(item2.hasta == null) {
                return 1;
            }
            if(item1.hasta < item2.hasta) {
                return -1;
            } else if (item1.hasta > item2.hasta) {
                return 1;
            } else return 0;
        });
        
    }
    public ImpuestoSobreRenta determinarImpuestoSobreRenta(Double ventas) {
        for (ImpuestoSobreRenta impuestoSobreRenta : listaImpuestoSobreRenta) {
            if(ventas <= impuestoSobreRenta.hasta || impuestoSobreRenta.hasta == null) {
                return impuestoSobreRenta;
            }
        }
        return null;
    }
    
    public void resolverFormula( ) {
        //obtener todos los elementos de la formula
        //obtener saldos inicial y saldo actual de las cuentas de la formula
        //iniciar a resolver la formula
        
        Double acumulado = new Double(0);
        for (dtoFormula elemFormula : listaFormula) {
            Formula formula = elemFormula.getFormula();
            Double valorFormula;
            if (formula.getTipo_formula().equals(Constantes.TIPO_FORMULA_SALDO)) {
                CuentaBalanza cuentaBalanza = buscarPorIdCuenta(formula.getId_cuenta());
                if( cuentaBalanza == null ) {
                    throw new IllegalStateException("Error: id cuenta ("+formula.getId_cuenta()+") no se encontró la cuenta en la balanza de comprobación");
                }
                
                if (cuentaBalanza.getTipoSaldo().equals(Constantes.TIPO_SALDO_DEUDOR.getValue()) ) {
                    valorFormula = cuentaBalanza.getSaldoDeudor();
                } else if (cuentaBalanza.getTipoSaldo().equals(Constantes.TIPO_SALDO_ACREEDOR.getValue()) ) {
                    valorFormula = cuentaBalanza.getSaldoAcreedor();
                } else throw new IllegalStateException("Error: id cuenta ("+formula.getId_cuenta()+") no tiene un tipo saldo valido");
            } else if (formula.getTipo_formula().equals(Constantes.TIPO_FORMULA_SALDO)) {
                
            }
        }
        //devolver datos que puede consumir el reporte
    }
    
    private CuentaBalanza buscarPorIdCuenta(Integer idCuenta) {
        for (CuentaBalanza cuentaBalanza : listaCuentaBalanza) {
            if(cuentaBalanza.getId().equals(idCuenta)) {
                return cuentaBalanza;
            }
        }
        return null;
    }
    
    class ImpuestoSobreRenta {
        public Double hasta;
        public Double porcentajeAAplicar;

        public ImpuestoSobreRenta(Double hasta, Double porcentajeAAplicar) {
            this.hasta = hasta;
            this.porcentajeAAplicar = porcentajeAAplicar;
        }
        public Double aplicar(Double ventas, Double utilidadAntesImpuestoSobreRenta) {
            if(ventas < hasta || hasta == null) {
                return utilidadAntesImpuestoSobreRenta + (porcentajeAAplicar / 100);
            } else throw new IllegalStateException("");
        }
    }
}
