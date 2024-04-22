/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dto.dtoFormula;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.spi.ExecutionControl;
import modelo.CicloContable;
import reportes.CuentaBalanza;
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
        
    }
    public ImpuestoSobreRenta determinarImpuestoSobreRenta(Double ventas) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("");
    }
    
    public void resolverFormula( ) {
        //obtener todos los elementos de la formula
        //obtener saldos inicial y saldo actual de las cuentas de la formula
        //iniciar a resolver la formula
        //devolver datos que puede consumir el reporte
    }
    
    class ImpuestoSobreRenta {
        public Double hasta;
        public Double porcentajeAAplicar;

        public ImpuestoSobreRenta(Double hasta, Double porcentajeAAplicar) {
            this.hasta = hasta;
            this.porcentajeAAplicar = porcentajeAAplicar;
        }
        public Double aplicar(Double ventas) throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("");
        }
    }
}
