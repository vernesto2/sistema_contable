/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import java.util.List;
import utils.constantes.Constantes;

/**
 *
 * @author student
 */
public class CuentaBalanceGeneral extends CuentaBalanza {
    private Integer nivel;
    private List<CuentaBalanceGeneral> subCuentas;
    
    public boolean tieneSubcuentas() {
        return subCuentas != null && subCuentas.size() > 0 ? true : false;
    }
    
    @Override
    //tener en cuenta que las cuentas de saldo contrario al de la cuenta principal llevan valor restado
    public Double saldo() {
        return getEsRestado() == false ? this.saldo() : - this.saldo();
    }

    public void saldo(Double saldo) {
        if(getTipoSaldo() == Constantes.TIPO_SALDO_DEUDOR.getValue()) {
            setSaldoDeudor(saldo);
        } else if(getTipoSaldo() == Constantes.TIPO_SALDO_ACREEDOR.getValue()) {
            setSaldoAcreedor(saldo);
        }
    }
    
    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public List<CuentaBalanceGeneral> getSubCuentas() {
        return subCuentas;
    }

    public void setSubCuentas(List<CuentaBalanceGeneral> subCuentas) {
        this.subCuentas = subCuentas;
    }
    
}
