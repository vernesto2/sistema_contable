/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dto.dtoFormula;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jdk.jshell.spi.ExecutionControl;
import modelo.CicloContable;
import modelo.Formula;
import modelo.FormulaParametro;
import reportes.CuentaBalanza;
import reportes.ElementoFormulaReporte;
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
    ArrayList<FormulaParametro> listaParametros;
    public CalculadoraEstadoResultados(
            ArrayList<dtoFormula> listaFormula, 
            ArrayList<CuentaBalanza> listaCuentaBalanza, 
            ArrayList<FormulaParametro> listaParametros, 
            CicloContable cicloContable
    ) {
        
        this.listaFormula = listaFormula;
        Collections.sort(this.listaFormula);
        
        this.listaCuentaBalanza = listaCuentaBalanza;
        this.listaParametros = listaParametros;
        tipoSociedad = cicloContable.getTipo_sociedad();
        porcentajeReservaLegal = cicloContable.getPorcentaje_reserva_legal();
        this.listaImpuestoSobreRenta = new ArrayList<ImpuestoSobreRenta>();
        
        ImpuestoSobreRenta impuestoSobreRenta;
        
        impuestoSobreRenta = new ImpuestoSobreRenta(cicloContable.getMonto_maximo_ventas(), cicloContable.getPorcentaje_min());
        listaImpuestoSobreRenta.add(impuestoSobreRenta);
        
        impuestoSobreRenta = new ImpuestoSobreRenta(null, cicloContable.getPorcentaje_max());
        listaImpuestoSobreRenta.add(impuestoSobreRenta);
        
        //ordenar por la propiedad hasta
        Comparator<ImpuestoSobreRenta> comparador = (item1, item2) -> {
            //si ambos son null, tienen el mismo orden
            if(item1.hasta == null && item2.hasta == null) {
                return 0;
            }
            //item1 es mayor
            if(item1.hasta == null) {
                return 1;
            } //item2 es mayor
            else if(item2.hasta == null) {
                return 1;
            }
            if(item1.hasta < item2.hasta) {
                return -1;
            } else if (item1.hasta > item2.hasta) {
                return 1;
            } else return 0;
        };
        
        Collections.sort(listaImpuestoSobreRenta, comparador);
    }
    public ImpuestoSobreRenta determinarImpuestoSobreRenta(Double ventas) {
        for (ImpuestoSobreRenta impuestoSobreRenta : listaImpuestoSobreRenta) {
            if(impuestoSobreRenta.hasta == null || ventas <= impuestoSobreRenta.hasta) {
                return impuestoSobreRenta;
            }
        }
        return null;
    }
    
    
    public List<ElementoFormulaReporte> resolverFormula() {
        List<dtoFormula> arbolFormula = this.agregarPadres(listaFormula);
        List<ElementoFormulaReporte> listaFormulaResuelta = new ArrayList<ElementoFormulaReporte>();
        int nivel = 1;
        Double utilidadPerdida = resolverFormula(arbolFormula, listaFormulaResuelta, nivel);
        return listaFormulaResuelta;
    }
    private Double resolverFormula( List<dtoFormula> listaFormulaArbol, List<ElementoFormulaReporte> listaFormulaResuelta, int nivel) {
        //obtener todos los elementos de la formula
        //obtener saldos inicial y saldo actual de las cuentas de la formula
        //iniciar a resolver la formula
        //validar si es necesario convertir la lista de formula en arbol para resolverlo mediante recursividad
        //es altamente probable que sea necesario
        Double acumulado = Double.valueOf(0);
        
        
        for (dtoFormula elemFormula : listaFormulaArbol) {
            Formula formula = elemFormula.getFormula();
            Double valorFormula = Double.valueOf(0);
            final String tipoCuentaEspecial = ""+formula.getTipo_cuenta_especial();
            //en caso se entra en cuenta recursiva, se agrega primero el elemento actual ya que el elemento actual va primero, 
            //y posteriormente se le setea el valor
            ElementoFormulaReporte elemFormulaReporte = new ElementoFormulaReporte();
            
            elemFormulaReporte.setId(formula.getCuenta().getId());
            elemFormulaReporte.setCodigo(formula.getCuenta().getCodigo());
            elemFormulaReporte.setNombre(formula.getNombre());
            elemFormulaReporte.setSigno(formula.getSigno());
            
            listaFormulaResuelta.add(elemFormulaReporte);
            
            if(elemFormula.tieneHijas()) {
                valorFormula = resolverFormula( elemFormula.getHijas(), listaFormulaResuelta, nivel + 1);
                acumulado = elemFormula.operar(valorFormula, acumulado);
            } else if( tipoCuentaEspecial.equals(Constantes.TIPO_CUENTA_ESPECIAL_CALCULADO.getValue()) 
                    && formula.getSigno().equals(Constantes.SIGNO_IGUAL.getValue())) {
                //ver el acumulado al momento
                valorFormula = acumulado;
                //no cambia el acumulado, ya que el valor calculado 
                //se usa para reflejar el valor resultante de las operaciones anteriores
                //acumulado = valorFormula;
            } else if ( tipoCuentaEspecial.equals(Constantes.TIPO_CUENTA_ESPECIAL_SALDO_INICIAL.getValue()) ) {
                CuentaBalanza cuentaBalanza = buscarCuentaPorId(formula.getId_cuenta());
                if( cuentaBalanza == null ) {
                    //si no se encontro, asignarle 0
                    valorFormula = Double.valueOf(0);
                    //lanzar excepcion
                    //throw new IllegalStateException("Error: id cuenta ("+formula.getId_cuenta()+") no se encontró la cuenta en la balanza de comprobación");
                } else {
                    valorFormula = cuentaBalanza.saldo();
                }
                //sumar o restar según signo
                acumulado = elemFormula.operar(valorFormula, acumulado);
            } else if ( tipoCuentaEspecial.equals(Constantes.TIPO_CUENTA_ESPECIAL_SALDO.getValue()) ) {
                CuentaBalanza cuentaBalanza = buscarCuentaPorId(formula.getId_cuenta());
                if( cuentaBalanza == null ) {
                    //si no se encontro, asignarle 0
                    valorFormula = Double.valueOf(0);
                    //lanzar excepcion
                    //throw new IllegalStateException("Error: id cuenta ("+formula.getId_cuenta()+") no se encontró la cuenta en la balanza de comprobación");
                } else {
                    valorFormula = cuentaBalanza.saldo();
                }
                //sumar o restar según signo
                acumulado = elemFormula.operar(valorFormula, acumulado);
            } else if ( tipoCuentaEspecial.equals(Constantes.TIPO_CUENTA_ESPECIAL_VENTAS_TOTALES.getValue()) ) {
                CuentaBalanza cuentaBalanza = buscarCuentaPorId(formula.getId_cuenta());
                if( cuentaBalanza == null ) {
                    throw new IllegalStateException("Error: id cuenta ("+formula.getId_cuenta()+") no se encontró la cuenta en la balanza de comprobación");
                }
                valorFormula = cuentaBalanza.saldo();
                //sumar o restar según signo
                acumulado = elemFormula.operar(valorFormula, acumulado);
            } else if ( tipoCuentaEspecial.equals(Constantes.TIPO_CUENTA_ESPECIAL_VALOR_INGRESADO.getValue()) ) {
                FormulaParametro formulaParametro = buscarFormulaParametroPorIdFormula(formula.getId());
                if(formulaParametro == null) {
                    valorFormula = Double.valueOf(0);
                } else {
                    valorFormula = formulaParametro.getValor();
                }
                acumulado = elemFormula.operar(valorFormula, acumulado);
            } else if ( tipoCuentaEspecial.equals(Constantes.TIPO_CUENTA_ESPECIAL_RESERVA_LEGAL.getValue()) ) {
                Double utilidadAntesReservaLegal = acumulado;
                valorFormula = utilidadAntesReservaLegal * ( porcentajeReservaLegal / 100 );
                //sumar o restar según signo
                acumulado = elemFormula.operar(valorFormula, acumulado);
            } else if ( tipoCuentaEspecial.equals(Constantes.TIPO_CUENTA_ESPECIAL_IMPUESTO_SOBRE_RENTA.getValue()) ) { 
                //buscar la cuenta de ventas totales en la formula, luego traer su saldo final
                Integer intTipoCuentaEspecial = Integer.parseInt(Constantes.TIPO_CUENTA_ESPECIAL_VENTAS_TOTALES.getValue());
                //buscar cual elemento de la formula tiene las ventas totales
                Formula formulaVentas = buscarFormulaPorTipoCuenta(intTipoCuentaEspecial);
                //obtener el saldo de la cuenta desde la balanza de comprobacion
                CuentaBalanza cuentaBalanza = buscarCuentaPorId(formulaVentas.getId_cuenta());
                Double ventasTotales = cuentaBalanza.saldo();
                ImpuestoSobreRenta impuestoSobreRenta = determinarImpuestoSobreRenta(ventasTotales);
                
                Double utilidadAntesDelImpuesto = acumulado;
                valorFormula = impuestoSobreRenta.aplicar(ventasTotales, utilidadAntesDelImpuesto);
                
                acumulado = elemFormula.operar(valorFormula, acumulado);
            }
            
            //y posteriormente se le setea el valor
            elemFormulaReporte.setValor(valorFormula, nivel);
            System.out.println(elemFormulaReporte.getSigno()+ " "+ elemFormulaReporte.getNombre()+" = " + elemFormulaReporte.getValor());
        }
        return acumulado;
        //devolver datos que puede consumir el reporte
    }
/*
agregarPadres(lista: any, expanded?: boolean): TreeNode {
    let listaAux = {data: []}
    lista.forEach(detalle => {
      if (Number(detalle.idRelacion) == -1) {
        listaAux.data.push({data: detalle, children: this.agregarHijos(lista, Number(detalle.id)), expanded: expanded == null ? false : expanded });
      }
    });
    return listaAux;
  }
*/
    private List<dtoFormula> agregarPadres(List<dtoFormula> lista) {
        List<dtoFormula> listaAux = new ArrayList<dtoFormula>();
        for (dtoFormula formula : lista) {
            if (formula.getFormula().getId_formula() == -1) {
                listaAux.add(new dtoFormula(formula.getFormula(), formula.getFormulaPadre(), agregarHijos(lista, formula.getFormula().getId())));
            }
        }

        return listaAux;
    }

    public List<dtoFormula> agregarHijos(List<dtoFormula> listaHijos, Integer idFormula) {
        List<dtoFormula> arbolHijos = new ArrayList<dtoFormula>();
        for (dtoFormula formula : listaHijos) {
            if (formula.getFormula().getId_formula() == idFormula && formula.getFormula().getId_formula() > 0) {
                arbolHijos.add(new dtoFormula(formula.getFormula(), formula.getFormulaPadre(), agregarHijos(listaHijos, formula.getFormula().getId())));
            }
        }
        return arbolHijos;
    }
    /*
  agregarHijos(listaHijos: any, idPadre: number): TreeNode[] {
    let arbolHijos: TreeNode[]=[];
    listaHijos.forEach(detalle => {
      if (Number(detalle.idRelacion) == idPadre && Number(detalle.idRelacion) > 0) {
        arbolHijos.push({ data: detalle, children: this.agregarHijos(listaHijos, Number(detalle.id)), expanded: false });
      }
    });
    return arbolHijos;
  }
*/

    private FormulaParametro buscarFormulaParametroPorIdFormula(Integer idFormula) {
        for (FormulaParametro formulaParametro : listaParametros) {
            Formula formula = formulaParametro.getFormula();
            if(formula.getId() == idFormula) {
                return formulaParametro;
            }
        }
        return null;
    }
    private Formula buscarFormulaPorTipoCuenta(Integer tipoCuentaEspecial) {
        for (dtoFormula dtoFormula : listaFormula) {
            Formula formula = dtoFormula.getFormula();
            if(formula.getTipo_cuenta_especial() == tipoCuentaEspecial) {
                return formula;
            }
        }
        return null;
    }
    private CuentaBalanza buscarCuentaPorId(Integer idCuenta) {
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
            if( hasta == null || ventas < hasta) {
                return utilidadAntesImpuestoSobreRenta + (porcentajeAAplicar / 100);
            } else throw new IllegalStateException("");
        }
    }
}
