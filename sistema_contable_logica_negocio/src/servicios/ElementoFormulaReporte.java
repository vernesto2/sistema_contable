/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dto.dtoFormula;

/**
 *
 * @author student
 */
public class ElementoFormulaReporte extends dtoFormula {
    private Double valor1;
    private Double valor2;
    private Double valor3;

    public Double getValor1() {
        return valor1;
    }

    public void setValor1(Double valor1) {
        this.valor1 = valor1;
    }

    public Double getValor2() {
        return valor2;
    }

    public void setValor2(Double valor2) {
        this.valor2 = valor2;
    }

    public Double getValor3() {
        return valor3;
    }

    public void setValor3(Double valor3) {
        this.valor3 = valor3;
    }
    
    public void setValor(Double valor, int nivel) {
        if(nivel == 1) {
            setValor1(valor1);
        } else if(nivel == 2) {
            setValor3(valor);
        } else {
            setValor3(valor);
        }
    }
    public Double getValor() {
        if(valor1 != null) {
            return valor1;
        } else if(valor2 != null) {
            return valor2;
        } else if(valor3 != null) {
            return valor3;
        } else return null;
    }
}
