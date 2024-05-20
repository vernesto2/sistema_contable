/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import java.util.List;

/**
 *
 * @author student
 */
public class CuentaBalanceGeneral extends CuentaBalanza {
    private Integer nivel;
    private List<CuentaBalanceGeneral> subCuentas;
    
    @Override
    public Double saldo() {
        return null;
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
