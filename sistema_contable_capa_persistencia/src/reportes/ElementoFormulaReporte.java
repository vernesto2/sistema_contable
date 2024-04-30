/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import dto.dtoFormula;
import reportes.CuentaBalanza;

/**
 *
 * @author student
 */
public class ElementoFormulaReporte {
    
    private Integer id;
    private String codigo;
    private String nombre;
    private String signo;
    private Double valor1;
    private Double valor2;
    private Double valor3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

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
            setValor1(valor);
        } else if(nivel == 2) {
            setValor2(valor);
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
